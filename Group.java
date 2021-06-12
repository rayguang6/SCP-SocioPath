/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package s2assignment;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author doublechin
 */
public class Group<T extends Comparable<T>, N extends Comparable<N>> implements Cloneable {

    Student<T, N> head;
    int size;

    public Group() {
    }

    public boolean addVertex(T v) {
        if (hasStudent(v) == false) {
            Student<T, N> temp = head;
            Student<T, N> newVertex = new Student<>(v, null);
            if (head == null) {
                head = newVertex;
            } else {
                Student<T, N> previous = head;;
                while (temp != null) {
                    previous = temp;
                    temp = temp.nextVertex;
                }
                previous.nextVertex = newVertex;
            }
            size++;
            return true;
        } else {
            return false;
        }
    }

    public boolean addEdge(T source, T destination, int rep, boolean friend) {
        //no condition checking required as alredy checked in previous event
        Student<T, N> sourceV = head;
        while (sourceV != null) {
            if (sourceV.vertexInfo.equals(source)) {
                Student<T, N> destV = head;
                while (destV != null) {
                    if (destV.vertexInfo.equals(destination)) {
                        Edge<T, N> firstRelative = sourceV.relativeRep;
                        Edge<T, N> newRelative = new Edge<>(destV, rep, firstRelative);
                        sourceV.relativeRep = newRelative;
                        sourceV.outdeg++;
                        destV.indeg++;
                        if (friend) {
                            sourceV.friendList.add(destination);
                        }
                        return true;
                    }
                    destV = destV.nextVertex;
                }
            }
            sourceV = sourceV.nextVertex;
        }
        return false;
    }

    public boolean updateRep(T source, T destination, int weight, boolean friend) {
        //no condition checking of nodes is required because will be checked in previous method
        Student<T, N> sourceV = head;
        while (sourceV != null) {
            if (sourceV.vertexInfo.equals(source)) {
                Edge<T,N> temp=sourceV.relativeRep;
                while(temp!=null){
                    if(temp.toVertex.vertexInfo.equals(destination)){
                        temp.addRep(weight);
                    }
                    temp=temp.nextEdge;
                }
                if(friend) sourceV.friendList.add(destination);
                return true;
            }
            sourceV = sourceV.nextVertex;
        }
        return false;
    }

    public void printAllStudentProfile() {
        Student<T, N> temp = head;
        while (temp != null) {
            System.out.println(temp.toString());
            temp = temp.nextVertex;
        }
    }

    public boolean hasStudent(T v) {
        if (head == null) {
            return false;
        }
        Student<T, N> temp = head;
        while (temp != null) {
            if (temp.vertexInfo.compareTo(v) == 0) {
                return true;
            }
            temp = temp.nextVertex;
        }
        return false;
    }
    
    public boolean findEdge(T source, T destination){
        Student<T, N> sourceV = head;
        while (sourceV != null) {
            if (sourceV.vertexInfo.equals(source)) {
                Edge<T, N> temp = sourceV.relativeRep;
                while (temp != null) {
                    if (temp.toVertex.vertexInfo.equals(destination)) {
                        return true;
                    }
                    temp = temp.nextEdge;
                }
            }
            sourceV = sourceV.nextVertex;
        }
        return false;
    }
    
    public int getRep(T source, T destination) { //get rep point between 2 nodes
        Student<T, N> sourceV = head;
        int weight = 0;
        while (sourceV != null) {
            if (sourceV.vertexInfo.equals(source)) {
                Edge<T, N> temp = sourceV.relativeRep;
                while (temp != null) {
                    if (temp.toVertex.vertexInfo.equals(destination)) {
                        weight = temp.rep;
                        return weight;
                    }
                    temp = temp.nextEdge;
                }
            }
            sourceV = sourceV.nextVertex;
        }
        return weight;
    }

