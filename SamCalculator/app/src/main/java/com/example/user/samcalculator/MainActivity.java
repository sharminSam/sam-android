package com.example.user.samcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.text.DecimalFormat;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class MainActivity extends AppCompatActivity {
    EditText txtBill;
    RadioGroup radGroup;
    Double bill = 0.00;
    Double taxPercentage = 6.00;
    TextView txtTax;
    TextView txtTotal;
    TextView txtTotalPerPerson;
    Double taxAmount = 0.00;
    Double total = 0.00;
    Double totalPerPerson = 0.00;
    int numofppl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);txtBill = (EditText) findViewById(R.id.txtBil);
        txtBill.setOnKeyListener(onKeyListener);

        radGroup = (RadioGroup) findViewById(R.id.radGroup);
        radGroup.setOnCheckedChangeListener(onCheckedChangeListener);

        txtTax = (TextView) findViewById(R.id.txtTax);
        txtTotal = (TextView) findViewById(R.id.txtTotal);
        txtTotalPerPerson = (TextView) findViewById(R.id.txtEach);

        Spinner spinner1 = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,R.array.split_tax, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);
        spinner1.setOnItemSelectedListener(split_tax_spinner_listener);
    }

    public OnItemSelectedListener split_tax_spinner_listener = new OnItemSelectedListener(){
        public void onItemSelected(AdapterView<?> parent,
                                   View view, int pos, long id){

            numofppl = new Integer(parent.getItemAtPosition(pos).toString());
            if(txtBill.getText().length() > 0){
                calculate();
                display();
            }else{
                reset();
            }
        }
        public void onNothingSelected(AdapterView parent){
            //Do nothing
        }
    };

    public OnKeyListener onKeyListener = new OnKeyListener() {
        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            // TODO Auto-generated method stub
            if (event.getAction() == KeyEvent.ACTION_UP) {
                if(txtBill.getText().length() > 0){
                    calculate();
                    display();

                }else{
                    reset();
                }
                return true;
            }
            return false;
        }
    };
    public OnCheckedChangeListener onCheckedChangeListener = new OnCheckedChangeListener(){
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            // TODO Auto-generated method stub
            switch (checkedId){
                case R.id.rad6:
                    taxPercentage = 6.00;
                    if(txtBill.getText().length() > 0){
                        calculate();
                        display();
                    }else{
                        reset();
                    }
                    break;
                case R.id.rad16:
                    taxPercentage = 16.00;
                    if(txtBill.getText().length() > 0){
                        calculate();
                        display();
                    }else{
                        reset();
                    }
                    break;
                case R.id.rad10:
                    taxPercentage = 10.00;
                    if(txtBill.getText().length() > 0){
                        calculate();
                        display();
                    }else{
                        reset();
                    }
                    break;
            }
        }
    };
    public void calculate() {
        bill = Double.parseDouble(txtBill.getText().toString());
        taxAmount = ((bill * taxPercentage) / 100);
        total = bill + taxAmount;
        totalPerPerson = total / numofppl;
    }
    public void display(){
        txtTax.setText("RM"+new DecimalFormat("#,###,##0.00").format(taxAmount).toString());
        txtTotal.setText("RM"+(new DecimalFormat("#,###,##0.00").format(total).toString()));
        txtTotalPerPerson.setText("RM"+new DecimalFormat("#,###,##0.00").format(totalPerPerson).toString());
    }
    public void reset(){
        txtTax.setText("RM0.00");
        txtTotal.setText("RM0.00");
        txtTotalPerPerson.setText("RM0.00");
        taxAmount = 0.00;
        total = 0.00;
        totalPerPerson = 0.00;
        bill = 0.00;
        taxPercentage = 6.00;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch(item.getItemId())
        {
            case R.id.menu_about:
                Toast.makeText(MainActivity.this,"About App",Toast.LENGTH_SHORT).show();
                AlertDialog alertDialog = new AlertDialog.Builder(this).create();
                alertDialog.setTitle("About App");
                alertDialog.setMessage("This app developed by Sharmindran Raj @ SAM, this is an app that calculate and split the bill evenly to make our life easier so that we have more time enjoying with our friends and family");
                alertDialog.setButton("OK",new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which){
                        //TODO Auto generated method stub
                    }
                });
                alertDialog.show();
                return true;
            case R.id.menu_exit:
                Toast.makeText(MainActivity.this,"Thank you!, exiting in progress.",Toast.LENGTH_SHORT).show();
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
