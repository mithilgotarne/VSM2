package com.matrix.mithil.vsm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * A placeholder fragment containing a simple view.
 */
public class startupFragment extends Fragment {

    public startupFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_startup, container, false);

        final Button submit= (Button) rootView.findViewById(R.id.submit_button); // you have to use rootview object..
        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                submitPasscode();
            }
        });

        return rootView;
    }


    private void submitPasscode(){
        EditText e = (EditText) getView().findViewById(R.id.startupPasscode_editText);
        String passcode = e.getText().toString();
        if(passcode.equals(""))
            Toast.makeText(getContext(),"Enter passcode",Toast.LENGTH_SHORT).show();
        else if(passcode.equals("0000")){
            Intent i = new Intent(getActivity(), VSM.class);
            startActivity(i);

        }
        else
            Toast.makeText(getContext(),"Sorry! Wrong passcode.",Toast.LENGTH_SHORT).show();
    }
}
