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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class DetailsForm extends AppCompatActivity
{
    EditText txtname,txtage,txtmail,txtphn,txtaddress,txtcity,txtstate,txtother,txtRname,txtRmail,txtRphn;
    Button btnsubmit,ch;
    ImageView img;
    StorageReference mStorageRef;
    private StorageTask uploadTask;
    public Uri imguri;
    DatabaseReference reff;
    FirebaseAuth mfirebaseAuth;
    FirebaseFirestore fstore;
    DatabaseReference databaseReference;


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
        mfirebaseAuth=FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("details");
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
                String txtname1 = txtname.getText().toString().trim();
                String txtage1 = txtage.getText().toString().trim();
                String txtmail1 = txtmail.getText().toString().trim();
                String txtphn1 = txtphn.getText().toString().trim();
                String txtaddress1 = txtaddress.getText().toString().trim();
                String txtcity1 = txtcity.getText().toString().trim();
                String txtstate1 = txtstate.getText().toString().trim();
                String txtother1 = txtother.getText().toString().trim();
                String txtRname1 = txtRname.getText().toString().trim();
                String txtRmail1 = txtRmail.getText().toString().trim();
                String txtRphn1 = txtRphn.getText().toString().trim();
                if (txtname1.isEmpty()) {
                    txtname.setError("Please Enter Name");
                    txtname.requestFocus();
                    return;
                }
                if (txtage1.isEmpty()) {
                    txtage.setError("Please Enter Age");
                    txtage.requestFocus();
                    return;
                }
                if (txtmail1.isEmpty()) {
                    txtmail.setError("Please Enter your E-mail Address");
                    txtmail.requestFocus();
                    return;
                }
                if (txtphn1.isEmpty()) {
                    txtphn.setError("Please Enter Your Phone No.");
                    txtphn.requestFocus();
                    return;
                }
                if (txtaddress1.isEmpty()) {
                    txtaddress.setError("Please Enter Your Address");
                    txtaddress.requestFocus();
                    return;
                }
                if (txtcity1.isEmpty()) {
                    txtcity.setError("Please Enter Your City");
                    txtcity.requestFocus();
                    return;
                }
                if (txtstate1.isEmpty()) {
                    txtstate.setError("Please Enter Your State");
                    txtstate.requestFocus();
                    return;
                }
                if (txtother1.isEmpty()) {
                    txtother.setError("Please Enter Other Details");
                    txtother.requestFocus();
                    return;
                }
                if (txtRname1.isEmpty()) {
                    txtRname.setError("Please Enter Your Name");
                    txtRname.requestFocus();
                    return;
                }
                if (txtRmail1.isEmpty()) {
                    txtRmail.setError("Please Enter Your Mail");
                    txtRmail.requestFocus();
                    return;
                }
                if (txtRphn1.isEmpty()) {
                    txtRphn.setError("Please Enter Mobile Number");
                    txtRphn.requestFocus();
                    return;
                }
                String id = databaseReference.push().getKey();
                Relative relative=new Relative(id,txtname1,txtage1,txtmail1,txtphn1,txtaddress1,txtcity1,txtstate1,txtother1,txtRname1,txtRmail1,txtRphn1);
                databaseReference.child(id).setValue(relative);
                Toast.makeText(DetailsForm.this, "Information Submitted Successfully!", Toast.LENGTH_SHORT).show();
                Intent intents = new Intent(DetailsForm.this, MainActivity.class);
                startActivity(intents);

                if(uploadTask!=null && uploadTask.isInProgress())
                {
                    Toast.makeText(DetailsForm.this, "Upload in Progress", Toast.LENGTH_LONG).show();
                }
                else {
                    Fileuploader();
                }
                   /* int age = Integer.parseInt(txtage.getText().toString().trim());
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
                    Toast.makeText(DetailsForm.this, "Record added Successfully", Toast.LENGTH_LONG).show();*/
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
