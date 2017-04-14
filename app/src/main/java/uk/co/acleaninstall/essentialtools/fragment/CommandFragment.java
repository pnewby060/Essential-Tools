package uk.co.acleaninstall.essentialtools.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.github.lazylibrary.util.LogUtil;
import com.github.lazylibrary.util.ToastUtils;
import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import uk.co.acleaninstall.essentialtools.MyApp;
import uk.co.acleaninstall.essentialtools.R;
import uk.co.acleaninstall.essentialtools.activity.ShellCommandsActivity;
import uk.co.acleaninstall.essentialtools.adapter.CommandRecyclerviewAdapter;
import uk.co.acleaninstall.essentialtools.dialog.ShowInfoDialogFragment;
import uk.co.acleaninstall.essentialtools.listener.CustomItemClickListener;
import uk.co.acleaninstall.essentialtools.model.CommandModel;
import uk.co.acleaninstall.essentialtools.util.Constants;
import uk.co.acleaninstall.essentialtools.util.MiscTools;
import uk.co.acleaninstall.essentialtools.util.SuperTools;

import static uk.co.acleaninstall.essentialtools.util.MiscTools.hasWhatsAppDirectory;
import static uk.co.acleaninstall.essentialtools.util.RootTools.isRooted;
import static uk.co.acleaninstall.essentialtools.util.ShellTools.getResultAsList;

public class CommandFragment extends Fragment {

    public static final String PAGE_ARG = "PAGE_ARG";
    public int mPage;

    @BindView(R.id.commandsRecyclerView)
    RecyclerView mRecyclerView;

    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;


    public static CommandFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(PAGE_ARG, page);
        CommandFragment fragment = new CommandFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPage = getArguments().getInt(PAGE_ARG);

    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_command, container, false);
        ButterKnife.bind(this, v);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(v.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        // Our List of items to choose from
        CommandModel commands = new CommandModel(
            "Linux commands",
            "A masive list containing useful commands",
            getString(R.string.gmd_adb));

        CommandModel whatsAppHack = new CommandModel(
            "Get WhatsApp SharedPrefs.xml",
            "Retrieve the sharedprefs file with root privelages",
            getString(R.string.gmd_warning));

        CommandModel dialMyNumber = new CommandModel(
            "Dials your number",
            "Opens the dialler and inserts your phone number",
            getString(R.string.gmd_phone_android));

        CommandModel listRootFiles = new CommandModel(
            "List all dirs / files etc in root",
            "List everything inside of the root directory and returns the number",
            getString(R.string.gmd_filter_list));

        CommandModel hasAnyFilesInRoot = new CommandModel(
            "Checks if root is empty",
            "I think you know what this will return!",
            getString(R.string.gmd_warning));

        // Create an Arraylist to work with our RecyclerView
        final ArrayList<CommandModel> commandModels = new ArrayList<>();

        commandModels.add(commands);
        commandModels.add(whatsAppHack);
        commandModels.add(dialMyNumber);
        commandModels.add(listRootFiles);
        commandModels.add(hasAnyFilesInRoot);

        // Setup our adapter and custom onclick listener
        mAdapter = new CommandRecyclerviewAdapter(commandModels, new CustomItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {

                switch (position) {
                    case 0:
                        // Shell Commands Activity
                        MiscTools.startActivity(v.getContext(), ShellCommandsActivity.class);
                        break;

                    case 1:
                        //<editor-fold desc="WhatsApp test vulnerability">
                        // Check for root
                        if (isRooted()) {

                            // Check for whatsapp directory
                            if (hasWhatsAppDirectory()) {

                                // Get ref to the file
                                File file = new File(
                                    Constants.FileConstants.WHATSAPP_SHARED_PREFS_FILE);

                                // Log if the file is available
                                if (file.exists()) {
                                    LogUtil.i("File " + file.getAbsoluteFile().getPath() + " " +
                                        "is there", true);
                                }

                                // Run the command and get the result
                                List<String> results = getResultAsList("cat " + file.getPath());
                                StringBuilder builder = new StringBuilder();
                                for (String s : results) {
                                    builder.append(s + "\n");
                                }

                                // Set the results in the info fragment and show them
                                showResultsInFragment(builder);

                            }

                            // If NOT Rooted
                        } else {

                            // Alert the user this phone aint rooted
                            ToastUtils.showToast(MyApp.getContext(),
                                "Your phone aint rooted mate!");
                            break;
                        }
                        //</editor-fold>
                        break;

                    case 2:
                        //<editor-fold desc="Java reflection with ITelephony">
                        TelephonyManager tm = (TelephonyManager) MyApp.getContext()
                            .getSystemService(Context.TELEPHONY_SERVICE);

                        try {

                            Class c = Class.forName(tm.getClass().getName());
                            Method m = c.getDeclaredMethod("getITelephony");
                            m.setAccessible(true);
                            Object telephonyService = m.invoke(
                                tm); // Get the internal ITelephony object
                            c = Class.forName(
                                telephonyService.getClass().getName()); // Get its class

                            m = c.getDeclaredMethod("dial", String.class);
                            m.setAccessible(true);
                            m.invoke(telephonyService, "07450089319");

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        //</editor-fold>
                        break;

                    case 3:
                        // Get list of entire root dir
                        Toast.makeText(MyApp.getContext(),
                            SuperTools.RootDirectory.getAllInRootDir() +
                                " Files / Directories in root",
                            Toast.LENGTH_LONG).show();
                        break;
                    case 4:
                        // Check if root dir empty
                        Toast.makeText(MyApp.getContext(),
                            "Empty root directory = " +
                                String.valueOf(SuperTools.RootDirectory.isEmpty()),
                            Toast.LENGTH_LONG).show();
                        break;

                }

            }
        });
        mRecyclerView.setAdapter(mAdapter);

        return v;

    }


    private void showResultsInFragment(StringBuilder results) {
        FragmentManager fm = getFragmentManager();
        ShowInfoDialogFragment fragment = ShowInfoDialogFragment.newInstance("WhatsApp prefs xml",
            results.toString());
        fragment.show(fm, "resultsFrag");
    }

}
