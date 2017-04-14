package uk.co.acleaninstall.essentialtools.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import uk.co.acleaninstall.essentialtools.R;
import uk.co.acleaninstall.essentialtools.listener.CustomItemClickListener;
import uk.co.acleaninstall.essentialtools.model.ShellModel;

public class ShellCommandRecyclerviewAdapter
    extends RecyclerView.Adapter<ShellCommandRecyclerviewAdapter.ViewHolder> {

    private ArrayList<ShellModel> mShellModels;
    private CustomItemClickListener customItemClickListener;


    // Our Constructor called in the Activities/Fragments
    public ShellCommandRecyclerviewAdapter(ArrayList<ShellModel> ShellModelArrayList, CustomItemClickListener customItemClickListener) {
        this.mShellModels = ShellModelArrayList;
        this.customItemClickListener = customItemClickListener;
    }


    @Override
    public ShellCommandRecyclerviewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // create a new view
        final View inflatedView = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.single_view_shell_command, parent, false);

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
        ShellModel shell = mShellModels.get(position);

        // bind the model to the holder
        holder.bindShell(shell);
    }


    @Override
    public int getItemCount() {
        // return the number of game models
        return mShellModels.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        // The views in our single layout
        private TextView mTitleView, mSynopsisView, mDescriptionView, mOptionsView;

        // a single model
        private ShellModel mShell;


        public ViewHolder(View v) {
            super(v);
            mTitleView = (TextView) v.findViewById(R.id.shellTitleView);
            mSynopsisView = (TextView) v.findViewById(R.id.shellSynopsisView);
            mDescriptionView = (TextView) v.findViewById(R.id.shellDescriptionView);
            mOptionsView = (TextView) v.findViewById(R.id.shellOptionsView);

        }


        public void bindShell(ShellModel ShellModel) {
            mShell = ShellModel;
            mTitleView.setText(ShellModel.getTitle());
            mSynopsisView.setText(ShellModel.getSynopsis());
            mDescriptionView.setText(ShellModel.getDescription());
            mOptionsView.setText(ShellModel.getOptions());
        }

    }
}
