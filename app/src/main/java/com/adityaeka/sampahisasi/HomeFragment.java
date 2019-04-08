package com.adityaeka.sampahisasi;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements View.OnClickListener {


//    Button showList, addNew, register, logout;
    private OnHomeFragmentListener listener;

    public HomeFragment() {
        // Required empty public constructor
    }

    public void setListener(HomeFragment.OnHomeFragmentListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.home_add_comic:
                Intent intent = new Intent(this.getContext(), AddComicActivity.class);
                startActivity(intent);
                break;
            case R.id.home_logout:
                listener.onLogoutClick();
                break;
            case R.id.home_show_list:
                Intent intent1 = new Intent(this.getContext(), ListComicActivity.class);
                startActivity(intent1);
                break;
        }

    }

    public interface OnHomeFragmentListener {
        void onLogoutClick();
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        Button logoutButton = view.findViewById(R.id.home_logout);
        Button toList = view.findViewById(R.id.home_show_list);
        Button toAdd = view.findViewById(R.id.home_add_comic);

        logoutButton.setOnClickListener(this);
        toList.setOnClickListener(this);
        toAdd.setOnClickListener(this);


        return view;
    }

}
