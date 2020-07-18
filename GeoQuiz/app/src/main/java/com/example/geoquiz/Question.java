package com.example.geoquiz;

/**
 * Question is a class that models a question being asked.
 */
public class Question {
    private int textResId;
    private boolean answerTrue;

    /**
     * Constructor
     * @param textResId and integer of the id of the text area for the question
     * @param answerTrue a boolean indicating if the answer is true or false
     */
    public Question(int textResId, boolean answerTrue) {
        this.textResId = textResId;
        this.answerTrue = answerTrue;
    }

    /**
     * getter for textResId field
     * @return returns an int representing the id of the text area for the question
     */
    public int getTextResId() {
        return this.textResId;
    }

    /**
     * setter textResId field
     * @param textResId an integer for textResId
     */
    public void setTextResId(int textResId) {
        this.textResId = textResId;
    }

    /**
     * a boolean determining the whether the question is true false
     * @return a boolean for the answer of the question
     */
    public boolean isAnswerTrue() {
        return this.answerTrue;
    }

    /**
     * Setter for answerTrue
     * @param answerTrue boolean value
     */
    public void setAnswerTrue(boolean answerTrue) {
        this.answerTrue = answerTrue;
    }
}
