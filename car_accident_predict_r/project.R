rm(list = ls())

setwd('C:/Users/user/Desktop/듀/단대/프로젝트/데이터/교통사고/솔개')
data = read.csv('전처리3.csv')
head(data)

real_data = data[,-c(1,2,3)]
str(real_data)
real_data$원아수.유치원. = as.numeric(real_data$원아수.유치원.)
real_data[,2] = scale(real_data[,2])

#상관계수
library(stats)
cor(real_data)
summary(real_data)
colnames(real_data[3])

ncol(real_data)

vec1 = c()
vec2 = c()
for(i in 1:ncol(real_data)){
  c= cor(real_data[,29],real_data[,i])
  vec1 = c(c, colnames(real_data[i]))
  vec2 = c(vec2,vec1)
}

c = c()
d = c()
for(i in 1:length(vec2)){
  if(i%%2==0){
    a= vec2[i]
    c = c(c,a)
  }
  if(i%%2==1){
    b = vec2[i]
    d = c(d,b)
  }
}
c
d
class(c)
class(d)

corr = cbind(c,d)
colnames(corr) = c('독립변수','상관계수')
corr = data.frame(corr)
str(corr)

corr[,2] = as.numeric(paste(corr[,2]))
class(corr[,2])

cor_result = subset(corr, abs(상관계수) >=0.6)
cor_result

#그래프
str(real_data)

par(mfrow = c(2,2))
plot(발생건수~사망자수, data= real_data)
plot(발생건수~부상자수, data= real_data)#
plot(발생건수~남자발생건수, data= real_data)#
plot(발생건수~여자발생건수, data= real_data)#

plot(발생건수~남자사망자수, data= real_data)
plot(발생건수~여자사망자수, data= real_data)
plot(발생건수~남자부상자수, data= real_data)#
plot(발생건수~여자부상자수, data= real_data)#

plot(발생건수~자동차등록대수, data= real_data)
plot(발생건수~성별사망률, data= real_data)
plot(발생건수~평균속도, data= real_data)
plot(발생건수~발생비, data= real_data)#

plot(발생건수~발생안개, data= real_data)
plot(발생건수~발생눈, data= real_data)
plot(발생건수~점포수, data= real_data)
plot(발생건수~인구, data= real_data)

plot(발생건수~주유량, data= real_data)
plot(발생건수~신호등있는횡단보도, data= real_data)#
plot(발생건수~신호등없는횡단보도, data= real_data)#
plot(발생건수~횡단보도수, data= real_data)#

plot(발생건수~횡단보도신호등비율..., data= real_data)
plot(발생건수~주차장.총합., data= real_data)#
plot(발생건수~자살자.수., data= real_data)
plot(발생건수~재정자립도, data= real_data)

plot(발생건수~원아수.유치원., data= real_data)
plot(발생건수~학생수, data= real_data)
plot(발생건수~도서관.방문자수, data= real_data)
plot(발생건수~나무수량, data= real_data)#

par(mfrow=c(1,1))
plot(발생건수~교통안전도평가지수, data= real_data)

#선형회귀
real_data2 = real_data[,-c(1:8)]

model = lm(발생건수구분~., data=real_data2)
model
summary(model) 

#공산성알아보기
library(car)
car::vif(model)
(car::vif(model))>10 # 10넘어 가는 것들이 많아 다중공산성 존재 - pca이용

# 전체적인 p-value의 값이 0에 근접하므로 종속변수는 독립변수에 영향을 미치지만
#몇가지의 변수를 추려야 사용할 수 있다.

#변수선택법
model2 = lm(발생건수구분~1, data = real_data)

both = step(model2, scope = list(lower = model2, upper = model),direction = 'both')
summary(both)

library(olsrr)
ols_step_best_subset(model)
ols_step_all_possible(model)

#새로운변수
model3 = lm(발생건수구분 ~ 발생비 + 신호등있는횡단보도 + 도서관.방문자수 + 
                    교통안전도평가지수 + 횡단보도신호등비율... + 주유량 + 원아수.유치원., 
                  data = real_data)
summary(model3)

car::vif(model3)>10

both = step(model2, scope = list(lower = model2, upper = model3),direction = 'both')
summary(both)

