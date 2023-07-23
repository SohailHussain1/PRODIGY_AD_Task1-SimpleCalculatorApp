package easy.tuto.easycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView resultTv,solutionTv;
    MaterialButton buttonC,buttonBrackOpen,buttonBrackClose;
    MaterialButton buttonDivide,buttonMultiply,buttonPlus,buttonMinus,buttonEquals;
    MaterialButton button0,button1,button2,button3,button4,button5,button6,button7,button8,button9;
    MaterialButton buttonAC,buttonDot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultTv = findViewById(R.id.result_tv1);
        solutionTv = findViewById(R.id.solution_tv1);

        assignId(buttonC,R.id.button_c1);
        assignId(buttonBrackOpen,R.id.button_open_bracket1);
        assignId(buttonBrackClose,R.id.button_close_bracket1);
        assignId(buttonDivide,R.id.button_divide1);
        assignId(buttonMultiply,R.id.button_multiply1);
        assignId(buttonPlus,R.id.button_plus1);
        assignId(buttonMinus,R.id.button_minus1);
        assignId(buttonEquals,R.id.button_equals1);
        assignId(button0,R.id.button_01);
        assignId(button1,R.id.button_11);
        assignId(button2,R.id.button_21);
        assignId(button3,R.id.button_31);
        assignId(button4,R.id.button_41);
        assignId(button5,R.id.button_51);
        assignId(button6,R.id.button_61);
        assignId(button7,R.id.button_71);
        assignId(button8,R.id.button_81);
        assignId(button9,R.id.button_91);
        assignId(buttonAC,R.id.button_ac1);
        assignId(buttonDot,R.id.button_dot1);





    }

    void assignId(MaterialButton btn,int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        MaterialButton button =(MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataToCalculate = solutionTv.getText().toString();

        if(buttonText.equals("AC")){
            solutionTv.setText("");
            resultTv.setText("0");
            return;
        }
        if(buttonText.equals("=")){
            solutionTv.setText(resultTv.getText());
            return;
        }
        if(buttonText.equals("C")){
            if(resultTv.getText().toString().equals("0")){
                return;
            }
            dataToCalculate = dataToCalculate.substring(0,dataToCalculate.length()-1);
        }else{
            dataToCalculate = dataToCalculate+buttonText;
        }
        solutionTv.setText(dataToCalculate);

        String finalResult = getResult(dataToCalculate);

        if(!finalResult.equals("Err")){
            resultTv.setText(finalResult);
        }

    }

    String getResult(String data){
        try{
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            Object result = context.evaluateString(scriptable, data, "Javascript", 1, null);

            if (result == null || result == Context.getUndefinedValue()) {
                return "0"; // Or any other user-friendly message you prefer
            }

            String finalResult = result.toString();
            if(finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0","");
            }
            return finalResult;
        } catch (Exception e){
            return "";
        }
    }


}