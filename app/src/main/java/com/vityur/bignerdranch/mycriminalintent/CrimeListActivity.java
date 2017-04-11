package com.vityur.bignerdranch.mycriminalintent;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

/**
 * Created by Vitaly on 05.04.2017.
 */

public class CrimeListActivity extends SingleFragmentActivity {
    @Override
    Fragment createFragment() {
        return new CrimeListFragment();
    }


}
