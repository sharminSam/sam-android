package com.example.user.samcalculator;

import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;

public class MainSplash extends Activity {

    ImageView splashing;
    AlphaAnimation animation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main_splash);

        //set up fade in animation
        animation = new AlphaAnimation(0.0f, 1.0f);
        animation.setDuration(1000);
        splashing = (ImageView) findViewById(R.id.imageView1);
        splashing.setAnimation(animation);

        //Thread to waste time while displaying splash screen
        Thread SplashThread = new Thread(){
            @Override
            public void run(){
                try{
                    synchronized (this){
                        //wait given period of time
                        wait(5000);
                    }
                }catch(InterruptedException ex){
                }
                finish();

                //Run next activity
                Intent intent = new Intent();
                intent.setClass(MainSplash.this, MainActivity.class);
                MainSplash.this.startActivity(intent);

                //Terminate splash screen
                MainSplash.this.finish();

            }
        };
        SplashThread.start();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_splash, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

