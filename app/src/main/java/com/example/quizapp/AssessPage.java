// AssessPage.java
package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AssessPage extends AppCompatActivity implements View.OnClickListener {

    Button mathBtn;
    Button scienceBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_assess_page);

        mathBtn = findViewById(R.id.MathBtn);
        scienceBtn = findViewById(R.id.ScienceBtn);

        mathBtn.setOnClickListener(this);
        scienceBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(AssessPage.this, QuizActivity.class);

        if (view.getId() == R.id.MathBtn) {
            intent.putExtra("Subject", "Math");
        } else if (view.getId() == R.id.ScienceBtn) {
            intent.putExtra("Subject", "Science");
        }

        startActivity(intent);
    }


}
