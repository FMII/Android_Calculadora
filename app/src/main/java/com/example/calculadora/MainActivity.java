package com.example.calculadora;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView resultado, solucion;
    MaterialButton buttonC, buttonOpenBracket, buttonCloseBracket;
    MaterialButton buttonDivisor, buttonSuma, buttonMinus, buttonMulti, buttonIgual;
    MaterialButton button0, button1, button2, button3, button4, button5, button6, button7, button8, button9;
    MaterialButton buttonAC, buttonDot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultado = findViewById(R.id.resultado);
        solucion = findViewById(R.id.solucion);

        asignarID(buttonC, R.id.button_C);
        asignarID(buttonOpenBracket, R.id.button_open_bracket);
        asignarID(buttonCloseBracket, R.id.button_close_bracket);
        asignarID(buttonDivisor, R.id.button_divisor);
        asignarID(buttonSuma, R.id.button_suma);
        asignarID(buttonMinus, R.id.button_minus);
        asignarID(buttonMulti, R.id.button_multi);
        asignarID(buttonIgual, R.id.button_igual);
        asignarID(button0, R.id.button_cero);
        asignarID(button1, R.id.button_uno);
        asignarID(button2, R.id.button_dos);
        asignarID(button3, R.id.button_tres);
        asignarID(button4, R.id.button_cuatro);
        asignarID(button5, R.id.button_cinco);
        asignarID(button6, R.id.button_seis);
        asignarID(button7, R.id.button_siete);
        asignarID(button8, R.id.button_ocho);
        asignarID(button9, R.id.button_nueve);
        asignarID(buttonAC, R.id.button_ac);
        asignarID(buttonDot, R.id.button_dot);
    }

    void asignarID(MaterialButton button, int id) {
        button = findViewById(id);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        String calcular = solucion.getText().toString();

        if (buttonText.equals("AC")) {
            solucion.setText("");
            resultado.setText("0");
            return;
        }
        if (buttonText.equals("=")) {
            String resultadofinal = getResult(calcular);
            resultado.setText(resultadofinal);
            return;
        }
        if (buttonText.equals("C")) {
            calcular = calcular.substring(0, calcular.length() - 1);
        } else {
            calcular = calcular + buttonText;
        }
        solucion.setText(calcular);
    }

    String getResult(String data) {
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scope = context.initStandardObjects();
            Object result = context.evaluateString(scope, data, "JavaScript", 1, null);
            if (result != null) {
                return Context.toString(result);
            } else {
                return "Error";
            }
        } catch (Exception e) {
            return "Error";
        } finally {
            Context.exit();
        }
    }
}
