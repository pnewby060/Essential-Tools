package uk.co.acleaninstall.essentialtools.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.mikepenz.iconics.view.IconicsImageView;
import java.util.ArrayList;
import uk.co.acleaninstall.essentialtools.R;
import uk.co.acleaninstall.essentialtools.listener.CustomItemClickListener;
import uk.co.acleaninstall.essentialtools.model.GameModel;

public class GamesRecyclerviewAdapter
    extends RecyclerView.Adapter<GamesRecyclerviewAdapter.ViewHolder> {

    private ArrayList<GameModel> mGameModels;
    private CustomItemClickListener customItemClickListener;


    // Our Constructor called in the Activities/Fragments
    public GamesRecyclerviewAdapter(ArrayList<GameModel> gameModelArrayList, CustomItemClickListener customItemClickListener) {
        this.mGameModels = gameModelArrayList;
        this.customItemClickListener = customItemClickListener;
    }


    @Override
    public GamesRecyclerviewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // create a new view
        final View inflatedView = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.single_view_games, parent, false);

        // create a new view holder for our inflated new view
        final ViewHolder holder = new ViewHolder(inflatedView);

        // setup an on click listener on our inflated view
        inflatedView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // call our custom click listener
                customItemClickListener.onItemClick(inflatedView, holder.getAdapterPosition());
            }
        });
        return holder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        // get ref to a single model
        GameModel game = mGameModels.get(position);

        // bind the model to the holder
        holder.bindGame(game);
    }


    @Override
    public int getItemCount() {
        // return the number of game models
        return mGameModels.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        // The views in our single layout
        private TextView mTitleView, mSubTitleView;
        private IconicsImageView mIconView;

        // a single model
        private GameModel mGame;


        public ViewHolder(View v) {
            super(v);
            mTitleView = (TextView) v.findViewById(R.id.gamesTitleView);
            mSubTitleView = (TextView) v.findViewById(R.id.gamesSubTitleView);
            mIconView = (IconicsImageView) v.findViewById(R.id.gamesIconView);

        }


        public void bindGame(GameModel gameModel) {
            mGame = gameModel;
            mTitleView.setText(gameModel.getTitle());
            mSubTitleView.setText(gameModel.getSubTitle());
            mIconView.setIcon(gameModel.getIcon());
        }

    }
}
