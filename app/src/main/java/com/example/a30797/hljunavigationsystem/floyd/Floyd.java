package com.example.a30797.hljunavigationsystem.floyd;

import android.util.Log;

import com.example.a30797.hljunavigationsystem.schoolgate.MyGraph;

public class Floyd {
    //矩阵大小  图中点数
    int num ;
    //距离矩阵
    int distance[][] ;
    //父母矩阵
    int parent[][] ;
    /*
     * 构造方法
     */
    public Floyd() {
        super();
        distance = new int[0][0];
        parent = new int[0][0];
    }

    public Floyd(int[][] distance, int[][] parent) {
        super();
        this.distance = distance;
        this.parent = parent;
    }


    public Floyd(int num) {
        super();
        this.num = num;
        this.distance = new int[num][num];
        this.parent = new int[num][num];
    }

    /*
     * 初始化所有矩阵方法
     */
    private void initialize() {
        for(int i = 0 ; i < distance.length ; i++ ){
            for(int j = 0 ; j < distance.length ; j++ ){
                parent[i][j] = j ;
            }
        }
    }

    /*
     * 三次循环找到所有最短路径
     */
    public void findShort() {
        //更新矩阵
        initialize();
        //三个循环 设定所有
        for (int temp = 0; temp < distance.length; temp++) {
            for (int i = 0; i < distance.length; i++) {
                for (int j = 0; j < distance.length; j++) {
                    if (distance[i][temp]!= Integer.MAX_VALUE && distance[temp][j]!= Integer.MAX_VALUE) {
                        if (distance[i][j] > ( distance[i][temp] + distance[temp][j] )) {
                            distance[i][j] = ( distance[i][temp] + distance[temp][j] );
                            parent[i][j] = parent[i][temp];
                        }
                    }
                }
            }
        }
    }

    /*
     * get set 方法
     */
    public int[][] getDiatance() {
        return distance;
    }
    public void setDiatance(int[][] diatance) {
        this.distance = diatance;
    }
    public int[][] getParent() {
        return parent;
    }
    public void setParent(int[][] parent) {
        this.parent = parent;
    }

    /*
    在命令行输出弗洛伊德表格：
     */
    /*
    查看floyd的结果
     */
    public static void FloydShow(int [][]distance , int[][] parent){
        String distanceOut = null ;
        for (int i = 0; i < MyGraph.points.length ; i++ ){
            for ( int j = 0 ; j < MyGraph.points.length ; j++ ){
                distanceOut = distanceOut + "  " + String.valueOf(distance[i][j]);
            }
            Log.v("qwe1",distanceOut);
            distanceOut = null ;
        }
        distanceOut = null ;
        Log.v("qwe1","");
        Log.v("qwe1","");
        for ( int i = 0 ; i < MyGraph.points.length ; i++ ){
            for ( int j = 0 ; j < MyGraph.points.length ; j++ ){
                distanceOut = distanceOut + "  " + String.valueOf(parent[i][j]);
            }
            Log.v("qwe1",distanceOut);
            distanceOut = null ;
        }
    }
}
