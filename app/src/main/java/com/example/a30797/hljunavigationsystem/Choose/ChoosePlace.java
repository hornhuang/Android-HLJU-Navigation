package com.example.a30797.hljunavigationsystem.Choose;

import android.app.Activity;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.example.a30797.hljunavigationsystem.R;

public class ChoosePlace {
    private static String[] placeNames = {
            "四号楼", "三号楼","一号楼", "汇文楼","实验楼","主楼","艺术楼",
            "C区大门(南门)","C区游泳馆","C区冰场","C区食堂","C区篮球场",
            "C区29","C区28","C区26","C区25","C区23","C区24","C区22","C区21","C区20",
            "C区19","C区18","C区17","C区16","C区15","C区14","C区13","C区12","C区11",
            "二号楼","老图书馆","新图书馆","A区游泳馆","体育馆","体育场"
    };
    /*
    使用AutoCompleteTextView进行动态匹配
    输入内容
     */
    public static AutoCompleteTextView setAutoCompleteTextView(AutoCompleteTextView autoCompleteTextView , Activity activity){
        //创建ArrayAdapter封装数组
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(activity,
                android.R.layout.simple_list_item_multiple_choice,placeNames);

        autoCompleteTextView = (AutoCompleteTextView) activity.findViewById(R.id.auto);
        //设置adapter
        autoCompleteTextView.setAdapter(adapter);
        return autoCompleteTextView;
    }
}
