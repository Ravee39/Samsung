 package com.example.samsungproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton btn;

    private RecyclerView list;
    private LinearLayout LL;

    private EditText ETN;
    private EditText ETD;

    private TextView RES;
    private TextView RESmin;
    private TextView RESplus;

    final Context context = this;

    int Result = 0;
    int ResultM = 0;
    int ResultP = 0;
    int num = 0;

    String ResultMinS;
    String ResultPlusS;
    String ResultR;
    String Num;

    SharedPreferences sPref1;

    final String SavedRes = "0";
    final String SavedResP = "0";
    final String SavedResM = "0";
    final String SAVE1 = "";
    final String SAVE2 = "";
    final String SAVE3 = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        sPref1 = getPreferences(MODE_PRIVATE);
//        SharedPreferences.Editor ed1 = sPref1.edit();
//        SharedPreferences.Editor ed2 = sPref1.edit();
//        SharedPreferences.Editor ed3 = sPref1.edit();
//        SharedPreferences.Editor ed4 = sPref1.edit();
//        SharedPreferences.Editor ed5 = sPref1.edit();
//        SharedPreferences.Editor ed6 = sPref1.edit();
//        ed1.clear().apply();
//        ed2.clear().apply();
//        ed3.clear().apply();
//        ed4.clear().apply();
//        ed5.clear().apply();
//        ed6.clear().apply();

        RES = (TextView) findViewById(R.id.ResultR);
        RESmin = (TextView) findViewById(R.id.MinusRes);
        RESplus = (TextView) findViewById(R.id.PlusRes);
        ETN = (EditText) findViewById(R.id.input_num);
        ETD = (EditText) findViewById(R.id.input_des);
        list = findViewById(R.id.RV);
        btn = findViewById(R.id.Btn);
        LL = findViewById(R.id.ll);

        //load();


        ArrayList<info> info = new ArrayList<>();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder AD = new AlertDialog.Builder(MainActivity.this);
                AD.setMessage("").setCancelable(true)

                        .setPositiveButton("Расход", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                LayoutInflater li = LayoutInflater.from(context);
                                View DialogView = li.inflate(R.layout.dialog, null);
                                AlertDialog.Builder mDialogBuilder = new AlertDialog.Builder(context);
                                mDialogBuilder.setView(DialogView);
                                mDialogBuilder.setCancelable(false)
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {

                                                final EditText userInput = (EditText) DialogView.findViewById(R.id.input_num);
                                                final EditText userInput2 = (EditText) DialogView.findViewById(R.id.input_des);

                                                Num = userInput.getText().toString();
                                                String Des = userInput2.getText().toString();
                                                String NumM = "-"+Num;

                                                boolean a = true;

                                                info.add(new info(NumM,Des,a));

                                                num = Integer.parseInt(Num);

                                                ResultM += num;
                                                Result -= num;
                                                RESmin.setText("Расходы:" + "-" + ResultM);
                                                RES.setText("Итого:" +  Result);
                                            }
                                        })

                                        .setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                dialog.cancel();
                                            }
                                        });

                                AlertDialog alertDialog = mDialogBuilder.create();
                                alertDialog.show();
                            }
                        })

                        .setNegativeButton("Прибыль", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                LayoutInflater li = LayoutInflater.from(context);
                                View DialogView = li.inflate(R.layout.dialog, null);
                                AlertDialog.Builder mDialogBuilder = new AlertDialog.Builder(context);
                                mDialogBuilder.setView(DialogView);
                                mDialogBuilder.setCancelable(false)

                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {

                                                final EditText userInput = (EditText) DialogView.findViewById(R.id.input_num);
                                                final EditText userInput2 = (EditText) DialogView.findViewById(R.id.input_des);
                                                String Num = userInput.getText().toString();
                                                String Des = userInput2.getText().toString();
                                                boolean a = false;

                                                info.add(new info(Num, Des,a));
                                                num = Integer.parseInt(Num);



                                                ResultP += num;
                                                Result += num;
                                                RESplus.setText("Прибыль:"  + ResultP);
                                                RES.setText("Итого:" + Result);

                                            }
                                        })

                                        .setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {

                                                dialog.cancel();
                                            }
                                        });

                                AlertDialog alertDialog = mDialogBuilder.create();
                                alertDialog.show();
                            }
                        });

                AlertDialog alert = AD.create();
                alert.setTitle("");
                alert.show();
            }
        });

        Adapter adapter = new Adapter(info);
        list.setAdapter(adapter);
        list.setLayoutManager(new LinearLayoutManager(getApplicationContext()));


    }

//    @Override
//    protected void onPause() {
//        super.onPause();
//        save();
//    }


//    private void save(){
//        sPref1 = getPreferences(MODE_PRIVATE);
//        SharedPreferences.Editor ed1 = sPref1.edit();
//        SharedPreferences.Editor ed2 = sPref1.edit();
//        SharedPreferences.Editor ed3 = sPref1.edit();
//        SharedPreferences.Editor ed4 = sPref1.edit();
//        SharedPreferences.Editor ed5 = sPref1.edit();
//        SharedPreferences.Editor ed6 = sPref1.edit();
//
//        ed1.putInt(SavedRes, Result).apply();
//        ed2.putInt(SavedResP, ResultP).apply();
//        ed3.putInt(SavedResM, ResultM).apply();
//
//        ed4.putString(SAVE1, ResultR).apply();
//        ed5.putString(SAVE2, ResultPlusS).apply();
//        ed6.putString(SAVE3, ResultMinS).apply();
//    }
//    void load() {
//        sPref1 = getPreferences(MODE_PRIVATE);
//
//        String savedText1 = sPref1.getString(SAVE1, "");
//        RES.setText(savedText1);
//
//        String savedText2 = sPref1.getString(SAVE2, "");
//        RESplus.setText(savedText2);
//
//        String savedText3 = sPref1.getString(SAVE3, "");
//        RESmin.setText(savedText3);
//
//        ResultP = sPref1.getInt(SavedRes, 0);
//        Result  = sPref1.getInt(SavedResP, 0);
//        ResultM = sPref1.getInt(SavedResM, 0);
//    }

}