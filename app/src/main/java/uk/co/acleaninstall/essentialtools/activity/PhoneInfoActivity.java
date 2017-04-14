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
import uk.co.acleaninstall.essentialtools.MyApp;
import uk.co.acleaninstall.essentialtools.R;
import uk.co.acleaninstall.essentialtools.adapter.PhoneInfoRecyclerviewAdapter;
import uk.co.acleaninstall.essentialtools.listener.CustomItemClickListener;
import uk.co.acleaninstall.essentialtools.model.MyDevice;
import uk.co.acleaninstall.essentialtools.model.PhoneInfoModel;
import uk.co.acleaninstall.essentialtools.util.MiscTools;

public class PhoneInfoActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.phoneInfoRecyclerView)
    RecyclerView mRecyclerView;

    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_info);
        ButterKnife.bind(this);

        setupToolbar();

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // Custom Item Click Listener taken from https://gist.github.com/riyazMuhammad/1c7b1f9fa3065aa5a46f
        mAdapter = new PhoneInfoRecyclerviewAdapter(new PhoneInfoModelsList().getList(),
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


    private class PhoneInfoModelsList extends ArrayList<PhoneInfoModel> {

        List<PhoneInfoModel> list;


        PhoneInfoModelsList() {
            list = new ArrayList<>();
        }


        ArrayList<PhoneInfoModel> getList() {

            // Our list of phone info models to choose from
            PhoneInfoModel phoneId = new PhoneInfoModel(getString(R.string.device_id),
                "Information about the devices ID", MyDevice.ANDROID_ID(MyApp.getContext()),
                getResources().getString(R.string.gmd_phone_android));

            PhoneInfoModel phoneBoard = new PhoneInfoModel(getString(R.string.device_board),
                "The name of the underlying board, like \"goldfish\"", MyDevice.BOARD_NAME,
                getResources().getString(R.string.gmd_developer_board));

            PhoneInfoModel phoneBootloader = new PhoneInfoModel(
                getString(R.string.device_bootloader), "The system bootloader version number",
                MyDevice.BOOTLOADER_VERSION,
                getResources().getString(R.string.gmd_radio));

            PhoneInfoModel phoneBrand = new PhoneInfoModel(getString(R.string.device_brand),
                "The consumer-visible brand with which the product/hardware will be associated, if any",
                MyDevice.BRAND_NAME,
                getResources().getString(R.string.gmd_branding_watermark));

            PhoneInfoModel phoneDesign = new PhoneInfoModel(getString(R.string.device_design),
                "The name of the industrial design", MyDevice.DESIGN_NAME,
                getResources().getString(R.string.gmd_format_color_fill));

            PhoneInfoModel phoneModel = new PhoneInfoModel(getString(R.string.device_model),
                "The name / model of phone", MyDevice.MODEL_NAME,
                getResources().getString(R.string.gmd_format_color_fill));

            PhoneInfoModel phoneManufacturer = new PhoneInfoModel(
                getString(R.string.device_manufacturer), "The phones manufacturer",
                MyDevice.MANUFACTURER_NAME,
                getResources().getString(R.string.gmd_developer_mode));

            list.add(phoneId);
            list.add(phoneBoard);
            list.add(phoneBootloader);
            list.add(phoneBrand);
            list.add(phoneDesign);
            list.add(phoneModel);
            list.add(phoneManufacturer);

            return (ArrayList<PhoneInfoModel>) list;
        }

    }

}
