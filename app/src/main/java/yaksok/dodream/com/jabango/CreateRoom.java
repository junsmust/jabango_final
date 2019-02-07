package yaksok.dodream.com.jabango;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import yaksok.dodream.com.jabango.model.Roomate;
import yaksok.dodream.com.jabango.model.UserDetail;
import yaksok.dodream.com.jabango.model.UserModel;

public class CreateRoom extends AppCompatActivity implements View.OnClickListener {
    String room_number ="";
    FirebaseDatabase firebaseDatabase;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    DatabaseReference databaseReference,databaseReference2;
    UserModel userModel;
    TextView roomate1,roomate2,roomate3,roomate4;
    ArrayList<TextView> roomates = new ArrayList<>();
    Roomate roomate;
    boolean isAlreadyExists_1 = false;
    int user_count;
    UserDetail userDetail;
    DatabaseReference databaseReference1;
    ArrayList<Integer> roomate_count = new ArrayList<>();
    Button exit_btn;
    int outPerson ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_room);
        Intent intent = getIntent();
        room_number = intent.getStringExtra("room_number");



//        roomate1 = (TextView)findViewById(R.id.roomate_1);
//        roomate2 = (TextView)findViewById(R.id.roomate_2);
//        roomate3 = (TextView)findViewById(R.id.roomate_3);
//        roomate4 = (TextView)findViewById(R.id.roomate_4);

        roomate1 = (TextView)findViewById(R.id.roomate_1);
        roomate2 = (TextView)findViewById(R.id.roomate_2);
        roomate3 = (TextView)findViewById(R.id.roomate_3);
        roomate4 = (TextView)findViewById(R.id.roomate_4);

        roomates.add(roomate1);
        roomates.add(roomate2);
        roomates.add(roomate3);
        roomates.add(roomate4);

        roomate1.setOnClickListener(this);
        roomate2.setOnClickListener(this);
        roomate3.setOnClickListener(this);
        roomate4.setOnClickListener(this);



        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        databaseReference = firebaseDatabase.getReference("user");

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                userModel =  dataSnapshot.child(firebaseUser.getUid()).getValue(UserModel.class);
                assert userModel != null;
                Log.d("usermodel",userModel.getName());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        databaseReference = firebaseDatabase.getReference("userInfo").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
