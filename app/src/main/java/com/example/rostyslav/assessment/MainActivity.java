package com.example.rostyslav.assessment;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.text.TextWatcher;
import android.app.Activity;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

//// main application Activity, for data entry
public class MainActivity extends AppCompatActivity {

    // Declaration of variables (buttons, input fields)
    protected Button assButton;
    protected EditText eName, eSurname, eRatings;
    protected String name, surname;
    private boolean boolName = false, boolSurname = false, boolRating = false;
    private int ratings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // read input fields
        eName = (EditText) findViewById(R.id.editName);
        eSurname = (EditText) findViewById(R.id.editSurname);
        eRatings = (EditText) findViewById(R.id.editRatings);

        // the enter button is initially invisible until all fields are correctly executed
        assButton = (Button) findViewById(R.id.butttonAssessment);
        assButton.setVisibility(View.INVISIBLE);

        // track name input field
        eName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            // when focusing on the name input field, check if the field is empty
            @Override
            public void afterTextChanged(Editable s) {
                name = eName.getText().toString();
                if(!name.isEmpty()) {
                    boolName = true;
                    setAssButton();
                } else {
                    boolName = false;
                    eName.setError("Please, write your name.");
                    assButton.setVisibility(View.INVISIBLE);
                }
            }
        });

        // process the same input field names as with the name
        eSurname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                surname = eSurname.getText().toString();
                if(!surname.isEmpty()) {
                    boolSurname = true;
                    setAssButton();
                } else {
                    boolSurname = false;
                    eSurname.setError("Please, write your name.");
                    assButton.setVisibility(View.INVISIBLE);
                }
            }
        });

        // a similar tracking fields to enter the amount of the assessment, as with the name
        eRatings.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                try{
                    if (eRatings.getText().toString().equals("")) {
                        eRatings.setError("Number must be to range 5 - 15 .");
                        boolRating = false;
                        assButton.setVisibility(View.INVISIBLE);
                    }
                    else if((Integer.parseInt(eRatings.getText().toString()) < 5) || (Integer.parseInt(eRatings.getText().toString()) > 15)) {
                        eRatings.setError("Number must be to range 5 - 15 .");
                        boolRating = false;
                        assButton.setVisibility(View.INVISIBLE);
                    }
                    else {
                        ratings = Integer.parseInt(eRatings.getText().toString());
                        boolRating = true;
                        setAssButton();
                    }
                } catch (NumberFormatException e){
                    e.printStackTrace();
                }
            }
        });

        // service evaluation buttons
        assButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Main2Activity.class);
                intent.putExtra("number", ratings); // take the number of ratings all otpawic strone on ina
                startActivityForResult(intent, 0); // redirect to another page
            }
        });
    }

    // method of enabling visibility of the button
    private void setAssButton() {

        // check whether input fields are completed
        if(boolName == true && boolSurname == true && boolRating == true)
            assButton.setVisibility(View.VISIBLE);
        else
            assButton.setVisibility(View.INVISIBLE);
    }

    // the final method shows the result
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // final phrases
        String congratulations = "Congratulation!";
        String regret = "To regret in the other times";

        // if everything is in order, the number is transmitted ...
        if(resultCode == RESULT_OK) {

            // create a new Bundle
            final Bundle finish = data.getExtras();

            // download number redirection
            float number = finish.getFloat("Average");

            // enable TextView
            TextView textAverage = (TextView)findViewById(R.id.textAverage);
             textAverage.setVisibility(View.VISIBLE);

            TextView textRatings = (TextView)findViewById(R.id.average);
             textRatings.setText("" + number);
              textRatings.setVisibility(View.VISIBLE);

            eName.setFocusable(false);
            eSurname.setFocusable(false);
            eRatings.setFocusable(false);

            // check whether the delivery, setting the color of the button
            if(number > 3) {
                assButton.setText(congratulations);
                  assButton.setBackgroundColor(Color.GREEN);
            } else {
                assButton.setText(regret);
                  assButton.setBackgroundColor(Color.RED);
            }

            // completion is scheduled for the program
            assButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // phrase output, congratulations
                    Toast.makeText(MainActivity.this, "Congratulation! You will receive a diploma", Toast.LENGTH_SHORT).show();
                    finish();
                }
            });
        }
    }
}