#더새로운변수
model4 = lm(발생건수구분 ~ 발생비 + 신호등있는횡단보도 + 도서관.방문자수 + 
                    교통안전도평가지수 + 원아수.유치원., 
                  data = real_data)
summary(model4)

car::vif(model4)>10

#pca 분석
real_data4 = real_data[,c('발생건수구분','발생비','신호등있는횡단보도','도서관.방문자수','횡단보도신호등비율...','교통안전도평가지수','주유량','원아수.유치원.')]
real_data4

write.csv(real_data4, 'final_data3.csv')

real_data5 = real_data[,c('발생건수구분','발생비','신호등있는횡단보도','도서관.방문자수','교통안전도평가지수','원아수.유치원.')]
write.csv(real_data5, 'final_data4.csv')

real_data4_y = real_data4[,1]
real_data4_x = real_data4[,-1]

pca_var = prcomp(real_data4_x, scale = T,center = T)
summary(pca_var)
pca_var

screeplot(pca_var, type="lines", pch=1, main="scree plot")

PRC = as.matrix(real_data4_x) %*% pca_var$rotation
head(PRC)

train1 <- cbind(real_data4_y,as.data.frame(PRC))
train1[,1] <- as.factor(train1[,1])
train1[,1]
colnames(train1)[1] <- "label"
train1

fit1 <- lm(label~PC1+PC2+PC3, data=train1) 
fit1

fit1_pred <- predict(fit1,newdata = train1)
fit1_pred

b = fit1_pred - 1
b

for(i in 1:length(b)){
  if(b[i]<1){b[i]<-0}
  if(b[i]<2 & b[i]>=1){b[i]<-1}
  if(b[i]<3 & b[i]>=2){b[i]<-2}
  if(b[i]<4 & b[i]>=3){b[i]<-3}
}
b

a <- real_data4_y
table(b,a)
mean(b==a) # 0.2711111

#some preprocessing
real_data5 = real_data[,c('발생건수구분','발생비','신호등있는횡단보도','도서관.방문자수','횡단보도신호등비율...','원아수.유치원.')]

real_data5_y = real_data5[,1]
real_data5_x = real_data5[,-1]

pca_var2 = prcomp(real_data5_x, scale = T,center = T)
summary(pca_var2)
pca_var2

screeplot(pca_var2, type="lines", pch=1, main="scree plot")

PRC2 = as.matrix(real_data5_x) %*% pca_var2$rotation
head(PRC2)

train2 <- cbind(real_data5_y,as.data.frame(PRC2))
train2[,1] <- as.factor(train2[,1])
train2[,1]
colnames(train2)[1] <- "label"
train2

fit2 <- lm(label~PC1+PC2+PC3, data=train2) 
fit2

fit1_pred2 <- predict(fit2,newdata = train2)
fit1_pred2

b2 = fit1_pred2-1
b2

for(i in 1:length(b2)){
  if(b2[i]<1){b2[i]<-0}
  if(b2[i]<2 & b2[i]>=1){b2[i]<-1}
  if(b2[i]<3 & b2[i]>=2){b2[i]<-2}
  if(b2[i]<4 & b2[i]>=3){b2[i]<-3}
}

a2 <- real_data5_y
table(b2,a2)
mean(b2==a2) #0.4444444

#all
#some preprocessing
real_data6 = real_data

real_data6_y = real_data6[,29]
real_data6_x = real_data6[,-29]

pca_var3 = prcomp(real_data6_x, scale = T,center = T)
summary(pca_var3)
pca_var3

screeplot(pca_var3, type="lines", pch=1, main="scree plot")

PRC3 = as.matrix(real_data6_x) %*% pca_var3$rotation
head(PRC3)

train3 <- cbind(real_data6_y,as.data.frame(PRC3))
train3[,1] <- as.factor(train3[,1])
train3[,1]
colnames(train3)[1] <- "label"
train3

fit3 <- lm(label~PC1+PC2+PC3+PC4+PC5+PC6, data=train3) 
fit3

fit1_pred3 <- predict(fit3,newdata = train3)
fit1_pred3

b3 = fit1_pred3-1

for(i in 1:length(b3)){
  if(b3[i]<1){b3[i]<-0}
  if(b3[i]<2 & b3[i]>=1){b3[i]<-1}
  if(b3[i]<3 & b3[i]>=2){b3[i]<-2}
  if(b3[i]<4 & b3[i]>=3){b3[i]<-3}
}
b3

