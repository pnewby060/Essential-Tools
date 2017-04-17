package uk.co.acleaninstall.essentialtools.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import java.util.ArrayList;
import uk.co.acleaninstall.essentialtools.R;
import uk.co.acleaninstall.essentialtools.activity.BuildInfoActivity;
import uk.co.acleaninstall.essentialtools.activity.OtherToolsActivity;
import uk.co.acleaninstall.essentialtools.activity.PhoneInfoActivity;
import uk.co.acleaninstall.essentialtools.activity.SimInfoActivity;
import uk.co.acleaninstall.essentialtools.adapter.ToolsRecyclerviewAdapter;
import uk.co.acleaninstall.essentialtools.listener.CustomItemClickListener;
import uk.co.acleaninstall.essentialtools.model.ToolModel;

public class ToolsFragment extends Fragment {

    public static final String PAGE_ARG = "PAGE_ARG";
    public int mPage;

    @BindView(R.id.toolsRecyclerView)
    RecyclerView mRecyclerView;

    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;


    public static ToolsFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(PAGE_ARG, page);
        ToolsFragment fragment = new ToolsFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPage = getArguments().getInt(PAGE_ARG);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tools, container, false);
        ButterKnife.bind(this, v);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(v.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        // Our List of items to choose from
        ToolModel model1 = new ToolModel("Phone Information",
            "Show detailed information of your phone", getResources().getString(R.string.gmd_adb));
        ToolModel model2 = new ToolModel("Build Information", "Show information about the build",
            getResources().getString(R.string.gmd_android));
        ToolModel model3 = new ToolModel("Sim Information", "Show sim card details",
            getResources().getString(R.string.gmd_sim_card));
        ToolModel model4 = new ToolModel("Other Information", "Some other useful tools",
            getResources().getString(R.string.gmd_pan_tool));
        ToolModel model5 = new ToolModel("Root Information", "Info about Root settings from device",
            getResources().getString(R.string.gmd_developer_mode));

        // Create an Arraytlist to work with our RecyclerView
        ArrayList<ToolModel> toolModels = new ArrayList<>();
        toolModels.add(model1);
        toolModels.add(model2);
        toolModels.add(model3);
        toolModels.add(model4);
        toolModels.add(model5);

        // Setup our adapter and custom onclick listener
        mAdapter = new ToolsRecyclerviewAdapter(toolModels, new CustomItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {

                switch (position) {
                    case 0:
                        // Phone Info Activity
                        startActivity(new Intent(v.getContext(), PhoneInfoActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(v.getContext(), BuildInfoActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(v.getContext(), SimInfoActivity.class));
                        break;
                    case 3:
                        startActivity(new Intent(v.getContext(), OtherToolsActivity.class));
                        break;
                }

            }
        });
        mRecyclerView.setAdapter(mAdapter);

        return v;

    }

}
