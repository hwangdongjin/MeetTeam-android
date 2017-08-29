package com.example.inyoung.teamapp.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
    Button btn_call;
    Intent itemIntent;
    String faceBookUrl;
    View view;
    static int number = 0;
    String phoneNum;
    public MemberlistFragment() {
    }

    public static MemberlistFragment newInstance(ArrayList<UserListDTO> userList, int num) {
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_memberlist, container, false);
        btn_call= (Button) view.findViewById(R.id.btn_call);
        initListView(view);
        return view;
    }
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private void initListView(View view) {
        fListView = (ListView)view.findViewById(R.id.listView_profile);
        profileList = new ArrayList<>();
        for(int i=0;i<number;i++){
            profileList.add(new MemberDTO("https://upload.wikimedia.org/wikipedia/commons/thumb/1/1b/Facebook_icon.svg/2000px-Facebook_icon.svg.png", userList.get(i).getName(), "서울지부 총괄 GM",userList.get(i).getPhoneNum()));
        }
        pAdapter = new ProfileAdapter(profileList, getContext());
        fListView.setAdapter(pAdapter);


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
