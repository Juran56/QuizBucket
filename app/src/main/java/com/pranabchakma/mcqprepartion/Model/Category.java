package com.pranabchakma.mcqprepartion.Model;

/**
 * Created by Pranab on 3/5/2018.
 */

public class Category {
    String CategoryName;
    String CategoryValue;

    public Category() {
    }

    public Category(String categoryName, String categoryValue) {
        CategoryName = categoryName;
        CategoryValue =categoryValue;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public String getCategoryValue() {
        return CategoryValue;
    }
}
