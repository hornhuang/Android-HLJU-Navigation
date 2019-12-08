package com.example.a30797.hljunavigationsystem.floyd;

import com.example.a30797.hljunavigationsystem.schoolgate.Edge;
import com.example.a30797.hljunavigationsystem.schoolgate.MyGraph;

import static com.example.a30797.hljunavigationsystem.schoolgate.MyGraph.edges;
import static com.example.a30797.hljunavigationsystem.schoolgate.MyGraph.getArrayIndex;

public class EdgeToMatrix {
    //转换成的 distance 矩阵
    private static int distance[][] = new int[MyGraph.points.length][MyGraph.points.length];
    //转换成的 parent 矩阵
    private static int parent[][] = new int[MyGraph.points.length][MyGraph.points.length];

    private static void Initialize(){
        for ( int i = 0 ; i < MyGraph.points.length ; i++ ){
            for ( int j = 0 ; j < MyGraph.points.length ; j++ ){
                distance[i][j] = Integer.MAX_VALUE;
                parent[i][j] = j;
            }
        }
    }

    public static void Change(){
        Initialize();
        for ( int i = 0 ; i < edges.length ; i++ ){
            for(Edge e:edges) {
                distance[getArrayIndex(e.from)][getArrayIndex(e.to)] = edges.length;
            }
        }

    }

    public static int[][] getDistance() {
        return distance;
    }

    public static void setDistance(int[][] distance) {
        EdgeToMatrix.distance = distance;
    }

    public static int[][] getParent() {
        return parent;
    }

    public static void setParent(int[][] parent) {
        EdgeToMatrix.parent = parent;
    }
}
