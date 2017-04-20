package uk.co.acleaninstall.essentialtools.activity;

import android.os.Bundle;
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
import uk.co.acleaninstall.essentialtools.adapter.BuildInfoRecyclerviewAdapter;
import uk.co.acleaninstall.essentialtools.listener.CustomItemClickListener;
import uk.co.acleaninstall.essentialtools.model.BuildInfoModel;
import uk.co.acleaninstall.essentialtools.model.MyBuild;
import uk.co.acleaninstall.essentialtools.util.MiscTools;

public class BuildInfoActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.buildInfoRecyclerView)
    RecyclerView mRecyclerView;

    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_build_info);
        ButterKnife.bind(this);

        setupToolbar();

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // Custom Item Click Listener taken from https://gist.github.com/riyazMuhammad/1c7b1f9fa3065aa5a46f
        mAdapter = new BuildInfoRecyclerviewAdapter(new BuildInfoModelsList().getList(),
            new CustomItemClickListener() {
                @Override
                public void onItemClick(View v, int position) {
                    // Setup on clicks if required
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


    private class BuildInfoModelsList extends ArrayList<BuildInfoModel> {

        List<BuildInfoModel> list;


        BuildInfoModelsList() {
            list = new ArrayList<>();
        }


        ArrayList<BuildInfoModel> getList() {

            // Our list of build info models to choose from

            /**
             * CHARACTERISTICS
             */
            BuildInfoModel buildCharacteristics = new BuildInfoModel(
                getString(R.string.device_build_characteristics),
                "Displays any special characteristics about the device",
                MyBuild.BUILD_CHARACTERISTICS,
                getResources().getString(R.string.faw_building));

            /**
             * FINGERPRINT
             */
            BuildInfoModel buildFingerprint = new BuildInfoModel(
                getString(R.string.device_fingerprint),
                "A string that uniquely identifies this build", MyBuild.FINGERPRINT,
                getResources().getString(R.string.gmd_fingerprint));

            /**
             * FIRMWARE
             */
            BuildInfoModel buildFirmware = new BuildInfoModel(getString(R.string.device_firmware),
                "Either a changelist number, or a label like \"M4-rc20\"", MyBuild.FIRMWARE,
                getResources().getString(R.string.gmd_mode_edit));

            /**
             * HARDWARE
             */
            BuildInfoModel buildHardware = new BuildInfoModel(getString(R.string.device_hardware),
                "The devices hardware", MyBuild.HARDWARE,
                getResources().getString(R.string.gmd_devices_other));

            /**
             * HOST
             */
            BuildInfoModel buildHost = new BuildInfoModel(getString(R.string.device_host),
                "The person / company who created the rom", MyBuild.HOST,
                getResources().getString(R.string.gmd_phone_android));

            /**
             * ID
             */
            BuildInfoModel buildId = new BuildInfoModel(getString(R.string.device_build_id),
                "A build ID string meant for displaying to the user. This shows in the settings screen",
                MyBuild.ID,
                getResources().getString(R.string.gmd_build));

            /**
             * RADIO
             */
            BuildInfoModel buildRadio = new BuildInfoModel(getString(R.string.device_baseband),
                "The baseband radio version number", MyBuild.RADIO_VERSION,
                getResources().getString(R.string.gmd_radio));

            /**
             * SERIAL
             */
            BuildInfoModel buildSerial = new BuildInfoModel(
                getString(R.string.device_hardware_serial),
                "A hardware serial number, if available.  Alphanumeric only, case-insensitive.",
                MyBuild.SERIAL,
                getResources().getString(R.string.gmd_memory));

            /**
             * SELINUX
             */
            BuildInfoModel buildSeLinux = new BuildInfoModel(
                getString(R.string.device_selinux),
                "Shows the state of the SELinux on the device",
                MyBuild.SELINUX(),
                getResources().getString(R.string.gmd_security));

            /**
             * TAGS
             */
            BuildInfoModel buildTags = new BuildInfoModel(getString(R.string.device_tags),
                "Comma-separated tags describing the build, like \"unsigned,debug\"",
                MyBuild.TAGS,
                getResources().getString(R.string.gmd_tag_faces));

            /**
             * TIME
             */
            BuildInfoModel buildTime = new BuildInfoModel(getString(R.string.device_rom_created),
                "The date this rom was created", MyBuild.TIME,
                getResources().getString(R.string.gmd_date_range));

            /**
             * TYPE
             */
            BuildInfoModel buildType = new BuildInfoModel(getString(R.string.device_build_type),
                "The type of build, like \"user\" or \"eng\"", MyBuild.TYPE,
                getResources().getString(R.string.gmd_merge_type));

            /**
             * USER
             */
            BuildInfoModel buildUser = new BuildInfoModel(getString(R.string.device_maintainer),
                "The maintainer of the rom", MyBuild.USER,
                getResources().getString(R.string.gmd_verified_user));

            list.add(buildCharacteristics);
            list.add(buildFingerprint);
            list.add(buildFirmware);
            list.add(buildHardware);
            list.add(buildHost);
            list.add(buildId);
            list.add(buildRadio);
            list.add(buildSeLinux);
            list.add(buildSerial);
            list.add(buildTags);
            list.add(buildTime);
            list.add(buildType);
            list.add(buildUser);

            return (ArrayList<BuildInfoModel>) list;
        }

    }

}
