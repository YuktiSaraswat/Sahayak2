package com.example.hack1;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 */
public class C5 extends Fragment {

    TextView t1,t2,t3,t4,t5,t6;
    ImageButton i;
    TextToSpeech textToSpeech;
    public C5() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_c5, container, false);
        i = (ImageButton)v.findViewById(R.id.i);
        t1 = (TextView)v.findViewById(R.id.t1);
        t2 = (TextView)v.findViewById(R.id.t2);
        t3 = (TextView)v.findViewById(R.id.t3);
        t4 = (TextView)v.findViewById(R.id.t4);
        t5 = (TextView)v.findViewById(R.id.t5);
        textToSpeech=new TextToSpeech(getContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status==TextToSpeech.SUCCESS)
                {
                    int lang=textToSpeech.setLanguage(Locale.ENGLISH);
                }
            }
        });
        i.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1=t1.getText().toString()+"."+t2.getText().toString()+"..."+t3.getText().toString()+".."+t4.getText().toString()+"..."+t5.getText().toString();
                int speech1=textToSpeech.speak(s1,TextToSpeech.QUEUE_FLUSH,null);
                /*String s2=t2.getText().toString();
                int speech2=textToSpeech.speak(s2,TextToSpeech.QUEUE_FLUSH,null);
                String s3=t3.getText().toString();
                int speech3=textToSpeech.speak(s3,TextToSpeech.QUEUE_FLUSH,null);
                String s4=t4.getText().toString();
                int speech4=textToSpeech.speak(s4,TextToSpeech.QUEUE_FLUSH,null);
                String s5=t5.getText().toString();
                int speech=textToSpeech.speak(s5,TextToSpeech.QUEUE_FLUSH,null);*/
            }
        });



        return v;
    }

}
