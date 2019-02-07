package yaksok.dodream.com.jabango;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

import yaksok.dodream.com.jabango.model.Roomate;
import yaksok.dodream.com.jabango.model.UserDetail;
import yaksok.dodream.com.jabango.model.UserModel;

public class Input_user_information extends AppCompatActivity {
    public EditText sn_name_ed,sn_nuber_ed,sn_account_ed,sn_chairman_major,sn_parent_name_1_ed,sn_parent_1_job,sn_parent_name_2_ed,sn_parent_2_job,
    sn_unable_name,sn_unable_rank;
    public String sn_major,sn_bank,sn_relation_1,sn_relation_2,apply_date,choice_dormi;
    public RadioButton unable_ok,unable_no;
    public LinearLayout linearLayout;
    public boolean isunable = false;
    public Button save;
    public Spinner major,bank,relation_1,relation_2,apply_year,choice_dormitory;
    private FirebaseAuth mAuth;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference;
    UserDetail userDetail;
    int spin_bank,spin_relation_1,spin_relation_2,spin_apply,spin_dorim,spin_major;
    String major_string;
    FirebaseUser firebaseUser;
    boolean isFirst = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_user_information);

//        String[] phone_company = getResources().getStringArray(R.array.phone_company);
//        ArrayAdapter<String> phoneCompanyAdapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,phone_company);
//        sign_up_phone_conpany_spin.setAdapter(phoneCompanyAdapter);
        sn_name_ed = (EditText)findViewById(R.id.sn_name_edt);
        sn_nuber_ed = (EditText)findViewById(R.id.sn_number_edt);
        sn_chairman_major = (EditText)findViewById(R.id.chairman_of_major_edt);
        sn_account_ed = (EditText)findViewById(R.id.sn_acount);
        sn_parent_name_1_ed = (EditText)findViewById(R.id.user_info_parents_name_edt1);
        sn_parent_1_job = (EditText)findViewById(R.id.user_info_parent_job_1);
        sn_parent_name_2_ed = (EditText)findViewById(R.id.user_info_parents_name_edt2);
        sn_parent_2_job = (EditText)findViewById(R.id.user_info_parent_job_2);
        sn_unable_name = (EditText)findViewById(R.id.user_unable_edt);
        sn_unable_rank = (EditText)findViewById(R.id.user_unable_rank_edt);
        linearLayout = (LinearLayout)findViewById(R.id.linearLayout);
        unable_no = (RadioButton)findViewById(R.id.unable_no);
        save = (Button)findViewById(R.id.complete_btn);
        databaseReference = database.getReference("userInfo").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
