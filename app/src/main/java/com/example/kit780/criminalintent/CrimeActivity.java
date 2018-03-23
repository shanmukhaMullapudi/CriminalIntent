package com.example.kit780.criminalintent;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;

import java.util.UUID;

public class CrimeActivity extends SingleFragmentActivity {

    public static final String EXTRA_CRIME_ID="crime_id";
    @Override
    protected Fragment createFragment() {
        //return new CrimeFragment();
        UUID crimeid=(UUID)getIntent().getSerializableExtra(EXTRA_CRIME_ID);
        return CrimeFragment.newInstance(crimeid);
    }
    public static Intent newIntent(Context packageContext,UUID crimeId){
        Intent i=new Intent(packageContext,CrimeActivity.class);
        i.putExtra(EXTRA_CRIME_ID,crimeId);
        return i;
    }


}
