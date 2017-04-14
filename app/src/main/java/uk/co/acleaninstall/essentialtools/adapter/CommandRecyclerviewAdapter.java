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
import uk.co.acleaninstall.essentialtools.model.CommandModel;

public class CommandRecyclerviewAdapter
    extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<CommandModel> mCommandModels;
    private CustomItemClickListener customItemClickListener;


    // Our Constructor called in the Activities/Fragments
    public CommandRecyclerviewAdapter(ArrayList<CommandModel> CommandModelArrayList, CustomItemClickListener customItemClickListener) {
        this.mCommandModels = CommandModelArrayList;
        this.customItemClickListener = customItemClickListener;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder holder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        // create a new view
        final View inflatedView = inflater
            .inflate(R.layout.single_view_command, parent, false);
        holder = new ViewHolder(inflatedView);

        // setup an on click listener on our inflated view
        final RecyclerView.ViewHolder finalHolder = holder;
        inflatedView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // call our custom click listener
                customItemClickListener.onItemClick(inflatedView,
                    finalHolder.getAdapterPosition());
            }
        });

        return holder;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        // get ref to a single model
        CommandModel shell = mCommandModels.get(position);

        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.bindCommand(viewHolder, position);

    }


    @Override
    public int getItemCount() {
        // return the number of game models
        return mCommandModels.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        // The views in our single layout
        private TextView mTitleView, mSubTitleView;
        private IconicsImageView mIconView;

        // a single model
        private CommandModel mCommand;


        public ViewHolder(View v) {
            super(v);
            mTitleView = (TextView) v.findViewById(R.id.commandsTitleView);
            mSubTitleView = (TextView) v.findViewById(R.id.commandsSubTitleView);
            mIconView = (IconicsImageView) v.findViewById(R.id.commandsIconView);

        }


        public void bindCommand(ViewHolder holder, int position) {
            CommandModel commandModel = mCommandModels.get(position);
            holder.mTitleView.setText(commandModel.getTitle());
            holder.mSubTitleView.setText(commandModel.getSubTitle());
            holder.mIconView.setIcon(commandModel.getIcon());
        }

    }

}
