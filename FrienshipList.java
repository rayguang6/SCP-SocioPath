/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package s2assignment;

import java.util.ArrayList;

/**
 *
 * @author doublechin
 */
public class FrienshipList implements Comparable<FrienshipList> {
    ArrayList<Integer> relationship;
    
    public FrienshipList(ArrayList<Integer> i) {
        this.relationship=i;
    }
    
    public int getSize(){
        return relationship.size();
    }
    
    public String toString(){
        return relationship.toString();
    }
    
    public int get(int ind){
        return relationship.get(ind);
    }
    
    @Override
    public int compareTo(FrienshipList o) {
        //Sorting Way: The one with shortest distance in pathway will be priotised, 
        //if the distance is same, sort according the first node from 1-end
        if(relationship.size()>o.getSize()) return 1;
        else if(relationship.size()<o.getSize()) return -1;
        else{
            for(int i=0; i<relationship.size(); i++){
                if(relationship.get(i)>o.get(i)){
                    return 1;
                }
                if(relationship.get(i)<o.get(i)){
                    return -1;
                }
            }
            return 0;
        }
    }
}