a3 <- real_data6_y
table(b3,a3)
mean(b3==a3) #0.4933333


#train, test로 나누어 기계학습
#일부
rm(list =ls())
setwd('C:/Users/user/Desktop/듀/단대/프로젝트/데이터/교통사고/솔개')
data = read.csv('final_data3.csv',header = T)
data[,-1] = scale(data[,-1])
data = data.frame(data)

set.seed(122)
train_idx = sample(1:nrow(data), size = nrow(data)*0.7)

train = data[train_idx,]
test = data[-train_idx,]
train_ds = data[train_idx, -1]
train_cl = data[train_idx,1]
test_ds = data[-train_idx,-1]
test_cl = data[-train_idx,1]

model = lm(발생건수구분~., data= train)
pred = predict(model, test)

#전체
data2 = read.csv('전처리30.csv', header = T)
data2 = data2[,-1]
data2$원아수.유치원. = as.numeric(data2$원아수.유치원.)
ncol(data2)
data2[,-29] = scale(data2[,-29])
data2 = data.frame(data2)

set.seed(122)
train_idx2 = sample(1:nrow(data2), size = nrow(data2)*0.8)

train2 = data2[train_idx2,]
test2 = data2[-train_idx2,]
train_ds2 = data2[train_idx2, -1]
train_cl2 = data2[train_idx2,1]
test_ds2 = data2[-train_idx2,-1]
test_cl2 = data2[-train_idx2,1]

model2 = lm(발생건수구분~., data= train2)
pred2 = predict(model2, test2)

library(caret)
data.frame( R2_F = R2(pred2, test2$발생건수구분), # 0.129866
            RMSE_F = RMSE(pred2, test2$발생건수구분), #2.255138
            MAE_F = MAE(pred2, test2$발생건수구분), #0.7039051
            R2_S = R2(pred, test$발생건수구분), #0.7025721
            RMSE_S = RMSE(pred, test$발생건수구분), #0.6091909
            MAE_S = MAE(pred, test$발생건수구분)) #0.4766988


#kfold - repeat
set.seed(122) 
train.control <- trainControl(method = "repeatedcv", number = 5, repeats = 3)

Smodel <- train(발생건수구분 ~., data = data, method = "lm",
                      trControl = train.control)
Fmodel <- train(발생건수구분 ~ ., data = data2, method = "lm",
                      trControl = train.control)
print(Smodel) #RMSE       Rsquared   MAE      
#0.6313341  0.6916651 0.5143538
print(Fmodel) #RMSE       Rsquared   MAE
#0.9860641  0.615741  0.524254


#knn(분류)
library(class)
rm(list =ls())
setwd('C:/Users/user/Desktop/듀/단대/프로젝트/데이터/교통사고/솔개')
data = read.csv('final_data3.csv',header = T)
data[,-1] = scale(data[,-1])
data = data.frame(data)

set.seed(122)
train_idx = sample(1:nrow(data), size = nrow(data)*0.7)

train = data[train_idx,]
test = data[-train_idx,]
train_ds = data[train_idx, -1]
train_cl = data[train_idx,1]
test_ds = data[-train_idx,-1]
test_cl = data[-train_idx,1]

res = knn(train_ds, test_ds, cl = train_cl, k=3)
table(res,test_cl)
mean(res== test_cl) #0.7058824


#decision tree, random forest
rm(list =ls())
setwd('C:/Users/user/Desktop/듀/단대/프로젝트/데이터/교통사고/솔개')
data = read.csv('final_data3.csv',header = T)
data[,-1] = scale(data[,-1])
data = data.frame(data)

set.seed(1234)
train_idx = sample(1:nrow(data), size = nrow(data)*0.7)

train = data[train_idx,]
test = data[-train_idx,]
train_ds = data[train_idx, -1]
train_cl = data[train_idx,1]
train_cl = as.factor(train_cl)
test_ds = data[-train_idx,-1]
test_cl = data[-train_idx,1]
test_cl = as.factor(test_cl)

library(C50)

model = C5.0(x = train_ds, y= train_cl)#decision
pre = predict(model, test_ds)
table(pre, test_cl)
mean(pre == test_cl) #0.6911765

