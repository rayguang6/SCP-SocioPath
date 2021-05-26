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
   int reputation; 
   int divingrate; 
   int average_lunchStart; 
   int average_lunchPeriod; 
   int end_time;
   int[] lunchStart = new int[3];
   int[] lunchPeriod = new int[3];
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
      for(int i=0; i<lunchStart.length; i++){
          lunchStart[i]=setTime();
      }
      for(int j=0; j<lunchPeriod.length; j++){
          lunchPeriod[j]= r.nextInt(55)+5;
      }
   }	
   
    public int setTime(){
        int min=r.nextInt(181);
        int temp=1100;
        while(min>=60){
            temp=temp+100-60;
            min=min-60;
        }
        temp=temp+min;
        return temp;
    }
	
    public void calculateAverage(){
        //to calculate average lunch start time
        if(average_lunchStart==0){
            int min=0, hr=0, resultmin, resulthr, temp;
            for(int i=0; i<lunchStart.length; i++){
                min=min+lunchStart[i]%100;
            }
            for(int i=0; i<lunchStart.length; i++){
                hr=hr+lunchStart[i]/100;
            }
            resulthr=hr/lunchStart.length;
            temp=hr%lunchStart.length;
            min=min+temp*60;
            resultmin=min/lunchStart.length;
            while(resultmin>=60){
                resulthr+=1;
                resultmin-=60;
            }
            end_time=resulthr*100+resultmin;
        }   
        //to calculate average lunch period
        if(average_lunchPeriod==0){
            for(int j=0; j<lunchPeriod.length; j++){
                average_lunchPeriod=average_lunchPeriod+lunchPeriod[j];
            }
            average_lunchPeriod=average_lunchPeriod/(lunchPeriod.length);
        }
        //to calculate average lunch end time
        if(end_time==0){
            int endMinute=average_lunchStart%100+average_lunchPeriod;
            if(endMinute>=60){
                end_time=(average_lunchStart/100 * 100)+100+(endMinute-60);
            }else{
                end_time=average_lunchStart+average_lunchPeriod;
            }
        }
    }
	
    //to print array
    public String array_toString(int[] ary){
        String str="[ ";
        for(int i=0; i<ary.length; i++){
            str=str+ary[i]+" ";
        }
        return str+"]";
    }

    @Override
    public String toString() {
        return vertexInfo + "(Reputation : " + reputation + "| Diving rate : " + divingrate + "| Lunch Start Time : " + array_toString(lunchStart) + "| Lunch Period : " + array_toString(lunchPeriod)  + ')';
    }

    @Override
    public int compareTo(Student o) {
        //priority: people with less time
        if(this.end_time>o.end_time){
            return 1;
        }else if(this.end_time<o.end_time){
            return -1;
        }else{ 
            return 0;
        }
    }
   
}

