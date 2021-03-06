/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package s2assignment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 *
 * @author doublechin
 */
public class S2Assignment<T> {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        Group<Integer, Integer> g1=new Group<>();
        initialization(g1);
        g1.printFriends();
        g1.printAllStudentProfile();
        printFeature();
        int input=sc.nextInt();
        while(input!=-1){
            switch(input){
                case 1:
                    System.out.println("Who is teaching?");
                    int mentor=sc.nextInt();
                    while(mentor<=0 || mentor>10){
                        System.out.println("Please input valid person. ");
                        mentor=sc.nextInt();
                    }
                    System.out.println("Who is being teached?");
                    int mentee=sc.nextInt();
                    while(mentee==mentor || mentee<=0 || mentee > 10){
                        System.out.println("Please input valid person. ");
                        mentee=sc.nextInt();
                    }
                    System.out.println("Is the teaching good? 'true' for yes, 'false' for no.");
                    boolean good=sc.nextBoolean();
                    if(g1.teachStranger(mentor, mentee, good)){
                        System.out.println("updated realtive rep point "+mentor+" to "+mentee+" : ");
                    }else{
                        System.out.println("Invalid person. Action cannot be done. Please ensure people entered existed and they are stanger.");
                    }
                    g1.printEdges(mentee);
                    break;
                case 2:
                    System.out.println("Who is talking?");
                    int talk=sc.nextInt();
                    while(talk<=0 || talk>10 || g1.getFriends(talk).size()<=1){
                        System.out.println("Please input valid person. Ensure the speaker has more than one friend.");
                        talk=sc.nextInt();
                    }
                    System.out.println(talk+" has friend of "+g1.getFriends(talk));
                    System.out.println("Who is listening?");
                    int listen=sc.nextInt();
                    while(listen== talk || !g1.getFriends(talk).contains(listen)){
                        System.out.println("Please ensure speaker is friend with listener.");
                        listen=sc.nextInt();
                    }
                    System.out.println("Who is him talking?");
                    int gossip=sc.nextInt();
                    while(gossip == talk || gossip == listen|| !g1.getFriends(talk).contains(gossip)){
                        System.out.println("Please ensure speaker is friend with the one been discussed.");
                        gossip=sc.nextInt();
                    }
                    System.out.println("Is him good? 'true' for yes, 'false' for no.");
                    boolean chitchat=sc.nextBoolean();
                    System.out.println("relative rep point from "+talk+" : ");
                    g1.printEdges(talk);
                    System.out.println("last relative rep point from "+listen+" : ");
                    g1.printEdges(listen);
                    if(!g1.chitchat(talk, listen, gossip, chitchat)){
                        System.out.println("Oops, invalid person entered. Please ensure person entered is valid and speaker knows everyone you entered!");
                    }else{
                        if(chitchat){
                            System.out.println(talk+" told "+listen+" something good about "+gossip+". Relative rep point increased!");
                        }else{
                            System.out.println("Oh no! "+talk+" told "+listen+" something bad about "+gossip+". Relative rep point decreased!");
                        }
                    }
                    System.out.println("updated relative rep point from "+listen+" : ");
                    g1.printEdges(listen);
                    break;
                case 3:
                    System.out.println("Who are you?");
                    int observe=sc.nextInt();
                    System.out.println("Insert number of people you want to observe.");
                    int counter=sc.nextInt();
                    int[] list= new int[counter];
                    System.out.println("Insert people you want to observe.");
                    for(int i=0; i<counter;){
                        int observant=sc.nextInt();
                        boolean exist=true;
                        for(int j=0; j<counter; j++){
                            if(observant==list[j]){
                                exist=false;
                                break;
                            }
                        }
                        if(observant!=observe && observant>=1 && observant<=10 && exist){
                            list[i]=observant;
                            i++;
                        }
                    }
                    System.out.println("How many days u want to observe?");
                    int day=sc.nextInt();
                    g1.haveLunch(list, observe, day);
                    break;
                case 4:
                    System.out.println("Insert number of books: ");
                    int size=sc.nextInt();
                    int[] line=new int[size];
                    System.out.println("Insert list of height of books from left to right: ");
                    for(int i=0; i<size; i++){
                        line[i]=sc.nextInt();
                    }
                    ArrangeBook a=new ArrangeBook(size, line);
                    System.out.println("Total round required: "+a.getRound());
                    break;
                case 5:
                    MeetCrush go=new MeetCrush(g1);
                    break;
                case 6:
                    System.out.println("Number of people: ");
                    int num = sc.nextInt();
                    Friendship f = new Friendship(num+1);
                    for(int i=0; i<num; i++){
                        System.out.println("Please enter two people (1-"+num+")");
                        int first=sc.nextInt();
                        int second=sc.nextInt();
                        while(first<0 || first>num || second<0 || second>num || second==first){
                            System.out.println("Please enter the pair again. ");
                            first=sc.nextInt();
                            second = sc.nextInt();
                        }
                        System.out.println("Edges formed between "+first+" and "+second+".");
                        f.addEdge(first, second);
                    }
                    System.out.println("You can form the following friendship :");
                    ArrayList<FriendshipList> result=f.Pathlist(1);
                    Collections.sort(result); //to sort from shortest distance to longest distance
                    for(int i=1; i<=result.size(); i++){
                        System.out.println(i+". "+result.get(i-1));
                    }
                    System.out.println("");
                    break;
                case 7:
                    System.out.println("-----list with rep points-----");
                    g1.printEdges();
                    System.out.println("------All Student Profile-------");
                    g1.printAllStudentProfile();
                    break;
            }
            printFeature();
            input=sc.nextInt();
        }
        System.out.println("-----Thank You. Have a Nice Day.-------");
        
       
    }
    public static void initialization(Group g1){
        int[] student={1,2,3,4,5,6,7,9,10};
        for (int i : student)
            g1.addVertex(i);
        g1.addEdge(1, 2, 5, true);
        g1.addEdge(1, 7, 4, true);
        g1.addEdge(2, 3, 5, true);
        g1.addEdge(2, 5, 6, true);
        g1.addEdge(2, 1, 9, true);
        g1.addEdge(2, 6, 9, true);
        g1.addEdge(3, 2, 4, true);
        g1.addEdge(4, 8, 7, true);
        g1.addEdge(4, 10, 7, true);
        g1.addEdge(5, 2, 2, true);
        g1.addEdge(6, 2, 7, true);
        g1.addEdge(7, 1, 3, true);
        g1.addEdge(8, 4, 10, true);
        g1.addEdge(9, 10, 5, true);
        g1.addEdge(10, 9, 6, true);
        g1.addEdge(10, 4, 7, true);
    }
    
    public static void printFeature(){
        System.out.println("1. Teach stranger");
        System.out.println("2. Chit-chat");
        System.out.println("3. Your road to glory");
        System.out.println("4. Arranging book");
        System.out.println("5. Meet crush");
        System.out.println("6. See friendship");
        System.out.println("7. See all relationship with rep point");
        System.out.println("------ Enter -1 to quit ------");
        System.out.println("Enter number of feature you wished to execute: ");
    }
    
}
