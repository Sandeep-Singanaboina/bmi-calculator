package com.example.bmipro;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private EditText height;
    private EditText weight;
    private TextView result;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        height = (EditText) findViewById(R.id.height);
        weight = (EditText) findViewById(R.id.weight);
        result = (TextView) findViewById(R.id.result);


        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);
            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
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
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings( "StatementWithEmptyBody" )
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_what_is_bmi) {
            Intent intent = new Intent(MainActivity.this, WhatisBmiActivity.class);
            startActivity(intent);


        } else if (id == R.id.nav_Ranges) {
            Intent intent = new Intent(MainActivity.this, BMIrangeActivity.class);
            startActivity(intent);


        } else if (id == R.id.nav_Accuracy_of_BMI) {
            Intent intent = new Intent(MainActivity.this, AccuracyofBMIActivity.class);
            startActivity(intent);


        } else if (id == R.id.nav_health_benefits) {
            Intent intent = new Intent(MainActivity.this, HealthbenefitsActivity.class);
            startActivity(intent);
        }

        else if (id == R.id.nav_dite_plan) {
            Intent intent = new Intent(MainActivity.this, Dite_Activity.class);
            startActivity(intent);

        }


        else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void calculateBMI(View v)
    {
        String heightStr=height.getText().toString();
        String weightStr=weight.getText().toString();
        if(heightStr != null && !"" .equals(heightStr)&& weightStr !=null && !"".equals(weightStr))
        {
            float heightValue =Float.parseFloat(heightStr)/100;
            float weightValue=Float.parseFloat(weightStr);
            float bmi =weightValue/(heightValue*heightValue);
            displayBMI(bmi);

        }
    }
    private void displayBMI(float bmi)
    {
        String bmiLabel="";
        if (Float.compare(bmi,15f)<=0)
        {
            bmiLabel=getString(R.string.very_severely_underweight);

        }
        else if ((Float.compare(bmi,15f)>0 && Float.compare(bmi,16f)<=0))
        {
            bmiLabel=getString(R.string.severely_underweight);

        }
        else if (Float.compare(bmi,16f)>0 && Float.compare(bmi,18.5f)<=0)
        {
            bmiLabel=getString(R.string.underweight);

        }
        else if (Float.compare(bmi,18.5f)>0 && Float.compare(bmi,25f)<=0)
        {
            bmiLabel=getString(R.string.normal);

        }
        else if (Float.compare(bmi,25f)>0 && Float.compare(bmi,30f)<=0)
        {
            bmiLabel=getString(R.string.overweight);

        }
        else if (Float.compare(bmi,30f)>0 && Float.compare(bmi,35f)<=0)
        {
            bmiLabel=getString(R.string.obese_class_i);

        }
        else if (Float.compare(bmi,35f)>0 && Float.compare(bmi,40f)<=0)
        {
            bmiLabel=getString(R.string.obese_class_ii);

        }
        else
        {
            bmiLabel=getString(R.string.obese_class_iii);

        }

        bmiLabel=bmi +"\n\n" + bmiLabel;
        result.setText(bmiLabel);


    }
    public void sendMessage(View v) {
        String final_status="My Body Mass Index Status  ";
        String Message=result.getText().toString();
        String s=final_status+Message;
        String whatsAppMessage =s;

        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, whatsAppMessage);
        sendIntent.setType("text/plain");

        // Do not forget to add this to open whatsApp App specifically
        sendIntent.setPackage("com.whatsapp");
        startActivity(sendIntent);

    }

}
