package uk.co.acleaninstall.essentialtools.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import butterknife.BindView;
import butterknife.ButterKnife;
import java.util.ArrayList;
import java.util.List;
import uk.co.acleaninstall.essentialtools.R;
import uk.co.acleaninstall.essentialtools.adapter.OtherToolsRecyclerviewAdapter;
import uk.co.acleaninstall.essentialtools.dialog.ShowInfoDialogFragment;
import uk.co.acleaninstall.essentialtools.listener.CustomItemClickListener;
import uk.co.acleaninstall.essentialtools.model.OtherToolModel;
import uk.co.acleaninstall.essentialtools.util.MiscTools;

import static uk.co.acleaninstall.essentialtools.util.ShellTools.runCommand;

public class OtherToolsActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.otherToolsRecyclerView)
    RecyclerView mRecyclerView;

    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_tools);
        ButterKnife.bind(this);

        setupToolbar();

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // Custom Item Click Listener taken from https://gist.github.com/riyazMuhammad/1c7b1f9fa3065aa5a46f
        mAdapter = new OtherToolsRecyclerviewAdapter(
            new OtherToolsActivity.OtherToolModelsList().getList(), new CustomItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                // Setup on clicks if required
                FragmentManager fm = getSupportFragmentManager();
                // TODO Run async
                switch (position) {
                    case 0:
                        // list all apps
                        ShowInfoDialogFragment listAllFragment = ShowInfoDialogFragment.newInstance(
                            "List all apps", runCommand(true, "pm list packages"));
                        listAllFragment.show(fm, "listAllFrag");
                        break;
                    case 1:
                        // show install location
                        ShowInfoDialogFragment showInstallFragment
                            = ShowInfoDialogFragment.newInstance("Apps install location",
                            getInstallLocation(runCommand(true, "pm get-install-location")));
                        showInstallFragment.show(fm, "showInstallFrag");
                        break;
                    case 2:
                        ShowInfoDialogFragment showDeviceFeatures
                            = ShowInfoDialogFragment.newInstance("Devices features",
                            runCommand(true, "cmd package list features"));
                        showDeviceFeatures.show(fm, "showFeaturesFrag");
                        break;
                    case 3:
                        ShowInfoDialogFragment showDeviceLibraries
                            = ShowInfoDialogFragment.newInstance("Devices libraries",
                            runCommand(true, "cmd package list libraries"));
                        showDeviceLibraries.show(fm, "showLibrariesFrag");
                        break;
                    case 4:
                        ShowInfoDialogFragment showDevicePermissionGroups
                            = ShowInfoDialogFragment.newInstance("Devices permission groups",
                            runCommand(true, "cmd package list permission-groups"));
                        showDevicePermissionGroups.show(fm, "showPermissionsGroupsFrag");
                        break;
                    case 5:
                        ShowInfoDialogFragment showDevicePermissions
                            = ShowInfoDialogFragment.newInstance("Devices permissions",
                            runCommand(true, "cmd package list permissions"));
                        showDevicePermissions.show(fm, "showPermissionsFrag");
                        break;
                }

            }


            private String getInstallLocation(String command) {
                String s = null;
                if (command.equalsIgnoreCase("0[auto]")) {
                    s = "AUTO - Automatically set to internal";
                }
                if (command.equalsIgnoreCase("[2/external]")) {
                    s = "EXTERNAL - Set to external memory";
                }

                return s;
            }
        });

        mRecyclerView.setAdapter(mAdapter);
    }


    private void setupToolbar() {

        // Setup our Toolbar as an ActionBar
        setSupportActionBar(mToolbar);

        // Check for our Action Bat / Toolbar
        if (getSupportActionBar() != null) {

            // Set the back button on it
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            // Set the top padding to bring the view down
            mToolbar.setPadding(0, MiscTools.getStatusBarHeight(), 0, 0);
        }
    }


    private class OtherToolModelsList extends ArrayList<OtherToolModel> {

        List<OtherToolModel> list;


        OtherToolModelsList() {
            list = new ArrayList<>();
        }


        ArrayList<OtherToolModel> getList() {

            // Our list of other tools to choose from
            OtherToolModel listPackagesOnDevice = new OtherToolModel("List all apps",
                "List all apps currently on this device",
                null);
            OtherToolModel showInstallLocation = new OtherToolModel("Show install location",
                "Shows the current location for installed apps on the device",
                null);
            OtherToolModel showDevicesFeatures = new OtherToolModel("Show devices features",
                "Shows the current devices features that it is capable of",
                null);
            OtherToolModel showDevicesLibraries = new OtherToolModel("Show devices libraries",
                "Shows the current devices libraries that it is using",
                null);
            OtherToolModel showDevicesPermissionGroups = new OtherToolModel(
                "Show devices permission groups",
                "Shows the current devices permission groups that it is using",
                null);
            OtherToolModel showDevicesPermissions = new OtherToolModel("Show devices permissions",
                "Shows the current devices permissions that it is using",
                null);

            list.add(listPackagesOnDevice);
            list.add(showInstallLocation);
            list.add(showDevicesFeatures);
            list.add(showDevicesLibraries);
            list.add(showDevicesPermissionGroups);
            list.add(showDevicesPermissions);

            return (ArrayList<OtherToolModel>) list;
        }

    }
}
