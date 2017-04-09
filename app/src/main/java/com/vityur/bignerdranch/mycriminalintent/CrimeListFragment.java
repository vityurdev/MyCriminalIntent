package com.vityur.bignerdranch.mycriminalintent;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Vitaly on 05.04.2017.
 */

public class CrimeListFragment extends Fragment {
    private static final int REQUEST_CHANGES_DONE_OR_NOT = 12345;

    private RecyclerView mCrimeRecyclerView;
    private CrimeAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.crime_list_fragment, container, false);

        mCrimeRecyclerView = (RecyclerView) view.findViewById(R.id.crime_list_recycler_view);
        mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }


    private void updateUI() {
        CrimeLab mCrimeLab = CrimeLab.get(getActivity());
        List<Crime> crimeList = mCrimeLab.getCrimes();

        if (mAdapter == null) {
            mAdapter = new CrimeAdapter(crimeList);
            mCrimeRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {
            return;
        }

        if (requestCode == REQUEST_CHANGES_DONE_OR_NOT & resultCode == CrimeActivity.RESULT_CHANGES_DONE) {
            updateUI();
        }
    }

    private class CrimeHolder extends RecyclerView.ViewHolder {
        TextView mCrimeTitleTextView;
        TextView mCrimeDateTimeTextView;
        CheckBox mCrimeSolvedCheckBox;
        Crime mCrime;

        public CrimeHolder(View itemView) {
            super(itemView);

            mCrimeTitleTextView = (TextView) itemView.findViewById(R.id.list_item_crime_title);
            mCrimeDateTimeTextView = (TextView) itemView.findViewById(R.id.list_item_crime_date);
            mCrimeSolvedCheckBox = (CheckBox) itemView.findViewById(R.id.list_item_crime_solved_checkbox);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = CrimeActivity.newIntent(getActivity(), mCrime.getId());
                    startActivityForResult(intent, REQUEST_CHANGES_DONE_OR_NOT);
                }
            });
        }

        private void bindCrime(Crime crime) {
            mCrime = crime;
            mCrimeTitleTextView.setText(crime.getTitle());
            mCrimeDateTimeTextView.setText(crime.getDate().toString());
            mCrimeSolvedCheckBox.setChecked(crime.isSolved());
        }
    }

    private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder> {

        private List<Crime> mCrimes;

        public CrimeAdapter(List<Crime> crimes) {
            mCrimes = crimes;
        }

        @Override
        public CrimeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_view, parent, false);
            return new CrimeHolder(view);
        }

        @Override
        public void onBindViewHolder(CrimeHolder holder, int position) {
            Crime crime = mCrimes.get(position);
            holder.bindCrime(crime);
        }

        @Override
        public int getItemCount() {
            return mCrimes.size();
        }
    }
}
