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
class Student<T extends Comparable<T>, N extends Comparable<N>> implements Comparable<Student> {

    T vertexInfo;
    int reputation; //this one idk should be here or not
    int divingrate;
    int average_lunchStart;
    int average_lunchPeriod;
    int end_time;
    ArrayList<Integer> lunchStart = new ArrayList<>();
    ArrayList<Integer> lunchPeriod = new ArrayList<>();
    private int lastCheck=0;
    int outdeg;
    int indeg;
    Edge<T, N> relativeRep; //this is to show weight
    Student<T, N> nextVertex;
    ArrayList<T> friendList = new ArrayList<>(); //this is to show friend list only
    Random r = new Random();

    public Student() {
        vertexInfo = null;
        nextVertex = null;
        relativeRep = null;
    }

    public Student(T vInfo, Student<T, N> next) {
        vertexInfo = vInfo;
        nextVertex = next;
        relativeRep = null;
        divingrate = r.nextInt(100);
        reputation = 10 - (divingrate / 10);//if diving rate high, reputation low
        if (reputation == 0) {
            reputation = 1;
        }
        lunchStart.add(setTime());
        lunchPeriod.add(r.nextInt(56) + 5);
    }

    public int setTime() {
        int min = r.nextInt(180);
        int temp = 1100;
        while (min >= 60) {
            temp = temp + 100;
            min = min - 60;
        }
        temp = temp + min;
        return temp;
    }

    public void generateTime() {
        lunchStart.add(setTime());
        lunchPeriod.add(r.nextInt(54) + 6); //(6-59)
    }

    public void calculateAverage(int day) {
        //to calculate average lunch start time
        if (day != lastCheck) {
            int min = 0, hr = 0, resultmin, resulthr, temp;
            for (int i = 0; i < day; i++) {
                min = min + lunchStart.get(i) % 100;
            }
            for (int i = 0; i < day; i++) {
                hr = hr + lunchStart.get(i) / 100;
            }
            resulthr = hr / day;
            temp = hr % day;
            min = min + temp * 60;
            resultmin = min / day;
            while (resultmin >= 60) {
                resulthr += 1;
                resultmin -= 60;
            }
            average_lunchStart = resulthr * 100 + resultmin;

            //to calculate average lunch period
            int sumPeriod=0;
            for (int j = 0; j < day; j++) {
                sumPeriod = sumPeriod + lunchPeriod.get(j);
            }
            average_lunchPeriod = sumPeriod / day;

            //to calculate average lunch end time
            int endMinute = average_lunchStart % 100 + average_lunchPeriod;
            if (endMinute >= 60) { //using if instead of while is because largest value of min will be 59 + 59 = 118
                end_time = (average_lunchStart / 100 * 100) + 100 + (endMinute - 60);
            } else {
                end_time = average_lunchStart + average_lunchPeriod;
            }
            //update lastCheck
            lastCheck=day;
        }
    }

    @Override
    public String toString() {
        return vertexInfo + "(Reputation : " + reputation + "| Diving rate : " + divingrate
                + "| Lunch Start Time : " + lunchStart.toString() + "| Lunch Period : " + lunchPeriod.toString()
                + "| friendList : " + friendList + ")";
    }

    @Override
    public int compareTo(Student o) {
        //priority: people with less time
        if (this.end_time > o.end_time) {
            return 1;
        } else if (this.end_time < o.end_time) {
            return -1;
        } else {
            return 0;
        }
    }

}
