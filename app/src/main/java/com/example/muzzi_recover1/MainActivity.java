package com.example.muzzi_recover1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private EditText name;

    private EditText password;
    private CheckBox remember_me;
    private Button go;
    private TextView signup;
    private int counter =5;
    //private  FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name=(EditText)findViewById(R.id.etname) ;
        password=(EditText)findViewById(R.id.etpassword) ;
        remember_me=(CheckBox)findViewById(R.id.cbremeber_me) ;
        go=(Button)findViewById(R.id.btnlogin);
       // firebaseAuth=FirebaseAuth.getInstance();
        progressDialog =new ProgressDialog(this);
       /* FirebaseUser user= firebaseAuth.getCurrentUser();
        if(user!=null){
            finish();;
           startActivity(new Intent(MainActivity.this,welcome.class));

        }*/
        signup=(TextView)findViewById(R.id.tvsignup) ;
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,sign_up.class));
            }
        });
       go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(name.getText().toString(),password.getText().toString());
            }
        });

    }
    private void validate(String username ,String password)
    {
        progressDialog.setMessage("welcome to my academic world");
        progressDialog.show();
        if((username.equals("sarmesh"))&& (password.equals("rajawat"))) {
            Intent intent = new Intent(MainActivity.this, welcome.class);
            startActivity(intent);
        }
      /* firebaseAuth.signInWithEmailAndPassword(username,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
           @Override
           public void onComplete(@NonNull Task<AuthResult> task) {
              if(task.isSuccessful()){
                  progressDialog.dismiss();
                  Toast.makeText(MainActivity.this,"login successful",Toast.LENGTH_SHORT).show();
                  startActivity(new Intent(MainActivity.this,welcome.class));
              }
              else
              {
                  Toast.makeText(MainActivity.this,"login failed",Toast.LENGTH_SHORT).show();
                counter--;
                  progressDialog.dismiss();
                if(counter==0)
                {
                    go.setEnabled(false);
                }
              }

           }
       });*/
    }
}
