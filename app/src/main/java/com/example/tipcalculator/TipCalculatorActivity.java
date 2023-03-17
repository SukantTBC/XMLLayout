package com.example.tipcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

import java.text.DecimalFormat;

public class TipCalculatorActivity extends AppCompatActivity {
    double tipPercent = 0;
    int costOfService=0;

    TextView costOfServiceTv;
    TextView resultTv;

    RadioGroup tipRadioGroup;
    Button calculateBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip_calculator);
        costOfServiceTv = findViewById(R.id.editCostOfService);

        resultTv = findViewById(R.id.resultTv);

        tipRadioGroup = findViewById(R.id.radioGroup);
        tipRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId) {
                    case R.id.amazingRadioBtn:
                        tipPercent = 0.20;
                        break;
                    case R.id.goodRadioBtn:
                        tipPercent = 0.18;
                        break;
                    case R.id.okayRadioBtn:
                        tipPercent = 0.15;
                        break;
                }
            }
        });

        RadioButton rbOkay= findViewById(R.id.okayRadioBtn);

        calculateBtn = findViewById(R.id.calculateBtn);
        calculateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                double costOfService;
                double tipAmount;

                int checkedId=tipRadioGroup.getCheckedRadioButtonId();
                if(costOfServiceTv.getText()==""){
                    costOfServiceTv.setError("Enter Cost of Service");
                } else if (checkedId<0) {
                    rbOkay.setError("Select service quality");
                }else{
                    try {
                        costOfService = Double.parseDouble(costOfServiceTv.getText().toString());
                    }catch (Exception e){
                        costOfServiceTv.setError("Invalid Characters");
                        return;
                    }
                    tipAmount = costOfService * tipPercent;
                    DecimalFormat f = new DecimalFormat("##.00");
                    tipAmount = Double.parseDouble(f.format(tipAmount));

                    Switch roundTipSwitch = findViewById(R.id.roundTipSwitch);
                    if(roundTipSwitch.isChecked()){
                        tipAmount = Math.round(tipAmount);
                    }
                    resultTv.setText("Total Tip is: " + tipAmount);
                }
            }
        });
    }
}