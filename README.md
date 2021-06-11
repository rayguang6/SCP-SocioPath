# SCP-SocioPath
UM_Data Structure Tuto6 Graoup SCP Assignment
<BR>2BRANCH : GUITesting and main
<BR>GUITesting: JFrame (if u guys want to play with it, code kurang sama with main branch nia)
<BR>main: java only
  <BR>
<BR> CF: 25/05/2021 - updated Group.java with extra feature (parallel farming) and teachStranger(mentor, mentee, performance).
  <BR> Can only teach Stranger
<BR> CF: 26/05/2021 - updated Group - haveLunch(), Student - compareTo(), calcAverage(), private variables, main - (event 3 input parameters).
<BR> event3改了蛮多的就是 （大致上是）:
  <BR> - priority以前是跟着谁吃的时间越短就排在前面 现在变谁先吃完睡在前面 (Student.java)
  <BR> - 学生有various开始吃的时间与duration, so 加多一个method来算average几点开始，吃多久，几点结束 (Student.java)
  <BR> - 之前miss掉要compare其他人跟自己吃饭的时间 eg.我1200-1255 如果其他人是1300开始吃 我是遇不到的 所以minute_slot也缩小size了 因为会根据我吃饭的分钟来declare 如果有人可以一起拼桌 就是+1 到3就满人 (Group.java)
    <BR>
<BR> JS: 05/06/2021 - [EVENT 6] uploaded Frienship.java & FrienshipList.java & updated main.java 
<BR> CF: 05/06/2021 - Student.java (ArrayList<> Friend), Group.java (updated addEdge to accept one more boolean parameters to indicate the source and destination will be friend or not. In [EVENT 1] they will be friend, whereas in [EVENT 2] they will only update rep point but not be friend.)(so printEdge(), printEdge(T v), printFriend(), printFriends(T v) all updated as they will print the rep point and also mention that they are frens or not)  
<BR>
  <BR> HC: 09/06/2021 - [EVENT 5] updated main.java & Group.java (addUndirectedEdge())
<BR> CF: 09/06/2021 - [EVENT 1 & 2] updated Group.java (boolean updateRep(), boolean findEdge(), int getRep(), boolean teachStranger(), boolean chitchat())
<BR>
  <BR> CF: 11/06/2021 - [EVENT 5] updated MeetCrush.java
