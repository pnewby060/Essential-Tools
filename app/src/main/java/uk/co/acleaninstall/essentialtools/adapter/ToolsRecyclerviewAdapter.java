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
import uk.co.acleaninstall.essentialtools.model.ToolModel;

public class ToolsRecyclerviewAdapter
    extends RecyclerView.Adapter<ToolsRecyclerviewAdapter.ViewHolder> {

    private ArrayList<ToolModel> mToolModels;
    private CustomItemClickListener mCustomItemClickListener;


    // Our Constructor called in the Activities/Fragments
    public ToolsRecyclerviewAdapter(ArrayList<ToolModel> toolModelArrayList, CustomItemClickListener customItemClickListener) {
        this.mToolModels = toolModelArrayList;
        this.mCustomItemClickListener = customItemClickListener;
    }


    @Override
    public ToolsRecyclerviewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // create a new view with our layout
        final View inflatedView = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.single_view_tools, parent, false);

        // create a new view holder for our inflated new view
        final ViewHolder holder = new ViewHolder(inflatedView);

        // setup an on click listener on our inflated view
        inflatedView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // call our custom click listener
                mCustomItemClickListener.onItemClick(inflatedView, holder.getAdapterPosition());
            }
        });
        return holder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        // get ref to a single model
        ToolModel tool = mToolModels.get(position);

        // bind the model to the holder
        holder.bindTool(tool);

    }


    @Override
    public int getItemCount() {
        // return the number of tool models
        return mToolModels.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        // The views in our single layout
        private TextView mTitleView, mSubTitleView;
        private IconicsImageView mIconView;

        // A single model
        private ToolModel mTool;


        public ViewHolder(View v) {
            super(v);
            mTitleView = (TextView) v.findViewById(R.id.toolsTitleView);
            mSubTitleView = (TextView) v.findViewById(R.id.toolsSubTitleView);
            mIconView = (IconicsImageView) v.findViewById(R.id.toolsIconView);
        }


        public void bindTool(ToolModel toolModel) {
            mTool = toolModel;
            mTitleView.setText(toolModel.getTitle());
            mSubTitleView.setText(toolModel.getSubTitle());
            mIconView.setIcon(toolModel.getIcon());
        }

    }
}