    public void printEdges() { //print friends of all with rep point
        Student<T, N> temp = head;
        while (temp != null) {
            Edge<T, N>[] list = new Edge[temp.outdeg]; //creating array to sort
            Edge<T, N> currentEdge = temp.relativeRep;
            int i = 0;
            while (currentEdge != null) {
                list[i] = currentEdge;
                currentEdge = currentEdge.nextEdge;
                i++;
            }
            Arrays.sort(list); //sort according their rep point
            System.out.print("#" + temp.vertexInfo + " : ");
            for (int j = 0; j < list.length; j++) {
                System.out.print("[");
                if (temp.friendList.contains(list[j].toVertex.vertexInfo)) {
                    System.out.print("Friend: ");
                } else {
                    System.out.print("Stranger: ");
                }
                System.out.print(list[j].toVertex.vertexInfo + ": " + list[j].rep + "p]");
            }
            System.out.println("");
            temp = temp.nextVertex;
        }
    }

    public void printEdges(T v) { //print friends of one with rep point
        Student<T, N> temp = head;
        while (temp != null) {
            if (temp.vertexInfo.equals(v)) {
                Edge<T, N>[] list = new Edge[temp.outdeg];
                Edge<T, N> currentEdge = temp.relativeRep;
                int i = 0;
                while (currentEdge != null) {
                    list[i] = currentEdge;
                    currentEdge = currentEdge.nextEdge;
                    i++;
                }
                Arrays.sort(list);
                System.out.print("#" + temp.vertexInfo + " : ");
                for (int j = 0; j < list.length; j++) {
                    System.out.print("[");
                    if (temp.friendList.contains(list[j].toVertex.vertexInfo)) {
                        System.out.print("Friend: ");
                    } else {
                        System.out.print("Stranger: ");
                    }
                    System.out.print(list[j].toVertex.vertexInfo + ": " + list[j].rep + "p]");
                }
                System.out.println("");
                break;
            }
            temp = temp.nextVertex;
        }
    }

    public ArrayList<T> getFriends(T v) { //print friends of one
        if (!hasStudent(v)) {
            return null;
        }
        ArrayList<T> list = new ArrayList<T>();
        Student<T, N> temp = head;
        while (temp != null) {
            if (temp.vertexInfo.compareTo(v) == 0) {
                return temp.friendList;
            }
            temp = temp.nextVertex;
        }
        return list;
    }

    public void printFriends() { //print friends of all
        Student<T, N> temp = head;
        while (temp != null) {
            System.out.print("# " + temp.vertexInfo + " : ");
            System.out.println(temp.friendList);
            temp = temp.nextVertex;
        }
    }

    public boolean teachStranger(T mentor, T mentee, boolean good) { //feature 1
        if (head == null) {
            return false;
        }
        if (!hasStudent(mentor) || !hasStudent(mentee)) {
            return false;
        }
        if (Group.this.getFriends(mentor).contains(mentee)) {
            return false; //if they are friends, cannot be done
        }
        //modify mentee--->mentor
        if (findEdge(mentee, mentor)){ //如果他们已经对他有刻板印象
            if(good){
                updateRep(mentee, mentor, 10, true);
            }else{
                updateRep(mentee, mentor, 2, true);
            }
        }else{ //如果他们毫不相识
            if(good){
                addEdge(mentee, mentor, 10, true);
            }else{
                addEdge(mentee, mentor, 2, true);
            }
        }
        //modify meontor---->mentee
        if(findEdge(mentor, mentee)){
            updateRep(mentor, mentee, 0, true);
        }else{
            addEdge(mentor, mentee, 0, true);
        }
        return true;
    }

    public boolean chitchat(T talker, T listener, T rumors, boolean good) { //feature 2
        if (head == null) {
            return false;
        }
        if (!hasStudent(talker) || !hasStudent(listener) || !hasStudent(rumors)) {
            return false;
        }
        //basic requirement: talker should know both listener and rumors (safe check)
        if (!Group.this.getFriends(talker).contains(listener) || !Group.this.getFriends(talker).contains(rumors)) {
            return false;
        }
        int weight = getRep(talker, rumors); 
        if (findEdge(listener, rumors)) { //如果他们已经有刻板印象 （也包括如果他们是朋友） //modidication of point
            if(good){
                return updateRep(listener, rumors, weight / 2, false);
            }else{
                return updateRep(listener, rumors, weight * -1, false);
            }
        } else { //如果他们毫不相识 
            if (good) {
                return addEdge(listener, rumors, weight / 2, false);
            } else {
                return addEdge(listener, rumors, weight * -1, false);
            }
        }
    }

