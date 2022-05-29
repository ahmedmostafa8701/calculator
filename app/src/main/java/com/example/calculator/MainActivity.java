package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.lang.String;

public class MainActivity extends AppCompatActivity {

    Calculations calculations;
    Input input = new Input();
    TextView typing, result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calculations = new Calculator();
        typing = (TextView) findViewById(R.id.input);
        result = (TextView) findViewById(R.id.result);
    }

    public void setInput(View view){
        Button btnRef = ((Button) view);
        String s = typing.getText().toString();
        typing.setText(typing.getText().toString() + btnRef.getText());
    }

    public void clear(View view){
        typing.setText("");
        result.setText("");
    }

    public void remove(View view){
        StringBuffer s = new StringBuffer(typing.getText().toString());
        s.deleteCharAt(s.length() - 1);
        typing.setText(s.toString());
        result.setText("");
    }

    public void calc(View view){
        String s = typing.getText().toString();
        try {
            input.setInput(s);
            result.setText(calculations.getResult(input));
        }catch (Exception e){
            result.setText("Error");
        }
    }

}