package com.example.quiz_app;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView questionTextview;
    TextView totalQuestionTextview;
    Button ansA,ansB,ansC,ansD;
    Button btn_submit;

    int score=0;
    int totalQuestion= questionAnswer.question.length;
    int currentQuestionIndex=0;
    String selectedAnswer= "";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        totalQuestionTextview=findViewById(R.id.total_question);
        questionTextview=findViewById(R.id.question);
        ansA=findViewById(R.id.ans_a);
        ansB=findViewById(R.id.ans_b);
        ansC=findViewById(R.id.ans_c);
        ansD=findViewById(R.id.ans_d);
        btn_submit=findViewById(R.id.btn_submit);


        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);
        ansD.setOnClickListener(this);
        btn_submit.setOnClickListener(this);


        totalQuestionTextview.setText("Total question"+totalQuestion);

        loadNewQuestion();

    }

    private  void loadNewQuestion(){
        if(currentQuestionIndex == totalQuestion){
            finishQuiz();
            return;
        }
        questionTextview.setText(questionAnswer.question[currentQuestionIndex]);
        ansA.setText(questionAnswer.choice[currentQuestionIndex][0]);
        ansB.setText(questionAnswer.choice[currentQuestionIndex][1]);
        ansC.setText(questionAnswer.choice[currentQuestionIndex][2]);
        ansD.setText(questionAnswer.choice[currentQuestionIndex][3]);

        selectedAnswer="";
    }
      private void finishQuiz(){
        String passStatus;
        if(score >= totalQuestion*0.6){
            passStatus="passed";
        }
        else{
            passStatus="fail";
        }
        new AlertDialog.Builder(this)
                .setTitle(passStatus)
                .setMessage("your score is "+score+"out of"+totalQuestion)
                .setPositiveButton("Restart",((dialog,i)-> restartQuiz() ))
                .setCancelable(false)
                .show();
    }
    private void restartQuiz(){
        score=0;
        currentQuestionIndex=0;
        loadNewQuestion();
    }
     @Override

    public void onClick (View view){
        ansA.setBackgroundColor(Color.WHITE);
        ansB.setBackgroundColor(Color.WHITE);
        ansC.setBackgroundColor(Color.WHITE);
        ansD.setBackgroundColor(Color.WHITE);


        Button clickedButton = (Button) view;
        if(clickedButton.getId()==R.id.btn_submit){
            if(!selectedAnswer.isEmpty()){
                if(selectedAnswer.equals(questionAnswer.correctAnswer[currentQuestionIndex])) {
                    score++;
                }
                else{
                    clickedButton.setBackgroundColor(Color.MAGENTA);
                }
                currentQuestionIndex++;
                loadNewQuestion();
            }
            else{
            }
        }
        else{
                selectedAnswer=clickedButton.getText().toString();
                clickedButton.setBackgroundColor(Color.YELLOW);

        }
    }
}
