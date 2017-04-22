package uk.co.acleaninstall.essentialtools.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import uk.co.acleaninstall.essentialtools.R;
import uk.co.acleaninstall.essentialtools.listener.CustomItemClickListener;
import uk.co.acleaninstall.essentialtools.model.RootInfoModel;

public class RootInfoRecyclerviewAdapter
    extends RecyclerView.Adapter<RootInfoRecyclerviewAdapter.ViewHolder> {

    private ArrayList<RootInfoModel> mRootInfoModels;
    private CustomItemClickListener customItemClickListener;


    // Our Constructor called in the Activities/Fragments
    public RootInfoRecyclerviewAdapter(ArrayList<RootInfoModel> rootInfoModelArrayList, CustomItemClickListener customItemClickListener) {
        this.mRootInfoModels = rootInfoModelArrayList;
        this.customItemClickListener = customItemClickListener;
    }


    @Override
    public RootInfoRecyclerviewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // create a new view
        final View inflatedView = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.single_view_root_info, parent, false);

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
        RootInfoModel root = mRootInfoModels.get(position);

        // bind the model to the holder
        holder.bindRoot(root);
    }


    @Override
    public int getItemCount() {
        // return the number of game models
        return mRootInfoModels.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        // The views in our single layout
        private TextView mTitleView, mSubTitleView, mResultView;

        // a single model
        private RootInfoModel mRoot;


        public ViewHolder(View v) {
            super(v);
            mTitleView = (TextView) v.findViewById(R.id.rootInfoTitleView);
            mSubTitleView = (TextView) v.findViewById(R.id.rootInfoSubTitleView);
            mResultView = (TextView) v.findViewById(R.id.rootInfoResultView);

        }


        public void bindRoot(RootInfoModel RootInfoModel) {
            mRoot = RootInfoModel;
            mTitleView.setText(RootInfoModel.getTitle());
            mSubTitleView.setText(RootInfoModel.getSubTitle());
            mResultView.setText(RootInfoModel.getResult());
        }

    }
}
