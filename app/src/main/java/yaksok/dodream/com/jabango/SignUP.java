package yaksok.dodream.com.jabango;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import yaksok.dodream.com.jabango.model.UserModel;

public class SignUP extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText name_edt,email_edt,pw_edt1,pw_edt2;
    private Button ok_btn;
    private RadioButton woman_btn,man_btn;
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    FirebaseUser firebaseUser;
    String uid;
    String sex = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        name_edt = (EditText)findViewById(R.id.name_edt);
        email_edt = (EditText)findViewById(R.id.email_edt);
        pw_edt1 = (EditText)findViewById(R.id.pw_edt1);
        pw_edt2 = (EditText)findViewById(R.id.pw_edt2);
        ok_btn = (Button)findViewById(R.id.ok_btn);

        mAuth = FirebaseAuth.getInstance();

        woman_btn = (RadioButton)findViewById(R.id.women);
        woman_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sex = "female";
            }
        });
        man_btn = (RadioButton)findViewById(R.id.man);

        man_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sex = "male";

            }
        });

        ok_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


               if(checkEmail(email_edt.getText().toString())&&isValidPw(pw_edt1.getText().toString())){
                   createAccount(email_edt.getText().toString(),pw_edt1.getText().toString());
               }
               else{
                   Toast.makeText(SignUP.this, "작성하신 정보에 문제가 있습니다.", Toast.LENGTH_SHORT).show();

               }
            }
        });


    }

    public void createAccount(final String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        firebaseUser = mAuth.getCurrentUser();
                          uid = task.getResult().getUser().getUid();
                        UserModel userModel = new UserModel();
                        userModel.setName(name_edt.getText().toString());
                        userModel.setEmail(email_edt.getText().toString());
                        userModel.setPw(pw_edt1.getText().toString());
                        userModel.setSex(sex);
                        userModel.setUid(uid);

                        database.getReference("user").child(uid).setValue(userModel).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                startActivity(new Intent(getApplicationContext(),Input_user_information.class));
                                finish();
                            }
                        });
    }


});
    }
    public  boolean isValidPw(String pw){
        boolean returnValue = false;

        String regex = "^[a-zA-Z0-9!@.#$%^&*?_~]{7,12}$";


        Pattern p = Pattern.compile(regex);

        Matcher m = p.matcher(pw);

        if (m.matches()) {
            returnValue = true;

        }

        return returnValue;


    }
    public  boolean checkEmail(String email){
        boolean returnValue = false;

        String regex = "^[_a-zA-Z0-9-\\.]+@[\\.a-zA-Z0-9-]+\\.[a-zA-Z]+$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(email);
        if (m.matches()) return true;
        else return false;

    }
}


