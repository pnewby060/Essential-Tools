package uk.co.acleaninstall.essentialtools.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.PhoneStateListener;
import at.amartinz.execution.ShellManager;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.jrummyapps.android.shell.CommandResult;
import com.jrummyapps.android.shell.Shell;
import uk.co.acleaninstall.essentialtools.MyApp;
import uk.co.acleaninstall.essentialtools.R;
import uk.co.acleaninstall.essentialtools.adapter.MyPagerAdapter;
import uk.co.acleaninstall.essentialtools.listener.CustomCallStateListener;
import uk.co.acleaninstall.essentialtools.util.MiscTools;
import uk.co.acleaninstall.essentialtools.viewpager.MyPageChangeListener;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.toolbar)
    public Toolbar mToolbar;

    @BindView(R.id.tabLayout)
    public TabLayout mTabLayout;

    @BindView(R.id.viewpager)
    ViewPager mViewPager;

    public static final String Doof
        = "echo \"Android $(cat /system/build.prop | grep build.version.release | sed s/.*=//) ($(cat /system/build.prop | grep ro.build.host | sed s/.*=//) $(cat /system/build.prop | grep ro.modversion | sed s/.*=//))\\n$(cat /system/build.prop | grep ro.product.model | sed s/.*=//) ($(cat /system/build.prop | grep ro.product.device | sed s/#.*// | sed s/.*=// | tr -d \\n))\"";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        // Setup our Toolbar
        setupToolbar();

        // Setup our ViewPager with our TabLayout
        setupViewPager(mTabLayout);

        // Listen for Telephony changes
        MyApp.getTelephonyManager().listen(new CustomCallStateListener(),
            PhoneStateListener.LISTEN_CALL_STATE |
                PhoneStateListener.LISTEN_DATA_CONNECTION_STATE);

    }


    @Override protected void onDestroy() {

        // cleanup our console history
        ShellManager.get().cleanupShells();

        super.onDestroy();
    }


    private void setupToolbar() {

        setSupportActionBar(mToolbar);

        // push out toolbar down from no action bar theme value
        mToolbar.setPadding(0, MiscTools.getStatusBarHeight(), 0, 0);

        CommandResult result = Shell.SU.run(Doof);

        mToolbar.setSubtitle(result.isSuccessful() ? result.getStdout() : result.getStderr());
    }


    private void setupViewPager(TabLayout tabLayout) {
        tabLayout.setupWithViewPager(mViewPager);
        mViewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        mViewPager.addOnPageChangeListener(new MyPageChangeListener(this));
    }

}