    public void haveLunch(int[] student, T me, int day) { //feature 3
        Student<T, N>[] ary = new Student[student.length];
        Student<T, N> ME = new Student<>();
        //inseting students into a list
        for (int i = 0; i < student.length; i++) {
            Student<T, N> sourceV = head;
            while (sourceV != null) {
                if (sourceV.vertexInfo.equals(student[i])) {
                    while(sourceV.lunchPeriod.size()<day){
                        sourceV.generateTime();
                    }
                    sourceV.calculateAverage(day);
                    ary[i] = sourceV;
                } else if (sourceV.vertexInfo.equals(me) && i==0) {
                    while(sourceV.lunchPeriod.size()<day){
                        sourceV.generateTime();
                    }
                    sourceV.calculateAverage(day);
                    ME = sourceV;
                }
                sourceV = sourceV.nextVertex;
            }
        }
        //sort accroding priority, less time used will be priotized, first observed has least time
        Arrays.sort(ary);
        //ArrayList indicates the schedule plan with who, start time and end time
        ArrayList<T> people = new ArrayList<>();
        //declare an array with minutes value of the observant
        int[] time_slot = new int[ME.average_lunchPeriod];
        //calculate my minute value of start time and end time
        int my_start_min = calcMin(ME.average_lunchStart);
        int my_end_min = calcMin(ME.end_time);
        //print my eating time
        System.out.println("My eating time : " + ME.average_lunchStart + " " + ME.average_lunchPeriod + " " + ME.end_time);
        //go for loop to check every individual time of eating 
        System.out.println("[people]starting time--period---end time");
        for (int i = 0; i < ary.length; i++) {
            //calculate their minute value of eating time
            int start_min = calcMin(ary[i].average_lunchStart);
            int end_min = calcMin(ary[i].end_time);
            //to display value for checking purpose only
            System.out.println("[" + ary[i].vertexInfo + "]" + ary[i].average_lunchStart + " " + ary[i].average_lunchPeriod + " " + ary[i].end_time);
            //calculating slot in minutes value 
            //check is there intersection on observant and observer time of eating, if no, straight print result
            //his_start---mystart----his_end----myend 
            //mystart---his_start---myend---his_end 
            //his_start--mystart---myend---his_end
            if (end_min >= my_start_min && end_min <= my_end_min
                    || start_min >= my_start_min && start_min <= my_end_min
                    || start_min <= my_start_min && end_min >= my_end_min) {
                //compare with array, to check whether the table is full or not
                boolean full = false;
                for (int ss = 0; ss < ME.average_lunchPeriod; ss++) {
                    if (time_slot[ss] >= 3) {
                        full = true;
                        break;
                    }
                }
                //if the table is not full with 3 people yet, they can eat with them
                if (!full) {
                    people.add(ary[i].vertexInfo);
                    int temp = 0;
                    if (start_min >= my_start_min) {
                        temp = start_min - my_start_min;
                    }
                    for (int s = temp; s < end_min - my_start_min; s++) {
                        if (s >= time_slot.length) {
                            break;
                        }
                        time_slot[s] = time_slot[s] + 1;
                    }
                    for (int v = 0; v < time_slot.length; v++) {
                        if (v >= temp && v < end_min - my_start_min) {
                            System.out.print(ary[i].vertexInfo);
                        } else {
                            System.out.print(0);
                        }
                    }
                    System.out.println("");
                }
            }
        }
        //printing the lunch time plan
        System.out.println("Target Lunch Partner: ");
        System.out.println(people.toString());
        //how much people he can have lunch with
        System.out.println("Total target: " + people.size());
    }

    public int calcMin(int time) {
        int min = time % 100;
        if (time / 100 == 12) {
            min = min + 60;
        } else if (time / 100 == 13) {
            min = min + 120;
        } else if (time / 100 == 14) {
            min = 180;
        }
        return min;
    }

}
