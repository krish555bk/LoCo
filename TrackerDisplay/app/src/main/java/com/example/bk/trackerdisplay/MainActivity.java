package com.example.bk.trackerdisplay;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    EditText u,p;
    Button b;
    private static final String TAG = DisplayActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        u=(EditText)findViewById(R.id.usertext);
        p=(EditText)findViewById(R.id.passtext);
        b=(Button)findViewById(R.id.getbtn);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginToFirebase();
            }
        });
    }
    public void loginToFirebase() {

        String email = u.getText().toString().trim();
        String password = p.getText().toString().trim();
        // Authenticate with Firebase and subscribe to updates
        FirebaseAuth.getInstance().signInWithEmailAndPassword(
                email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Intent intent=new Intent(getApplicationContext(),com.example.bk.trackerdisplay.DisplayActivity.class);
                    startActivity(intent);
                    Log.d(TAG, "firebase auth success");
                } else {

                    Log.d(TAG,"firebase auth failed");
                }
            }
        });
    }


}