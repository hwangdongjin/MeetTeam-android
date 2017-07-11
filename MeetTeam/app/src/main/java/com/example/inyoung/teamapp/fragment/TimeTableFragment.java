package com.example.inyoung.teamapp.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.inyoung.teamapp.MapActivity;
import com.example.inyoung.teamapp.R;

/**
 * Created by MYpc on 2017-04-05.
 */

public class TimeTableFragment extends Fragment {
    private AlertDialog.Builder dlg;
    EditText name,edtTimeOk,edtPlaceOk;
    Button TimeSelectButton,PlaceSelectButton;
    TextView text1,timeText,placeText;
    View view;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        view = inflater.inflate(R.layout.fragment_time_table, container, false);
        text1=(TextView) view.findViewById(R.id.TableText1);
        timeText=(TextView) view.findViewById(R.id.textViewTimeOk);
        placeText=(TextView)view.findViewById(R.id.textViewPlaceOk) ;
        TimeSelectButton=(Button)view.findViewById(R.id.TimeSelectButton);
        PlaceSelectButton=(Button) view.findViewById(R.id.PlaceSelectButton);

        TimeSelectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dlg = new AlertDialog.Builder(view.getContext());
                LayoutInflater inflater = (LayoutInflater)view.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View view1 = inflater.inflate(R.layout.item_time_ok,null, false);
                edtTimeOk=(EditText)view1.findViewById(R.id.editTextTimeOk);
                dlg.setView(view1);
                dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        timeText.setText(edtTimeOk.getText().toString());
                    }
                });

                dlg.setNegativeButton("취소", null);
                dlg.show();
            }
        });

        PlaceSelectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dlg = new AlertDialog.Builder(view.getContext());
                LayoutInflater inflater = (LayoutInflater)view.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View view1 = inflater.inflate(R.layout.item_place_ok,null, false);
                edtPlaceOk=(EditText)view1.findViewById(R.id.editTextPlaceOk);
                dlg.setView(view1);
                dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        placeText.setText(edtPlaceOk.getText().toString());
                    }
                });
                dlg.setNegativeButton("취소", null);
                dlg.show();
            }
        });

        text1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                dlg = new AlertDialog.Builder(view.getContext());
                LayoutInflater inflater = (LayoutInflater)view.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View view1 = inflater.inflate(R.layout.item_time_selecter,null, false);
                name = (EditText) view1.findViewById(R.id.editTextName);
                dlg.setView(view1);
                dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        text1.setText(name.getText().toString());
                    }
                });
                dlg.setNegativeButton("취소", null);
                dlg.show();
                return true;
            }
        });

        placeText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Intent intent2 = new Intent();
                intent2.setClass(getContext(), MapActivity.class);
                startActivity(intent2);
                return true;
            }
        });
        return  view;
    }
}
