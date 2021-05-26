/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package s2assignment;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author doublechin
 */
public class main {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        Group<Integer, Integer> g1=new Group<>();
        //initial setup 
        int[] student={1,2,3,4,5,6,7,8,9,10};
        for (int i : student)
            g1.addVertex(i);
        g1.addEdge(1, 2, 5);
        g1.addEdge(1, 7, 4);
        g1.addEdge(2, 3, 5);
        g1.addEdge(2, 5, 6);
        g1.addEdge(2, 1, 9);
        g1.addEdge(2, 6, 9);
        g1.addEdge(3, 2, 4);
        g1.addEdge(4, 8, 7);
        g1.addEdge(4, 10, 7);
        g1.addEdge(5, 2, 2);
        g1.addEdge(6, 2, 7);
        g1.addEdge(7, 1, 3);
        g1.addEdge(8, 4, 10);
        g1.addEdge(9, 10, 5);
        g1.addEdge(10, 9, 6);
        g1.addEdge(10, 4, 7);
        g1.printFriends();
        g1.printAllStudentProfile();
        printFeature();
        int input=sc.nextInt();
        while(input!=-1){
            switch(input){
                case 1:
                    //test case:
                    //g1.teachStrager(1, 2, true);
                    //g1.teachStrager(4, 2, false);
                    System.out.println("Who is teaching?");
                    int mentor=sc.nextInt();
                    System.out.println("Who is being teached?");
                    int mentee=sc.nextInt();
                    System.out.println("Is the teaching good? 'true' for yes, 'false' for no.");
                    boolean good=sc.nextBoolean();
                    System.out.println("last relative rep point"+mentor+" to "+mentee+" : ");
                    g1.printEdges(mentee);
                    g1.teachStrager(mentor, mentee, good);
                    System.out.println("updated realtive rep point"+mentor+" to "+mentee+" : ");
                    g1.printEdges(mentee);
                    break;
                case 2:
                    //test case: 
                    // 1 2 7 (stranger)
                    // 7 2 5 (invalid person)
                    System.out.println("Who is talking?");
                    int talk =sc.nextInt();
                    System.out.println("Who is listening?");
                    int listen=sc.nextInt();
                    System.out.println("Who is him talking?");
                    int gossip=sc.nextInt();
                    System.out.println("Is him good? 'true' for yes, 'false' for no.");
                    boolean chitchat=sc.nextBoolean();
                    System.out.println("relative rep point to "+talk+" : ");
                    g1.printEdges(talk);
                    System.out.println("last relative rep point to "+listen+" : ");
                    g1.printEdges(listen);
                    if(!g1.chitchat(talk, listen, gossip, chitchat)){
                        System.out.println("----invalid person----");
                    }
                    System.out.println("updated relative rep point "+gossip+" to "+listen+" : ");
                    g1.printEdges(listen);
                    break;
                case 3:
                    System.out.println("Who are you?");
                    int observe=sc.nextInt();
                    System.out.println("Insert number of people you want to observe.");
                    int counter=sc.nextInt();
                    int[] list= new int[counter];
                    System.out.println("Insert people you want to observe.");
                    for(int i=0; i<counter; i++){
                        int observant=sc.nextInt();
                        list[i]=observant;
                    }
                    g1.haveLunch(list, observe);
                    break;
                case 4:
                    //test case: 13 16 17 12 15
                    System.out.println("Insert number of books: ");
                    int size=sc.nextInt();
                    int[] line=new int[size];
                    System.out.println("Insert list of height of books from left to right: ");
                    for(int i=0; i<size; i++){
                        line[i]=sc.nextInt();
                    }
                    System.out.println(arrangeBook(size, line));
                    break;
                case 5:
                case 6:
                case 7:
                    System.out.println("-----list with rep points-----");
                    g1.printEdges();
                    break;
            }
            printFeature();
            input=sc.nextInt();
        }
        System.out.println("-----Thank You. Have a Nice Day.-------");
        
       
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
