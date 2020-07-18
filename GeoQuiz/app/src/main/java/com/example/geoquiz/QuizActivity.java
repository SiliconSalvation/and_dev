package com.example.geoquiz;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    private Button trueButton;
    private Button falseButton;
    private ImageButton nextButton;
    private ImageButton prevButton;
    private TextView questionTextView;
    private LinearLayout ll;

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
        setContentView(R.layout.activity_quiz);

        questionTextView = (TextView) findViewById(R.id.question_text_view);

        /**
         * Buttons
         */
        trueButton = (Button) findViewById(R.id.true_button);
        falseButton = (Button) findViewById(R.id.false_button);
        nextButton = (ImageButton) findViewById(R.id.next_button);
        prevButton = (ImageButton) findViewById(R.id.prev_button);
        ll = (LinearLayout) findViewById(R.id.mainlayout);

        /**
         * Listeners
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

        ll.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                updateIndex(true);
                updateQuestion();
            }
        });

        updateQuestion();
    }

    /**
     * Updates the index based on whether the user pressed the Next Button
     * or Previous Button
     * @param nextButtonPressed A boolean value indicating true if the next
     * button was pressed or false if the prev button was pressed
     */
    private void updateIndex(boolean nextButtonPressed) {

        if(nextButtonPressed) {
            currentIndex++;
            currentIndex = currentIndex % questionBank.length;
        } else {
            if(currentIndex == 0) { currentIndex = questionBank.length; }
            currentIndex--;
            currentIndex = currentIndex % questionBank.length;
        }
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