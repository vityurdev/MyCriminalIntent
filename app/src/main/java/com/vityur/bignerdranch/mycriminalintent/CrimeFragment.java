package com.vityur.bignerdranch.mycriminalintent;

import android.os.Bundle;
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

/**
 * Created by Vitaly on 05.04.2017.
 */

public class CrimeFragment extends Fragment {

    private Crime mCrime;
    private EditText mCrimeTitleEditText;
    private Button mCrimeDateTimeButton;
    private CheckBox mCrimeSolvedCheckBox;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mCrime = new Crime();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.crime_fragment, container, false);

        mCrimeTitleEditText = (EditText) view.findViewById(R.id.crime_title_edit);
        mCrimeTitleEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mCrime.setTitle(s.toString());
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
        mCrimeSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mCrime.setSolved(isChecked);
            }
        });

        return view;


    }
}
