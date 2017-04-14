package uk.co.acleaninstall.essentialtools.dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.acleaninstall.essentialtools.R;

public class ShowInfoDialogFragment extends DialogFragment {

    public static final String TITLE = "title";
    public static final String STDOUT = "stdout";

    @BindView(R.id.showInfoDialogTitle)
    TextView titleView;

    @BindView(R.id.showInfoDialogContent)
    TextView contentsView;


    public ShowInfoDialogFragment() {

    }


    public static ShowInfoDialogFragment newInstance(String title, String stdout) {
        ShowInfoDialogFragment fragment = new ShowInfoDialogFragment();
        Bundle args = new Bundle();
        args.putString(TITLE, title);
        args.putString(STDOUT, stdout);
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_fragment_show_info, container);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        // set the title
        titleView.setText(getArguments().getString(TITLE));

        // set scrolling for the textview
        contentsView.setMovementMethod(new ScrollingMovementMethod());

        // set the result
        contentsView.setText(getArguments().getString(STDOUT));

    }
}