//                String getkey = databaseReference1.getKey();

                Toast.makeText(getApplicationContext(), ""+dataSnapshot.getChildrenCount(),Toast.LENGTH_SHORT).show();
                for(DataSnapshot snapShot : dataSnapshot.getChildren()){
                    if(!snapShot.exists()){
                        sn_name_ed.setFocusable(true);
                        return;
                    }else{
                        userDetail = dataSnapshot.getValue(UserDetail.class);
                        Log.d("name",userDetail.getSt_name());
                        sn_name_ed.setText(userDetail.getSt_name());
                        sn_nuber_ed.setText(userDetail.getSt_number());
                        major.setSelection(userDetail.getMajor_spin());
                        sn_chairman_major.setText(userDetail.getSt_major_chairman());
                        bank.setSelection(userDetail.getBank_spin());
                        sn_account_ed.setText(userDetail.getSt_account());
                        sn_parent_name_1_ed.setText(userDetail.getSt_parent_name_1());
                        relation_1.setSelection(userDetail.getSpin_reli_1());
                        sn_parent_1_job.setText(userDetail.getSt_parent_job_1());
                        sn_parent_name_2_ed.setText(userDetail.getSt_parent_name_2());
                        relation_2.setSelection(userDetail.getSpin_reli_2());
                        sn_parent_2_job.setText(userDetail.getSt_parent_job_2());
                        unable_no.setChecked(userDetail.isUnable());
                        unable_ok.setChecked(userDetail.isAble());
                        apply_year.setSelection(userDetail.getSpin_apply());
                        choice_dormitory.setSelection(userDetail.getSpin_dormi());
                    }

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        unable_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            isunable = true;
            }
        });
        unable_ok = (RadioButton)findViewById(R.id.unable_yes);
        unable_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayout.setVisibility(View.VISIBLE);
             isunable = true;
            }
        });



        major = (Spinner)findViewById(R.id.major_spin);
        String[] majors = getResources().getStringArray(R.array.major);
        ArrayAdapter<String> majorAdapter = new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,majors);
        major.setAdapter(majorAdapter);
        major.setSelection(0);
        major.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
             sn_major =  major.getItemAtPosition(position).toString();
             spin_major = major.getSelectedItemPosition();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        bank = (Spinner)findViewById(R.id.user_info_bank_spin);
        String[] banks = getResources().getStringArray(R.array.bank);
        ArrayAdapter<String> bankAdapter = new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,banks);
        bank.setAdapter(bankAdapter);
        bank.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spin_bank = bank.getSelectedItemPosition();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        relation_1 = (Spinner)findViewById(R.id.relation_parents_spin_1);
        relation_2 = (Spinner)findViewById(R.id.relation_parents_spin_2);

        String[] parents = getResources().getStringArray(R.array.parents);
        ArrayAdapter<String> parentAdapter = new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,parents);
        relation_1.setAdapter(parentAdapter);
        relation_2.setAdapter(parentAdapter);

        relation_1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sn_relation_1 = relation_1.getItemAtPosition(position).toString();
                spin_relation_1 = relation_1.getSelectedItemPosition();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        relation_2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sn_relation_2 = relation_2.getItemAtPosition(position).toString();
                spin_relation_2 = relation_2.getSelectedItemPosition();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        apply_year = (Spinner)findViewById(R.id.apply_date_dormitory_spin);
        choice_dormitory = (Spinner)findViewById(R.id.choose_dormitory_spin);

        String[] years = getResources().getStringArray(R.array.years);
        ArrayAdapter<String> yeatAdapter = new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,years);
        apply_year.setAdapter(yeatAdapter);
        apply_year.setSelection(0);
        apply_year.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                apply_date = apply_year.getItemAtPosition(position).toString();
                spin_apply = apply_year.getSelectedItemPosition();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        String[] dormitory = getResources().getStringArray(R.array.dormitory);
        ArrayAdapter<String> dormitoryAdapter = new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,dormitory);
        choice_dormitory.setAdapter(dormitoryAdapter);
        choice_dormitory.setSelection(0);
        choice_dormitory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                choice_dormi = choice_dormitory.getItemAtPosition(position).toString();
                spin_dorim = choice_dormitory.getSelectedItemPosition();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // UserModel userModel = new UserModel();
                userDetail = new UserDetail();

                userDetail.setSt_name(sn_name_ed.getText().toString());
                userDetail.setSt_number(sn_nuber_ed.getText().toString());
                userDetail.setMajor_spin(spin_major);
                userDetail.setSt_major_chairman(sn_chairman_major.getText().toString());
                userDetail.setBank_spin(spin_bank);
                userDetail.setSt_account(sn_account_ed.getText().toString());
                userDetail.setSt_parent_name_1(sn_parent_name_1_ed.getText().toString());
                userDetail.setSpin_reli_1(spin_relation_1);
                userDetail.setSt_parent_job_1(sn_parent_1_job.getText().toString());
                userDetail.setSt_parent_name_2(sn_parent_name_2_ed.getText().toString());
                userDetail.setSpin_reli_2(spin_relation_2);
                userDetail.setSt_parent_job_2(sn_parent_2_job.getText().toString());
                userDetail.setUnable(unable_ok.isChecked());
                userDetail.setAble(unable_no.isChecked());
                userDetail.setSpin_apply(spin_apply);
                userDetail.setSpin_dormi(spin_dorim);
                userDetail.setSt_major(sn_major);



                database.getReference("userInfo").child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid()).setValue(userDetail).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if(!isFirst){
                            Toast.makeText(getApplicationContext(),"저장 되었습니다.",Toast.LENGTH_SHORT).show();
                        }
                        else if(isFirst){
                            startActivity(new Intent(getApplicationContext(),SelectDormitory.class));
                            finish();
                            isFirst = false;
                        }



                    }
                });
            }
        });
    }
}
