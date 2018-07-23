package com.pranabchakma.mcqprepartion.Model;

/**
 * Created by Pranab on 3/5/2018.
 */

public class Question {
    private String questionText, optionOne, optionTwo, optionThree,optionFour, correctAns;

    public Question(String questionText, String optionOne, String optionTwo, String optionThree, String optionFour, String correctAns) {
        this.questionText = questionText;
        this.optionOne = optionOne;
        this.optionTwo = optionTwo;
        this.optionThree = optionThree;
        this.optionFour = optionFour;
        this.correctAns = correctAns;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String getOptionOne() {
        return optionOne;
    }

    public String getOptionTwo() {
        return optionTwo;
    }

    public String getOptionThree() {
        return optionThree;
    }

    public String getOptionFour() {
        return optionFour;
    }

    public String getCorrectAns() {
        return correctAns;
    }
}
