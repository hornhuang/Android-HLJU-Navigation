package com.example.a30797.hljunavigationsystem.schoolgate;

public class Edge {
    public int from;
    public int to;
    public double length;
    public int index;
    public Edge(int from,int to,double length,int index){
        this.from=from;
        this.to=to;
        this.length=length;
        this.index=index;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "from=" + from +
                ", to=" + to +
                ", length=" + length +
                ", index=" + index +
                '}';
    }
}
