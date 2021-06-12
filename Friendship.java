/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package s2assignment;

/**
 *
 * @author doublechin
 */

import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Scanner;

public class Friendship {

    private int numFriend;
    private ArrayList<Integer>[] List;
    private ArrayList<FrienshipList> result = new ArrayList<>(); 
    //An ArrayList for FrienshipList is to enable sorting of the possible pathway
    
    
    public Friendship(int a) {
        this.numFriend = a;
        List = new ArrayList[a];
        for (int i = 0; i < a; i++) {
            List[i] = new ArrayList<>();
        }
    }

    public void addEdge(int b, int a) {
        List[a].add(b);
        List[b].add(a);
    }

    public void getPathList(Integer start, Integer end, boolean[] visited, ArrayList<Integer> pathlist) {
        if (start.equals(end)) { //if there is a path from start to the end
            ArrayList<Integer> t=new ArrayList<>(pathlist); //to create a copy of the list (not reference)
            FrienshipList temp=new FrienshipList(t); 
            result.add(temp); //adding elements to the list
            return;
        }

        visited[start] = true; //the starting vertex is pass true so that the vertex will not be visited again

        for (Integer i : List[start]) { //checking all the nextNode of this node
            if (!visited[i]) {
                pathlist.add(i);
                getPathList(i, end, visited, pathlist); //check any possible pathway from the nextNode node to the end eg.1(start)-3-2(end)
                pathlist.remove(i); //clear traveling history so only the start node will be lefted in the list
            }
        }
        visited[start] = false;
    }
    
    public ArrayList<FrienshipList> Pathlist(int start){
        if(start<=numFriend){ //start checking available  relationship from node start from 1 and then loop with recursion
            boolean[] visited = new boolean[numFriend];
            ArrayList<Integer> List = new ArrayList<>();
            List.add(start);
            for(int end=numFriend-1 ; end>start ; end--){ //check is there any relation between this start node and all the node in the list
                /*
                no repeatation of node will happened as loop condition: end>start 
                eg. in first round (start:1 end:4,3,2 possible path:[1,2]) second round(start:2 end:4,3, [2,1] will not be discovered again)
                */
                getPathList(start, end, visited, List);
            }
            Pathlist(++start); //loop to check following nodes(1,2,3,...)
        }
        return result;
    }
    

}


