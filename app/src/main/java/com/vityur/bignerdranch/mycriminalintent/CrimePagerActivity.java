package com.vityur.bignerdranch.mycriminalintent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;

import java.util.List;
import java.util.UUID;

/**
 * Created by Vitaly on 10.04.2017.
 */

public class CrimePagerActivity extends FragmentActivity {
    private ViewPager mViewPager;
    private List<Crime> mCrimes;

    private static final String EXTRA_CRIME_ID =
            "com.vityur.bignerdranch.mycriminalintent.extra_crime_id";

    public static final int RESULT_CHANGES_DONE = 10;
    public static final int RESULT_NO_CHANGES = 20;

    public static Intent newIntent(Context context, UUID id) {
        Intent intent = new Intent(context, CrimePagerActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, id);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime_pager);

        UUID id = (UUID) getIntent()
                .getSerializableExtra(EXTRA_CRIME_ID);

        mViewPager = (ViewPager) findViewById(R.id.activity_crime_pager_view_pager);
        mCrimes = CrimeLab.get(getApplicationContext())
                .getCrimes();

        FragmentManager fm = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fm) {
            @Override
            public Fragment getItem(int position) {
                Crime crime = mCrimes.get(position);
                return CrimeFragment.newInstance(crime.getId());
            }

            @Override
            public int getCount() {
                return mCrimes.size();
            }
        });

        for (int i = 0; i < mCrimes.size(); i++) {
            if (mCrimes.get(i).getId().equals(id)) {
                mViewPager.setCurrentItem(i);
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            setResult(RESULT_NO_CHANGES);
        }
        return super.onKeyDown(keyCode, event);
    }
}
