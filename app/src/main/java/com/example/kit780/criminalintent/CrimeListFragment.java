package com.example.kit780.criminalintent;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public class CrimeListFragment extends Fragment {
    //

    private RecyclerView mRecyclerView;
    private CrimeAdapter mAdapter;

    private class CrimeHolder extends RecyclerView.ViewHolder
    implements View.OnClickListener{
        private TextView mTitleTextView,mDateTextView;
        private CheckBox mSolvedCheckBox;
        private Crime mCrime;

        public void onClick(View v){
            //Toast.makeText(getActivity(),mCrime.getTitle(),Toast.LENGTH_SHORT).show();
            Intent crimeIntent=CrimeActivity.newIntent(getActivity(),mCrime.getId());
            startActivity(crimeIntent);
        }
        public CrimeHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mTitleTextView = (TextView) itemView.findViewById(R.id.list_item_crime_title_text_view);
            mDateTextView=(TextView)itemView.findViewById(R.id.list_item_crime_date_text_view);
            mSolvedCheckBox=(CheckBox)itemView.findViewById(R.id.list_item_crime_solved_checkbox);
        }

        public void bindCrime(Crime crime){
            mCrime=crime;
            mTitleTextView.setText(mCrime.getTitle());
            mDateTextView.setText(mCrime.getDate().toString());
            mSolvedCheckBox.setChecked(mCrime.isSolved());
        }
    }
    private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder>{
        private List<Crime> mCrimes;
        public CrimeAdapter(List<Crime> crimeList){
            mCrimes=crimeList;
        }

        @Override
        public void onBindViewHolder(CrimeHolder holder, int position) {
                Crime crime=mCrimes.get(position);
                holder.bindCrime(crime);
        }

        @Override
        public CrimeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater lf=LayoutInflater.from(getActivity());
            View v=lf.inflate(R.layout.list_item_crime,parent,false);
            return new CrimeHolder(v);
        }

        @Override
        public int getItemCount() {
            return mCrimes.size();
        }

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View v=inflater.inflate(R.layout.fragment_crime_list,container,false);
        RecyclerView.LayoutManager lm=new LinearLayoutManager(getActivity());
        mRecyclerView=(RecyclerView)v.findViewById(R.id.crime_recycler_view);
        mRecyclerView.setLayoutManager(lm);
        updateUI();
        return v;
    }
    private void updateUI(){
        CrimeLab crimeLab=CrimeLab.get(getActivity());
        List<Crime> crimes=crimeLab.getCrimes();
        mAdapter=new CrimeAdapter(crimes);
        mRecyclerView.setAdapter(mAdapter);
    }

}
