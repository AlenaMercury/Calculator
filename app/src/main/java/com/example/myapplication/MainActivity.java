package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    Button mBtn0;
    Button mBtn1;
    Button mBtn2;
    Button mBtn3;
    Button mBtn4;
    Button mBtn5;
    Button mBtn6;
    Button mBtn7;
    Button mBtn8;
    Button mBtn9;

    TextView mDisplay;

    Button mBackSpace;
    Button mClear;
    Button mComma;
    Button mSign;

    Button mPlus;
    Button mMinus;
    Button mDiv;
    Button mMul;
    Button mResult;

    float mValue = 0;
    String mOperator = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtn0 = findViewById(R.id.btn0);
        mBtn1 = findViewById(R.id.btn1);
        mBtn2 = findViewById(R.id.btn2);
        mBtn3 = findViewById(R.id.btn3);
        mBtn4 = findViewById(R.id.btn4);
        mBtn5 = findViewById(R.id.btn5);
        mBtn6 = findViewById(R.id.btn6);
        mBtn7 = findViewById(R.id.btn7);
        mBtn8 = findViewById(R.id.btn8);
        mBtn9 = findViewById(R.id.btn9);

        mDisplay = findViewById(R.id.display);

        mBackSpace = findViewById(R.id.backspace);
        mClear = findViewById(R.id.clear);
        mComma = findViewById(R.id.btnComma);

        mSign = findViewById(R.id.changeSign);

        mPlus = findViewById(R.id.btnPlus);
        mMinus = findViewById(R.id.btnMinus);
        mDiv = findViewById(R.id.btnDiv);
        mMul = findViewById(R.id.btnMul);

        mResult = findViewById(R.id.btnResult);

        View.OnClickListener numberListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNumberClick(v);
            }

            public void onNumberClick(View button) {
                String number = ((Button) button).getText().toString();
                String display = mDisplay.getText().toString();

                if (display.equals("0"))
                    display = number;
                else
                    display += number;

                mDisplay.setText(display);
            }
        };

        mBtn0.setOnClickListener(numberListener);
        mBtn1.setOnClickListener(numberListener);
        mBtn2.setOnClickListener(numberListener);
        mBtn3.setOnClickListener(numberListener);
        mBtn4.setOnClickListener(numberListener);
        mBtn5.setOnClickListener(numberListener);
        mBtn6.setOnClickListener(numberListener);
        mBtn7.setOnClickListener(numberListener);
        mBtn8.setOnClickListener(numberListener);
        mBtn9.setOnClickListener(numberListener);

        View.OnClickListener operatorListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOperatorListener(v);
            }

            public void onOperatorListener(View button) {

                String operator = ((Button) button).getText().toString();
                mOperator = operator;

                String display = mDisplay.getText().toString();
                mValue = Float.parseFloat(display);

                mDisplay.setText("0");
            }
        };

        mPlus.setOnClickListener(operatorListener);
        mMinus.setOnClickListener(operatorListener);
        mMul.setOnClickListener(operatorListener);
        mDiv.setOnClickListener(operatorListener);

        View.OnClickListener resultListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onResultListener(v);
            }

            public void onResultListener(View button) {

                String display = mDisplay.getText().toString();
                float value = Float.parseFloat(display);
                boolean flag = false;
                float result = value;

                switch (mOperator) {
                    case "+": {
                        result = value + mValue;
                        break;
                    }
                    case "-":
                    {
                        result = value - mValue;
                        break;
                    }
                    case "*": {
                        result = value * mValue;
                        break;
                    }
                    case "/": {
                        if (value == 0) {
                            flag = true;
                        }
                        else {
                            flag = false;
                            result = mValue / value;
                            break;
                        }
                    }
                }
                DecimalFormat format = new DecimalFormat("0,######");
                format.setRoundingMode(RoundingMode.DOWN);
                String resultText = format.format(value);

                if (flag) {
                    mDisplay.setText("0");
                }

                else {
                    mDisplay.setText(resultText);

                    mValue = result;
                    mOperator = "";
                }
            }
        };

        mResult.setOnClickListener(resultListener);

        View.OnClickListener signListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSignListener(v);
            }
            public void onSignListener(View button) {

                String display = mDisplay.getText().toString();
                float value = Float.parseFloat(display);

                value = value * -1;

                DecimalFormat format = new DecimalFormat("0,######");
                format.setRoundingMode(RoundingMode.DOWN);
                String resultText = format.format(value);

                mDisplay.setText(resultText);
            }
        };
        mSign.setOnClickListener(signListener);

        View.OnClickListener clearListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClearListener(v);
            }
            public void onClearListener(View button) {

                mValue = 0;
                mOperator = "";
                mDisplay.setText("");
            }
        };
        mClear.setOnClickListener(clearListener);

        View.OnClickListener backspaceListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackspaceListener(v);
            }
            public void onBackspaceListener(View button) {

                String display = mDisplay.getText().toString();
                String result;

                if(display.length()==1)
                    result = "0";
                else
                    result = display.substring(0,display.length()-1);

                mDisplay.setText(result);
            }
        };
        mBackSpace.setOnClickListener(backspaceListener);

        View.OnClickListener commaListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCommaListener(v);
            }
            public void onCommaListener(View button) {

                String display = mDisplay.getText().toString();

                display = display + ",";
                mDisplay.setText(display);
            }
        };
        mComma.setOnClickListener(commaListener);
    }
}


