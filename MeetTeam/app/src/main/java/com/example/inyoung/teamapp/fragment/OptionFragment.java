package com.example.inyoung.teamapp.fragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.inyoung.teamapp.MapActivity;
import com.example.inyoung.teamapp.R;
import com.example.inyoung.teamapp.RetroFit.SharedPreferenceUtil;
import com.example.inyoung.teamapp.TtableActivity;

/**
 * Created by MYpc on 2017-08-11.
 */

public class OptionFragment extends Fragment {


    View view;
    Button btn_table,btn_map,btn_store;
    Intent intent;
    TextView DateSelectView;
    static int year1,month1,dayOfMonth1;
    SharedPreferenceUtil sessDB;
    Button DateSelectButton;





    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState){

        view = inflater.inflate(R.layout.fragment_option, container, false);
        sessDB = new SharedPreferenceUtil(getContext());
        DateSelectButton=(Button) view.findViewById(R.id.DateSelectButton);
        DateSelectView = (TextView) view.findViewById(R.id.DateSelectView);
        btn_table= (Button) view.findViewById(R.id.btn_table);
        btn_table.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                     if(year1!=0&&month1!=0&&dayOfMonth1!=0){
                     intent = new Intent(getContext(), TtableActivity.class);
                     intent.putExtra("year", year1);
                     intent.putExtra("month", month1);
                     intent.putExtra("day", dayOfMonth1);
                     startActivity(intent);
                     }
                else{
                         Toast.makeText(getContext(),"날짜를 선택해 주세요",Toast.LENGTH_LONG).show();
                     }
            }
        });


        final DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                DateSelectView.setText(year+"년 "+month+"월 "+dayOfMonth+"일");
                year1=year;
                month1=month+1;
                dayOfMonth1=dayOfMonth;

            }
        };

        DateSelectButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(getContext(),listener,2017,07,03);
                dialog.show();
            }



        });

        btn_map = (Button) view.findViewById(R.id.btn_map);
        btn_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getContext(), MapActivity.class);
                intent.putExtra("year", year1);
                intent.putExtra("month", month1);
                intent.putExtra("day", dayOfMonth1);
                startActivity(intent);
            }
        });

        return view;

    }



}
