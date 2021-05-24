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
class Edge<T extends Comparable<T>, N extends Comparable<N>> implements Comparable<Edge>{

    Student<T, N> toVertex;
    int rep;
    Edge<T, N> nextEdge;

    public Edge() {
        toVertex = null;
        rep = 0;
        nextEdge = null;
    }

    public Edge(Student<T, N> destination, int rep, Edge<T, N> a) {
        toVertex = destination;
        this.rep = rep;
        nextEdge = a;
    }

    public void addRep(int rep) {
        this.rep = this.rep + rep;
    }

    @Override
    public int compareTo(Edge o) {
        if(this.rep > o.rep){
            return 1;
        }else if(this.rep<o.rep){
            return -1;
        }else{
            return 0;
        }
    }
    
    




}