model2 = C5.0(x = train_ds, y= train_cl,trials = 5)#decision-boosting
pre2 = predict(model2, test_ds)
table(pre2, test_cl)
mean(pre2 == test_cl) #0.7058824

library(randomForest)
model3 = randomForest(x = train_ds, y=train_cl, ntree = 10000, mtry = 10) # random forest
pre3 = predict(model3, test_ds)
table(pre, test_cl)
mean(pre3 == test_cl) #0.7941176


#logistic regression(다항)
rm(list =ls())
setwd('C:/Users/user/Desktop/듀/단대/프로젝트/데이터/교통사고/솔개')
data = read.csv('final_data3.csv',header = T)
data[,-1] = scale(data[,-1])
data = data.frame(data)

library(nnet)
library(pscl)

set.seed(122)
train_idx = sample(1:nrow(data), size = nrow(data)*0.7)

train = data[train_idx,]
test = data[-train_idx,]

model = multinom(발생건수구분~.,data = train) 
summary(model) #4를 기준으로 

coef(model)/ summary(model)$standard.errors # z통계랑
exp(coef(model))#오즈비

fitted(model) # 예측확률 - 가장 큰 값으로 분류한다
pred = predict(model,test) 

table(pred,test$발생건수구분)
mean(pred == test$발생건수구분) #0.7205882


# 판별분류
rm(list =ls())
setwd('C:/Users/user/Desktop/듀/단대/프로젝트/데이터/교통사고/솔개')
data = read.csv('final_data3.csv',header = T)
data[,-1] = scale(data[,-1])
data = data.frame(data)

library(biotools)
library(MASS)
boxM(data[,2:8], grouping = data[,1]) # 공분산이 다르다 이차판별함수

set.seed(122)
train_idx = sample(1:nrow(data), size = nrow(data)*0.7)

train = data[train_idx,]
test = data[-train_idx,]

res = qda(train[,2:8], grouping = train[,1], prior = c(0.25,0.25,0.25,0.25))
pred = predict(res, newdata = test[,2:8])

table(test$발생건수구분, pred$class)
mean(test$발생건수구분==pred$class) #0.6029412

#naive bayes 
rm(list =ls())
setwd('C:/Users/user/Desktop/듀/단대/프로젝트/데이터/교통사고/솔개')
data = read.csv('final_data3.csv',header = T)
data[,-1] = scale(data[,-1])
data = data.frame(data)

set.seed(122)
train_idx = sample(1:nrow(data), size = nrow(data)*0.7)

train = data[train_idx,]
test = data[-train_idx,]

train_ds = data[train_idx, -1]
train_cl = data[train_idx,1]
train_cl = as.factor(train_cl)
test_ds = data[-train_idx,-1]
test_cl = data[-train_idx,1]
test_cl = as.factor(test_cl)
class(test_cl)

library(e1071)
model2 = naiveBayes(train_cl~., data= train)
model2
pred = predict(model2, test_ds)
pred
mean(pred == test_cl) # 0.6323529
table(pred, test_cl)

#svm
rm(list =ls())
setwd('C:/Users/user/Desktop/듀/단대/프로젝트/데이터/교통사고/솔개')
data = read.csv('final_data3.csv',header = T)
data[,-1] = scale(data[,-1])
data = data.frame(data)

set.seed(122)
train_idx = sample(1:nrow(data), size = nrow(data)*0.7)

train = data[train_idx,]
test = data[-train_idx,]

train_ds = data[train_idx, -1]
train_cl = data[train_idx,1]
test_ds = data[-train_idx,-1]
test_cl = data[-train_idx,1]

tuned = tune.svm(발생건수구분~., data = train, degree = 1:5, coef0 = 0:1, cost=10^(0:2),gamma = 10^(-6:-1))
pm = tuned$best.parameters
pm = as.matrix(pm)
pm

model = svm(발생건수구분~., data = train,degree = pm[1], coef0 = pm[3], cost= pm[4], gamma = pm[2])
pred = predict(model, test_ds)
pred = round(pred)
mean(pred == test_cl) #0.7647059
table(pred , test_cl)


#neaural network
## nnet
library(nnet)

rm(list =ls())
setwd('C:/Users/user/Desktop/듀/단대/프로젝트/데이터/교통사고/솔개')
data = read.csv('final_data3.csv',header = T)
data[,-1] = scale(data[,-1])
data = data.frame(data)
data[,1] = as.factor(data[,1])

