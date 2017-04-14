package uk.co.acleaninstall.essentialtools.viewpager;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import uk.co.acleaninstall.essentialtools.R;
import uk.co.acleaninstall.essentialtools.activity.MainActivity;

@SuppressWarnings("deprecation") public class MyPageChangeListener
    implements ViewPager.OnPageChangeListener {

    private int[] colorsToMerge;
    private ArgbEvaluator argbEvaluator;

    private Toolbar mToolbar;
    private TabLayout mTabLayout;

    private static final String TAG = "MyPageChangeListener";


    public MyPageChangeListener(Context context) {

        // Get ref to our Main Activity Layout
        MainActivity activity = (MainActivity) context;

        this.mToolbar = activity.mToolbar;
        this.mTabLayout = activity.mTabLayout;
        this.colorsToMerge = new int[] { context.getResources().getColor(R.color.colorPrimary),
            Color.parseColor("#FF4A148C"), Color.parseColor("#FFB71C1C") };
        this.argbEvaluator = new ArgbEvaluator();
    }


    private void setToolbarAndTabsColor(int colorToSet) {

        mToolbar.setBackgroundColor(colorToSet);
        mTabLayout.setBackgroundColor(colorToSet);
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (position < (3 - 1) && position < (colorsToMerge.length - 1)) {

            setToolbarAndTabsColor(
                (Integer) argbEvaluator.evaluate(positionOffset, colorsToMerge[position],
                    colorsToMerge[position + 1]));

        } else {

            setToolbarAndTabsColor(colorsToMerge[colorsToMerge.length - 1]);

        }
    }


    @Override
    public void onPageSelected(int position) {
        Log.v(TAG, "Page " + String.valueOf(position) + " selected");
    }


    @Override
    public void onPageScrollStateChanged(int state) {

    }

}
