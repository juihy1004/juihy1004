#t test
daily.intake <- c(5260, 5470, 5640, 6180, 6390, 6515, 6805, 7515,
                  7515, 8230, 8770)
length(daily.intake)
mean(daily.intake)
sd(daily.intake)
t.test(daily.intake, mu= 7725, alternative = c('less'))
# t가 -값이 도출 7725보다 작다. df는 자유도. p-value는 -2.8202보다 작을 확률
#-2.8208이 나올 확률은 0.01 거의 나올 수 없다 기준은 0.05- 7725기각
# alternatvie 를 주면 p-value가 반으로 줄어든다 less만 보기 때문에 

setwd('C:/Rwork')
idol = read.table('idol.txt',header= TRUE)
head(idol)
attach(idol)
boxplot(RV, GF, BP, BTS, WO, EXO, names=c("RV", "GF", "BP", "BTS", "WO", "EXO"))
mean(c(RV, GF, BP, BTS, WO, EXO))
mean(EXO)
idol_s = c(mean(RV), mean(GF),mean(BP),mean(BTS),mean(WO),mean(EXO))
idol_s

#표본의 수를 늘려서 본다
t.test(EXO, mu = 3)
t.test(EXO, mu =2.7)
t.test(EXO, mu =2.7, alternative = c('less'))

detach(idol)

#sigma test(카이제곱)
install.packages("TeachingDemos")
library(TeachingDemos)
sd(daily.intake)
sigma.test(daily.intake, simga= 1304445)

#f검정
install.packages("ISwR")
library(ISwR)
#Two sample t-test
attach(energy)
energy
var.test(expend~stature)
t.test(expend~stature,  var.equal=T)
t.test(expend~stature)
expend.lean <- expend[stature=="lean"]
expend.obese <- expend[stature=="obese"]
var.test(expend.obese,expend.lean)
t.test(expend.lean, expend.obese)
wilcox.test(expend~stature) # 정규분포가 무너졌을지 몰라서,,
# ttest willcox.test비교 같으면 다행 다르면 고민

#paired - ttest
attach(intake)
intake 
#동일한 사람에게서 두번 관측한다 - 차이를 이용 하나의 데이터셋으로 - 0인가
post - pre
t.test(pre,post, paired = T) #데이터의 갯수가 같을 경우 가능
t.test(pre,post)#위의 것과 다른 결과가 나온다
wilcox.test(pre,  post,  paired=T)

#중요도 - 실행값 = 만족도, S = I - P

#각그룹별간의 선호도
var.test(EXO,RV)
var.test(EXO,GF)
var.test(EXO,BP)
var.test(EXO,BTS)
var.test(EXO,WO)

t.test(EXO,RV,var.equal =T, alternative = c('greater'))
t.test(EXO,GF,var.equal =T, alternative = c('greater'))
t.test(EXO,BP,var.equal =T, alternative = c('greater'))
t.test(EXO,BTS,var.equal =T, alternative = c('greater'))
t.test(EXO,WO,var.equal =T, alternative = c('greater'))

t.test(WO,RV,var.equal =T, alternative = c('greater'))
t.test(WO,GF,var.equal =T, alternative = c('less'))
t.test(WO,BP,var.equal =T, alternative = c('greater'))
t.test(WO,BTS,var.equal =T, alternative = c('less'))

t.test(BTS,RV,var.equal =T, alternative = c('greater'))
t.test(BTS,BP,var.equal =T, alternative = c('greater'))
t.test(BTS,GF,var.equal =T, alternative = c('greater'))
t.test(GF,BTS,var.equal =T, alternative = c('greater') )


t.test(BP,RV,var.equal =T, alternative = c('greater'))
t.test(BP,GF,var.equal =T, alternative = c('less'))

t.test(GF,RV,var.equal =T, alternative = c('greater'))
#exo -> bts, gf -> wo -> bp ->rv 각각의 t를 비교하면 앞의 결과를 얻을 수 있다.

#각 항목간의 만족

t.test(dance_i, dance_p, var.equal = T, paired	 = T)
t.test(dance_i,dance_p,mu=0.125, var.equal=T) 
#t = 1.4409, 차이가 존재한다. -> 0.1264368

t.test(sing_i, sing_p, var.equal = T, paired	 = T)
t.test(sing_i,sing_p,mu=0.41, var.equal=T) 
#t = 4.5789, 0.4137931 차이가 존재한다. -> 0.4137931

t.test(variety_i, variety_p, var.equal = T, paired	 = T)
t.test(variety_i,variety_p,mu=0.43, var.equal=T) 
#t = 3.7894 차이가 존재한다. -> 0.4367816

t.test(plan_i, plan_p, var.equal = T, paired	 = T)
t.test(plan_i,plan_p,mu=0.12, var.equal=T)
#t = 1.4409 차이가 존재한다.-> 0.1264368

t.test(capital_i, capital_p, var.equal = T, paired	 = T)
t.test(capital_i,capital_p,mu=-0.09, var.equal=T)
#t = -0.85145 차이가 존재한다. -> -0.09195402

t.test(marketing_i, marketing_p, var.equal = T, paired	 = T)
t.test(marketing_i,marketing_p, mu=0.045, var.equal=T)
#t = -0.45674 차이가 존재한다. -> 0.04597701

#따라서 capital의 만족도가 가장 높고 variety의 만족도가 가장 낮다고 결론된다.

#회귀
#93.186으로 시작 각각의 계수를 더해서 계산한다.(+ popsitive, - negative)
#+이면 잘하면 호감도가 올라가고 -이면 잘하면 떨어진다
#93.186은 기본적으로 먹고들어간다.
#각각이 0인지 봐야한다, Pr(>|t|)이 0이냐의 확률 - 0이아니면 의미없다
#Pr(>|t|) = 0 사용해도 된다
cor(boy,girl)
boxplot(boy,girl, col= c('lightblue','lightpink'))

blm = lm(boy~dance_i+sing_i+variety_i+person_i+plan_i+capital_i+marketing_i)
summary(blm)
blm = step(lm(boy~dance_i+sing_i+variety_i+person_i+plan_i+capital_i+marketing_i)
           ,direction="both")#backward,foreward
#필요없는 것을 잘라준다
blm <- lm(boy ~ dance_i + sing_i + variety_i + person_i + plan_i)
summary(blm)

glm = lm(girl~dance_i+sing_i+variety_i+person_i+plan_i+capital_i+marketing_i)
summary(glm)
glm = step(lm(girl~dance_i+sing_i+variety_i+person_i+plan_i+capital_i+marketing_i),
           direction = 'both')

#3개를 비교할 때 
#전체차이 = 집단간차이 + 집단내차이 if 집단간차이/ 집단내차이 big - 그룹화가 잘 되어있다
boxplot(boy~type)
#anova는 집단간 평균비교이다.
anova(lm(boy~type))#type 대신 gender를 사용시 에러가난다. 2개라
boxplot(boy~sing_i)
anova(lm(boy~sing_i)) # Pr(>F) 0.0001009 확률이 낮다, 각 그룹간의 값은 같지않다.

pairwise.t.test(boy, sing_i) #그룹간의 차이를 알아보기 위하여- oneway를 사용해도 된다
pairwise.t.test(boy, sing_i, pool.sd=F) #분산이 다르
