set.seed(122)
train_idx = sample(1:nrow(data), size = nrow(data)*0.7)

train = data[train_idx,]
test = data[-train_idx,]

model = nnet(발생건수구분~.,data =train, maxit = 200, decay = 5e-4, size =2)
pred =  predict(model, test, type = 'class')
pred

mean(test[,1] == pred) #0.7941176

##neuralnet
rm(list =ls())
setwd('C:/Users/user/Desktop/듀/단대/프로젝트/데이터/교통사고/솔개')
data = read.csv('final_data3.csv',header = T)
data[,-1] = scale(data[,-1])
data = data.frame(data)

set.seed(12345)
train_idx = sample(1:nrow(data), size = nrow(data)*0.7)

train = data[train_idx,]
test = data[-train_idx,]

library(neuralnet)
set.seed(123)
model <-neuralnet(발생건수구분 ~ 발생비 + 신호등있는횡단보도 + 도서관.방문자수 + 
                          교통안전도평가지수 + 횡단보도신호등비율... + 주유량 + 원아수.유치원.,
                        data = train, hidden = c(4,3,2),threshold=0.01)

model

test$nnet2_pred_prob <- compute(model, covariate=test[,-1])$net.result
test$nnet2_pred_prob = abs(test$nnet2_pred_prob)
test$nnet2_pred_prob = round(test$nnet2_pred_prob)

mean(test$nnet2_pred_prob == test[,1]) # 0.4411764706

#new data - 구별평균
rm(list =ls())
setwd('C:/Users/user/Desktop/듀/단대/프로젝트/데이터/교통사고/솔개')
data = read.csv('seoul_9.csv',header = T)
head(data)
ncol(data)

data[2] = round(data[2])
data_label = data[1]
data_label
data2 = cbind(data[2],scale(data[,-c(1,2)]))
nrow(data2)
data3 = data2[1]
data3 = as.numeric(as.matrix(data3))
data3

for(i in 1:length(data3)){
  if(data3[i]<1258){data3[i]<-0}
  if(data3[i]<1782 & data3[i]>=1258){data3[i]<-1}
  if(data3[i]<3970 & data3[i]>=1782){data3[i]<-2}
  if(data3[i]>=3970){data3[i]<-3}
}

data3 = data.frame(data3)
colnames(data3) = '발생건수구분'
data4 = cbind(data3, data2[,2:8])
data4

library(randomForest)
model3 = randomForest(발생건수구분~., data = data4, ntree = 10000, mtry = 10) # random forest
pre3 = predict(model3, data4[,-1])
pre3 = round(pre3)
pre3
table(pre3, data4[,1])
mean(pre3 == data4[,1]) # 6, 16,19, 22, 23, 24(동대문구, 강서구, 영등포구, 서초구, 강남구, 송파구)

library(nnet)
set.seed(122)

data4[,1] = as.factor(data4[,1])

model = nnet(발생건수구분~.,data =data4, maxit = 100, decay = 5e-4, size =2)
pred =  predict(model, data4, type = "class")
pred  # 6, 16,19, 22, 23, 24(동대문구, 강서구, 영등포구, 서초구, 강남구, 송파구)

mean(data4[,1] == pred)

#detail-data
rm(list=ls())
setwd('C:/Users/user/Desktop/듀/단대/프로젝트/데이터/교통사고/솔개')
d1 = read.csv('09-11.csv', header = T, stringsAsFactors = F)
d2 = read.csv('10-12.csv',  header = T, stringsAsFactors = F)
d3 = read.csv('11-13.csv', header = T, stringsAsFactors = F)
d4 = read.csv('12-14.csv', header = T, stringsAsFactors = F)
d5 = read.csv('13-15.csv', header = T, stringsAsFactors = F)
d6 = read.csv('16.csv', header = T, stringsAsFactors = F)

newdata <- rbind(d1,d2,d3,d4,d5,d6)

an_data <- newdata[,c("발생요일","사고유형","기상상태","도로형태","가해운전자.차종","가해운전자.성별","가해운전자.연령","피해운전자.성별","피해운전자.연령","사고내용")]

colnames(an_data) <- c("day","acci_type","weather","road_type","attack_car","attack_gender","attack_age","victim_gender","victim_age","acci_degree")

