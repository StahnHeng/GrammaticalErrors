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

public class Main3Activity extends AppCompatActivity {

    private AdView mAdView;

    private TextView textView3;
    private TextView textView2;
    private EditText editText;
    private EditText editText2;

    private String wrongAnswer;
    private String rightAnswer;
    private int rightAnswerCount = 0;
    private int quizCount = 1;
    static final private int QUIZ_COUNT = 18;

    ArrayList<ArrayList<String>> quizArray = new ArrayList<>();

    String quizData[][] = new String[][]{
            //{"Country", "Right Answer", "Choice1", "Choice2", "Choice3"}

            {"Sara and her family go to Pulau Pinag during the last school holiday.", "go", "went"},
            {"Roza is serve drinks to her neighbours.", "serve", "serving"},
            {"Kamal is serving drinks to her neighbours.", "her", "his"},
            {"Encik Adam and his family goes to mosque on Friday.", "goes", "go"},
            {"They buy a lot of things and they want to put the thing in the car.", "thing", "things"},
            {"The man who you met yesterday is an actor.", "who", "whom"},
            {"The woman who I lent my is a nurse.", "who", "whom"},
            {"Where are the books whom I borrowed from the library?", "whom", "which"},
            {"This is the cat who scratched me.", "who", "which"},
            {"Your magazine is as thicker as mine.", "thicker", "thick"}, //10
            {"Question one is easy than question two.", "easy", "easier"},
            {"This is the sweet apple that I have ever eaten", "sweet", "sweetest"},
            {"Among the ten question, this is the difficult to answer.", "difficult", "most difficult"},
            {"I ran quick all the way to school this morning.", "quick", "quickly"},
            {"Mr Lioo works hardly to support his family.", "hardly", "hard"}, //15
            {"It has rained continuous for eight hours.", "continuous", "continuously"},
            {"Hui Xing was doing a jigsaw puzzle while the doorbell rang.", "while", "when"},
            {"I was reading while my mother called out to me.", "while", "when"},
            {"When we were jogging in the park, we heard a scream.", "When", "While"}

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
            alertTitle = "Wrong!! Tips = " + wrongAnswer ;
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
