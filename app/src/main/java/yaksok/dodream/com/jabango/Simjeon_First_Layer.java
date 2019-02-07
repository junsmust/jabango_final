package yaksok.dodream.com.jabango;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import yaksok.dodream.com.jabango.model.Roomate;
import yaksok.dodream.com.jabango.model.UserDetail;
import yaksok.dodream.com.jabango.model.UserModel;

public class Simjeon_First_Layer extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    UserModel userModel;
    FirebaseAuth auth;
    FirebaseUser firebaseUser;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    TextView room_1001,room_1002;
    ImageView room1101,room1102,room1103,room1104,room1105,room1106,room1107,room1108,room1109,room1110,room1111,room1112,room1113,room1114,room1115,room1116;
    AlertDialog.Builder builder;
    String room_number = "1101";
    TextView r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16;
    public static  ArrayList<TextView> textLists = new ArrayList<>();
    public static  ArrayList<Integer> room_num = new ArrayList<>();
    DatabaseReference databaseReference1;
    String title;
    ArrayList<Integer> roomate_count = new ArrayList<>();
    UserDetail userDetail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simjeon_1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        userModel = new UserModel();

        auth = FirebaseAuth.getInstance();
        firebaseUser = auth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();

        databaseReference = firebaseDatabase.getReference("user");

        r1 = (TextView)findViewById(R.id.room_1_txt);
        r2 = (TextView)findViewById(R.id.room_2_txt);
        r3 = (TextView)findViewById(R.id.room_3_txt);
        r4 = (TextView)findViewById(R.id.room_4_txt);
        r5 = (TextView)findViewById(R.id.room_5_txt);
        r6 = (TextView)findViewById(R.id.room_6_txt);
        r7 = (TextView)findViewById(R.id.room_7_txt);
        r8 = (TextView)findViewById(R.id.room_8_txt);

        r9 = (TextView)findViewById(R.id.room_9_txt);
        r10 = (TextView)findViewById(R.id.room_10_txt);
        r11 = (TextView)findViewById(R.id.room_11_txt);
        r12 = (TextView)findViewById(R.id.room_12_txt);
        r13 = (TextView)findViewById(R.id.room_13_txt);
        r14 = (TextView)findViewById(R.id.room_14_txt);
        r15 = (TextView)findViewById(R.id.room_15_txt);
        r16 = (TextView)findViewById(R.id.room_16_txt);

        textLists.add(r1);
        textLists.add(r2);
        textLists.add(r3);
        textLists.add(r4);
        textLists.add(r5);
        textLists.add(r6);
        textLists.add(r7);
        textLists.add(r8);
        textLists.add(r9);
        textLists.add(r10);
        textLists.add(r11);
        textLists.add(r12);
        textLists.add(r13);
        textLists.add(r14);
        textLists.add(r15);
        textLists.add(r16);

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

        for(int i = 0; i < room_num.size();i++){
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
        for(int i = 0 ; i < room_num.size(); i++){
            databaseReference1.child(String.valueOf(room_num.get(i))).addListenerForSingleValueEvent(new ValueEventListener() {

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
                            textLists.get(j).setTextColor(getResources().getColor(R.color.black));
                            textLists.get(j).setBackgroundColor(getResources().getColor(R.color.white));
                        }else if(roomate_count.get(j)==1){
                            textLists.get(j).setTextColor(getResources().getColor(R.color.white));
                            textLists.get(j).setBackgroundColor(getResources().getColor(R.color.yellow));
                        }else if(roomate_count.get(j)==2){
                            textLists.get(j).setTextColor(getResources().getColor(R.color.white));
                            textLists.get(j).setBackgroundColor(getResources().getColor(R.color.lightGreen));
                        }else if(roomate_count.get(j)==3){
                            Simjeon_First_Layer.textLists.get(j).setTextColor(getResources().getColor(R.color.white));
                            Simjeon_First_Layer.textLists.get(j).setBackgroundColor(getResources().getColor(R.color.redyellow));
                        }else if(roomate_count.get(j)==4){
                            textLists.get(j).setTextColor(getResources().getColor(R.color.white));
                            textLists.get(j).setBackgroundColor(getResources().getColor(R.color.red));
                        }
                    }

                }


                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View nav_header_view = navigationView.getHeaderView(0);

        final TextView user_name = (TextView)nav_header_view.findViewById(R.id.user_name_txt);
        final TextView user_major = (TextView)nav_header_view.findViewById(R.id.user_major_txt);
        final TextView user_number = (TextView)nav_header_view.findViewById(R.id.user_number_txt);


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

                        user_name.setText(userDetail.getSt_name());
                        user_major.setText(userDetail.getSt_major());
                        user_number.setText(userDetail.getSt_number());


                    }

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+userModel.getName());

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
              UserModel userModel =  dataSnapshot.child(firebaseUser.getUid()).getValue(UserModel.class);
                assert userModel != null;
               // Log.d("usermodel",userModel.getName());


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        getUserInfo();

        room1101 = (ImageView)findViewById(R.id.room_1101);
        room1102 = (ImageView)findViewById(R.id.room_1102);
        room1103 = (ImageView)findViewById(R.id.room_1103);
        room1104 = (ImageView)findViewById(R.id.room_1104);
        room1105 = (ImageView)findViewById(R.id.room_1105);
        room1106 = (ImageView)findViewById(R.id.room_1106);
        room1107 = (ImageView)findViewById(R.id.room_1107);
        room1108 = (ImageView)findViewById(R.id.room_1108);

        room1101.setOnClickListener(this);
        room1102.setOnClickListener(this);
        room1103.setOnClickListener(this);
        room1104.setOnClickListener(this);
        room1105.setOnClickListener(this);
        room1106.setOnClickListener(this);
        room1107.setOnClickListener(this);
        room1108.setOnClickListener(this);


        room1109 = (ImageView)findViewById(R.id.room_1109);
        room1110 = (ImageView)findViewById(R.id.room_1110);
        room1111 = (ImageView)findViewById(R.id.room_1111);
        room1112 = (ImageView)findViewById(R.id.room_1112);
        room1113 = (ImageView)findViewById(R.id.room_1113);
        room1114 = (ImageView)findViewById(R.id.room_1114);
        room1115 = (ImageView)findViewById(R.id.room_1115);
        room1116 = (ImageView)findViewById(R.id.room_1116);

        room1109.setOnClickListener(this);
        room1110.setOnClickListener(this);
        room1111.setOnClickListener(this);
        room1112.setOnClickListener(this);
        room1113.setOnClickListener(this);
        room1114.setOnClickListener(this);
        room1115.setOnClickListener(this);
        room1116.setOnClickListener(this);



    }

    private void getUserInfo() {

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.simjeon_1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.check_facility) {
            startActivity(new Intent(getApplicationContext(),ShowFacility.class));
        } else if (id == R.id.check_user_info) {
            startActivity(new Intent(getApplicationContext(),Input_user_information.class));

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.room_1101:{
                room_number = "1101";
                checkposiible(room_number);
                break;
            }

            case R.id.room_1102:
                room_number = "1102";
                checkposiible(room_number);
                break;
            case R.id.room_1103:
                room_number = "1103";
                checkposiible(room_number);
                break;
            case R.id.room_1104:
                room_number = "1104";
                checkposiible(room_number);
                break;
            case R.id.room_1105:
                room_number = "1105";
                checkposiible(room_number);
                break;
            case R.id.room_1106:
                room_number = "1106";
                checkposiible(room_number);
                break;
            case R.id.room_1107:
                room_number = "1107";
                checkposiible(room_number);
                break;
            case R.id.room_1108:
                room_number = "1108";
                checkposiible(room_number);
                break;
            case R.id.room_1109:
                room_number = "1109";
                checkposiible(room_number);
                break;
            case R.id.room_1110:
                room_number = "1110";
                checkposiible(room_number);
                break;
            case R.id.room_1111:
                room_number = "1111";
                checkposiible(room_number);
                break;
            case R.id.room_1112:
                room_number = "1112";
                checkposiible(room_number);
                break;
            case R.id.room_1113:
                room_number = "1113";
                checkposiible(room_number);
                break;
            case R.id.room_1114:
                room_number = "1114";
                checkposiible(room_number);
                break;
            case R.id.room_1115:
                room_number = "1115";
                checkposiible(room_number);
                break;
            case R.id.room_1116:
                room_number = "1116";
                checkposiible(room_number);
                break;


        }

    }
    public void makeDialog(){
        builder = new AlertDialog.Builder(this);
        builder.setTitle("방 고르기");
        builder.setMessage("방을 만드시겠습니까?");
        builder.setPositiveButton("네", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(getApplicationContext(),CreateRoom.class);
                intent.putExtra("room_number",room_number);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("아니요", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        Dialog dialog = builder.create();
        dialog.show();
    }
    public void isAlreadyExisted(){
        builder = new AlertDialog.Builder(this);
        builder.setTitle("방 고르기");
        builder.setMessage("이미 방이 만들어져 있습니다. \n 들어가시겠습니까 ?");
        builder.setPositiveButton("네", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(getApplicationContext(),CreateRoom.class);
                intent.putExtra("room_number",room_number);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("아니요", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        Dialog dialog = builder.create();
        dialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


            // 수행을 제대로 한 경우
            if(resultCode == RESULT_OK && data != null)
            {   Log.d("ddddddd","됨");
                Toast.makeText(getApplicationContext(),"컴백",Toast.LENGTH_SHORT).show();

            }
            // 수행을 제대로 하지 못한 경우
            else if(resultCode == RESULT_CANCELED)
            {
                Log.d("ddddddd","안됨");
            }



    }
    public void checkposiible(String room_number){

        databaseReference1.child(String.valueOf(room_number)).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.getChildrenCount()>=4){
                    Toast.makeText(getApplicationContext(),"방이 꽉 찼습니다.",Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(dataSnapshot.getChildrenCount()>=1){
                    isAlreadyExisted();
                }
                else if (dataSnapshot.getChildrenCount()==0){
                    makeDialog();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
