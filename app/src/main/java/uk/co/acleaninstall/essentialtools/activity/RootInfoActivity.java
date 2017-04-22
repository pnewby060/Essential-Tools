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
import uk.co.acleaninstall.essentialtools.adapter.RootInfoRecyclerviewAdapter;
import uk.co.acleaninstall.essentialtools.listener.CustomItemClickListener;
import uk.co.acleaninstall.essentialtools.model.RootInfoModel;
import uk.co.acleaninstall.essentialtools.util.MiscTools;
import uk.co.acleaninstall.quietlibrary.RootEquipment;

public class RootInfoActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.rootInfoRecyclerView)
    RecyclerView mRecyclerView;

    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;

    RootEquipment mRootEquipment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Init our RootBeer library
        mRootEquipment = new RootEquipment(MyApp.getContext());

        setContentView(R.layout.activity_root_info);
        ButterKnife.bind(this);

        setupToolbar();

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // Custom Item Click Listener taken from https://gist.github.com/riyazMuhammad/1c7b1f9fa3065aa5a46f
        mAdapter = new RootInfoRecyclerviewAdapter(
            new RootInfoActivity.RootInfoModelsList().getList(),
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


    private class RootInfoModelsList extends ArrayList<RootInfoModel> {

        List<RootInfoModel> list;


        RootInfoModelsList() {
            list = new ArrayList<>();

        }


        ArrayList<RootInfoModel> getList() {

            // Our list of root info models to choose from
            RootInfoModel checkSUbyWhich = new RootInfoModel(
                getString(R.string.rootInfoCheckSuExists),
                "Shows if phone is rooted by checking if SU exists. Runs a which su command",
                String.valueOf(mRootEquipment.checkSuExists()));

            RootInfoModel checkRWpaths = new RootInfoModel(getString(R.string.rootInfoCheckRWpaths),
                "Checks if System paths can be mounted RW. You can only do this if you were rooted",
                String.valueOf(mRootEquipment.checkForRWPaths()));

            RootInfoModel checkDangerProps = new RootInfoModel(
                getString(R.string.rootInfoCheckDangerProps),
                "Searches for dangerous properties from the build.prop file",
                String.valueOf(mRootEquipment.checkForDangerousProps()));

            list.add(checkSUbyWhich);
            list.add(checkRWpaths);
            list.add(checkDangerProps);

            return (ArrayList<RootInfoModel>) list;
        }

    }
}
