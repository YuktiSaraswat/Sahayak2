package com.example.hack1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;


import java.util.ArrayList;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_SPEECH_INPUT = 1000;
    Button b1,b2,b3,b4,b7,b8;
    ImageButton bs;
    TextView ts;
    TextToSpeech textToSpeech;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1=(Button)findViewById(R.id.b1);
        b2=(Button)findViewById(R.id.b2);
        b4=(Button)findViewById(R.id.b4) ;
        b3=(Button)findViewById(R.id.b3);

        bs=(ImageButton)findViewById(R.id.bs);
        ts=(TextView)findViewById(R.id.ts);
        textToSpeech=new TextToSpeech(getApplicationContext(),new TextToSpeech.OnInitListener(){

            @Override
            public void onInit(int status) {
                if(status==TextToSpeech.SUCCESS)
                {
                    int lang=textToSpeech.setLanguage(Locale.ENGLISH);
                }
            }
        });

        bs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                speak();
            }
        });

}

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    ts.setText(result.get(0));
                }
                break;
            }
        }
        String sr=ts.getText().toString();
        if(sr.contentEquals("earthquake")){
            Intent intente = new Intent(MainActivity.this, Earthquake.class);
            startActivity(intente);
        }
        else if(sr.contentEquals("floods") || sr.contentEquals("flood")){
            Intent intentf = new Intent(MainActivity.this, Floods.class);
            startActivity(intentf);
        }
        else if(sr.contentEquals("donate")){
            Intent intentd = new Intent(MainActivity.this, Donate.class);
            startActivity(intentd);
        }
        else if(sr.contentEquals("flash flood") || sr.contentEquals("flash floods")){
            Intent intente = new Intent(MainActivity.this, Flash_Floods.class);
            startActivity(intente);
        }
        else if(sr.contentEquals("landslide") || sr.contentEquals("landslides")){
            Intent intentf = new Intent(MainActivity.this, Floods.class);
            startActivity(intentf);
        }
        else if(sr.contentEquals("cloudburst")){
            Intent intentd = new Intent(MainActivity.this, Cloudburst.class);
            startActivity(intentd);
        }
        else if(sr.contentEquals("avalanche")){
            Intent intentd = new Intent(MainActivity.this, Avalanche.class);
            startActivity(intentd);
        }
        else if(sr.contentEquals("chemical disaster") || sr.contentEquals("chemical disasters")){
            Intent intente = new Intent(MainActivity.this, Chemical.class);
            startActivity(intente);
        }
        else if(sr.contentEquals("nuclear disaster") || sr.contentEquals("nuclear disasters")){
            Intent intentf = new Intent(MainActivity.this, Nuclear.class);
            startActivity(intentf);
        }
        else if(sr.contentEquals("biological disaster") || sr.contentEquals("biological disasters")) {
            Intent intentd = new Intent(MainActivity.this, Biological.class);
            startActivity(intentd);
        }
        else if(sr.contentEquals("accept")) {
            Intent intentd = new Intent(MainActivity.this, Login.class);
            startActivity(intentd);
        }
        else if(sr.contentEquals("emergency call") ) {
            String no="9760955555";
            Intent i=new Intent(Intent.ACTION_CALL);
            i.setData(Uri.parse("tel:"+no));
            if(ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED)
            {

            }
            startActivity(i);
        }
        else if(sr.contentEquals("map")) {
            Intent intentd = new Intent(MainActivity.this, GoogleMapDemo.class);
            startActivity(intentd);
        }
        else if(sr.contentEquals("register as NGO")) {
            Intent intentd = new Intent(MainActivity.this, NGO.class);
            startActivity(intentd);
        }
        else if(sr.contentEquals("register as volunteer")) {
            Intent intentd = new Intent(MainActivity.this, Volunteers.class);
            startActivity(intentd);
        }
        else if(sr.contentEquals("search for relative") || sr.contentEquals("search for relatives")){
            Intent intentf = new Intent(MainActivity.this, DetailsForm.class);
            startActivity(intentf);
        }
        else{
            Toast.makeText(this, "Invalid Search", Toast.LENGTH_SHORT).show();
        }
    }
    public void ecall(View view)
    {
        String no="9760955555";
        Intent i=new Intent(Intent.ACTION_CALL);
        i.setData(Uri.parse("tel:"+no));
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED)
        {

        }
        startActivity(i);
    }



    public void speak() {
        Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        i.putExtra(RecognizerIntent.EXTRA_PROMPT, "Which page u want to search?");
        try {
            startActivityForResult(i, REQUEST_CODE_SPEECH_INPUT);

        } catch (Exception e) {
            Toast.makeText(this, "" + e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu1,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.M1:
                Toast.makeText(this, "Map", Toast.LENGTH_SHORT).show();
                Intent intentm = new Intent(MainActivity.this, GoogleMapDemo.class);
                startActivity(intentm);
                return false;
            case R.id.M2:
                Toast.makeText(this, "Home Page", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.M3_1_1:
                Intent intent = new Intent(MainActivity.this, Earthquake.class);
                startActivity(intent);
                return false;
            case R.id.M3_1_2:
                Intent intent1 = new Intent(MainActivity.this, Floods.class);
                startActivity(intent1);
                return false;
            case R.id.M3_1_3:
                Intent intent2 = new Intent(MainActivity.this, Landslides.class);
                startActivity(intent2);
                return false;
            case R.id.M3_1_4:
                Intent intent3 = new Intent(MainActivity.this, Cloudburst.class);
                startActivity(intent3);
                return false;
            case R.id.M3_1_5:
                Intent intent4 = new Intent(MainActivity.this, Avalanche.class);
                startActivity(intent4);
                return false;
            case R.id.M3_1_6:
                Intent intent5 = new Intent(MainActivity.this, Flash_Floods.class);
                startActivity(intent5);
                return false;
            case R.id.M3_2_1:
                Intent intent6 = new Intent(MainActivity.this, Chemical.class);
                startActivity(intent6);
                return false;
            case R.id.M3_2_2:
                Intent intent7 = new Intent(MainActivity.this, Nuclear.class);
                startActivity(intent7);
                return false;
            case R.id.M3_2_3:
                Intent intent8 = new Intent(MainActivity.this, Biological.class);
                startActivity(intent8);
                return false;
            case R.id.M4:
                Toast.makeText(this, "Notification", Toast.LENGTH_SHORT).show();
                Intent intent9 = new Intent(MainActivity.this, Guidelines.class);
                startActivity(intent9);
                return true;
            case R.id.M5:
                Toast.makeText(this, "Notification", Toast.LENGTH_SHORT).show();
                Intent intent10= new Intent(MainActivity.this, Contact.class);
                startActivity(intent10);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void donate(View view)
    {
        Intent intentd = new Intent(MainActivity.this, Donate.class);
        startActivity(intentd);
    }
    public void signup(View view)
    {
        Intent intents = new Intent(MainActivity.this, SignUp.class);
        startActivity(intents);
    }
    public void accept(View view)
    {
        Intent intenta = new Intent(MainActivity.this, Accept.class);
        startActivity(intenta);
    }
    public void login(View view)
    {
        Intent intentl = new Intent(MainActivity.this, Login.class);
        startActivity(intentl);
    }
    public void relative(View view)
    {
        Intent intentn = new Intent(MainActivity.this, SendEmail.class);
        startActivity(intentn);
    }
    public void relative1(View view)
    {
        Intent intentn = new Intent(MainActivity.this, DetailsForm.class);
        startActivity(intentn);
    }
    public void nGO(View view)
    {
        Intent intentn = new Intent(MainActivity.this, NGO.class);
        startActivity(intentn);
    }
    public void volunteer(View view)
    {
        Intent intentp = new Intent(MainActivity.this, Volunteers.class);
        startActivity(intentp);
    }



}
