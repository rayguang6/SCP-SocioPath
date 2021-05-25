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
public class Group<T extends Comparable<T>, N extends Comparable<N>> {
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

    public boolean addEdge(T source, T destination, int rep) {
        if (head == null) {
            return false;
        }
        if (!hasStudent(source) || !hasStudent(destination)) {
            return false;
        }
        Student<T, N> sourceV = head;
        while (sourceV != null) {
            if (sourceV.vertexInfo.equals(source)) {
                Student<T, N> destV = head;
                while (destV != null) {
                    if (destV.vertexInfo.equals(destination)) {
                        Edge<T, N> currentFriend = sourceV.firstFriend;
                        Edge<T, N> newFriend = new Edge<>(destV, rep, currentFriend);
                        sourceV.firstFriend = newFriend;
                        sourceV.outdeg++;
                        destV.indeg++;
//                        sourceV.addFriend(destination);
                        return true;
                    }
                    destV = destV.nextVertex;
                }
            }
            sourceV = sourceV.nextVertex;
        }
        return false;
    }

    public void printAllStudentProfile(){
        Student<T,N> temp=head;
        while(temp!=null){
            System.out.println(temp.toString());
            temp=temp.nextVertex;
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

    public void printEdges() { //print friends of all with rep point
        Student<T, N> temp = head;
        while(temp!=null){
            Edge<T,N> [] list= new Edge[temp.outdeg]; //creating array to sort
            Edge<T, N> currentEdge = temp.firstFriend;
            int i=0;
            while(currentEdge!=null){
                list[i]=currentEdge;
                currentEdge=currentEdge.nextEdge;
                i++;
            }
            Arrays.sort(list); //sort according their rep point
            System.out.print("#"+temp.vertexInfo+" : ");
            for(int j=0; j<list.length; j++){
                System.out.print("["+list[j].toVertex.vertexInfo + ": "+list[j].rep+"]");
            }
            System.out.println("");
            temp=temp.nextVertex;
        }
    }
    
    public void printEdges(T v) { //print friends of one with rep point
        Student<T, N> temp = head;
        while(temp!=null){
            if(temp.vertexInfo.equals(v)){
                Edge<T,N> [] list= new Edge[temp.outdeg];
                Edge<T, N> currentEdge = temp.firstFriend;
                int i=0;
                while(currentEdge!=null){
                    list[i]=currentEdge;
                    currentEdge=currentEdge.nextEdge;
                    i++;
                }
                Arrays.sort(list);
                System.out.print("#"+temp.vertexInfo+" : ");
                for(int j=0; j<list.length; j++){
                    System.out.print("["+list[j].toVertex.vertexInfo + ": "+list[j].rep+"]");
                }
                System.out.println("");
                break;
            }
            temp=temp.nextVertex;
        }
    }

    public T getVertex(int pos) {
        if (pos > size - 1 || pos < 0) {
            return null;
        }
        Student<T, N> temp = head;
        for (int i = 0; i < pos; i++) {
            temp = temp.nextVertex;
        }
        return temp.vertexInfo;
    }

    public ArrayList<T> getFriends(T v) { //print friends of one
        if (!hasStudent(v)) {
            return null;
        }
        ArrayList<T> list = new ArrayList<T>();
        Student<T, N> temp = head;
        while (temp != null) {
            if (temp.vertexInfo.compareTo(v) == 0) {
                // Reached vertex, look for destination now
                Edge<T, N> currentEdge = temp.firstFriend;
                while (currentEdge != null) {
                    list.add(currentEdge.toVertex.vertexInfo);
                    currentEdge = currentEdge.nextEdge;
                }
            }
            temp = temp.nextVertex;
        }
        return list;
    }
    
    public void printFriends(){ //print friends of all
        Student<T, N> temp = head;
        while (temp != null) {
            System.out.print("# " + temp.vertexInfo + " : ");
            Edge<T, N> currentEdge = temp.firstFriend;
            while (currentEdge != null) {
                System.out.print(currentEdge.toVertex.vertexInfo+" ");
                currentEdge = currentEdge.nextEdge;
            }
            System.out.println();
            temp = temp.nextVertex;
        }
    }
    
    public boolean teachStrager(T mentor, T mentee, boolean good){ //feature 1
        if (head == null) {
            return false;
        }
        if (!hasStudent(mentor) || !hasStudent(mentee)) {
            return false;
        }
        //if they are friend, not stranger, action cannot be done
        if(findEdge(mentor, mentee)>0) return false;
        Student<T, N> sourceV = head;
        while (sourceV != null) {
            if (sourceV.vertexInfo.equals(mentee)) {
                if(good){
                    addEdge(mentee, mentor, 10);
                }else{
                    addEdge(mentee, mentor, 2);
                }
                return true;
            }
            sourceV = sourceV.nextVertex;
        }
        return false;
    }
    
    public boolean chitchat(T talker, T listener, T rumors, boolean good){ //feature 2
        if (head == null) {
            return false;
        }
        if (!hasStudent(talker) || !hasStudent(listener) ||!hasStudent(rumors)) {
            return false;
        }
        //basic requirement: talker should know both listener and rumors
        if(findEdge(talker, listener)==0) return false;
        int weight=findEdge(talker, rumors);
        if(weight==0) return false;
        //modify rep point for listener-rumor
        Student<T, N> sourceV = head;
        while (sourceV != null) {
            if (sourceV.vertexInfo.equals(listener)) {
                Edge<T, N> temp = sourceV.firstFriend;
                boolean found=false;
                while (temp != null) { //while loop to check whether they are frens or not
                    //if他们是朋友 
                    if (temp.toVertex.vertexInfo.equals(rumors)) {
                        if(good){
                            temp.addRep(weight/2);
                        }else{
                            temp.addRep(weight*-1);
                        }
                        found=true;
                    }
                    temp = temp.nextEdge;
                }
                //if 他们原本不是朋友
                if(!found){
                    if(good){
                        addEdge(listener, rumors, weight/2);
                    }else{
                        addEdge(listener, rumors, weight*-1);
                    }
                }
                return true;
            }
            sourceV = sourceV.nextVertex;
        }
        return false;
    }
    
    public int findEdge(T source, T destination){
        Student<T, N> sourceV = head;
        int weight=0;
        //get rep point for talker-rumor
        while(sourceV!=null){
            if (sourceV.vertexInfo.equals(source)) {
                Edge<T,N> temp=sourceV.firstFriend;
                while(temp!=null){
                    if(temp.toVertex.vertexInfo.equals(destination)){
                        weight=temp.rep;
                        break;
                    }
                    temp=temp.nextEdge;
                }
            }
            sourceV=sourceV.nextVertex;
        }
        return weight;
    }
    
    public void haveLunch(int[] student){ //feature 3 （1-
        Student<T, N>[] ary=new Student[student.length];
        //inseting students into a list
        for(int i=0; i<student.length; i++){
            Student<T, N> sourceV = head;
            while(sourceV!=null){
                if (sourceV.vertexInfo.equals(student[i])) {
                    ary[i]=sourceV;
                }
                sourceV=sourceV.nextVertex;
            }
        }
        //sort accroding priority, less time used will be priotized, first observed has least time
        Arrays.sort(ary);
        ArrayList<Integer> start = new ArrayList<>();
        ArrayList<Integer> end = new ArrayList<>();
        ArrayList<T> people = new ArrayList<>();
        for(int i=0; i<ary.length; i++){
            //calculate endTime
            int endTime, endMinute;
            endMinute=ary[i].lunchStart%100+ary[i].lunchPeriod;
            if(endMinute>=60){
                endTime=ary[i].lunchStart/100 * 100;
                endTime=endTime+(endMinute-60)+100;
            }else{
                endTime=ary[i].lunchStart/100 * 100 +endMinute;
            }
            //if the student didn't plan to eat with people yet, the first he observed will be the first target
            if(start.isEmpty()){
                start.add(ary[i].lunchStart);
                end.add(endTime);
                people.add(ary[i].vertexInfo);
            }else{
                //compare got clash or not
                boolean clash=false;
                for(int j=0; j<start.size(); j++){
                    int ListStart=start.get(j);
                    int ListEnd=end.get(j);
                    if(ary[i].lunchStart>=ListStart && ary[i].lunchStart<=ListEnd || //listStart----start------listEnd
                            endTime>=ListStart && endTime<=ListEnd || //listStart-----end------listEnd
                            ary[i].lunchStart<=ListStart && endTime>=ListEnd){ //start----ListStart----ListEnd----end
                        clash=true;
                        break;
                    }
                }
                //if no clash, he can have lunch with that person
                if(!clash){
                    start.add(ary[i].lunchStart);
                    end.add(endTime);
                    people.add(ary[i].vertexInfo);
                }
            }
            //printing for checking (each observant eating start time - period - end time)
            System.out.print(ary[i].lunchStart+" "+ary[i].lunchPeriod+" "+endTime+" | ");
        }
        System.out.println("");
        //printing the lunch time plan
        System.out.println("Lunch Schedule: ");
        System.out.println(people.toString());
        System.out.println(start.toString());
        System.out.println(end.toString());
        //how much people he can have lunch with
        System.out.println(start.size());
    }
    
    public void haveLunch(int[] student){ //feature 3 (EXTRA FEATURE: up to 3 people)
        Student<T, N>[] ary=new Student[student.length];
        //inseting students into a list
        for(int i=0; i<student.length; i++){
            Student<T, N> sourceV = head;
            while(sourceV!=null){
                if (sourceV.vertexInfo.equals(student[i])) {
                    ary[i]=sourceV;
                }
                sourceV=sourceV.nextVertex;
            }
        }
        //sort accroding priority, less time used will be priotized, first observed has least time
        Arrays.sort(ary);
        //ArrayList indicates the schedule plan with who, start time and end time
        ArrayList<Integer> start = new ArrayList<>();
        ArrayList<Integer> end = new ArrayList<>();
        ArrayList<T> people = new ArrayList<>();
        //we have 1100-1500 eatiing slot, total of 60*4+1=241 minutes , time_slot in minutes value
        int[] time_slot=new int[241];
        for(int i=0; i<ary.length; i++){
            //calculate endTime
            int endTime, endMinute;
            endMinute=ary[i].lunchStart%100+ary[i].lunchPeriod;
            if(endMinute>=60){
                endTime=ary[i].lunchStart/100 * 100;
                endTime=endTime+(endMinute-60)+100;
            }else{
                endTime=ary[i].lunchStart/100 * 100 +endMinute;
            }
            //calculating slot in minutes value 
            int start_slot=ary[i].lunchStart%100;
            if(ary[i].lunchStart/100==12){
                start_slot=start_slot+60;
            }else if(ary[i].lunchStart/100==13){
                start_slot=start_slot+120;
            }
            //compare with array, to check whether the table is full or not
            //example of array: 00000111110000002222223333332222200000000
            //5th-10th minute got 1 people, 2 indicates 2 people on table on that particular time, and so on.
            boolean full=false; //the table will be full when there are already 3 people there
            for(int ss=start_slot; ss<start_slot+ary[i].lunchPeriod; ss++){
                if(time_slot[ss]>=3){
                    full=true;
                    break;
                }
            }
            //if the table is not full with 3 people yet, they can eat with them
            if(!full){
                start.add(ary[i].lunchStart);
                end.add(endTime);
                people.add(ary[i].vertexInfo);
                for(int s=start_slot; s<start_slot+ary[i].lunchPeriod; s++){
                    time_slot[s]=time_slot[s]+1;
                }
            }
            //to display value for checking purpose only
            System.out.println("["+ary[i].vertexInfo+"]"+ary[i].lunchStart+" "+ary[i].lunchPeriod+" "+endTime);
            for(int t=0; t<time_slot.length; t++){
                    System.out.print(time_slot[t]);
            }
            System.out.println("");
        }
        //printing the lunch time plan
        System.out.println("Lunch Schedule: ");
        System.out.println(people.toString());
        System.out.println(start.toString());
        System.out.println(end.toString());
        //how much people he can have lunch with
        System.out.println("Total people: "+start.size());
    }
}
