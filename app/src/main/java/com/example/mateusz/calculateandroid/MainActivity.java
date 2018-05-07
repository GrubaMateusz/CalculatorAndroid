package com.example.mateusz.calculateandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {




    private Button bEqual,bSub,bDiv,bAdd,bClc,bMult;                                                    //deklaracja przycisków operatorów

    private int [] idButtons = {R.id.one,R.id.two,R.id.three,R.id.four,R.id.five,R.id.six,R.id.seven    //deklaracja przycisków 0-9
                                ,R.id.eight,R.id.nine,R.id.zero};


    private static final String add = "+";                                                              //deklaracja stałych za pomocą których wiadomo jaki
    private static final String sub = "-";
    private static final String div = "/";                                                              //operator byl ostatni
    private static final String mult = "*";

    private ClickOperatorTimer timerAdd, timerMult, timerDiv, timerSub;



    public EditText historyText;                                                                        //zmienne do wyswietlania textu
    public EditText textField;

    private StringBuilder historyBuilder = new StringBuilder("");                                       // stworzenie stringa sklejającego ze soba wszystkie
    private StringBuilder saveVariable = new StringBuilder("");                                         //operacje

    private float globalValue= 0;                                                                       //zmienne do realizowania obliczen
    private float methodValue=0;                                                                        // wykozystane tylko global i method value
    private float rightVariable =0;
    private float leftVariable =0;


    private boolean dubleClickOperator = false;                                                         // zmienna do ustawiania czy operator 2x nie zostal uzyty
    public String temporary;
    private String lastClick="";                                                                        // zmienna do ustawienia ostatniego operatora


    public boolean clickIsFalse()                                                                       // dwie metody do kontrolowania podwójnego operatora
    {
        return this.dubleClickOperator=false;
    }
    public boolean clickIsTrue()
    {
        return this.dubleClickOperator=true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    // pobranie id z xml dla operatorów
        bEqual = (Button) findViewById(R.id.equal);
        bMult = (Button)  findViewById(R.id.mult);
        bDiv = (Button) findViewById(R.id.div);
        bAdd = (Button) findViewById(R.id.add);
        bSub = (Button) findViewById(R.id.sub);
        bClc = (Button) findViewById(R.id.clc);
    // Text editing gives id for TextFrame in .xml
        textField= (EditText) findViewById(R.id.print);
        historyText= (EditText) findViewById(R.id.historyText);
        //cooming soon timers
        timerAdd = new ClickOperatorTimer(add);
        timerMult = new ClickOperatorTimer(mult);
        timerDiv = new ClickOperatorTimer(div);
        timerSub = new ClickOperatorTimer(sub);

    /**
     *   Listener dla zwykłych przycisków
     * */
       View.OnClickListener listener = new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Button b = (Button) v;

                    clickIsFalse();                                                                     //ustawienie podwojnego operatora



                    historyBuilder.append(b.getTag().toString());                                       // pobranie i budowanie stringu z historią
                    historyText.setText(historyBuilder);


                    saveVariable.append(b.getTag().toString());
                    temporary=saveVariable.toString();

                    rightVariable = Float.parseFloat(temporary);                                        //pobranie i zapis liczby
                    textField.setText(""+ rightVariable);

                    switch (lastClick)                                                                  //sprawdzenie ostatniego klikniecia i wykonanie obliczen
                    {
                        case add:
                            methodValue+= rightVariable;
                            break;
                        case mult:
                            methodValue*=rightVariable;
                            break;
                        case sub:
                            methodValue-=rightVariable;
                            break;
                        case div:
                            methodValue/=rightVariable;
                            break;

                    }


                System.out.println("right var : "+ rightVariable);                                      // informacje co jest w zmiennej rightVariable

            }
        };

        /**
         * inicialize listeners for numeric buttons where we can find if from tags
         */
        for(int id : idButtons ){
            findViewById(id).setOnClickListener(listener);

        }
        /**
         *  inicialize add button
         * */




        bAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(dubleClickOperator == false) {                                       //sprawdzenie podwojnego operatora



                    if(lastClick=="")                                                   // sprawdzenie ostatniego klikniecia analogicznie dla wszystkich operatorow
                    {                                                                   // dla 3 przypadkow pierwszy jest posty drogi po znaku = trzeba przeprowadzic ostatnie dzialenie
                                                                                        // trzeci to reszta operatorow
                        historyText.setText(historyBuilder.append(add));
                        textField.setText(rightVariable +"+");


                      methodValue+= rightVariable;


                    } else if(lastClick=="=")
                        {
                            historyText.setText(historyBuilder.append(add));
                            textField.setText(globalValue+"+");
                            methodValue=globalValue+rightVariable;
                        } else
                            {
                                historyText.setText(historyBuilder.append(add));
                                textField.setText(rightVariable +"+");



                            }



                    timerAdd.incrementTimer();
                    System.out.println("Ilosc klikniec "+timerAdd.getClickTimer());



                    saveVariable = new StringBuilder("");
                    clickIsTrue();                                                                          //ustawienie pddwojnego klikniecia

                    lastClick=add;                                                                          //ustawienie ostatniego klikniecia


                    System.out.println("zmienne w add w globalVal : "+globalValue );
                    System.out.println("zmienne w add w metodVal: "+methodValue);
                    System.out.println("zmienne w left var : "+ leftVariable);
                    System.out.println("zmienne w right var : "+ rightVariable);
                }
                else{

                    textField.setText("Only one operator");                                                 //wyswietla tekst na ekranie gdy damy 2 razy operator

                }
            }
        });

        /**
         *  inicialize multiplay button
         * */
        bMult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dubleClickOperator==false)
                {
                    timerMult.incrementTimer();
                    System.out.println("Ilosc klikniec "+timerMult.getClickTimer());

                        if ( lastClick == "")
                        {
                            historyText.setText(historyBuilder.append(mult));
                            textField.setText(rightVariable +"*");


                           methodValue= rightVariable;

                        } else if ( lastClick == "=")
                                {

                                    historyText.setText(historyBuilder.append(mult));
                                    textField.setText(globalValue+"*");
                                  methodValue=globalValue;

                                } else
                                    {
                                        historyBuilder.append(mult);
                                        historyText.setText(historyBuilder);
                                        textField.setText(rightVariable +"*");

                                    }






                    saveVariable = new StringBuilder("");
                    lastClick=mult;
                    clickIsTrue();
                    System.out.println("zmienne w mult w globalVal : "+globalValue );
                    System.out.println("zmienne w mult w metodVal: "+methodValue);
                    System.out.println("zmienne w left var : "+ leftVariable);
                    System.out.println("zmienne w right var : "+ rightVariable);

                }else
                {
                    textField.setText("Only one operator");
                }

            }
        });

        /**
         *  inicialize substraction button
         * */
        bSub.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                if(dubleClickOperator==false)
                {
                    timerSub.incrementTimer();
                    System.out.println("Ilosc klikniec "+timerMult.getClickTimer());

                    if ( lastClick == "")
                    {
                        historyText.setText(historyBuilder.append(sub));
                        textField.setText(rightVariable +"-");


                        methodValue= rightVariable;

                    } else if ( lastClick == "=")
                    {

                        historyText.setText(historyBuilder.append(sub));
                        textField.setText(globalValue+"-");
                        methodValue=globalValue;

                    } else
                    {
                        historyBuilder.append(sub);
                        historyText.setText(historyBuilder);
                        textField.setText(rightVariable +"-");

                    }

                    saveVariable = new StringBuilder("");
                    lastClick=sub;
                    clickIsTrue();
                    System.out.println("zmienne w sub w globalVal : "+globalValue );
                    System.out.println("zmienne w sub w metodVal: "+methodValue);
                    System.out.println("zmienne w left var : "+ leftVariable);
                    System.out.println("zmienne w right var : "+ rightVariable);


                }else
                {
                    textField.setText("Only one operator");
                }

            }
        });

        /**
         *  inicialize divide button
         * */
        bDiv.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {


                if(dubleClickOperator==false)
                {
                    timerSub.incrementTimer();
                    System.out.println("Ilosc klikniec "+timerMult.getClickTimer());


                    if ( lastClick == "")
                    {
                        historyText.setText(historyBuilder.append(div));
                        textField.setText(rightVariable +"/");


                        methodValue/=rightVariable;

                    } else if ( lastClick == "=")
                    {

                        historyText.setText(historyBuilder.append(div));
                        textField.setText(globalValue+"/");
                        methodValue=globalValue;

                    } else
                    {
                        historyBuilder.append(div);
                        historyText.setText(historyBuilder);
                        textField.setText(rightVariable +"/");

                    }

                    saveVariable = new StringBuilder("");
                    lastClick=div;
                    clickIsTrue();
                    System.out.println("zmienne w div w globalVal : "+globalValue );
                    System.out.println("zmienne w div w metodVal: "+methodValue);
                    System.out.println("zmienne w left var : "+ leftVariable);
                    System.out.println("zmienne w right var : "+ rightVariable);


                }else
                {
                    textField.setText("Only one operator");
                }

            }
        });

            /**
             * Inicialize Equal Button
             *
             * */
        bEqual.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
            System.out.println("lastClick= "+lastClick);
                switch (lastClick){
                    case add:

                        globalValue=methodValue;
                        textField.setText("="+globalValue);
                        rightVariable =0;
                        break;
                    case mult:


                        globalValue=methodValue;
                        textField.setText("="+globalValue);
                        rightVariable =0;

                        break;
                    case sub:

                        globalValue=methodValue;
                        textField.setText("="+globalValue);
                        rightVariable =0;
                        break;
                    case div:

                        if(rightVariable ==0){textField.setText("NIE DZIEL PRZEZ 0"); break;}
                        globalValue= methodValue;
                        textField.setText("="+globalValue);
                        rightVariable =0;
                        break;
                }

                historyText.setText(historyBuilder.append("="+globalValue));
                lastClick= "=";


                System.out.println("zmienne w equal w globalVal : "+globalValue );
                System.out.println("zmienne w equal w metodVal: "+methodValue);
                System.out.println("zmienne w left var : "+ leftVariable);
                System.out.println("zmienne w right var : "+ rightVariable);

            }
        });


        bClc.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                globalValue=0;
                methodValue=0;
                rightVariable=0;
                leftVariable=0;
                temporary=new String();
                saveVariable = new StringBuilder("0");
                historyBuilder = new StringBuilder("0");
                textField.setText(historyBuilder);
                historyText.setText(historyBuilder);

                lastClick="";
                System.out.println("zmienne w equal w globalVal : "+globalValue );
                System.out.println("zmienne w equal w metodVal: "+methodValue);
                System.out.println("zmienne w left var : "+ leftVariable);
                System.out.println("zmienne w right var : "+ rightVariable);

            }
        });

        if (savedInstanceState!=null){                                              //odczyt zmiennych po obruceniu telefonu
            methodValue= (float) savedInstanceState.getSerializable("methodValue");
            globalValue= (float) savedInstanceState.getSerializable("globalValue");
            rightVariable= (float) savedInstanceState.getSerializable("rightVariable");
            lastClick= (String) savedInstanceState.getSerializable("lastClick");
            historyBuilder= (StringBuilder) savedInstanceState.getSerializable("historyBuilder");
            saveVariable= (StringBuilder) savedInstanceState.getSerializable("saveVariable");
        }



    }

    @Override                                                                       //zapis tych zmiennych
    protected  void  onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putSerializable("methodValue",methodValue);
        outState.putSerializable("globalValue",globalValue);
        outState.putSerializable("rightVariable",rightVariable);
        outState.putSerializable("lastClick",lastClick);
        outState.putSerializable("historyBuilder",historyBuilder);
        outState.putSerializable("saveVariable",saveVariable);

    }



}







