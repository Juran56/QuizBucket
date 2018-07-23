package com.pranabchakma.mcqprepartion.Model;

/**
 * Created by Pranab on 3/29/2018.
 */

public class UserData {
    String Category;
    String level;
    String Datetime;
    String total;
    String correct;
    String wrong;

    public UserData(String category, String level, String datetime, String total, String correct,String wrong) {
        Category = category;
        this.level = level;
        Datetime = datetime;
        this.total = total;
        this.correct = correct;
        this.wrong = wrong;
    }

    public String getCategory() {
        return Category;
    }

    public String getLevel() {
        return level;
    }

    public String getDatetime() {
        return Datetime;
    }

    public String getTotal() {
        return total;
    }

    public String getCorrect() {
        return correct;
    }

    public String getWrong() {
        return wrong;
    }
}
