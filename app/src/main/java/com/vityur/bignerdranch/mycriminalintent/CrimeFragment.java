package com.vityur.bignerdranch.mycriminalintent;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import java.util.UUID;

/**
 * Created by Vitaly on 05.04.2017.
 */

public class CrimeFragment extends Fragment {

    private static final String ARG_CRIME =
            "arg_crime";

    private static final int RESULT_CHANGES_DONE = 10;

    private Crime mCrime;
    private EditText mCrimeTitleEditText;
    private Button mCrimeDateTimeButton;
    private CheckBox mCrimeSolvedCheckBox;
    private Button mCrimeSubmitChangesButton;

    public static CrimeFragment newInstance(UUID crimeId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_CRIME, crimeId);

        CrimeFragment fragment = new CrimeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        UUID id = (UUID) getArguments().
                getSerializable(ARG_CRIME);

        mCrime = CrimeLab.get(getActivity()).
                getCrime(id);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.crime_fragment, container, false);

        mCrimeTitleEditText = (EditText) view.findViewById(R.id.crime_title_edit);
        mCrimeTitleEditText.setText(mCrime.getTitle());
        mCrimeTitleEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //
            }

            @Override
            public void afterTextChanged(Editable s) {
                //
            }
        });

        mCrimeDateTimeButton = (Button) view.findViewById(R.id.crime_date_time_button);
        mCrimeDateTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // dialog view
            }
        });
        mCrimeDateTimeButton.setText(mCrime.getDate().toString());

        mCrimeSolvedCheckBox = (CheckBox) view.findViewById(R.id.crime_solved_checkbox);
        mCrimeSolvedCheckBox.setChecked(mCrime.isSolved());
        mCrimeSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // mCrime.setSolved(isChecked);
            }
        });

        mCrimeSubmitChangesButton = (Button) view.findViewById(R.id.crime_submit_changes_button);
        mCrimeSubmitChangesButton.setText(R.string.crime_submit_changes);
        mCrimeSubmitChangesButton.setClickable(true);
        mCrimeSubmitChangesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCrime.setTitle(mCrimeTitleEditText.getText().toString());
                // mCrime.setDate();
                mCrime.setSolved(mCrimeSolvedCheckBox.isChecked());
                final Intent intent = new Intent(getContext(), CrimeListActivity.class);
                getActivity().setResult(CrimeActivity.RESULT_CHANGES_DONE, intent);

                mCrimeSubmitChangesButton.setText("Submitted!");
                mCrimeSubmitChangesButton.setClickable(false);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(intent);
                    }
                }, 1000);
            }
        });


        return view;


    }
}
