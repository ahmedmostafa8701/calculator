package com.example.calculator;

import android.widget.Button;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class Calculator implements Calculations{
    HashMap<String, Integer> degree;

    public Calculator(){
        degree = new HashMap<>();
        degree.put("+", 1);
        degree.put("-", 1);
        degree.put("*", 2);
        degree.put("/", 2);
    }
    @Override
    public String calculate(String operand1, String operation, String operand2) {
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
            return String.valueOf(Double.parseDouble(operand1) / Double.parseDouble(operand2));
        }
        else {
            return "error";
        }
    }
    @Override
    public String getResult(Input input){
        List<String> operand = input.operand;
        List <String> operation = input.operation;
        int i = 0;
        while (operation.size() != 0 && i < operation.size()){
            if(i == operation.size() - 1 || degree.get(operation.get(i)) >= degree.get(operation.get(i + 1))){
                operand.set(i, calculate(operand.get(i), operation.get(i), operand.get(i + 1)));
                operand.remove(i + 1);
                operation.remove(i);
                i = 0;
            }
            else{
                i++;
            }
        }
        return operand.get(0);
    }
}
