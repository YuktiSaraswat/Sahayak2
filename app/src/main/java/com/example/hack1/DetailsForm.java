package com.example.hack1;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

public class DetailsForm extends AppCompatActivity
{
    EditText txtname,txtage,txtmail,txtphn,txtaddress,txtcity,txtstate,txtother,txtRname,txtRmail,txtRphn;
    Button btnsubmit,ch;
    ImageView img;
    StorageReference mStorageRef;
    private StorageTask uploadTask;
    public Uri imguri;
    DatabaseReference reff;


    Member member;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_form);

        mStorageRef= FirebaseStorage.getInstance().getReference("Imagea");

        txtname=(EditText)findViewById(R.id.txtname);
        txtage=(EditText)findViewById(R.id.txtage);
        txtmail=(EditText)findViewById(R.id.txtmail);
        txtphn=(EditText)findViewById(R.id.txtphn);
        txtaddress=(EditText)findViewById(R.id.txtaddress);
        txtcity=(EditText)findViewById(R.id.txtcity);
        txtstate=(EditText)findViewById(R.id.txtstate);
        txtother=(EditText)findViewById(R.id.txtother);
        txtRname=(EditText)findViewById(R.id.txtRname);
        txtRmail=(EditText)findViewById(R.id.txtRmail);
        txtRphn=(EditText)findViewById(R.id.txtRphn);
        btnsubmit=(Button)findViewById(R.id.btnsubmit);
        ch=(Button)findViewById(R.id.btnimage);
        img=(ImageView)findViewById(R.id.imgview);
        member=new Member();
        reff= FirebaseDatabase.getInstance().getReference().child("Member");
        ch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Filechooser();
            }
        });
        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(uploadTask!=null && uploadTask.isInProgress())
                {
                    Toast.makeText(DetailsForm.this, "Upload in Progress", Toast.LENGTH_LONG).show();
                }
                else {
                    Fileuploader();
                }
                    int age = Integer.parseInt(txtage.getText().toString().trim());
                    Long phn = Long.parseLong(txtphn.getText().toString().trim());
                    Long rphn = Long.parseLong(txtRphn.getText().toString().trim());

                    member.setName(txtname.getText().toString().trim());
                    member.setAge(age);
                    member.setEmail(txtmail.getText().toString().trim());
                    member.setPhone(phn);
                    member.setAddress(txtaddress.getText().toString().trim());
                    member.setCity(txtcity.getText().toString().trim());
                    member.setState(txtstate.getText().toString().trim());
                    member.setOther(txtother.getText().toString().trim());
                    member.setRName(txtRname.getText().toString().trim());
                    member.setRPhone(rphn);
                    member.setREmail(txtRmail.getText().toString().trim());
                    reff.push().setValue(member);
                    Toast.makeText(DetailsForm.this, "Record added Successfully", Toast.LENGTH_LONG).show();
                    String[] TO_EMAILS = {"yukti.saraswat99@gmail.com", "divyaahuja1432@gmail.com", "harshitasaxena012@gmail.com"};
                    Intent i = new Intent(Intent.ACTION_SENDTO);
                    i.setData(Uri.parse("mailto:"));
                    i.putExtra(Intent.EXTRA_EMAIL, TO_EMAILS);
                    i.putExtra(Intent.EXTRA_SUBJECT, "Enquiry for " + txtRname.getText().toString().trim());
                    i.putExtra(Intent.EXTRA_TEXT, txtRname.getText().toString().trim() + "\n" + txtage.getText().toString().trim()+ "\n" + txtother.getText().toString().trim() + "\nIf found then Contact: " + txtname.getText().toString().trim() + "\n" + txtphn.getText().toString().trim() + "\n" + txtaddress.getText().toString().trim() + "," + txtcity.getText().toString().trim() + "," + txtstate.getText().toString().trim());
                    startActivity(Intent.createChooser(i, "Choose one application"));

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 && resultCode==RESULT_OK && data!=null && data.getData()!=null)
        {
            imguri=data.getData();
            img.setImageURI(imguri);
        }
    }
    public String getExtension(Uri uri)
    {
        ContentResolver cr=getContentResolver();
        MimeTypeMap mimeTypeMap=MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(cr.getType(uri));
    }
    public void Fileuploader()
    {
        StorageReference Ref=mStorageRef.child(System.currentTimeMillis()+"."+getExtension(imguri));
        uploadTask=Ref.putFile(imguri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Get a URL to the uploaded content
                        //Uri downloadUrl = taskSnapshot.getDownloadUrl();
                        Toast.makeText(DetailsForm.this, "Image Uploaded Successfully", Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                        // ...
                    }
                });
    }

    public void Filechooser()
    {
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,1);
    }
}
