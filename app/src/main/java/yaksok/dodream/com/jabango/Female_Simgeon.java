package yaksok.dodream.com.jabango;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Female_Simgeon extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.female_select_dormitory);

        Button simjeon_1 = (Button)findViewById(R.id.f_simjeon1_btn);
        Button simjeon_2 = (Button)findViewById(R.id.f_simjeon2_btn);

        simjeon_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Female_simjeon_1.class));
            }
        });

        simjeon_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Female_simjeon_2.class));
            }
        });
    }

}
