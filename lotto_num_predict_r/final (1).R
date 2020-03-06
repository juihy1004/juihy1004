setwd('C:/Rwork')
lotto = read.csv('lotto.csv')
head(lotto)

dat.2018 <- lotto[1:12,14:19]
dat.2017 <- lotto[13:64,14:19]
dat.2016 <- lotto[65:117,14:19]
dat.2015 <- lotto[18:169,14:19]
dat.2014 <- lotto[170:221,14:19]
dat.2013 <- lotto[222:273,14:19]
dat.2012 <- lotto[274:325,14:19]
dat.2011 <- lotto[326:378,14:19]
dat.2010 <- lotto[379:430,14:19]
dat.2009 <- lotto[431:482,14:19]

# 기존데이터
num_2009 <- as.matrix(t(table(as.matrix(dat.2009))))
num_2010 <- as.matrix(t(table(as.matrix(dat.2010))))
num_2011 <- as.matrix(t(table(as.matrix(dat.2011))))
num_2012 <- as.matrix(t(table(as.matrix(dat.2012))))
num_2013 <- as.matrix(t(table(as.matrix(dat.2013))))
num_2014 <- as.matrix(t(table(as.matrix(dat.2014))))
num_2015 <- as.matrix(t(table(as.matrix(dat.2015))))
num_2016 <- as.matrix(t(table(as.matrix(dat.2016))))
num_2017 <- as.matrix(t(table(as.matrix(dat.2017))))
num_2018 <- as.matrix(t(table(as.matrix(dat.2018))))

a <- num_2009+num_2010+num_2011+num_2012+
  num_2013+num_2014+num_2015+num_2016+num_2017

a

a <- t(as.matrix(a))
a <- as.numeric(a)
b <- 1:45
names(a) <- b
barplot(a,ylim = c(0,110))
x2 <- head(sort(a, decreasing = T),6)

# 가중치 데이터

num_2009 <- as.matrix(t(table(as.matrix(dat.2009))))*0.1
num_2010 <- as.matrix(t(table(as.matrix(dat.2010))))*0.2
num_2011 <- as.matrix(t(table(as.matrix(dat.2011))))*.3
num_2012 <- as.matrix(t(table(as.matrix(dat.2012))))*.4
num_2013 <- as.matrix(t(table(as.matrix(dat.2013))))*.5
num_2014 <- as.matrix(t(table(as.matrix(dat.2014))))*.6
num_2015 <- as.matrix(t(table(as.matrix(dat.2015))))*.7
num_2016 <- as.matrix(t(table(as.matrix(dat.2016))))*.8
num_2017 <- as.matrix(t(table(as.matrix(dat.2017))))*.9
num_2018 <- as.matrix(t(table(as.matrix(dat.2018))))*1

a <- num_2009+num_2010+num_2011+num_2012+
  num_2013+num_2014+num_2015+num_2016+num_2017

a <- t(as.matrix(a))
a <- as.numeric(a)
b <- 1:45
names(a) <- b
barplot(a,ylim = c(0,60))
x1 <- head(sort(a, decreasing = T),6)

x1 # 가중치
x2 # 정상
rank <- cbind(names(x2),names(x1)) # 번호 순위