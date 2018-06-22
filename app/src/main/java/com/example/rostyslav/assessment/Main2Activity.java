package com.example.rostyslav.assessment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

// second window for entering ratings
public class Main2Activity extends AppCompatActivity {

    private int number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // to create a new bundle
          Bundle bundle = getIntent().getExtras();
            number = bundle.getInt("number");

        // modelow collection declarations of estimates
          final ArrayList<ModelAssessment> asses = new ArrayList<ModelAssessment>();

        // add new items to the collection (add TextView scores)
            for(int i=1; i<=number; i++) {
                asses.add(new ModelAssessment("Assessment " + i));
            }

            //declaration of the private adapter of the ListView and associated
            AssessmentAdapter adapter = new AssessmentAdapter(this,asses);
              ListView viewAss = (ListView)findViewById(R.id.viewAssessment);
               viewAss.setAdapter(adapter); // connected by the list adapter.

        // button maintenance, taking the result of evaluations
        Button ready = (Button)findViewById(R.id.ready);
         ready.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                 // create new bundle result
                 Bundle result = new Bundle();
                   float average = 0;

                 // assigning ListView with XML
                 ListView assessList = (ListView)findViewById(R.id.viewAssessment);

                 // record the number of ratings
                  int count = assessList.getAdapter().getCount();
                     for(int i=0; i < count; i++) {

                         // create a model object, estimates the summation of the total estimated
                           ModelAssessment model = (ModelAssessment) assessList.getAdapter().getItem(i);
                              average += model.getAssessment();
                     }

                    // calculate the average rating
                       average = average / count;

                 // transfer of average assessment to another Activity
                   result.putFloat("Average", average);

                 // switch to another activity
                 Intent finish = new Intent();
                   finish.putExtras(result);
                    setResult(RESULT_OK, finish);
                    finish();
             }
         });

    }
}
