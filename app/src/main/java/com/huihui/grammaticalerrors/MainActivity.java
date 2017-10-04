package com.huihui.grammaticalerrors;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private AdView mAdView;

    private TextView textView3;
    private TextView textView2;
    private EditText editText;
    private EditText editText2;

    private String wrongAnswer;
    private String rightAnswer;
    private int rightAnswerCount = 0;
    private int quizCount = 1;
    static final private int QUIZ_COUNT = 20;

    ArrayList<ArrayList<String>> quizArray = new ArrayList<>();

    String quizData[][] = new String[][]{
            //{"Country", "Right Answer", "Choice1", "Choice2", "Choice3"}

            {"My cousin is go to San Francisco next month.", "go", "going"},
            {"Andrew enjoy collecting baseball cards and action figures.", "enjoy", "enjoys"},
            {"Marcie swim in the Pacific Ocean last week.", "swim", "swam"},
            {"The teacher has three important case to teach on next month.", "case", "cases"},
            {"We walked across the Golden Gate Bridge during their vacation.", "their", "our"},
            {"My brother will be go to Journey Middle School next year.", "go", "going"},
            {"His favorite author are Christopher Paolini.", "are", "is"},
            {"Mandy named his new puppy Toby.", "his", "her"},
            {"The first country he wants to visited is Australia.", "visited", "visit"},
            {"There is 12 months  in a year.", "is", "are"},
            {"I lives in a small town in England.", "lives", "live"},
            {"Call me when you arrive on the hotel.", "on", "at"},
            {"I have a check-up with the doctor in Friday.", "in", "on"},
            {"Lisa work as a programmer at Microsoft.", "work", "works"},
            {"The pupils love Miss Lee because he is a kind", "he", "she"},
            {"John use a pair of rollerblades while playing at the park.", "use", "uses"},
            {"My aunts does their laundry every day.", "does", "do"},
            {"We have our breakfast at about 7 o'clock yesterday morning.", "have", "had"},
            {"Pei Yan has a lot of fun at the party last Sunday.", "has", "had"},
            {"James and his family has lived in Johor for eight years.", "has", "have"},
            {"Aman wants to make a paper weight. First, he cleans a stone with soap or water.", "or", "and"}

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        textView3 = (TextView) findViewById(R.id.textView3);
        textView2 = (TextView) findViewById(R.id.textView2);
        editText = (EditText) findViewById(R.id.editText);
        editText2 = (EditText) findViewById(R.id.editText2);


        //Create QuizArray From quizData.
        for (int i = 0; i < quizData.length; i++) {

            //prepare array
            ArrayList<String> tmpArray = new ArrayList<>();
            tmpArray.add(quizData[i][0]); //Question/soalan
            tmpArray.add(quizData[i][1]); //wrongAnswer
            tmpArray.add(quizData[i][2]); //correctAnswer


            //Add tmpArray to the quizarray
            quizArray.add(tmpArray);

        }

        showNextQuiz();


    }

    private void showNextQuiz() {

        //Update quizCountLable.
        textView3.setText("Q" + quizCount);

        //Genarate random number between 0 and 4
        Random random = new Random();
        int randomNum = random.nextInt(quizArray.size());

        //pick one quiz set
        ArrayList<String> quiz = quizArray.get(randomNum);

        //setquestion and right answer
        //Array format:
        textView2.setText(quiz.get(0));
        wrongAnswer = quiz.get(1);
        rightAnswer = quiz.get(2);

        //Ramove country from quiz and shuffle choice.
        quiz.remove(0);
        Collections.shuffle(quiz);

        //Rwmove this quiz from quizArray
        quizArray.remove(randomNum);
    }

    public void submit(View view) {

        String alertTitle;
        if (editText.getText().toString().equals(wrongAnswer) && editText2.getText().toString().equals(rightAnswer)) {
            //correct
            alertTitle = "Correct!!";
            quizCount++;
            editText.setText("");
            editText2.setText("");
            showNextQuiz();
        } else {
            //wrong
            alertTitle = "Wrong!! Tip= " + wrongAnswer;
            rightAnswerCount++;

        }

        //Create Dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(alertTitle);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener(){


            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (quizCount == QUIZ_COUNT){
                    //Show result
                    Intent intent = new Intent(getApplicationContext(),ResultActivity.class);
                    intent.putExtra("RIGHT_ANSWER_COUNT",rightAnswerCount);
                    startActivity(intent);

                }
            }
        });
        builder.setCancelable(false);
        builder.show();
    }
}
