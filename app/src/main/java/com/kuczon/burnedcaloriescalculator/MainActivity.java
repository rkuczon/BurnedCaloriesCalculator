package com.kuczon.burnedcaloriescalculator;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.NumberFormat;


public class MainActivity extends Activity implements OnSeekBarChangeListener,TextView.OnEditorActionListener{


    SeekBar seekbar1;
    int value;
    TextView result;
    private EditText Weight;
    private EditText name;
    private TextView calories;
    private TextView BmiTotals;
    private String  resultString = "";
    private String  WeightString = "";
    private String spinFVal = "";
    private String spinIVal = "";
    private double numbers = .75;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekbar1 = (SeekBar)findViewById(R.id.seekBar);
        result = (TextView)findViewById(R.id.MinseekBar);
        Weight = (EditText) findViewById(R.id.Weight);
        name = (EditText) findViewById(R.id.name);
        calories = (TextView) findViewById(R.id.calories);
        BmiTotals = (TextView) findViewById(R.id.BmiTotals);
        Spinner spin = (Spinner) findViewById((R.id.HeightFeet));
       String spinFVal = String.valueOf(spin.getSelectedItem());
        Spinner spin1 = (Spinner) findViewById((R.id.HeightInches));
        String spinIVal = String.valueOf(spin1.getSelectedItem());


        //set change listener
        seekbar1.setOnSeekBarChangeListener(this);
    }
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress,
                                  boolean fromUser) {
        value = progress;
        result.setText ("" +value);
    }
    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        // TODO Auto-generated method stub
    }
    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        // TODO Auto-generated method stub
    }
    public boolean onEditorAction (TextView v, int actionId, KeyEvent event) {

        if(actionId == EditorInfo.IME_ACTION_DONE ||
                actionId == EditorInfo.IME_ACTION_UNSPECIFIED) {
            calculateAndDisplay();
        }
        return false;
    }

    private void calculateAndDisplay() {
        WeightString =  Weight.getText().toString();
        float WeightStrings;
        if( WeightString.equals("")) {
            WeightStrings = 0;
        }
        else {
            WeightStrings =Float.parseFloat( WeightString);
        }
        float resultStrings;
        resultString =  result.getText().toString();
        resultStrings =Float.parseFloat( resultString);
        float astrings;
        String aString = Double.toString(numbers);
        astrings = Float.parseFloat(aString);
        int Feetvalues = Integer.parseInt(spinFVal);
        int Inchvalues = Integer.parseInt(spinIVal);


        float BmiTotal =(WeightStrings * 703) / ((12 * Feetvalues) * (12 * Inchvalues));
       float caloriesTotal = astrings *WeightStrings * resultStrings;

        NumberFormat currency = NumberFormat.getCurrencyInstance();
        calories.setText(currency.format(caloriesTotal));
        BmiTotals.setText(currency.format( BmiTotal));



    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);

    }
}
