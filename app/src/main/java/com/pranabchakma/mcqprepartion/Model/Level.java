package com.pranabchakma.mcqprepartion.Model;

/**
 * Created by Pranab on 3/26/2018.
 */

public class Level {
    String category;
    int level;

    public Level(String category, int level) {
        this.category = category;
        this.level = level;
    }

    public String getCategory() {
        return category;
    }

    public int getLevel() {
        return level;
    }
}
