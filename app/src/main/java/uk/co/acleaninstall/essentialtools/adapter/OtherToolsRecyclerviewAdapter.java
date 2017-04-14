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
import uk.co.acleaninstall.essentialtools.model.OtherToolModel;

public class OtherToolsRecyclerviewAdapter
    extends RecyclerView.Adapter<OtherToolsRecyclerviewAdapter.ViewHolder> {

    private ArrayList<OtherToolModel> mOtherToolModels;
    private CustomItemClickListener mCustomItemClickListener;


    // Our Constructor called in the Activities/Fragments
    public OtherToolsRecyclerviewAdapter(ArrayList<OtherToolModel> OtherToolModelArrayList, CustomItemClickListener customItemClickListener) {
        this.mOtherToolModels = OtherToolModelArrayList;
        this.mCustomItemClickListener = customItemClickListener;
    }


    @Override
    public OtherToolsRecyclerviewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // create a new view with our layout
        final View inflatedView = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.single_view_other_info, parent, false);

        // create a new view holder for our inflated new view
        final ViewHolder holder = new ViewHolder(inflatedView);

        // setup an on click listener on our inflated view
        inflatedView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCustomItemClickListener.onItemClick(inflatedView, holder.getAdapterPosition());
            }
        });
        return holder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        // get ref to a single model
        OtherToolModel otherInfo = mOtherToolModels.get(position);

        // bind the model to the holder
        holder.bindOtherInfo(otherInfo);
    }


    @Override
    public int getItemCount() {

        // return the number of info models
        return mOtherToolModels.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        // The views in our single layout
        private TextView mTitleView, mSubTitleView;
        private IconicsImageView mIconView;

        // A single model
        private OtherToolModel mOtherTool;


        public ViewHolder(View v) {
            super(v);
            mTitleView = (TextView) v.findViewById(R.id.otherToolTitleView);
            mSubTitleView = (TextView) v.findViewById(R.id.otherToolSubTitleView);
            mIconView = (IconicsImageView) v.findViewById(R.id.otherToolIconView);
        }


        public void bindOtherInfo(OtherToolModel model) {
            mOtherTool = model;
            mTitleView.setText(model.getTitle());
            mSubTitleView.setText(model.getSubTitle());
            mIconView.setIcon(model.getIcon());
        }

    }
}
