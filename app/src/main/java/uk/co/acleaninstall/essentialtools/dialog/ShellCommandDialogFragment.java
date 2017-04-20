package uk.co.acleaninstall.essentialtools.dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import com.jrummyapps.android.shell.Shell;
import com.txusballesteros.AutoscaleEditText;
import java.util.ArrayList;
import java.util.List;
import uk.co.acleaninstall.essentialtools.MyApp;
import uk.co.acleaninstall.essentialtools.R;
import uk.co.acleaninstall.essentialtools.util.MiscTools;

public class ShellCommandDialogFragment extends DialogFragment {

    @BindView(R.id.runCommandLayout)
    LinearLayout layout;
    @BindView(R.id.runCommandDialogTitle)
    TextView titleView;
    @BindView(R.id.runCommandDialogContent)
    TextView contentView;
    @BindView(R.id.runCommandDialogSearchInput)
    AutoscaleEditText inputQueryView;
    @BindViews({ R.id.runCommandDialogArg1, R.id.runCommandDialogArg2, R.id.runCommandDialogArg3,
                   R.id.runCommandDialogArg4, R.id.runCommandDialogArg5 })
    List<CheckBox> argsArray;
    @BindView(R.id.runCommandDialogClearButton)
    Button clearButton;
    @BindView(R.id.runCommandDialogCancelButton)
    Button cancelButton;
    @BindView(R.id.runCommandDialogRunButton)
    Button runButton;

    // key values for bundle args
    public static final String TITLE = "title";
    public static final String CONTENT = "content";
    public static final String COMMAND = "command";
    public static final String HAS_SEARCH_QUERY = "has_search_query";
    public static final String ARG_A = "arg_a";
    public static final String ARG_B = "arg_b";
    public static final String ARG_C = "arg_c";
    public static final String ARG_D = "arg_d";
    public static final String ARG_E = "arg_e";

    // our command and args passed from shell commands activity
    private String commandPassed, argApassed, argBpassed, argCpassed, argDpassed, argEpassed;


    public ShellCommandDialogFragment() {

    }


    public static ShellCommandDialogFragment newInstance(String title, String content, String command,
                                                         Boolean hasSearchQuery,
                                                         @Nullable String argA,
                                                         @Nullable String argB,
                                                         @Nullable String argC,
                                                         @Nullable String argD,
                                                         @Nullable String argE) {
        ShellCommandDialogFragment fragment = new ShellCommandDialogFragment();
        Bundle args = new Bundle();
        args.putString(TITLE, title);
        args.putString(CONTENT, content);
        args.putString(COMMAND, command);
        args.putBoolean(HAS_SEARCH_QUERY, hasSearchQuery);
        args.putString(ARG_A, argA);
        args.putString(ARG_B, argB);
        args.putString(ARG_C, argC);
        args.putString(ARG_D, argD);
        args.putString(ARG_E, argE);
        fragment.setArguments(args);
        return fragment;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dialog_fragment_shell_command, container);

        return view;

    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        // set the title of the dialog
        titleView.setText(getArguments().getString(TITLE));

        // set the content / description of the dialog
        contentView.setText(getArguments().getString(CONTENT));

        inputQueryView.setSingleLine(false);
        inputQueryView.setEnabled(getArguments().getBoolean(HAS_SEARCH_QUERY));
        inputQueryView.setHint(getArguments().getBoolean(HAS_SEARCH_QUERY) ? "Search query" : "");
        inputQueryView.setCursorVisible(getArguments().getBoolean(HAS_SEARCH_QUERY));

        runButton.setEnabled(!getArguments().getBoolean(HAS_SEARCH_QUERY));
        commandPassed = getArguments().getString(COMMAND);

        argApassed = getArguments().getString(ARG_A);
        argBpassed = getArguments().getString(ARG_B);
        argCpassed = getArguments().getString(ARG_C);
        argDpassed = getArguments().getString(ARG_D);
        argEpassed = getArguments().getString(ARG_E);

        List<String> argsPassed = new ArrayList<>();
        argsPassed.add(argApassed);
        argsPassed.add(argBpassed);
        argsPassed.add(argCpassed);
        argsPassed.add(argDpassed);
        argsPassed.add(argEpassed);

