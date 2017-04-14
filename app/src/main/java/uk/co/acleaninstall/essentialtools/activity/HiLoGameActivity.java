package uk.co.acleaninstall.essentialtools.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.acleaninstall.essentialtools.R;
import uk.co.acleaninstall.essentialtools.util.MiscTools;

public class HiLoGameActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.resultTextView)
    TextView mResultTextView;

    @BindView(R.id.usersGuessEditText)
    EditText mUsersGuessEditText;

    @BindView(R.id.submitAnswerButton)
    Button mSubmitButton;

    @BindView(R.id.yesButton)
    Button mYesButton;

    @BindView(R.id.noButton)
    Button mNoButton;

    // Number of user attempts
    int userAttempts = 0;

    // Our users guess
    int guess = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hi_lo_game);
        ButterKnife.bind(this);

        setupToolbar();

        // Create a new random number
        final int theNumber = (int) (Math.random() * 100 + 1);

        // Get the users guess
        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Add an attempt
                userAttempts++;

                guess = Integer.parseInt(mUsersGuessEditText.getText().toString());
                mUsersGuessEditText.setText("");

                checkAnswer(theNumber, guess);
            }
        });

    }


    private void checkAnswer(int theNumber, int guess) {
        // If the guess is is too low than the correct number
        if (guess < theNumber)

        {
            mResultTextView.setText(
                String.format("%s is too low try again", String.valueOf(guess)));
        }

        // If the guess is is too high than the correct number
        else if (guess > theNumber)

        {
            mResultTextView.setText(
                String.format("%s is too high try again", String.valueOf(guess)));
        }

        // If the guess is correct
        else {

            MiscTools.hideSoftKeyboard(this, getCurrentFocus());

            // Ask user if he / she wants to play again
            mResultTextView.setText(
                String.format("Well done you got it in %s moves! Play again Y/N", userAttempts));

            // Show the Yes / No Buttons
            mYesButton.setVisibility(View.VISIBLE);
            mNoButton.setVisibility(View.VISIBLE);

            if (mYesButton.getVisibility() == View.VISIBLE) {
                mUsersGuessEditText.setEnabled(false);
                mSubmitButton.setEnabled(false);
            }

            // Set onClicks
            mYesButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    HiLoGameActivity.this.recreate();
                }
            });

            mNoButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });

        }
    }


    private void setupToolbar() {

        setSupportActionBar(mToolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            // Set the top padding to bring the view down
            mToolbar.setPadding(0, MiscTools.getStatusBarHeight(), 0, 0);
        }

    }
}
