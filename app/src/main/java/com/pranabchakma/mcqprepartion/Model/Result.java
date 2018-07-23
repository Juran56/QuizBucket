package com.pranabchakma.mcqprepartion.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Pranab on 3/9/2018.
 */

public class Result implements Parcelable {
    String questions;
    String correct_answer;
    String given_answer;
    boolean isCorrect;

    public Result(String questions, String correct_answer, boolean isCorrect) {
        this.questions = questions;
        this.correct_answer = correct_answer;
        this.given_answer = given_answer;
        this.isCorrect = isCorrect;
    }
    public Result(String questions, String correct_answer, String given_answer, boolean isCorrect) {
        this.questions = questions;
        this.correct_answer = correct_answer;
        this.given_answer = given_answer;
        this.isCorrect = isCorrect;
    }


    public String getQuestions() {
        return questions;
    }

    public String getCorrect_answer() {
        return correct_answer;
    }

    public String getGiven_answer() {
        return given_answer;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(questions);
        parcel.writeString(correct_answer);
        parcel.writeString(given_answer);
        parcel.writeByte((byte)(isCorrect?1:0));
    }

    public Result(Parcel parcel){
        questions = parcel.readString();
        correct_answer = parcel.readString();
        given_answer = parcel.readString();
        isCorrect = parcel.readByte()!=0;
    }
    public static final Parcelable.Creator<Result> CREATOR = new Parcelable.Creator<Result>(){

        @Override
        public Result createFromParcel(Parcel parcel) {
            return new Result(parcel);
        }

        @Override
        public Result[] newArray(int i) {
            return new Result[i];
        }
    };
}
