package yaksok.dodream.com.jabango;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import yaksok.dodream.com.jabango.adapter.MyAdapter;
import yaksok.dodream.com.jabango.fragment.FragmentSimjeon2;
import yaksok.dodream.com.jabango.fragment.MyFragment;

public class ShowFacility extends AppCompatActivity {

    private StorageReference mStorageRef;
    private FirebaseStorage storage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_facility);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        MyAdapter adapter = new MyAdapter(getSupportFragmentManager());
        adapter.addFragment(new MyFragment(), "심전 1 관");
        adapter.addFragment(new FragmentSimjeon2(), "심전 2 관");

        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }
}
