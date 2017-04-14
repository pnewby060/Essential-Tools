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
import uk.co.acleaninstall.essentialtools.model.PhoneInfoModel;

public class PhoneInfoRecyclerviewAdapter
    extends RecyclerView.Adapter<PhoneInfoRecyclerviewAdapter.ViewHolder> {

    private ArrayList<PhoneInfoModel> mPhoneInfoModels;
    private CustomItemClickListener mCustomItemClickListener;


    // Our Constructor called in the Activities/Fragments
    public PhoneInfoRecyclerviewAdapter(ArrayList<PhoneInfoModel> PhoneInfoModelArrayList, CustomItemClickListener customItemClickListener) {
        this.mPhoneInfoModels = PhoneInfoModelArrayList;
        this.mCustomItemClickListener = customItemClickListener;
    }


    @Override
    public PhoneInfoRecyclerviewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // create a new view with our layout
        final View inflatedView = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.single_view_phone_info, parent, false);

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
        PhoneInfoModel phoneInfo = mPhoneInfoModels.get(position);

        // bind the model to the holder
        holder.bindPhoneInfo(phoneInfo);
    }


    @Override
    public int getItemCount() {

        // return the number of info models
        return mPhoneInfoModels.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        // The views in our single layout
        private TextView mTitleView, mSubTitleView, mResultView;
        private IconicsImageView mIconView;

        // A single model
        private PhoneInfoModel mPhoneInfo;


        public ViewHolder(View v) {
            super(v);
            mTitleView = (TextView) v.findViewById(R.id.phoneInfoTitleView);
            mSubTitleView = (TextView) v.findViewById(R.id.phoneInfoSubTitleView);
            mResultView = (TextView) v.findViewById(R.id.phoneInfoResultView);
            mIconView = (IconicsImageView) v.findViewById(R.id.phoneInfoIconView);
        }


        public void bindPhoneInfo(PhoneInfoModel model) {
            mPhoneInfo = model;
            mTitleView.setText(model.getTitle());
            mSubTitleView.setText(model.getSubTitle());
            mResultView.setText(model.getResult());
            mIconView.setIcon(model.getIcon());
        }

    }
}
