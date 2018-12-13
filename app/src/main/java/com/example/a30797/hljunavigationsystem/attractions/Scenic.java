package com.example.a30797.hljunavigationsystem.attractions;

import android.widget.ImageView;

import com.example.a30797.hljunavigationsystem.schoolgate.Point;

public class Scenic {

    private int imageId ;

    private String name ;

    private String location;

    private String introduce ;

    private Point attractionsPoint;

    /**
     * 构造方法
     * @param imageId
     * @param introduce
     */

    public Scenic(int imageId, String name, String introduce) {
        this.imageId = imageId;
        this.name = name;
        this.introduce = introduce;
    }

    public Scenic(int imageId, String introduce) {
        this.imageId = imageId;
        this.introduce = introduce;
    }

    public Scenic(int imageId) {
        this.imageId = imageId;
    }

    public Scenic(String introduce) {
        this.introduce = introduce;
    }

    public Scenic() {
    }

    public Scenic(int imageId, String name, String location, String introduce, Point attractionsPoint) {
        this.imageId = imageId;
        this.name = name;
        this.location = location;
        this.introduce = introduce;
        this.attractionsPoint = attractionsPoint;
    }


    /**
     * 访问数据成员的方法
     * @return
     */
    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Point getAttractionsPoint() {
        return attractionsPoint;
    }

    public void setAttractionsPoint(Point attractionsPoint) {
        this.attractionsPoint = attractionsPoint;
    }

    public String getLocation() {
        return location;
    }


}
