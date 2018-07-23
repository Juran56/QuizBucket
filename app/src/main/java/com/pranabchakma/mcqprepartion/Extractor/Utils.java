package com.pranabchakma.mcqprepartion.Extractor;

import com.pranabchakma.mcqprepartion.Model.Question;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Pranab on 3/8/2018.
 */

public class Utils {
    public Utils(){}
    public static List<Question> extractQuestion(String txt, String category){
        List<Question> questions = new ArrayList<>();
        List<String> options = new ArrayList<>();
        try {
            JSONObject root = new JSONObject(txt);
            JSONArray arr = root.getJSONArray(category);
            for (int i = 0; i<arr.length();i++){
                JSONObject currentQuestion = arr.getJSONObject(i);
                String questiontxt = currentQuestion.getString("Question");
                String optionOne = currentQuestion.getString("optionOne");
                String optionTwo = currentQuestion.getString("optionTwo");
                String optionThree = currentQuestion.getString("optionThree");
                String optionFour = currentQuestion.getString("optionFour");
                String correctAns = currentQuestion.getString("correctAns");
                options.add(optionOne);
                options.add(optionTwo);
                options.add(optionThree);
                options.add(optionFour);
                Collections.shuffle(options);
                Question question = new Question(questiontxt,options.get(0),options.get(1),options.get(2),options.get(3),correctAns);
                questions.add(question);
                options.clear();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return questions;
    }
}
