package com.example.inyoung.teamapp.fragment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.inyoung.teamapp.R;

/**
 * Created by MYpc on 2017-04-05.
 */

public class TimeTableFragment extends Fragment {
    private AlertDialog.Builder dlg;
    EditText name,edt_tableName;
    TextView text1;
    TextView timeText;
    TextView placeText;
    View view;
    static int textId;

    Button DateSelectButton;
    TextView DateSelectView;



    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        view = inflater.inflate(R.layout.fragment_time_table, container, false);
        timeText=(TextView) view.findViewById(R.id.textViewTimeOk);
        placeText=(TextView)view.findViewById(R.id.textViewPlaceOk) ;
        DateSelectButton=(Button) view.findViewById(R.id.DateSelectButton);
        DateSelectView = (TextView) view.findViewById(R.id.DateSelectView);
        view.findViewById(R.id.TableText0).setOnClickListener(onClick);
        view.findViewById(R.id.TableText1).setOnClickListener(onClick);
        view.findViewById(R.id.TableText2).setOnClickListener(onClick);
        view.findViewById(R.id.TableText3).setOnClickListener(onClick);
        view.findViewById(R.id.TableText4).setOnClickListener(onClick);
        view.findViewById(R.id.TableText5).setOnClickListener(onClick);
        view.findViewById(R.id.TableText6).setOnClickListener(onClick);
        view.findViewById(R.id.TableText7).setOnClickListener(onClick);
        view.findViewById(R.id.TableText8).setOnClickListener(onClick);
        view.findViewById(R.id.TableText9).setOnClickListener(onClick);
        view.findViewById(R.id.TableText10).setOnClickListener(onClick);
        view.findViewById(R.id.TableText11).setOnClickListener(onClick);
        view.findViewById(R.id.TableText12).setOnClickListener(onClick);
        view.findViewById(R.id.TableText13).setOnClickListener(onClick);
        view.findViewById(R.id.TableText14).setOnClickListener(onClick);
        view.findViewById(R.id.TableText15).setOnClickListener(onClick);
        view.findViewById(R.id.TableText16).setOnClickListener(onClick);
        view.findViewById(R.id.TableText17).setOnClickListener(onClick);
        view.findViewById(R.id.TableText18).setOnClickListener(onClick);
        view.findViewById(R.id.TableText19).setOnClickListener(onClick);
        view.findViewById(R.id.TableText20).setOnClickListener(onClick);
        view.findViewById(R.id.TableText21).setOnClickListener(onClick);
        view.findViewById(R.id.TableText22).setOnClickListener(onClick);
        view.findViewById(R.id.TableText23).setOnClickListener(onClick);
        view.findViewById(R.id.TableText24).setOnClickListener(onClick);
        view.findViewById(R.id.TableText25).setOnClickListener(onClick);
        view.findViewById(R.id.TableText26).setOnClickListener(onClick);
        view.findViewById(R.id.TableText27).setOnClickListener(onClick);



        final DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                DateSelectView.setText(year+"년 "+month+"월 "+dayOfMonth+"일");
            }
        };

        DateSelectButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(getContext(),listener,2017,07,03);
                dialog.show();
            }



        });

        return  view;



    }

    public void initDialog(View view,int textId){

        dlg = new AlertDialog.Builder(view.getContext());
        text1= (TextView) view.findViewById(textId);
        LayoutInflater inflater = (LayoutInflater)view.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view1 = inflater.inflate(R.layout.item_time_selecter,null, false);
        name = (EditText) view1.findViewById(R.id.edt_tableName);
        dlg.setView(view1);
        dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                text1.append(name.getText().toString()+"\n");

            }
        });

        dlg.setNegativeButton("취소", null);
        dlg.show();

    }

    TextView.OnClickListener onClick =  new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()){

                case R.id.TableText0:
                    textId=R.id.TableText0;
                    initDialog(view,textId);
                    break;
                case R.id.TableText1:
                    textId=R.id.TableText1;
                    initDialog(view,textId);
                    break;
                case R.id.TableText2:
                    textId=R.id.TableText2;
                    initDialog(view,textId);
                    break;
                case R.id.TableText3:
                    textId=R.id.TableText3;
                    initDialog(view,textId);
                case R.id.TableText4:
                    textId=R.id.TableText4;
                    initDialog(view,textId);
                    break;
                case R.id.TableText5:
                    textId=R.id.TableText5;
                    initDialog(view,textId);
                    break;
                case R.id.TableText6:
                    textId=R.id.TableText6;
                    initDialog(view,textId);
                case R.id.TableText7:
                    textId=R.id.TableText7;
                    initDialog(view,textId);
                    break;
                case R.id.TableText8:
                    textId=R.id.TableText8;
                    initDialog(view,textId);
                    break;
                case R.id.TableText9:
                    textId=R.id.TableText9;
                    initDialog(view,textId);
                case R.id.TableText10:
                    initDialog(view,textId);
                    break;
                case R.id.TableText11:
                    initDialog(view,textId);
                    break;
                case R.id.TableText12:
                    initDialog(view,textId);
                    break;
                case R.id.TableText13:
                    initDialog(view,textId);
                    break;
                case R.id.TableText14:
                    initDialog(view,textId);
                    break;
                case R.id.TableText15:
                    initDialog(view,textId);
                    break;
                case R.id.TableText16:
                    initDialog(view,textId);
                    break;
                case R.id.TableText17:
                    initDialog(view,textId);
                    break;
                case R.id.TableText18:
                    initDialog(view,textId);
                    break;
                case R.id.TableText19:
                    initDialog(view,textId);
                    break;
                case R.id.TableText20:
                    initDialog(view,textId);
                    break;
                case R.id.TableText21:
                    initDialog(view,textId);
                    break;
                case R.id.TableText22:
                    initDialog(view,textId);
                    break;
                case R.id.TableText23:
                    initDialog(view,textId);
                    break;
                case R.id.TableText24:
                    initDialog(view,textId);
                    break;
                case R.id.TableText25:
                    initDialog(view,textId);
                    break;
                case R.id.TableText26:
                    initDialog(view,textId);
                    break;
                case R.id.TableText27:
                    initDialog(view,textId);
                    break;


            }

        }
    };







    }







