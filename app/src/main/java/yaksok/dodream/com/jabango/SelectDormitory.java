package yaksok.dodream.com.jabango;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SelectDormitory extends AppCompatActivity {
    public Button simgeon1_btn,simgeon2_btn;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseUser firebaseUser;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.male_select_dormitory);

//        firebaseUser = mAuth.getCurrentUser();
//        firebaseDatabase = FirebaseDatabase.getInstance();
//
//        databaseReference = firebaseDatabase.getReference("user");


        simgeon1_btn = (Button)findViewById(R.id.simjeon1_btn);
        simgeon2_btn = (Button)findViewById(R.id.simjeon2_btn);

        simgeon1_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ChoiceLayer.class));
            }
        });

        simgeon2_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ChoiceLayer.class));
            }
        });
    }
}
