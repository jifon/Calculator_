package com.example.calculator_version1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;



public class MainActivity extends AppCompatActivity {

    String operator = "";
    String oldNumber;
    Boolean isNew = true;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
    }

    public void clickNumber(View view) {
        //по дефолту в самом начале стоит 0,но мы прировняли её к пустоте
        if(isNew)
            editText.setText("");
        isNew=false;


        String number = editText.getText().toString();

        switch (view.getId()){
            case R.id.bu1: number= number +"1"; break;
            case R.id.bu2: number= number +"2"; break;
            case R.id.bu3: number= number +"3"; break;
            case R.id.bu4: number= number +"4"; break;
            case R.id.bu5: number= number +"5"; break;
            case R.id.bu6: number= number +"6"; break;
            case R.id.bu7: number= number +"7"; break;
            case R.id.bu8: number= number +"8"; break;
            case R.id.bu9: number= number +"9"; break;
            case R.id.bu0: number= number +"0"; break;

            case R.id.buDot:
                if (dotIsPresent(number)){    }                 //если точки присутствуют
                else { number = number + ".";}                  //если точки не присутствуют
                break;

            case R.id.buPlusMinus:
               if(minusIsPresent(number)) {                     //если уже есть минус
                number = number.substring(1);                   //игнориреум минус под индексом(0) и начинаем с первого числа
               }else{number="-" + number; }
                break;

        }
        editText.setText(number);

    }



    public void operation(View view) {
        // убирает 0
        isNew = true;
        oldNumber = editText.getText().toString();
        switch (view.getId()){
            case R.id.buMinus: operator = "-"; break;
            case R.id.buPlus: operator = "+"; break;
            case R.id.buDivide: operator = "/"; break;
            case R.id.buMultiply: operator = "*"; break;
        }

        }

    public void clickEqual(View view) {

        String newNumber = editText.getText().toString();
        Double result = 0.0;

        switch (operator){
            case "-": result = Double.parseDouble(oldNumber) - Double.parseDouble(newNumber); break;
            case "+": result = Double.parseDouble(oldNumber) + Double.parseDouble(newNumber); break;
            case "*": result = Double.parseDouble(oldNumber) * Double.parseDouble(newNumber); break;
            case "/": result = Double.parseDouble(oldNumber) / Double.parseDouble(newNumber); break;
        }
        editText.setText(result+"");


    }

    public void acClick(View view) {
        editText.setText("0");
        isNew = true; // убирает 0
    }

    private boolean dotIsPresent(String number) {
        if(number.indexOf(".") == -1){
            return false;                  //если . не было
        }
        else{
            return true;                   //если есть .
        }

    }

    private boolean minusIsPresent(String number) {
        if(number.charAt(0) == '-') {                            //если значение под первым индексом -
        return true;
        }else{
            return false;
        }
    }

    public void clickPresent(View view) {

        if(operator == ""){
            String number = editText.getText().toString();
            double temp = Double.parseDouble(number) / 100;
            number = temp+"";
            editText.setText(number);
        }else{
            String newNumber = editText.getText().toString();
            Double result = 0.0;

            switch (operator){
                case "+": result = Double.parseDouble(oldNumber) + Double.parseDouble(oldNumber) * Double.parseDouble(newNumber) / 100;break;
                case "-": result = Double.parseDouble(oldNumber) - Double.parseDouble(oldNumber) * Double.parseDouble(newNumber) / 100; break;
                case "*": result = Double.parseDouble(oldNumber) * Double.parseDouble(newNumber) / 100; break;
                case "/": result = Double.parseDouble(oldNumber) /  Double.parseDouble(newNumber) * 100; break;
            }

            editText.setText(result+"");
            operator = "";



        }






    }
}