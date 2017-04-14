package uk.co.acleaninstall.essentialtools.fragment;

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
import uk.co.acleaninstall.essentialtools.activity.HiLoGameActivity;
import uk.co.acleaninstall.essentialtools.adapter.GamesRecyclerviewAdapter;
import uk.co.acleaninstall.essentialtools.listener.CustomItemClickListener;
import uk.co.acleaninstall.essentialtools.model.GameModel;
import uk.co.acleaninstall.essentialtools.util.MiscTools;

public class GamesFragment extends Fragment {

    public static final String PAGE_ARG = "PAGE_ARG";
    public int mPage;

    @BindView(R.id.gamesRecyclerView)
    RecyclerView mRecyclerView;

    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;


    public static GamesFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(PAGE_ARG, page);
        GamesFragment fragment = new GamesFragment();
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
        View v = inflater.inflate(R.layout.fragment_games, container, false);
        ButterKnife.bind(this, v);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(v.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        // Our List of items to choose from
        GameModel hiLoModel = new GameModel("Hi - Lo Guess Game",
            "A simple Hi or Lo guessing game to pass the time",
            getResources().getString(R.string.gmd_gamepad));

        // Create an Arraylist to work with our RecyclerView
        ArrayList<GameModel> gameModels = new ArrayList<>();
        gameModels.add(hiLoModel);

        // Custom Item Click Listener taken from https://gist.github.com/riyazMuhammad/1c7b1f9fa3065aa5a46f
        mAdapter = new GamesRecyclerviewAdapter(gameModels, new CustomItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                // Do stuff regarding indes of recycler item here
                switch (position) {
                    case 0:
                        // HI LO Game
                        MiscTools.startActivity(getActivity(), HiLoGameActivity.class);
                        break;
                }
            }
        });
        mRecyclerView.setAdapter(mAdapter);

        return v;

    }

}
