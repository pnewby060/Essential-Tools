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
import uk.co.acleaninstall.essentialtools.model.SimModel;

public class SimRecyclerviewAdapter
    extends RecyclerView.Adapter<SimRecyclerviewAdapter.ViewHolder> {

    private ArrayList<SimModel> mSimModels;
    private CustomItemClickListener customItemClickListener;


    // Our Constructor called in the Activities/Fragments
    public SimRecyclerviewAdapter(ArrayList<SimModel> SimModelArrayList, CustomItemClickListener customItemClickListener) {
        this.mSimModels = SimModelArrayList;
        this.customItemClickListener = customItemClickListener;
    }


    @Override
    public SimRecyclerviewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // create a new view
        final View inflatedView = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.single_view_sim, parent, false);

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
        SimModel sim = mSimModels.get(position);

        // bind the model to the holder
        holder.bindSim(sim);
    }


    @Override
    public int getItemCount() {
        // return the number of game models
        return mSimModels.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        // The views in our single layout
        private TextView mTitleView, mSubTitleView, mResultView;
        private IconicsImageView mIconView;

        // a single model
        private SimModel mSim;


        public ViewHolder(View v) {
            super(v);
            mTitleView = (TextView) v.findViewById(R.id.simTitleView);
            mSubTitleView = (TextView) v.findViewById(R.id.simSubTitleView);
            mResultView = (TextView) v.findViewById(R.id.simResultTextView);
            mIconView = (IconicsImageView) v.findViewById(R.id.simIconView);

        }


        public void bindSim(SimModel SimModel) {
            mSim = SimModel;
            mTitleView.setText(SimModel.getTitle());
            mSubTitleView.setText(SimModel.getSubTitle());
            mResultView.setText(SimModel.getResult());
            mIconView.setIcon(SimModel.getIcon());
        }

    }
}
