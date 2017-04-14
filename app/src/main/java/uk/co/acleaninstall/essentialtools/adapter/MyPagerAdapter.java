package uk.co.acleaninstall.essentialtools.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import uk.co.acleaninstall.essentialtools.fragment.CommandFragment;
import uk.co.acleaninstall.essentialtools.fragment.GamesFragment;
import uk.co.acleaninstall.essentialtools.fragment.ToolsFragment;

public class MyPagerAdapter extends FragmentPagerAdapter {

    // Our tab titles
    private String tabTitles[] = new String[] { "Tools", "Games", "Command" };


    public MyPagerAdapter(FragmentManager manager) {
        super(manager);
    }


    @Override
    public Fragment getItem(int position) {

        // Return a fragment according to the position
        switch (position) {
            case 0:
                return ToolsFragment.newInstance(0);
            case 1:
                return GamesFragment.newInstance(1);
            case 2:
                return CommandFragment.newInstance(2);
            default:
                return null;
        }

    }


    @Override
    public int getCount() {
        // How many fragments to return
        return 3;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        // Return our tab titles
        return tabTitles[position];
    }
}