//                String getkey = databaseReference1.getKey();

                Toast.makeText(getApplicationContext(), ""+dataSnapshot.getChildrenCount(),Toast.LENGTH_SHORT).show();
                for(DataSnapshot snapShot : dataSnapshot.getChildren()){
                    if(!snapShot.exists()){
                        return;
                    }else{
                        userDetail = dataSnapshot.getValue(UserDetail.class);
                        Log.d("name",userDetail.getSt_name());

                    }

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        if(Integer.parseInt(room_number)<=1116){
            final DatabaseReference databaseReference1 = firebaseDatabase.getReference("room").child(room_number);

            databaseReference1.addListenerForSingleValueEvent(new ValueEventListener() {
                ArrayList<Roomate> userModels = new ArrayList<>();
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
//                String getkey = databaseReference1.getKey();
                    userModels.clear();
                    Toast.makeText(getApplicationContext(), ""+dataSnapshot.getChildrenCount(),Toast.LENGTH_SHORT).show();
                    for(DataSnapshot snapShot : dataSnapshot.getChildren()){
                        roomate = dataSnapshot.child(String.valueOf(user_count)).getValue(Roomate.class);
                        userModels.add(roomate);

                    }
                    if(userModels.isEmpty()){
                        roomate1.setText("1");
                        roomate2.setText("2");
                        roomate3.setText("3");
                        roomate4.setText("4");
                        return;
                    }
                    else {
                        for (int i = 0; i < userModels.size(); i++) {
                            roomates.get(i).setText(userDetail.getSt_name()+"\n"+userDetail.getSt_major()+"\n"+userDetail.getSt_number());
                        }

                    }


                    user_count = Integer.parseInt(String.valueOf(dataSnapshot.getChildrenCount()));

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }
        else if(Integer.parseInt(room_number)<=2214){
            final DatabaseReference databaseReference2 = firebaseDatabase.getReference("room_2").child(room_number);

            databaseReference2.addListenerForSingleValueEvent(new ValueEventListener() {
                ArrayList<Roomate> userModels = new ArrayList<>();
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
//                String getkey = databaseReference1.getKey();
                    userModels.clear();
                    Toast.makeText(getApplicationContext(), ""+dataSnapshot.getChildrenCount(),Toast.LENGTH_SHORT).show();
                    for(DataSnapshot snapShot : dataSnapshot.getChildren()){
                        roomate = dataSnapshot.child(String.valueOf(user_count)).getValue(Roomate.class);
                        userModels.add(roomate);

                    }
                    if(userModels.isEmpty()){
                        roomate1.setText("1");
                        roomate2.setText("2");
                        roomate3.setText("3");
                        roomate4.setText("4");
                        return;
                    }
                    else {
                        for (int i = 0; i < userModels.size(); i++) {


                            roomates.get(i).setText(userDetail.getSt_name()+"\n"+userDetail.getSt_major()+"\n"+userDetail.getSt_number());
                        }

                    }


                    user_count = Integer.parseInt(String.valueOf(dataSnapshot.getChildrenCount()));

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


        }


        exit_btn = (Button)findViewById(R.id.exit_btn);



            Button back_btn = (Button)findViewById(R.id.back_btn);
        back_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(),Simjeon_First_Layer.class);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            });

            final Button getIntoRoom = (Button)findViewById(R.id.getIntoRoom);
        getIntoRoom.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onClick(View v) {

                    roomate = new Roomate();
                    roomate.setName(userModel.getName());
                    roomate.setEmail(userModel.getEmail());
                    userModel.setRoom_num(room_number);
                    roomate.setRoom_number(userModel.getRoom_num());
                    if(user_count==4){

                        Toast.makeText(getApplicationContext(),"방이 꽉 찼습니다.",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        if(Integer.parseInt(room_number)>=2201){
                            FirebaseDatabase.getInstance().getReference("room_2").child(room_number).child(String.valueOf(user_count)).setValue(roomate);
                            update_room_secon_layer();
                            updateAccessRoom2();
                        }
                        else {
                            FirebaseDatabase.getInstance().getReference("room").child(room_number).child(String.valueOf(user_count)).setValue(roomate);
                            updateAccessRoom();
                        }

                        if(roomate1.getText().toString().length()==1){
                            roomate1.setText(userDetail.getSt_name()+"\n"+userDetail.getSt_major()+"\n"+userDetail.getSt_number());
                        }else if(roomate2.getText().toString().length()==1){
                            roomate2.setText(userDetail.getSt_name()+"\n"+userDetail.getSt_major()+"\n"+userDetail.getSt_number());
                        }else if(roomate3.getText().toString().length()==1){
                            roomate3.setText(userDetail.getSt_name()+"\n"+userDetail.getSt_major()+"\n"+userDetail.getSt_number());
                        }else if(roomate4.getText().toString().length()==1){
                            roomate4.setText(userDetail.getSt_name()+"\n"+userDetail.getSt_major()+"\n"+userDetail.getSt_number());
                        }

                    }


//
                }
            });
        }

        private void update_room_secon_layer() {
            final DatabaseReference databaseReference2 = FirebaseDatabase.getInstance().getReference("room_2").child(room_number);

            databaseReference2.addListenerForSingleValueEvent(new ValueEventListener() {
                ArrayList<Roomate> userModels = new ArrayList<>();
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
//                String getkey = databaseReference1.getKey();
                    userModels.clear();
                    Toast.makeText(getApplicationContext(), ""+dataSnapshot.getChildrenCount(),Toast.LENGTH_SHORT).show();
                    for(DataSnapshot snapShot : dataSnapshot.getChildren()){
                        roomate = dataSnapshot.child(String.valueOf(user_count)).getValue(Roomate.class);
                        userModels.add(roomate);

                    }
                    if(userModels.isEmpty()){
                        roomate1.setText("1");
                        roomate2.setText("2");
                        roomate3.setText("3");
                        roomate4.setText("4");
                        return;
                    }
                    else {
                        for (int i = 0; i < userModels.size(); i++) {
                            roomates.get(i).setText(userDetail.getSt_name()+"\n"+userDetail.getSt_major()+"\n"+userDetail.getSt_number());
                        }

                    }


                    user_count = Integer.parseInt(String.valueOf(dataSnapshot.getChildrenCount()));

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


        }

        private void updateAccessRoom() {
            for(int i = 0; i < Simjeon_First_Layer.room_num.size();i++){
                databaseReference1 = firebaseDatabase.getReference("room");
                databaseReference1.addListenerForSingleValueEvent(new ValueEventListener() {
                    ArrayList<Integer> userModels = new ArrayList<>();
                    ArrayList<Roomate> roomates = new ArrayList<>();
                    int childCount;
                    @SuppressLint("ResourceAsColor")
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
//                String getkey = databaseReference1.getKey();
                        userModels.clear();
                        for(DataSnapshot snapShot : dataSnapshot.getChildren()){
                            childCount = (int)dataSnapshot.getChildrenCount();
                            userModels.add(childCount);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
            for(int i = 0 ; i <Simjeon_First_Layer.room_num.size(); i++){
                databaseReference1.child(String.valueOf(Simjeon_First_Layer.room_num.get(i))).addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        int roomateCount = 0;
                        for(DataSnapshot snapShot : dataSnapshot.getChildren()){
                            roomateCount = (int)dataSnapshot.getChildrenCount();
                        }
                        roomate_count.add(roomateCount);
                        Log.d("roma_mate_count",""+roomate_count+"\n");

                        for(int j = 0; j < roomate_count.size(); j++){
                            if(roomate_count.get(j)==0){
                                Simjeon_First_Layer.textLists.get(j).setTextColor(getResources().getColor(R.color.black));
                                Simjeon_First_Layer.textLists.get(j).setBackgroundColor(getResources().getColor(R.color.white));
                            }else if(roomate_count.get(j)==1){
                                Simjeon_First_Layer.textLists.get(j).setTextColor(getResources().getColor(R.color.white));
                                Simjeon_First_Layer.textLists.get(j).setBackgroundColor(getResources().getColor(R.color.yellow));
                            }
                            else if(roomate_count.get(j)==2){
                                Simjeon_First_Layer.textLists.get(j).setTextColor(getResources().getColor(R.color.white));
                                Simjeon_First_Layer.textLists.get(j).setBackgroundColor(getResources().getColor(R.color.lightGreen));
                            }else if(roomate_count.get(j)==3){
                                Simjeon_First_Layer.textLists.get(j).setTextColor(getResources().getColor(R.color.white));
                                Simjeon_First_Layer.textLists.get(j).setBackgroundColor(getResources().getColor(R.color.redyellow));
                            } else if(roomate_count.get(j)==4){
                                Simjeon_First_Layer.textLists.get(j).setTextColor(getResources().getColor(R.color.white));
                                Simjeon_First_Layer.textLists.get(j).setBackgroundColor(getResources().getColor(R.color.red));
                            }
                        }

                    }


                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }

        }

        //데이터베이스에 저장된 차일드를 가져와 카운트를 한 다음에 방의 갯수에 맞게 카운트가 0,1,2,3,4로 나누어서 현재 사용하고 있는 인원들을 색깚 표시로 보여줌
        private void updateAccessRoom2() {
            for(int i = 0; i < Simjeon_First_Layer.room_num.size();i++){
                databaseReference1 = firebaseDatabase.getReference("room_2_layer");
                databaseReference1.addListenerForSingleValueEvent(new ValueEventListener() {
                    ArrayList<Integer> userModels = new ArrayList<>();
                    ArrayList<Roomate> roomates = new ArrayList<>();
                    int childCount;
                    @SuppressLint("ResourceAsColor")
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
//                String getkey = databaseReference1.getKey();
                        userModels.clear();
                        for(DataSnapshot snapShot : dataSnapshot.getChildren()){
                            childCount = (int)dataSnapshot.getChildrenCount();
                            userModels.add(childCount);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
            for(int i = 0 ; i <Simjeon_First_Layer.room_num.size(); i++){
                databaseReference1.child(String.valueOf(Simjeon_First_Layer.room_num.get(i))).addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        int roomateCount = 0;
                        for(DataSnapshot snapShot : dataSnapshot.getChildren()){
                            roomateCount = (int)dataSnapshot.getChildrenCount();
                        }
                        roomate_count.add(roomateCount);
                        Log.d("roma_mate_count",""+roomate_count+"\n");

                        for(int j = 0; j < roomate_count.size(); j++){
                            if(roomate_count.get(j)==0){
                                Simjeon_Second_Layer.textLists.get(j).setTextColor(getResources().getColor(R.color.black));
                                Simjeon_Second_Layer.textLists.get(j).setBackgroundColor(getResources().getColor(R.color.white));
                            }else if(roomate_count.get(j)==1){
                                Simjeon_Second_Layer.textLists.get(j).setTextColor(getResources().getColor(R.color.white));
                                Simjeon_Second_Layer.textLists.get(j).setBackgroundColor(getResources().getColor(R.color.yellow));
                            }
                            else if(roomate_count.get(j)==2){
                                Simjeon_Second_Layer.textLists.get(j).setTextColor(getResources().getColor(R.color.white));
                                Simjeon_Second_Layer.textLists.get(j).setBackgroundColor(getResources().getColor(R.color.lightGreen));
                            }else if(roomate_count.get(j)==3){
                                Simjeon_Second_Layer.textLists.get(j).setTextColor(getResources().getColor(R.color.white));
                                Simjeon_Second_Layer.textLists.get(j).setBackgroundColor(getResources().getColor(R.color.redyellow));
                            } else if(roomate_count.get(j)==4){
                                Simjeon_Second_Layer.textLists.get(j).setTextColor(getResources().getColor(R.color.white));
                                Simjeon_Second_Layer.textLists.get(j).setBackgroundColor(getResources().getColor(R.color.red));
                            }
                        }

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
                case R.id.roomate_1:
                    outPerson = 0;
                    FirebaseDatabase.getInstance().getReference("room").child(room_number).child(String.valueOf(outPerson)).setValue(null);

                    Log.d("count",""+ roomate_count.size());
                    break;
                case R.id.roomate_2:
                    outPerson = 1;
                    FirebaseDatabase.getInstance().getReference("room").child(room_number).child(String.valueOf(outPerson)).setValue(null);

                    Log.d("count",""+ roomate_count.size());
                    break;

                case R.id.roomate_3:
                    outPerson = 2;
                    FirebaseDatabase.getInstance().getReference("room").child(room_number).child(String.valueOf(outPerson)).setValue(null);
                    break;
                case R.id.roomate_4:
                    outPerson = 3;
                    FirebaseDatabase.getInstance().getReference("room").child(room_number).child(String.valueOf(outPerson)).setValue(null);
                    break;
            }



        }

    }


