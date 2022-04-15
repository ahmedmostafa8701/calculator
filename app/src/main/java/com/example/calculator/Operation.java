package com.example.calculator;

import android.widget.Button;

class Operation {
    public static void setEnable(Boolean statue, Button... btn){
        for (Button button:btn) {
            button.setEnabled(statue);
        }
    }
    public static String calculate(String operand1, String operation, String operand2){
        if(operation.equals("+")){
            return String.valueOf(Double.parseDouble(operand1) + Double.parseDouble(operand2));
        }
        else if(operation.equals("-")){
            return String.valueOf(Double.parseDouble(operand1) - Double.parseDouble(operand2));
        }
        else if(operation.equals("*")){
            return String.valueOf(Double.parseDouble(operand1) * Double.parseDouble(operand2));
        }
        else if(operation.equals("/")){
            if(operand2.equals("0")){
                return "error you can't divide by 0";
            }
            return String.valueOf(Double.parseDouble(operand1) / Double.parseDouble(operand2));
        }
        else {
            return "error";
        }
    }
}
