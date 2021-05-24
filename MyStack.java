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
public class MyStack<E> {
    private java.util.ArrayList<E> list=new java.util.ArrayList<>();

    public MyStack() {
    }
    
    
    public void push(E o){
        list.add(o);
    }
    
    public E pop(){
        if(isEmpty()){
            return null;
        }
        return list.remove(list.size()-1); // index -1 error
    }
    
    public E peek(){
        if(isEmpty()){
            return null;
        }
        return list.get(list.size()-1); //index -1 error
    }
    
    public int getSize(){
        return list.size();
    }
    
    public boolean isEmpty(){
        return list.isEmpty();
    }
    
    public String toString(){
        return list.toString();
    }
    
    public boolean search(E o){
        return list.contains(o);
    }
    
    
}
