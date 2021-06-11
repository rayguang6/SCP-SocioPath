/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package s2assignment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;
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
                    System.out.println("Who is being teached?");
                    int mentee=0;
                    do{
                    mentee=sc.nextInt();
                    }while(mentee==mentor);
                    System.out.println("Is the teaching good? 'true' for yes, 'false' for no.");
                    boolean good=sc.nextBoolean();
                    if(g1.teachStrager(mentor, mentee, good)){
                        System.out.println("updated realtive rep point "+mentor+" to "+mentee+" : ");
                    }else{
                        System.out.println("Invalid person. Action cannot be done. Please ensure people entered existed and they are stanger.");
                    }
                    g1.printEdges(mentee);
                    break;
                case 2:
                    System.out.println("Who is talking?");
                    int talk =sc.nextInt();
                    System.out.println("Who is listening?");
                    int listen=0;
                    do{
                        listen=sc.nextInt();
                    }while(listen==talk);
                    System.out.println("Who is him talking?");
                    int gossip=0;
                    do{
                        gossip=sc.nextInt();
                    }while(gossip==talk || gossip==listen);
                    System.out.println("Is him good? 'true' for yes, 'false' for no.");
                    boolean chitchat=sc.nextBoolean();
                    System.out.println("relative rep point from "+talk+" : ");
                    g1.printEdges(talk);
                    System.out.println("last relative rep point from "+listen+" : ");
                    g1.printEdges(listen);
                    if(!g1.chitchat(talk, listen, gossip, chitchat)){
                        System.out.println("Oops, invalid person entered. Please ensure person entered is valid and talker knows everyone you entered!");
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
                        if(observant!=observe){
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
                    System.out.println("Total round required: "+arrangeBook(size, line));
                    break;
                case 5:
                    MeetCrush go=new MeetCrush(g1);
                    break;
                case 6:
                    System.out.println("Input: ");
                    int num = sc.nextInt();
                    Friendship f = new Friendship(num+1);
                    for(int i=0; i<num; i++){
                        f.addEdge(sc.nextInt(), sc.nextInt());
                    }
                    System.out.println("You can form the following friendship :\n");
                    ArrayList<FrienshipList> result=f.Pathlist(1);
                    Collections.sort(result); //to sort from shortest distance to longest distance
                    for(int i=1; i<=result.size(); i++){
                        System.out.println(i+". "+result.get(i-1));
                    }
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
        int[] student={1,2,3,4,5,6,7,8,9,10};
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
        System.out.println("Enter number of feature you wihsed to execute: ");
    }
    
    public static int arrangeBook(int size, int[] line){
        MyStack<Integer> stck=new MyStack<>();
        boolean action=true;
        int round=0;
        while(action){
            action=false;
            stck.push(line[0]);
            /*
            if book arrangement: 13 16 12 17 15, book on shelf: array; book left on shelf after arrangment:stack
            first round: 16 17 taken out, left: 13 12 15 (stack)
            second round: 15 taken out, left: 13 12 (stack)
            */
            for(int j=1; j<size; j++){
                if(line[j]>line[j-1]){
                    action=true; //if there is book higher than the previous one, book is taken out.
                }else{
                    stck.push(line[j]);//is the book is already lower than previous one, no need take out, put to the stack.
                }
            }
            if(action) round++; //if there is books picked out, mean that action had been done, thus round++
            else break; //if no action taken out, end the loop
            size=stck.getSize();
            for(int j=size-1; j>=0; j--){
                line[j]=stck.pop();
            } //restoring the book in stack into array for next round of arrangement
            System.out.print(round+" .  ");
            for(int j=0; j<size; j++){
                System.out.print(line[j]+" ");
            }
            System.out.println("");
        }
        return round;
    }
    
    
}
