package com.example.calculator;

import android.widget.Button;

interface Calculations {
    public String calculate(String operand1, String operation, String operand2);
    public String getResult(Input input);
}
