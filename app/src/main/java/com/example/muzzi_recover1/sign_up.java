package com.example.muzzi_recover1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class sign_up extends AppCompatActivity {
    private EditText name;
    private EditText password;
    String userid;
    private EditText email;
    private EditText favourite_subject;
    private Button register;
    private TextView login;
    private FirebaseAuth firebaseAuth;
       FirebaseFirestore firestore;
       ProgressBar prgbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        firebaseAuth=FirebaseAuth.getInstance();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        prgbar=(findViewById(R.id.progress_bar));

        name=( EditText)findViewById(R.id.etname);
      password=( EditText)findViewById(R.id.etpassword);
        email=( EditText)findViewById(R.id.etemail);
        favourite_subject=( EditText)findViewById(R.id.etfav_subject);
        register=(Button)findViewById(R.id.btnlogin);
        login=(TextView) findViewById(R.id.tvlogin);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    final String username= name.getText().toString().trim();
                    String userpassword= password.getText().toString().trim();
                    final String useremail= email.getText().toString().trim();
                    final String user_fav_sub= favourite_subject.getText().toString().trim();
                    if(username.isEmpty()||userpassword.isEmpty()||useremail.isEmpty()||user_fav_sub.isEmpty())
                    {
                        Toast.makeText(sign_up.this,"please enter all the fields",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        prgbar.setVisibility(View.VISIBLE);

                        firebaseAuth.createUserWithEmailAndPassword(useremail, userpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful())
                                {
                                    Toast.makeText(sign_up.this,"user created",Toast.LENGTH_SHORT).show();
                                    userid=firebaseAuth.getCurrentUser().getUid();
                                    DocumentReference doc_ref=firestore.collection("users").document(userid);
                                    Map<String,Object> user=new HashMap<>();
                                    user.put("fname",username);
                                    user.put("email",useremail);
                                    user.put("favourite subject",user_fav_sub);
                                    doc_ref.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            //Log.d(Tag,"onSuccess:user profile is created"+userid);

                                        }
                                    });
                                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                }
                                else
                                {
                                    Toast.makeText(sign_up.this,"Error!"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                                    prgbar.setVisibility(View.GONE);
                                }

                            }
                        });
                    }


            }
        });


    }
}
