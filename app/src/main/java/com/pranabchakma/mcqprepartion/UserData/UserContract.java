package com.pranabchakma.mcqprepartion.UserData;

import android.provider.BaseColumns;

/**
 * Created by Pranab on 3/29/2018.
 */

public final class UserContract  {
    private UserContract(){}
    public static final class UserEntry implements BaseColumns{
        public final static String TABLE_NAME = "users";
        public final static String _ID = BaseColumns._ID;
        public final static String CATEGORY = "category";
        public final static String LEVEL = "level";
        public final static String TOTAL_ANSWERED = "total_answered";
        public final static String CORRECT_ANSWER = "correct_answer";
        public final static String WRONG_ANSWER = "wrong_answer";
        public final static String DATE_TIME  = "date_time_now";
    }
}
