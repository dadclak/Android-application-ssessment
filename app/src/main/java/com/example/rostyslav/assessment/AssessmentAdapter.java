package com.example.rostyslav.assessment;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import java.util.List;
import android.view.LayoutInflater;
import android.widget.RadioGroup;
import android.widget.TextView;

//create your own Assessment model Adapters
public class AssessmentAdapter extends ArrayAdapter<ModelAssessment>  {

    // list definition's models estimates
       private List<ModelAssessment> assessmentList;
       private Activity context; // create a new Activity object

    // the constructor of an adapter
    public AssessmentAdapter(Activity context, List<ModelAssessment> assessmentList) {
        super(context, R.layout.activity_list_assessment);
          this.context = context;
          this.assessmentList = assessmentList; //// assignment assessment sheets
    }

    // overriding methods class ModelAssessment
    //  method return the size of the list
    @Override
    public int getCount() {
        return assessmentList.size();
    }

    @Override
    public ModelAssessment getItem(int position) {
        return assessmentList.get(position);
    }

    @Override
    public int getPosition(ModelAssessment assessment) {
          return super.getPosition(assessment);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View view = null;

        // check that there is already a view
           if(convertView == null) {
               // if not, we create "component Inflation"
               LayoutInflater inflater = context.getLayoutInflater();
               //to avoid possible falling of the application.
                view = inflater.inflate(R.layout.activity_list_assessment, null);
           } else
               view = convertView;

        // radiogroup definitions, download to view
          RadioGroup radioGroup = (RadioGroup)view.findViewById(R.id.groupRBtn);
            radioGroup.setTag(assessmentList.get(position));

        // checks which button is selected and save check to list
              radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                  @Override
                  public void onCheckedChanged(RadioGroup group, int checkedId) {

                       switch (group.getCheckedRadioButtonId()) {
                           case R.id.radioBtn2:
                              assessmentList.get(position).setAssessment(2);
                              break;
                           case R.id.radioBtn3:
                               assessmentList.get(position).setAssessment(3);
                               break;
                           case  R.id.radioBtn4:
                               assessmentList.get(position).setAssessment(4);
                               break;
                           case  R.id.radioBtn5:
                               assessmentList.get(position).setAssessment(5);
                               break;
                       }
                  }
              });

        //default is 2
          radioGroup.check(R.id.radioBtn2);

        // definitions of TextView estimates
        TextView textAss = (TextView)view.findViewById(R.id.textAssessment);
          textAss.setText(assessmentList.get(position).getName());

        // check whether there are estimates, if Yes, then record the selected
          if(assessmentList.get(position).getAssessment() > 0) {
              switch (assessmentList.get(position).getAssessment()) {
                  case 2:
                       radioGroup.check(R.id.radioBtn2);
                       break;
                  case 3:
                      radioGroup.check(R.id.radioBtn3);
                      break;
                  case 4:
                      radioGroup.check(R.id.radioBtn4);
                      break;
                  case 5:
                      radioGroup.check(R.id.radioBtn5);
                      break;
              }
          }

              return view;
    }
}
