package com.example.rostyslav.assessment;

  // create your own klassy (evaluation model)
public class ModelAssessment {

         private String name;
         private int assessment;

        // designer, name of evaluation
         public ModelAssessment(String name) {
             this.name = name;
         }

        // geters and seters
         public void setName(String name) {
             this.name = name;
         }

         public String getName() {
             return name;
         }

         public void setAssessment(int assessment) {
             this.assessment = assessment;
         }

         public  int getAssessment() {
             return assessment;
         }
}
