package com.havayi.weekdays.activities;

import android.support.annotation.IdRes;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;

import com.havayi.weekdays.R;
import com.havayi.weekdays.fragments.NotifyFragment;
import com.havayi.weekdays.fragments.PagerFragment;
import com.havayi.weekdays.fragments.RecViewFragment;
import com.havayi.weekdays.models.Day;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String RECVIEW_FRAGMENT_TAG = "rect_view";
    private static final String PAGER_FRAGMENT_TAG = "pager_fragment";
    private static final String NOTIFY_FRAGMENT_TAG = "notify_fragment";

    private RadioGroup mRadioGroup;
    private FragmentManager mFragmentManager;
    private Day[] mDays;
    private int currentPage;
    private int mCurrentOutAnimation;

    private ArrayList<Integer> initColors(){
        ArrayList<Integer> result = new ArrayList<>();
        result.add(getResources().getColor(R.color.color_monday));
        result.add(getResources().getColor(R.color.color_tuesday));
        result.add(getResources().getColor(R.color.color_wednesday));
        result.add(getResources().getColor(R.color.color_thursday));
        result.add(getResources().getColor(R.color.color_friday));
        result.add(getResources().getColor(R.color.color_saturday));
        result.add(getResources().getColor(R.color.color_sunday));
        return result;
    }
    private ArrayList<String> initNames(){
        ArrayList<String> result = new ArrayList<>();
        result.add("Monday");
        result.add("Tuesday");
        result.add("Wednesday");
        result.add("Thursday");
        result.add("Friday");
        result.add("Saturday");
        result.add("Sunday");
        return result;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRadioGroup = (RadioGroup) findViewById(R.id.radio_group);
        mFragmentManager = getSupportFragmentManager();

        initDays();
        initListeners();
    }

    private void initListeners(){
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId){
                    case R.id.radio_recview:
                        getCurrentAnimationType();
                        openRecyclerView();
                        currentPage = R.id.radio_recview;
                        break;
                    case R.id.radio_pager:
                        getCurrentAnimationType();
                        openPager();
                        currentPage = R.id.radio_pager;
                        break;
                    case R.id.radio_notify:
                        getCurrentAnimationType();
                        openNotify();
                        currentPage = R.id.radio_notify;
                        break;
                }
            }
        });
    }

    private void initDays(){
        ArrayList<Integer> dayColors = initColors();
        ArrayList<String> dayNames = initNames();
        mDays = new Day[7];
        for (int i = 0; i <mDays.length; i++){
            mDays[i] = new Day(dayColors.get(i), dayNames.get(i));
        }
    }

    private void getCurrentAnimationType(){
        switch (currentPage){
            case R.id.radio_recview:
                mCurrentOutAnimation = R.anim.anim_recview_out;
                break;
            case R.id.radio_pager:
                mCurrentOutAnimation = R.anim.anim_pager_out;
                break;
            case R.id.radio_notify:
                mCurrentOutAnimation = R.anim.anim_notify_out;
                break;
        }
    }

    private void openRecyclerView(){
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        RecViewFragment recViewFragment = (RecViewFragment) mFragmentManager.findFragmentByTag(RECVIEW_FRAGMENT_TAG);
        if(recViewFragment == null)
            recViewFragment = new RecViewFragment();
        recViewFragment.setDays(mDays);
        fragmentTransaction.setCustomAnimations(R.anim.anim_recview_in, mCurrentOutAnimation);
        fragmentTransaction.replace(R.id.frame_layout, recViewFragment, RECVIEW_FRAGMENT_TAG);
        fragmentTransaction.commit();
    }

    private void openNotify(){
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        NotifyFragment notifyFragment = (NotifyFragment) mFragmentManager.findFragmentByTag(NOTIFY_FRAGMENT_TAG);
        if(notifyFragment == null)
            notifyFragment = new NotifyFragment();
        fragmentTransaction.setCustomAnimations(R.anim.anim_notify_in, mCurrentOutAnimation);
        fragmentTransaction.replace(R.id.frame_layout, notifyFragment, NOTIFY_FRAGMENT_TAG);
        fragmentTransaction.commit();
    }

    private void openPager(){
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        PagerFragment pagerFragment = (PagerFragment) mFragmentManager.findFragmentByTag(PAGER_FRAGMENT_TAG);
        if(pagerFragment == null)
            pagerFragment = new PagerFragment();
        pagerFragment.setDays(mDays);
        fragmentTransaction.setCustomAnimations(R.anim.anim_pager_in, mCurrentOutAnimation);
        fragmentTransaction.replace(R.id.frame_layout, pagerFragment, PAGER_FRAGMENT_TAG);
        fragmentTransaction.commit();
    }
}
