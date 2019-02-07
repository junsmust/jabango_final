package yaksok.dodream.com.jabango;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ChoiceLayer extends AppCompatActivity implements View.OnClickListener{
    Button first_layer,second_layer,third_layer,fourth_layer;
    Intent intent;
    String title;

    FirebaseAuth auth;
    FirebaseUser firebaseUser;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference,databaseReference2;
    int avialableSum = 0,result = 0,avial=0;
    int secondLayer = 0, second_result = 0, avial_secon = 0;


    TextView first_available_people,first_already_people,first_all_people,second_available_people,second_already_people,second_all_people,
            third_available_people,third_already_people,third_all_people,fourth_available_people,fourth_already_people,fourth_all_people;


    ArrayList<Integer> room_num = new ArrayList<>();
    ArrayList<Integer> room_num_2 = new ArrayList<>();

    ArrayList<Integer> roomate_count = new ArrayList<>();
    ArrayList<Integer> roomate_count_2 = new ArrayList<>();
    ArrayList<TextView> textViews = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice_layer);

        auth = FirebaseAuth.getInstance();
        firebaseUser = auth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("room");
        databaseReference2 = FirebaseDatabase.getInstance().getReference("room_2");

        first_available_people = (TextView)findViewById(R.id.first_available_people);
        first_already_people = (TextView)findViewById(R.id.first_already_people);
        first_all_people = (TextView)findViewById(R.id.first_all_available_people);

        second_available_people = (TextView)findViewById(R.id.second_available_people);
        second_already_people = (TextView)findViewById(R.id.second_already_people);
        second_all_people = (TextView)findViewById(R.id.second_all_available_people);

        third_available_people = (TextView)findViewById(R.id.third_available_people);
        third_already_people = (TextView)findViewById(R.id.third_already_people);
        third_all_people = (TextView)findViewById(R.id.third_all_available_people);

        fourth_available_people = (TextView)findViewById(R.id.fourth_available_people);
        fourth_already_people = (TextView)findViewById(R.id.fourth_already_people);
        fourth_all_people = (TextView)findViewById(R.id.fourth_all_available_people);



        first_layer = (Button) findViewById(R.id.first_layer);
        first_layer.setOnClickListener(this);
        second_layer = (Button) findViewById(R.id.second_layer);
        second_layer.setOnClickListener(this);
        third_layer = (Button) findViewById(R.id.third_layer);
        third_layer.setOnClickListener(this);
        fourth_layer = (Button) findViewById(R.id.fourth_layer);
        fourth_layer.setOnClickListener(this);

        room_num.add(1101);
        room_num.add(1102);
        room_num.add(1103);
        room_num.add(1104);
        room_num.add(1105);
        room_num.add(1106);
        room_num.add(1107);
        room_num.add(1108);

        room_num.add(1109);
        room_num.add(1110);
        room_num.add(1111);
        room_num.add(1112);
        room_num.add(1113);
        room_num.add(1114);
        room_num.add(1115);
        room_num.add(1116);




        for(int j = 2201 ; j <= 2216 ; j++ ) {
            room_num_2.add(j);
        }

        for(int i = 0 ; i < room_num.size(); i++){
            databaseReference.child(String.valueOf(room_num.get(i))).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    int roomateCount = 0;
                    for(DataSnapshot snapShot : dataSnapshot.getChildren()){
                        roomateCount = (int)dataSnapshot.getChildrenCount();
                    }
                    roomate_count.add(roomateCount);
                    Log.d("roma_mate_count",""+roomate_count+"\n");

                    for(int j = 0; j < roomate_count.size(); j++){
                            roomateCount+=roomate_count.get(j);
                    }

                    first_already_people.setText(String.valueOf(roomateCount));
                    result = roomate_count.size()*4;
                    first_all_people.setText(String.valueOf(result));
                    avial = result-roomateCount;

                    first_available_people.setText(String.valueOf(avial));
                    }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }

        for(int t = 0; t < room_num_2.size(); t++){
            databaseReference2.child(String.valueOf(room_num_2.get(t))).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    int roomateCount_2 = 0;
                    for(DataSnapshot snapShot : dataSnapshot.getChildren()){
                        roomateCount_2 = (int)dataSnapshot.getChildrenCount();
                    }
                    Log.d("roomCon2",""+roomateCount_2);
                    roomate_count_2.add(roomateCount_2);
                    Log.d("roma_mate_count",""+roomate_count+"\n");

                    for(int j = 0; j < roomate_count_2.size(); j++){
                        roomateCount_2+=roomate_count_2.get(j);
                    }
                    System.out.println("!!!!!!!!!"+roomate_count_2.get(0));
                    second_already_people.setText(String.valueOf(roomateCount_2));
                    second_result = roomate_count.size()*4;
                    second_all_people.setText(String.valueOf(second_result));
                    avial_secon = second_result-roomateCount_2;
                    second_available_people.setText(String.valueOf(avial_secon));
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.first_layer:
                title = "first layer";
                intent = new Intent(getApplicationContext(),Simjeon_First_Layer.class);
                intent.putExtra("title",title);
                startActivity(intent);
                break;
            case R.id.second_layer:
                title = "seconde";
                intent = new Intent(getApplicationContext(),Simjeon_Second_Layer.class);
                intent.putExtra("title",title);
                startActivity(intent);
                break;

        }
    }
}
