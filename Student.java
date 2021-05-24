/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package s2assignment;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author doublechin
 */
class Student <T extends Comparable<T>, N extends Comparable <N>> implements Comparable<Student>{
   T vertexInfo;
   int reputation; //this one idk should be here or not
   int divingrate; 
   int lunchStart; //feature 3
   int lunchPeriod; //feature 3
   int outdeg;
   int indeg; 
   Edge<T,N> firstFriend; 
   Student<T,N> nextVertex;
   Random r=new Random(); 
   
   public Student() {
      vertexInfo=null;
      nextVertex = null;
      firstFriend = null;
   }
	
   public Student(T vInfo, Student<T,N> next) {
      vertexInfo = vInfo;
      nextVertex = next;
      firstFriend = null;
      divingrate = r.nextInt(100);
      reputation = 10-(divingrate/10);//if diving rate high, reputation low
      if(reputation==0) reputation=1;
      lunchStart = setTime();
      lunchPeriod = r.nextInt(55)+5;
   }	
   
    public int setTime(){
        int temp=r.nextInt(180)+1100;
        if(temp%100>=60){
            temp=temp+100-60;
        }
        return temp;
    }
    

    @Override
    public String toString() {
        return vertexInfo + "(Reputation : " + reputation + "| Diving rate : " + divingrate + "| Lunch Start Time : " + lunchStart + "| Lunch Period : " + lunchPeriod + ')';
    }

    @Override
    public int compareTo(Student o) {
        //priority: people with less time
        if(this.lunchPeriod>o.lunchPeriod){
            return 1;
        }else if(this.lunchPeriod<o.lunchPeriod){
            return -1;
        }else{ //if same time, then arrange according to their starting time (just for easier visualisation, no point)
            if(this.lunchStart>o.lunchStart){
                return 1;
            }else if(this.lunchStart<o.lunchStart){
                return -1;
            }else{
                return 0;
            }
        }
    }
   
   
}