        // iterate through our checkbox array and set the ones that haven't had null passed into them to visible
        for (int i = 0; i < argsArray.size(); i++) {
            argsArray.get(i)
                .setVisibility(argsPassed.get(i) != null ? View.VISIBLE : View.INVISIBLE);
            argsArray.get(i).setText(argsPassed.get(i));
        }

        // Check if there are no args to use but can be still ran with a user input only
        if (isNullArray(argsPassed)) {
            runButton.setEnabled(true);
        }

    }


    /**
     * @param args The list of args to pass and check if all are NULL.
     * @return If the list is empty then it's a NULL array otherwise it's not.
     */
    private boolean isNullArray(List<String> args) {
        Object[] array = args.toArray();
        boolean empty = true;
        for (Object ob : array) {
            if (ob != null) {
                empty = false;
            }
        }

        return empty;
    }


    @OnTextChanged(R.id.runCommandDialogSearchInput)
    public void onUserInput(CharSequence text) {

        // if the command to run dose'nt require a search query then don't run the TextWatcher
        if (getArguments().getBoolean(HAS_SEARCH_QUERY)) {

            // don't allow a null user input and then run it
            runButton.setEnabled(text.length() > 0);

        } else {

            inputQueryView.setEnabled(true);
        }

    }


    @OnClick(R.id.runCommandDialogClearButton)
    public void onClearInputs() {

        // clear all user input
        inputQueryView.setText("");
        inputQueryView.setLines(1);

        // clear the args check boxes
        for (int i = 0; i < argsArray.size(); i++) {
            argsArray.get(i).setChecked(false);
        }

        // if the command to run dose'nt require a search query then set the inputQuery to disabled
        if (!getArguments().getBoolean(HAS_SEARCH_QUERY)) {
            inputQueryView.setEnabled(false);
        }

        // if the run button has been disabled then enable it
        if (!runButton.isEnabled()) {
            runButton.setEnabled(true);
        }

    }


    @OnClick(R.id.runCommandDialogCancelButton)
    public void onCancelClick() {

        // dismiss the fragment all together
        dismiss();

    }


    @OnClick(R.id.runCommandDialogRunButton)
    public void onRunCommand(View v) {

        // init our run command passing in the main command to be run
        Log.i("Starting command ", initCommandBuilder(commandPassed));

        // if its an error were receiving
        if (runProcess(initCommandBuilder(commandPassed)).size() == 0) {
            inputQueryView.setText(R.string.check_syntax_error);
            return;
        }

        // start the running process if there is a result
        StringBuilder builder = new StringBuilder();

        int count = 0;
        for (String s : runProcess(initCommandBuilder(commandPassed))) {
            builder.append(s + "\n");
            // count++;
        }

        // set the editText to 7 lines in size
        inputQueryView.setMaxLines(7);
        inputQueryView.setText(builder);

        // disable the run button so we don't execute all ready outputted code
        runButton.setEnabled(false);

        // close our console so root is not running perm
        Shell.SU.closeConsole();

        // hide the keyboard
        MiscTools.hideSoftKeyboard(MyApp.getContext(), v);

    }


    private String initCommandBuilder(String mainCommandToRun) {

        // check if there is any args passed in each
        String argA = argsArray.get(0).isChecked() ? argApassed : "";
        String argB = argsArray.get(1).isChecked() ? argBpassed : "";
        String argC = argsArray.get(2).isChecked() ? argCpassed : "";
        String argD = argsArray.get(3).isChecked() ? argDpassed : "";
        String argE = argsArray.get(4).isChecked() ? argEpassed : "";

        // get the text from the user input box
        String inputQuery = inputQueryView.getText().toString();

        // build them up and return the actual command to run
        return mainCommandToRun + argA + argB + argC + argD + argE + inputQuery;
    }


    private List<String> runProcess(String builtCommandToRun) {

        List<String> error = Shell.SU.run(builtCommandToRun).stderr;
        List<String> result = Shell.SU.run(builtCommandToRun).stdout;

        return error.size() > 0 ? error : result;

    }
}
