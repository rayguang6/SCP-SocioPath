# SCP-SocioPath
UM_Data Structure Tuto6 Graoup SCP Assignment
<BR>2BRANCH : GUITesting and main
<BR>GUITesting: JFrame (if u guys want to play with it, code kurang sama with main branch nia)
<BR>main: java only
<BR> CF: 25/05/2021 - updated Group.java with extra feature (parallel farming) and teachStranger(mentor, mentee, performance).
  <BR> Can only teach Stranger
<BR> CF: 26/05/2021 - updated Group - haveLunch(), Student - compareTo(), calcAverage(), private variables, main - (event 3 input parameters).
<BR> event3改了蛮多的就是 （大致上是）:
  <BR> - priority以前是跟着谁吃的时间越短就排在前面 现在变谁先吃完睡在前面 (Student.java)
  <BR> - 学生有various开始吃的时间与duration, so 加多一个method来算average几点开始，吃多久，几点结束 (Student.java)
  <BR> - 之前miss掉要compare其他人跟自己吃饭的时间 eg.我1200-1255 如果其他人是1300开始吃 我是遇不到的 所以minute_slot也缩小size了 因为会根据我吃饭的分钟来declare 如果有人可以一起拼桌 就是+1 到3就满人 (Group.java)
  
  
