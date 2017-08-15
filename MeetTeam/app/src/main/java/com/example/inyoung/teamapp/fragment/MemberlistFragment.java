package com.example.inyoung.teamapp.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.inyoung.teamapp.R;
import com.example.inyoung.teamapp.adapter.ProfileAdapter;
import com.example.inyoung.teamapp.dto.MemberDTO;
import com.example.inyoung.teamapp.dto.UserListDTO;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MemberlistFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MemberlistFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MemberlistFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private ListView fListView;
    private ArrayList<MemberDTO> profileList;
    private static ArrayList<UserListDTO> userList = null;
    private ProfileAdapter pAdapter;
    private AlertDialog.Builder dlg;
    private RadioButton user_faceBook, user_PhoneNumber;
    Context context = getContext();
    Intent itemIntent;
    String faceBookUrl;
    View view;
    static int number = 0;
    public MemberlistFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GalleryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MemberlistFragment newInstance(ArrayList<UserListDTO> userList,int num) {
        MemberlistFragment fragment = new MemberlistFragment();
        Bundle args = new Bundle();
        args.putSerializable("userlist",userList);
        args.putInt("listSize",num);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

            userList = (ArrayList<UserListDTO>) getArguments().get("userlist");
            number= (int) getArguments().get("listSize");


        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_memberlist, container, false);
        initView(view);
        initListView(view);
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }



    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }



    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private void initView(View view){
        //gIntent = getIntent();
        //id = gIntent.getStringExtra("ID");
        Toast.makeText(getContext(),"반갑습니다.", Toast.LENGTH_LONG).show();
        //findViewById(R.id.facebook_page_for_cadi).setOnClickListener(this);
    }

    private void initListView(View view) {
        fListView = (ListView)view.findViewById(R.id.listView_profile);
        profileList = new ArrayList<>();
        for(int i=0;i<number;i++){
            profileList.add(new MemberDTO("https://upload.wikimedia.org/wikipedia/commons/thumb/1/1b/Facebook_icon.svg/2000px-Facebook_icon.svg.png", userList.get(i).getName(), "서울지부 총괄 GM"));

        }


        pAdapter = new ProfileAdapter(profileList, getContext());

        fListView.setAdapter(pAdapter);

        fListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            //position은 순서
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                initDialogBox();
                dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(user_PhoneNumber.isChecked()){
                            CallIntent(itemIntent, position);
                        }else if (user_faceBook.isChecked()) {
                            FacebookIntent(itemIntent, position);
                        } else {
                            Toast.makeText(getContext(), "보기 중 선택을 반드시 해주시기 바랍니다.", Toast.LENGTH_LONG).show();
                        }
                    }
                });
                dlg.setNegativeButton("취소", null);
                dlg.show();
            }
        });

    }

    private void initDialogBox(){
        dlg = new AlertDialog.Builder(view.getContext());
        LayoutInflater inflater = (LayoutInflater)view.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_listalertbox,null, false);

        user_faceBook = (RadioButton) view.findViewById(R.id.user_facebook_page);
        user_PhoneNumber = (RadioButton) view.findViewById(R.id.user_phoneNumber);
        dlg.setView(view);

    }


    private void CallIntent(Intent rIntent, int position){
        rIntent = new Intent(Intent.ACTION_DIAL);
        switch(position){
            case 0:
                rIntent.setData(Uri.parse("tel:" + "01046182721"));
                break;
            case 1:
                rIntent.setData(Uri.parse("tel:" + "01093014375"));
                break;
            case 2:
                rIntent.setData(Uri.parse("tel:"+ "01050079201"));
                break;
            case 3:
                rIntent.setData(Uri.parse("tel:"+"01027047513"));
                break;
            case 4:
                rIntent.setData(Uri.parse("tel:"+"01022141462"));
                break;
            case 5:
                rIntent.setData(Uri.parse("tel:"+"01068773722"));
                break;
        }
        startActivity(rIntent);
    }
    private void FacebookIntent(Intent fIntent, int position){
        switch (position){
            case 0:
                faceBookUrl = "https://www.facebook.com/seongho.haam";
                break;
            case 1:
                faceBookUrl = "https://www.facebook.com/kim.wooyoung.5?fref=ts";
                break;
            case 2:
                faceBookUrl = null;
                Toast.makeText(getContext(),"FaceBook 페이지가 존재하지 않습니다.",Toast.LENGTH_LONG).show();
                break;
            case 3:
                faceBookUrl = "https://www.facebook.com/chaeyeon.han.75?fref=ts";
                break;
            case 4:
                faceBookUrl = "https://www.facebook.com/profile.php?id=100004136567958&fref=ts";
                break;
            case 5:
                faceBookUrl = "https://www.facebook.com/profile.php?id=100004587577166&fref=ts";
                break;
        }

        fIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(faceBookUrl));
        startActivity(fIntent);
    }
}
