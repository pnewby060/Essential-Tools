package uk.co.acleaninstall.essentialtools.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

public class ToSortFragment extends Fragment {

    public static final String PAGE_ARG = "PAGE_ARG";
    public static final String NON_ROOTED = "Phone needs root privileges";
    public int mPage;


    public static ToSortFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(PAGE_ARG, page);
        ToSortFragment fragment = new ToSortFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPage = getArguments().getInt(PAGE_ARG);
    }

    /*@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // View v = inflater.inflate(R.layout.fragment_shell_command, container, false);
        ButterKnife.bind(this, v);

        return v;

    }*/

    /*@OnClick(R.id.listFilesystemButton)
    public void onListFilesystemClick() {

        if (Utils.checkSuExists()) {

            MiscUtils.runOnBackgroundThread(new Runnable() {
                @Override
                public void run() {
                    try {
                        CommandLineUtils.execCmdSync(Constants.RootConstants.listAllCommandsAsRoot, ShellCommandFragment.this);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

        } else
            MiscUtils.showToast(NON_ROOTED);


    }

    @Override
    public void onComplete(boolean success, int exitVal, String error, final List<String> outputList, String output, String[] originalCmd) {

        MiscUtils.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                commands.setText(outputList.toString());
            }
        });


    }*/

}
