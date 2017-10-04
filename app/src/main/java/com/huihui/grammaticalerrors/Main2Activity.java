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

public class Main2Activity extends AppCompatActivity {

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

            {"The field was decorated with many colourful flag.", "flag", "flags"},
            {"The field was decorate with many colourful flags." , "decorate", "decorated"},
            {"Jimmy takes part in a race yesterday.", "takes", "took"},
            {"He wins the event and received a big trophy.", "wins", "won"},
            {"He won the event and received a big trophys.", "trophys", "trophy"},
            {"She was crying sadly so she broke the cup.", "so", "because"},
            {"The boy was as hungry as a wolf because he does not have his breakfast before he came to school.", "does", "did"},
            {"The girls and the boys are play in the park", "play", "playing"},
            {"The pupils are sitting for them examination in the library.", "them", "their"},
            {"The pupils is sitting for their examination in the library.", "is", "are"}, //10
            {"Once upon a time, there was an old man whom lived in a remote village in China.", "whom", "who"},
            {"The pupils love Miss Lee because he is a kind", "he", "she"},
            {"No one go home without the headmaster's permission.", "go", "goes"},
            {"Let me know if someone knock the door.", "knock", "knocks"},
            {"Michael is hardworking and impolite.", "and", "but"},
            {"Take a rest unless you are tired.", "unless", "if"},
            {"Mr Lee's car was dirty or he washed it.", "or", "so"},
            {"You cannot go to school because you have recovered from your illness.", "because", "unless"},
            {"Please keep silent if you will have to leave the library.", "if", "or"},
            {"Emily is buy a new computer soon.", "buy", "buying"}, //20
            {"Switches off your cell phones, thank you.", "Switches", "Switch"}



    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

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

    public void submit2(View view) {
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
