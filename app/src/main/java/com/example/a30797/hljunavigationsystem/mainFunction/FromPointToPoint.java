package com.example.a30797.hljunavigationsystem.mainFunction;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a30797.hljunavigationsystem.R;
import com.example.a30797.hljunavigationsystem.schoolgate.MyGraph;

import java.sql.BatchUpdateException;

public class FromPointToPoint extends Activity {
    
    final String[] items=new String[]{
            "四号楼",
            "三号楼",
            "C11",
            "汇文楼",
            "主楼",
            "实验楼",
            "一号楼",
            "体育场",
            "老图书馆",
            "A区游泳馆",
            "二号楼",
            "体育馆",
            "新图书馆",
            "艺术楼",
            "C区游泳馆",
            "C区冰场",
            "C区大门(南门)",
            "C区篮球场",
            "C区食堂"
    };
    
    private int arrayIndexFrom=0,arrayIndexTo=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fp2p);

        final TextView textView=findViewById(R.id.text1);
        final TextView textView2=findViewById(R.id.text2);

        Button button=findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(FromPointToPoint.this);
                builder.setTitle("选择出发点");
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        arrayIndexFrom=which+1;
                        textView.setText(items[which]);
                    }
                });
                builder.create().show();
            }
        });

        Button button2=findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(FromPointToPoint.this);
                builder.setTitle("选择目的地");
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        arrayIndexTo=which+1;
                        textView2.setText(items[which]);
                    }
                });
                builder.create().show();
            }
        });

        Button button3=findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(arrayIndexFrom==arrayIndexTo){
                    Toast.makeText(FromPointToPoint.this,"出发点与目的地不能相同",Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent=new Intent();
                intent.putExtra("indexFrom",MyGraph.points[arrayIndexFrom].index);
                intent.putExtra("indexTo",MyGraph.points[arrayIndexTo].index);
                setResult(0x1,intent);
                finish();
            }
        });

        Button button4=findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
