package com.example.kit780.criminalintent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;

/**
 * Created by KIT780 on 3/21/2018.
 */

public abstract class SingleFragmentActivity extends FragmentActivity {
    public static String TAG="activity";
    protected abstract Fragment createFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG,"onCreate SingleFragmentActivity");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        FragmentManager fm=getSupportFragmentManager();
        Fragment f=fm.findFragmentById(R.id.fragment_container);
        if(f==null) {
            f= createFragment();
            fm.beginTransaction().add(R.id.fragment_container,f).commit();
        }
    }


}
