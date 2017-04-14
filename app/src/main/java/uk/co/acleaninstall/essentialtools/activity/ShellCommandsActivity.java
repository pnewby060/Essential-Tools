package uk.co.acleaninstall.essentialtools.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import butterknife.BindView;
import butterknife.ButterKnife;
import java.util.ArrayList;
import uk.co.acleaninstall.essentialtools.R;
import uk.co.acleaninstall.essentialtools.adapter.ShellCommandRecyclerviewAdapter;
import uk.co.acleaninstall.essentialtools.dialog.ShellCommandDialogFragment;
import uk.co.acleaninstall.essentialtools.listener.CustomItemClickListener;
import uk.co.acleaninstall.essentialtools.model.ShellModel;
import uk.co.acleaninstall.essentialtools.util.Constants.ShellConstants.ACPI;
import uk.co.acleaninstall.essentialtools.util.Constants.ShellConstants.Free;
import uk.co.acleaninstall.essentialtools.util.Constants.ShellConstants.GetProp;
import uk.co.acleaninstall.essentialtools.util.MiscTools;

import static uk.co.acleaninstall.essentialtools.util.Constants.ShellConstants.Which;

public class ShellCommandsActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.shellCommandsRecyclerView)
    RecyclerView mRecyclerView;

    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shell_commands);
        ButterKnife.bind(this);

        setupToolbar();

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // Custom Item Click Listener taken from https://gist.github.com/riyazMuhammad/1c7b1f9fa3065aa5a46f
        mAdapter = new ShellCommandRecyclerviewAdapter(new ShellCommandModelsList().getList(),
            new CustomItemClickListener() {
                @Override
                public void onItemClick(View v, int position) {
                    // Setup on clicks if required
                    FragmentManager manager = getSupportFragmentManager();
                    switch (position) {
                        case 0:
                            // acpi
                            ShellCommandDialogFragment acpiFragment = ShellCommandDialogFragment
                                .newInstance(ACPI.TITLE, ACPI.CONTENT, ACPI.COMMAND, false,
                                    ACPI.ARG_A,
                                    ACPI.ARG_B, ACPI.ARG_C, ACPI.ARG_D, ACPI.ARG_E);
                            acpiFragment.setCancelable(false);
                            acpiFragment.show(manager, "acpiFrag");
                            break;
                        case 1:
                            // free
                            ShellCommandDialogFragment freeFragment = ShellCommandDialogFragment
                                .newInstance(Free.TITLE, Free.CONTENT, Free.COMMAND, false,
                                    Free.ARG_A,
                                    Free.ARG_B, Free.ARG_C, Free.ARG_D, Free.ARG_E);
                            freeFragment.setCancelable(true);
                            freeFragment.show(manager, "freeFrag");
                            break;
                        case 2:
                            // getprop
                            ShellCommandDialogFragment getPropFragment = ShellCommandDialogFragment
                                .newInstance(GetProp.TITLE, GetProp.CONTENT, GetProp.COMMAND, true,
                                    null,
                                    null, null, null, null);
                            getPropFragment.setCancelable(false);
                            getPropFragment.show(manager, "getpropFrag");
                            break;
                        case 3:
                            // which
                            ShellCommandDialogFragment whichFragment = ShellCommandDialogFragment
                                .newInstance(Which.TITLE, Which.CONTENT, Which.COMMAND, true,
                                    Which.ARG_A,
                                    null, null, null, null);
                            whichFragment.setCancelable(false);
                            whichFragment.show(manager, "whichFrag");
                            break;

                    }
                }
            });
        mRecyclerView.setAdapter(mAdapter);
    }


    @Override
    protected void onResume() {
        super.onResume();

        mRecyclerView.setAdapter(mAdapter);
    }


    private void setupToolbar() {

        // Setup our Toolbar as an ActionBar
        setSupportActionBar(mToolbar);

        // Check for our Action Bat / Toolbar
        if (getSupportActionBar() != null) {

            // Set the back button on it
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            // Set the top padding to bring the view down
            mToolbar.setPadding(0, MiscTools.getStatusBarHeight(), 0, 0);
        }
    }


    private class ShellCommandModelsList extends ArrayList<ShellModel> {

        ArrayList<ShellModel> list;


        ShellCommandModelsList() {
            list = new ArrayList<>();
        }


        public ArrayList<ShellModel> getList() {

            // 'acpi'
            ShellModel acpi = new ShellModel(
                getString(R.string.shell_command_acpi),
                getString(R.string.shell_command_acpi_synopsis),
                getString(R.string.shell_command_acpi_description),
                getString(R.string.shell_command_acpi_options));

            // 'free'
            ShellModel free = new ShellModel(
                getString(R.string.shell_command_free),
                getString(R.string.shell_command_free_synopsis),
                getString(R.string.shell_command_free_description),
                getString(R.string.shell_command_free_options));

            // 'getProp'
            // TODO Possibly create a list of properties to choose from as an arg
            ShellModel getProp = new ShellModel(
                getString(R.string.shell_command_getprop),
                getString(R.string.shell_command_getprop_synopsis),
                getString(R.string.shell_command_getprop_description),
                getString(R.string.shell_command_getprop_options));

            // 'pm'
            ShellModel pm = new ShellModel(
                getString(R.string.shell_command_pm),
                getString(R.string.shell_command_which_synopsis),
                getString(R.string.shell_command_which_description),
                getString(R.string.shell_command_which_options));

            // 'which'
            ShellModel which = new ShellModel(
                getString(R.string.shell_command_which),
                getString(R.string.shell_command_which_synopsis),
                getString(R.string.shell_command_which_description),
                getString(R.string.shell_command_which_options));

            list.add(acpi);
            list.add(free);
            list.add(getProp);
            list.add(which);

            return list;
        }
    }

}
