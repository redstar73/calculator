package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;



import android.view.View;
import android.widget.Button;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    Button bracket1;
    Button bracket2;
    Button bp;
    Button b0;
    Button b1;
    Button b2;
    Button b3;
    Button b4;
    Button b5;
    Button b6;
    Button b7;
    Button b8;
    Button b9;
    Button bm;
    Button bplus;
    Button bdiv;
    Button bmulti;
    Button breset;
    Button bequal;
    TextView R1;
    TextView R2;
    private boolean reseted=true;
    private double Eq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bracket1=findViewById(R.id.bracket1);
        bracket2=findViewById(R.id.bracket2);
        bp=findViewById(R.id.bp);
        b0=findViewById(R.id.b0);
        b1=findViewById(R.id.b1);
        b2=findViewById(R.id.b2);
        b3=findViewById(R.id.b3);
        b4=findViewById(R.id.b4);
        b5=findViewById(R.id.b5);
        b6=findViewById(R.id.b6);
        b7=findViewById(R.id.b7);
        b8=findViewById(R.id.b8);
        b9=findViewById(R.id.b9);
        bm=findViewById(R.id.bm);
        bplus=findViewById(R.id.bplus);
        bdiv=findViewById(R.id.bdiv);
        bmulti=findViewById(R.id.bmulti);
        breset=findViewById(R.id.breset);
        bequal=findViewById(R.id.bequal);
        R1=findViewById(R.id.R1);
        R2=findViewById(R.id.R2);

        bp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chiffreClick(".");
            }});

        b0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chiffreClick("0");
            }});

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chiffreClick("1");
            }});

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chiffreClick("2");
            }});

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chiffreClick("3");
            }});

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chiffreClick("4");
            }});

        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chiffreClick("5");
            }});

        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chiffreClick("6");
            }});

        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chiffreClick("7");
            }});

        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chiffreClick("8");
            }});

        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chiffreClick("9");
            }});

        bm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateSecondairy("-");
            }});
        bdiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateSecondairy("/");
            }});
        bmulti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateSecondairy("*");
            }});
        bplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateSecondairy("+");
            }});

        bequal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateSecondairy("");
                String res=R1.getText().toString();
                R2.setText(Double.toString(eval(res)));
            }});

        breset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                R1.setText("");
                R2.setText("");

            }});
        bracket1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reseted=false;
                updateSecondairy("(");
            }});
        bracket2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateSecondairy(")");
            }});


    }

    public void updateSecondairy(String maindis){
        if ((!reseted)&&(!maindis.equals("("))) {
            String dis = R1.getText().toString() + R2.getText().toString() + maindis;
            R1.setText(dis);
            reseted = true;
            R2.setText("0");
        }else {
            String dis = R1.getText().toString()  + maindis;
            R1.setText(dis);
            reseted = true;
            R2.setText("0");
        }
    }

    public void chiffreClick(String num) {
        if((reseted)&(!num.equals("."))){
                R2.setText(num);
                reseted = false;
        }else{
            String dis = R2.getText().toString() + num;
            R2.setText(dis);
            reseted=false;
        }
    }

    public static double eval(final String str) {
        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char)ch);
                return x;
            }



            double parseExpression() {
                double x = parseTerm();
                for (;;) {
                    if      (eat('+')) x += parseTerm(); // addition
                    else if (eat('-')) x -= parseTerm(); // subtraction
                    else return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (;;) {
                    if      (eat('*')) x *= parseFactor(); // multiplication
                    else if (eat('/')) x /= parseFactor(); // division
                    else return x;
                }
            }

            double parseFactor() {
                if (eat('+')) return parseFactor(); // unary plus
                if (eat('-')) return -parseFactor(); // unary minus

                double x;
                int startPos = this.pos;
                if (eat('(')) { // parentheses
                    x = parseExpression();
                    eat(')');
                } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(str.substring(startPos, this.pos));
                } else if (ch >= 'a' && ch <= 'z') { // functions
                    while (ch >= 'a' && ch <= 'z') nextChar();
                    String func = str.substring(startPos, this.pos);
                    x = parseFactor();
                    if (func.equals("sqrt")) x = Math.sqrt(x);
                    else if (func.equals("sin")) x = Math.sin(Math.toRadians(x));
                    else if (func.equals("cos")) x = Math.cos(Math.toRadians(x));
                    else if (func.equals("tan")) x = Math.tan(Math.toRadians(x));
                    else throw new RuntimeException("Unknown function: " + func);
                } else {
                    throw new RuntimeException("Unexpected: " + (char)ch);
                }

                if (eat('^')) x = Math.pow(x, parseFactor()); // exponentiation

                return x;
            }
        }.parse();
    }
}