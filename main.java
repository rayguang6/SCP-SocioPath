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
public class main {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        Group<Integer, Integer> g1=new Group<>();
        //initial setup 
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
                    meetCrush(g1);
                    g1.printFriends();
                    break;
                case 6:
                    System.out.println("Input: ");
                    int num = sc.nextInt();
                    Friendship f = new Friendship(num+1);
                    for(int i=0; i<num; i++){
                        f.addEdge(sc.nextInt(), sc.nextInt());
                    }
                    System.out.println("\nYou can form the following friendship :\n");
                    ArrayList<FrienshipList> result=f.Pathlist(1);
                    Collections.sort(result); //to sort from shortest distance to longest distance
                    for(int i=1; i<=result.size(); i++){
                        System.out.println(i+". "+result.get(i-1));
                    }
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
    
    public static void meetCrush(Group g1){
        Random r = new Random();
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter your crush.");
        int crush = sc.nextInt(); //initialise crush
        
        System.out.println("Now rumors will start out of nowhere and spread around. You have to stop it "
                + "from spreading to your crush by convincing people! \nDo you wish to play this event in difficult mode?");
        int mode;
        do { //prompt user to enter event mode
            System.out.println("Type \"2\" as yes and \"1\" as no. ");
            mode = sc.nextInt();
        } while (mode != 1 && mode != 2);
        if (mode == 2) { //difficult mode - add two new connections into graph
            System.out.println("How many more additional edges u wanted to add?");
            int num=sc.nextInt();
            for (int i = 0; i < num;) {
                int[] students = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
                int s = r.nextInt(10) + 1; //randomly pick the first student
                students[s - 1]++;

                int d;
                do {
                    d = r.nextInt(10) + 1;
                    System.out.println("test" + s + " " + d);
                } while (students[d - 1] == 1 || g1.getFriends(s).contains(d)); 
                //one cannot form new connection with themselves or the relationship had existed
                students[d - 1]++;

                if (g1.addUndirectedEdge(s, d)) { //add the new connection (if fail try again)
                    i++;
                }
            }
        }

        //initialize where rumor starts
        int start;
        do {
            System.out.println("Where the rumor start?");
            start = sc.nextInt();
        } while (start == crush);

        System.out.println("Code name number " + start + " knows your secret. And they are telling other people."); //who starts the rumor
        System.out.println("The rumor spreads by one jump per day.");

        boolean visited[] = new boolean[g1.size]; //all vertices are not visited yet
        LinkedList<Integer> queue = new LinkedList<>(); //create a queue for bfs

        //Label starting student as visited and enqueue it
        visited[start - 1] = true;
        queue.add(start);

        int pathstocrush = 0, level = 0, lastElement = 0;
        ArrayList<Integer> tobeconvinced = new ArrayList<>();
        ArrayList<ArrayList<Integer>> levels = new ArrayList<>(); //array list to present different depth
        label1:
        while (queue.size() != 0) {
            //Dequeue and print the starting student
            start = queue.poll();
            //Get all adjacent vertices (friends) of dequeued starting student
            //If the friends have not been 'visited' yet mark them visited and enqueue them
            ArrayList<Integer> friendlist = g1.getFriends(start);
            for (int i = 0; i < friendlist.size(); i++) {
                int node = friendlist.get(i);
                if (node == crush) {
                    pathstocrush++;
                    tobeconvinced.add(start);
                }
                if (!visited[node - 1]) {
                    visited[node - 1] = true;
                    queue.add(node);
                    if (levels.size() == level) { //nodes in next level
                        ArrayList<Integer> temp = new ArrayList<>();
                        temp.add(node);
                        levels.add(temp);
                    } else { //nodes in the same level
                        levels.get(level).add(node);
                    }
                }
            }
            if (level == 0 || start == lastElement) { //when all elements in the same level are discovered
                System.out.println(levels);
                if(levels.size()==level){
                    break label1; //if already reached leaf (no node joined)
                }
                if (levels.get(level).contains(crush)) {
                    break label1; //if this current level already contains your crush, then stop bcos crush is been found
                }
                lastElement = levels.get(level).get(levels.get(level).size() - 1); //update the last element to know when a level is all discovered
                level=level+1; //new level index
                System.out.println("next level: " + level + " lastElement: " + lastElement);
            }
        }

        if (!visited[crush - 1]) { //rumour never reached crush
            System.out.println("\nCongratulations! You are safe because the rumors will never reach your crush. ");
            System.out.println("-----End of Event-----");
        } else {
            System.out.println("Total days for rumors to reach crush: " + levels.size());
            System.out.println("People to convinced: " + pathstocrush);
            if (pathstocrush < levels.size()) { //the days to spread rumors should be less than the people u need to convinced
                System.out.println("Yes, your targets to convinced are : " + tobeconvinced.toString());
            } else {
                System.out.println("No. You are out of time. ");
            }
        }
        System.out.println("");
    }
    
    
}
