package com.vityur.bignerdranch.mycriminalintent;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class CrimeActivity extends SingleFragmentActivity {

    @Override
    Fragment createFragment() {
        return new CrimeFragment();
    }
}
