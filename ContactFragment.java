package com.example.beathunger1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ContactFragment extends Fragment implements View.OnClickListener {
    ImageButton imgbtnCall, imgbtnEmail, imgbtnWeb;

    public ContactFragment() {


    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_contact, container, false);
        imgbtnCall = v.findViewById(R.id.call_button);
        imgbtnEmail = v.findViewById(R.id.email_button);
        imgbtnWeb = v.findViewById(R.id.web_button);
        imgbtnWeb.setOnClickListener(this);
        imgbtnCall.setOnClickListener(this);
        imgbtnEmail.setOnClickListener(this);
        return v;


    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.call_button:
                Toast.makeText(getActivity(), "Call Button", Toast.LENGTH_SHORT).show();

                Intent callIntent = new Intent (Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel: 0123355661"));
                if(callIntent.resolveActivity(getActivity().getPackageManager())!=null){
                    startActivity(callIntent);
                }
                else{
                    Toast.makeText(getActivity(), "Sorry, there is no app can handle this action and data", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.email_button:
                Toast.makeText(getActivity(), "Email Button", Toast.LENGTH_SHORT).show();
                Intent emailIntent = new Intent (Intent.ACTION_SEND);
                emailIntent.setType("text/plain");
                emailIntent.putExtra(Intent.EXTRA_SUBJECT,"YOUR QUESTION ABOUT US");
                emailIntent.putExtra(Intent.EXTRA_TEXT,"Email message: What would you like to ask?");
                emailIntent.putExtra(Intent.EXTRA_EMAIL,new String[]{"fooddonation@company.com"});
                if(emailIntent.resolveActivity(getActivity().getPackageManager())!=null){
                    startActivity(emailIntent);
                }
                else{
                    Toast.makeText(getActivity(), "Sorry, there is no app can handle this action and data", Toast.LENGTH_SHORT).show();
                }

                break;

            case R.id.web_button:
                Toast.makeText(getActivity(), "Search Button", Toast.LENGTH_SHORT).show();
                Uri webpage = Uri.parse("http://www.google.com");
                Intent webIntent = new Intent (Intent.ACTION_VIEW,webpage);
                if(webIntent.resolveActivity(getActivity().getPackageManager())!=null){
                    startActivity(webIntent);
                }
                else{
                    Toast.makeText(getActivity(), "Sorry, there is no app can handle this action and data", Toast.LENGTH_SHORT).show();
                }
                break;

        }
    }
}