str(an_data)

for( i in 1:nrow(an_data)){
  if(an_data$day[i] == "월요일"){
    an_data$day[i] = "1"
  }
  else if(an_data$day[i] == "화요일"){
    an_data$day[i] = "2"
  }
  else if(an_data$day[i] == "수요일"){
    an_data$day[i] = "3"
  }
  else if(an_data$day[i] == "목요일"){
    an_data$day[i] = "4"
  }
  else if(an_data$day[i] == "금요일"){
    an_data$day[i] = "5"
  }
  else if(an_data$day[i] == "토요일"){
    an_data$day[i] = "6"
  }
  else{
    an_data$day[i] = "7"
  }
}

an_data$acci_type<- as.factor(an_data$acci_type)
an_data$weather<- as.factor(an_data$weather)
an_data$road_type<- as.factor(an_data$road_type)
an_data$attack_car<- as.factor(an_data$attack_car)
an_data$attack_car<- as.factor(an_data$attack_car)
an_data$attack_gender<- as.factor(an_data$attack_gender)
an_data$victim_gender<- as.factor(an_data$victim_gender)

str(an_data)
table(an_data$acci_type)
nrow(an_data)

for( i in 1:nrow(an_data)){
  if(an_data$attack_age[i] =="기타불명"){
    an_data$attack_age[i] <- NA
  }
  else{
    an_data$attack_age[i] = substr(an_data$attack_age[i],1,2)
  }
}
an_data$attack_age <- as.numeric(an_data$attack_age)

for( i in 1:nrow(an_data)){
  if(an_data$victim_age[i] =="기타불명"){
    an_data$victim_age[i] <- NA
  }
  else{
    an_data$victim_age[i] = substr(an_data$victim_age[i],1,2)
  }
}
an_data$victim_age <- as.numeric(an_data$victim_age)

class(an_data$acci_degree)
table(an_data$acci_degree)

for( i in 1:nrow(an_data)){
  if(an_data$acci_degree[i] =="중상사고" | an_data$acci_degree[i] =="사망사고"){
    an_data$acci_degree[i] <- 1
  }
  if(an_data$acci_degree[i] =="경상사고" | an_data$acci_degree[i] =="부상신고사고"){
    an_data$acci_degree[i] <- 0
  }
}

an_data = na.omit(an_data)
table(an_data$acci_degree)
table(an_data$attack_gender)

write.csv(an_data, 'final_data6.csv')

rm(list=ls())
setwd('C:/Users/user/Desktop/듀/단대/프로젝트/데이터/교통사고/솔개')
data = read.csv('final_data7.csv', header = T)
head(data)

data = na.omit(data)

write.csv(data,'final_data8.csv')

#새로운데이터분석

rm(list=ls())
setwd('C:/Users/user/Desktop/듀/단대/프로젝트/데이터/교통사고/솔개')
data = read.csv('final_data8.csv', header = T)
head(data)

str(data)
summary(data$acci_type)

#선형
model = lm(acci_degree~., data=data)
model
summary(model) 
class(model)

#공선성
library(car)
car::vif(model)
(car::vif(model))>10 

model2 = lm(acci_degree~1, data = data)

both = step(model2, scope = list(lower = model2, upper = model),direction = 'both')
summary(both)

#model
data2 = data[,c(2,4,8,9,10)] 
str(data2)

train_idx = sample(1:nrow(data2), size = nrow(data2)*0.7)

train = data2[train_idx,]
test = data2[-train_idx,]
train_ds = data2[train_idx, -5]
train_cl = data2[train_idx,5]
test_ds = data2[-train_idx,-5]
test_cl = data2[-train_idx,5]

set.seed(123)
library(randomForest)
model3 = randomForest(x = train_ds, y=train_cl, ntree = 2000, mtry = 10) # random forest
plot(model3)
pre3 = predict(model3, test_ds)
pre3 = round(pre3)
table(pre3, test_cl)
mean(pre3 == test_cl)

set.seed(122) # nn
library(nnet)
data2[,5] = as.factor(data2[,5])

model = nnet(acci_degree~.,data =data2, maxit = 100, decay = 5e-4, size =2)
pred =  predict(model, data2, type = "class")
pred

mean(data2[,5] == pred)




















