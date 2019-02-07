package yaksok.dodream.com.jabango;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import yaksok.dodream.com.jabango.model.UserModel;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    Button login_btn,sign_btn;
    EditText email_edt,pw_edt;
    String uid;
    UserModel userModel;
    FirebaseUser firebaseUser;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    String sex = "";



    private FirebaseAuth.AuthStateListener stateListener;//로그인 성공 했으니까 다른 창으로 이동시켜주는 리스
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        mAuth = FirebaseAuth.getInstance();
        mAuth.signOut();

        firebaseUser = mAuth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();

        databaseReference = firebaseDatabase.getReference("user");

        login_btn = (Button)findViewById(R.id.login_btn);
        sign_btn = (Button)findViewById(R.id.sign_btn);
        email_edt = (EditText)findViewById(R.id.id_edt);
        pw_edt = (EditText)findViewById(R.id.pw_edt);

        sign_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),SignUP.class));
            }
        });



        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!email_edt.getText().toString().isEmpty() && !pw_edt.getText().toString().isEmpty() ){
                    eventLogin(email_edt.getText().toString(),pw_edt.getText().toString());
                    Toast.makeText(getApplicationContext(),sex,Toast.LENGTH_SHORT).show();
                }
                else if(firebaseUser == null){

                    startActivity(new Intent(getApplicationContext(),SignUP.class));

                }

            }
        });
        stateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user!=null){//로그인
                    Intent intent = new Intent(LoginActivity.this,SelectDormitory.class);
                    startActivity(intent);
                    finish();

                }else{//로그아웃

                }
            }
        };

    }

    private void eventLogin(String email,String pw) {
        mAuth.signInWithEmailAndPassword(email,pw).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                uid = task.getResult().getUser().getUid();

                if(!task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),task.getException().toString(),Toast.LENGTH_SHORT).show();
                }
                else{

                    databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            UserModel userModel =  dataSnapshot.child(uid).getValue(UserModel.class);
                            assert userModel != null;
                            Log.d("usermodel",userModel.getSex());
                            sex = userModel.getSex();
                            moveToMain();

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });









                }
            }
        });


    }

    private void moveToMain() {
        if(sex.equals("male")){
            Intent intent = new Intent(LoginActivity.this,SelectDormitory.class);
            startActivity(intent);
        }
        else if(sex.equals("female")){

            Intent intent = new Intent(LoginActivity.this,Female_Simgeon.class);
            startActivity(intent);
        }

    }

}
