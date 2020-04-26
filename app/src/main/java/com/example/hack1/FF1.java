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
public class FF1 extends Fragment {
    TextView t1;
    ImageButton i;
    TextToSpeech textToSpeech;

    public FF1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_ff1, container, false);
        i = (ImageButton) v.findViewById(R.id.i);
        t1 = (TextView) v.findViewById(R.id.t1);



        textToSpeech = new TextToSpeech(getContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int lang = textToSpeech.setLanguage(Locale.ENGLISH);
                }
            }
        });
        i.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1 = t1.getText().toString();
                int speech1 = textToSpeech.speak(s1, TextToSpeech.QUEUE_FLUSH, null);
            }
        });

        return v;
    }


}
