package com.example.harshvardhan.dynamiccalender;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    // Spinner Drop down months
    final String[] myMonth = {"January","February","March","April", "May", "June", "July","August","September","October", "November", "December"};

    // Spinner Drop down year
    final static Integer[] myYear = new Integer[47];

    RelativeLayout rl;
    CalendarView cal;
    Spinner s1, s2;
    String item, ite;
    int month1, year1;
    Calendar calen;
    Spinner spinner,spinner2;
    int mYear, mMonth, mDay, x, y, z;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initializing years
        for(int i=0;i<47;i++){
            myYear[i]=2016-i;
        }


        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> myArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, myMonth);
        myArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(myArrayAdapter);


        spinner2 = (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter myArrayAdapter2 = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_dropdown_item, myYear);
        myArrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(myArrayAdapter2);

        rl = (RelativeLayout) findViewById(R.id.rl);
        cal = new CalendarView(MainActivity.this);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams((int) GridLayout.LayoutParams.MATCH_PARENT, (int) GridLayout.LayoutParams.MATCH_PARENT);
        params.topMargin = 300;
        cal.setLayoutParams(params);
        rl.addView(cal);
        spinner.setOnItemSelectedListener(this);
        spinner2.setOnItemSelectedListener(this);

        calen = Calendar.getInstance();

        cal.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {
                // TODO Auto-generated method stub

                //year = 2015 ;
                //month = 3 ;
                //Long lol = cal.getDate();
                //String m = Long.toString(lol);

                x = dayOfMonth - mDay;
                y = month - mMonth;
                z = year - mYear;

                if (x == 0 && y == 0 && z == 0) {
                    Toast.makeText(getBaseContext(), "Its Today!!!!!", Toast.LENGTH_LONG).show();
                }
                else{
                    if (x < 0) {
                        x *= -1;
                    }
                    if (y < 0) {
                        y *= -1;
                    }
                    if (z < 0) {
                        z *= -1;
                    }

                    Toast.makeText(getBaseContext(), x + " Days, " + y + " Months, " + z + " Years ! ", Toast.LENGTH_LONG).show();

                }

                //Toast.makeText(getBaseContext(), "Days: " +(dayOfMonth - mDay)+ " " + "Months: " + (month - mMonth) + " " + "Years: " + (year-mYear), Toast.LENGTH_LONG).show();
                mDay=dayOfMonth;
                mMonth=month;
                mYear=year;
            }
        });


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        s1 = (Spinner)parent;
        s2 = (Spinner)parent;
        if(s1.getId() == R.id.spinner)
        {
            year1=2016-spinner2.getSelectedItemPosition();
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, year1);
            calendar.set(Calendar.MONTH,position);
            calendar.set(Calendar.DAY_OF_MONTH, 1);

            long milliTime = calendar.getTimeInMillis();
            cal.setDate(milliTime,true,true);
        }
        if(s2.getId() == R.id.spinner2)
        {
            year1=2016-spinner2.getSelectedItemPosition();
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, year1);
            position=spinner.getSelectedItemPosition();
            calendar.set(Calendar.MONTH,position);
            calendar.set(Calendar.DAY_OF_MONTH, 1);

            long milliTime = calendar.getTimeInMillis();
            cal.setDate(milliTime,true,true);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //calen.set(Calendar.MONTH,4);
    }
}
