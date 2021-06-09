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

    public boolean addEdge(T source, T destination, int rep, boolean friend) {
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
                        if(friend) sourceV.friendList.add(destination);
                        return true;
                    }
                    destV = destV.nextVertex;
                }
            }
            sourceV = sourceV.nextVertex;
        }
        return false;
    }
    
    public boolean addUndirectedEdge(T source, T destination){
        return addEdge(source, destination, 0, true) && addEdge(destination, source, 0, true);
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
                System.out.print("[");
                if(temp.friendList.contains(list[j].toVertex.vertexInfo)) System.out.print("Friend: ");
                else System.out.print("Stranger: ");
                System.out.print(list[j].toVertex.vertexInfo + ": "+list[j].rep+"p]");
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
                    System.out.print("[");
                    if(temp.friendList.contains(list[j].toVertex.vertexInfo)) System.out.print("Friend: ");
                    else System.out.print("Stranger: ");
                    System.out.print(list[j].toVertex.vertexInfo + ": "+list[j].rep+"p]");
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
                return temp.friendList;
            }
            temp = temp.nextVertex;
        }
        return list;
    }
    
    public void printFriends(){ //print friends of all
        Student<T, N> temp = head;
        while (temp != null) {
            System.out.print("# " + temp.vertexInfo + " : ");
            System.out.println(temp.friendList);
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
        if (getFriends(mentor).contains(mentee)) {
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
    
    public boolean chitchat(T talker, T listener, T rumors, boolean good){ //feature 2
        if (head == null) {
            return false;
        }
        if (!hasStudent(talker) || !hasStudent(listener) || !hasStudent(rumors)) {
            return false;
        }
        //basic requirement: talker should know both listener and rumors
        if (!getFriends(talker).contains(listener) || !getFriends(talker).contains(rumors)) {
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
    
    public void haveLunch(int[] student, T me){ // updated feature 3: PARALLEL FARMING
        Student<T, N>[] ary=new Student[student.length];
        Student<T, N> ME=new Student<>();
        //inseting students into a list
        for(int i=0; i<student.length; i++){
            Student<T, N> sourceV = head;
            while(sourceV!=null){
                if (sourceV.vertexInfo.equals(student[i])) {
                    //calculating average lunch period and end time
                    sourceV.calculateAverage();
                    ary[i]=sourceV;
                }else if(sourceV.vertexInfo.equals(me)){
                    sourceV.calculateAverage();
                    ME=sourceV;
                }
                sourceV=sourceV.nextVertex;
            }
        }
        //sort accroding priority, who will finished their lunch earlier will have higher priority
        Arrays.sort(ary);
        //ArrayList indicates the schedule plan to have lunch with who
        ArrayList<T> people = new ArrayList<>();
        //declare an array with minutes value of the observant each index indicates each minute
        int[] time_slot=new int[ME.average_lunchPeriod];
        //calculate my minute value of start time and end time
        int my_start_min=calcMin(ME.average_lunchStart);
        int my_end_min= calcMin(ME.end_time);
        //print my eating time
        System.out.println("My eating time : "+ME.average_lunchStart+" "+ME.average_lunchPeriod+" "+ME.end_time);
        //go for loop to check every individual time of eating 
        System.out.println("[people]starting time--period---end time");
        for(int i=0; i<ary.length; i++){
            //calculate their minute value of eating time
            int start_min=calcMin(ary[i].average_lunchStart);
            int end_min=calcMin(ary[i].end_time);
            //check is there intersection on observant and observer time of eating
            /* CONDITION: 
            his_start---mystart----his_end----myend 
            mystart---his_start---myend---his_end 
            his_start--mystart---myend---his_end 
            */
            if(end_min >= my_start_min && end_min <= my_end_min 
                    || start_min >= my_start_min && start_min <= my_end_min
                    || start_min<=my_start_min && end_min>=my_end_min){
                //compare with array, to check whether the table is full or not
                boolean full=false;
                for(int ss=0; ss<ME.average_lunchPeriod; ss++){
                    if(time_slot[ss]>=3){
                        full=true;
                        break;
                    }
                }
                //if the table is not full with 3 people yet, I can eat with them
                if(!full){
                    people.add(ary[i].vertexInfo);
                    /*
                    the value on each index of time_slot indicates the number of people on table on that minute, +1 to add people to the table
                    eg. my starting time is 1312, index 0 indicates the first minute of my eating time (1312-1313) 
                    if student start before my eating time, addition start from index 0 until the time he finished his lunch
                    if student start after my eating time, subtraction needed to compare his start eating time with my starting time
                    */
                    int temp=0;
                    if(start_min>=my_start_min){
                        temp=start_min-my_start_min;
                    }
                    for(int s=temp; s<end_min-my_start_min; s++){
                        if(s>=time_slot.length){
                            break;
                        }
                        time_slot[s]=time_slot[s]+1;
                    }
                }
            }
            //to display value for checking purpose only
            System.out.println("["+ary[i].vertexInfo+"]"+ary[i].average_lunchStart+" "+ary[i].average_lunchPeriod+" "+ary[i].end_time);
            for(int t=0; t<time_slot.length; t++){
                System.out.print(time_slot[t]);
            }
            System.out.println("");
        }
        //printing the lunch time plan
        System.out.println("Target Lunch Partner: ");
        System.out.println(people.toString());
        System.out.println("Total target: "+people.size());
    }
    
    //method to calculate the minute value of student lunch time
    public int calcMin(int time){
        int min=time%100;
        if(time/100==12){
            min=min+60;
        }else if(time/100==13){
            min=min+120;
        }else if(time/100==14){
            min=180;
        }
        return min;
    }
}
