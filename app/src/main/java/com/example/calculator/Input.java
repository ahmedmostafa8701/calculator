package com.example.calculator;

import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

class Input {

    List<String> operand;
    List <String> operation;

    public Input() {
        operand = new ArrayList<>();
        operation = new ArrayList<>();
    }
    public void setInput(String s) {
        operand.clear();
        operation.clear();
        String op = "";
        for (int i = 0, length = s.length(); i < length; i++) {
            if(Character.isDigit(s.charAt(i)) || s.charAt(i) == '.'){
                op += String.valueOf(s.charAt(i));
            }
            else{
                operand.add(op);
                op = "";
                operation.add(String.valueOf(s.charAt(i)));
            }
        }
        if(Character.isDigit(s.charAt(s.length() - 1)) || s.charAt(s.length() - 1) == '.'){
            operand.add(op);
        }
    }
}
