package com.adityaeka.sampahisasi.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.adityaeka.sampahisasi.R;
import com.adityaeka.sampahisasi.db.DbComic;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {

    private OnRegisterFragmentListener listener;

    public static DbComic dbComic;

    public RegisterFragment() {
        // Required empty public constructor
    }

    public interface OnRegisterFragmentListener{
        void onRegistClicked(View view, String username, String password);
        void toLogin();
    }

    public void setListener(OnRegisterFragmentListener listener){
        this.listener=listener;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        dbComic = new DbComic(this.getContext(), "Comic.db", null, 1);

        dbComic.queryData("CREATE TABLE IF NOT EXISTS User(idUser integer PRIMARY KEY AUTOINCREMENT, username text, password text )");

        View view = inflater.inflate(R.layout.fragment_register, container, false);
        final EditText usernameText = view.findViewById(R.id.reg_username);
        final EditText passwordText = view.findViewById(R.id.reg_password);
        Button registButton = view.findViewById(R.id.button_register);
        TextView login = view.findViewById(R.id.link_login);


        registButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameText.getText().toString();
                String password = passwordText.getText().toString();
                listener.onRegistClicked(v, username, password);
                usernameText.setText("");
                passwordText.setText("");
                Toast.makeText(getContext(), "Success added new user", Toast.LENGTH_SHORT).show();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.toLogin();
            }
        });

        return view;

    }

}
