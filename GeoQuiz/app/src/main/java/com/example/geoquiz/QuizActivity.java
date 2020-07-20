package com.example.geoquiz;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    private static final String TAG = "QuizActivity";
    private static final String KEY_INDEX = "index";

    private Button trueButton;
    private Button falseButton;
    private ImageButton nextButton;
    private ImageButton prevButton;
    private TextView questionTextView;
    //private LinearLayout ll;

    private Question[] questionBank = new Question[] {
            new Question(R.string.question_australia, true),
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, true)
    };

    private int currentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle) called");
        setContentView(R.layout.activity_quiz);

        if(savedInstanceState != null) {
            currentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        }

        questionTextView = (TextView) findViewById(R.id.question_text_view);

        /**
         * Button references
         */
        trueButton = (Button) findViewById(R.id.true_button);
        falseButton = (Button) findViewById(R.id.false_button);
        nextButton = (ImageButton) findViewById(R.id.next_button);
        prevButton = (ImageButton) findViewById(R.id.prev_button);
        //ll = (LinearLayout) findViewById(R.id.mainlayout);

        /**
         * Listeners for buttons
         */
        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
                //t.setGravity(Gravity.TOP, 15 , 15);  //Doesn't Work, Cannot call setGravity on text toast now
          }
        });

        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });

        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateIndex(false);
                updateQuestion();
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateIndex(true);
                updateQuestion();
            }
        });
        //Doesn't work with the new layout
//        ll.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                updateIndex(true);
//                updateQuestion();
//            }
//        });

        updateQuestion();
    }

    /**
     * Overrides onStart() and Logs the action
     */
    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    /**
     * Overrides onResume() and Logs the action
     */
    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    /**
     * Overrides onPause() and Logs the action
     */
    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSavedInstanceState");
        savedInstanceState.putInt(KEY_INDEX, currentIndex);
    }

    /**
     * Overrides onStop() and Logs the action
     */
    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    /**
     * Overrides onDestroy and Logs the action
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }

    /**
     * Updates the index based on whether the user pressed the Next Button
     * or Previous Button
     * @param nextButtonPressed A boolean value indicating true if the next
     * button was pressed or false if the prev button was pressed
     */
    private void updateIndex(boolean nextButtonPressed) {

        if (nextButtonPressed) {
            currentIndex++;
        } else {
            if(currentIndex == 0) {
                currentIndex = questionBank.length;
            }
            currentIndex--;
        }
        currentIndex = currentIndex % questionBank.length;
    }

    /**
     * updates the question to be displayed
     */
    private void updateQuestion() {
        int question = questionBank[currentIndex].getTextResId();
        questionTextView.setText(question);
    }

    /**
     * determines whether the answer is correct or not.
     * @param userAnswer a boolean, it is true if the answer is correct
     * or false if the answer is incorrect.
     */
    private void checkAnswer(boolean userAnswer) {
        boolean answerIsTrue = questionBank[currentIndex].isAnswerTrue();

        int messageResId = 0;

        if (userAnswer == answerIsTrue) {
            messageResId = R.string.correct_toast;
        } else {
            messageResId = R.string.false_toast;
        }

        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }

}