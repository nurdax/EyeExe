package com.baursaq.eye;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class IntroActivity extends AppCompatActivity {

    private ViewPager screenPager;
    IntroViewPagerAdapter introViewPagerAdapter ;
    TabLayout tabIndicator;
    Button btnNext;
    Button btnPrev;
    int position = 0 ;
    Button btnGetStarted;
    Animation btnAnim ;
    ImageView tvSkip;
    TextView mTextField;
    //timer

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // make the activity on full screen

      /*  requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);*/


        setContentView(R.layout.activity_intro);


        final List<ScreenItem> mList = new ArrayList<>();
        TextView mTextField = findViewById(R.id.timer);
        //timer
        CountDownTimer countDownTimer5 = new CountDownTimer(3 * 1000, 1000) {
            public void onTick(long millisUntilFinished) {
                mTextField.setText("" + millisUntilFinished / 1000);
            }
            public void onFinish() {
                position++;
                screenPager.setCurrentItem(position);
            }
        };
        CountDownTimer countDownTimer4 = new CountDownTimer(3 * 1000, 1000) {
            public void onTick(long millisUntilFinished) {
                mTextField.setText("" + millisUntilFinished / 1000);
            }
            public void onFinish() {
                position++;
                screenPager.setCurrentItem(position);
                countDownTimer5.start();
            }
        };
        CountDownTimer countDownTimer3 = new CountDownTimer(3 * 1000, 1000) {
            public void onTick(long millisUntilFinished) {
                mTextField.setText("" + millisUntilFinished / 1000);
            }
            public void onFinish() {
                position++;
                screenPager.setCurrentItem(position);
                countDownTimer4.start();
            }
        };

        CountDownTimer countDownTimer2 = new CountDownTimer(3 * 1000, 1000) {
            public void onTick(long millisUntilFinished) {
                mTextField.setText("" + millisUntilFinished / 1000);
            }
            public void onFinish() {
                position++;
                screenPager.setCurrentItem(position);
                countDownTimer3.start();
            }
        };


        CountDownTimer countDownTimer = new CountDownTimer(3 * 1000, 1000) {
            public void onTick(long millisUntilFinished) {
                mTextField.setText("" + millisUntilFinished / 1000);
            }
            public void onFinish() {
                position = screenPager.getCurrentItem();
                    position++;
                    screenPager.setCurrentItem(position);
                    countDownTimer2.start();
            }
        };countDownTimer.start();

        // hide the action bar
        getSupportActionBar().hide();

        // ini views
        btnNext = findViewById(R.id.btn_next);
        btnPrev = findViewById(R.id.btn_prev);
        //btnGetStarted = findViewById(R.id.btn_get_started);
        tabIndicator = findViewById(R.id.tab_indicator);
      //  btnAnim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.button_animation);
        tvSkip = findViewById(R.id.tv_skip);

        // fill list screen

        mList.add(new ScreenItem("1","Lorem ipsum",R.drawable.one));
        mList.add(new ScreenItem("2","Lorem ipsum",R.drawable.two));
        mList.add(new ScreenItem("3","Lorem ipsum",R.drawable.thr));
        mList.add(new ScreenItem("4","Lorem ipsum",R.drawable.four));
        mList.add(new ScreenItem("5","Lorem ipsum",R.drawable.five));
        mList.add(new ScreenItem("6","Lorem ipsum",R.drawable.six));

        // setup viewpager
        screenPager =findViewById(R.id.screen_viewpager);
        introViewPagerAdapter = new IntroViewPagerAdapter(this,mList);
        screenPager.setAdapter(introViewPagerAdapter);

        // setup tablayout with viewpager

        tabIndicator.setupWithViewPager(screenPager);

        // next button click Listner

       btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                position = screenPager.getCurrentItem();
                if (position < mList.size()) {

                    position++;
                    screenPager.setCurrentItem(position);
                    countDownTimer.start();


                }

              /*  if (position == mList.size()-1) { // when we rech to the last screen

                    // TODO : show the GETSTARTED Button and hide the indicator and the next button

                    loaddLastScreen();
                }*/

            }
        });

        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                position = screenPager.getCurrentItem();
                if (position < mList.size()) {

                    position--;
                    screenPager.setCurrentItem(position);
                    countDownTimer.start();

                }

               /* if (position == mList.size()-1) { // when we rech to the last screen

                    // TODO : show the GETSTARTED Button and hide the indicator and the next button

                    loaddLastScreen();


                }*/



            }
        });

        // tablayout add change listener


        /*tabIndicator.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                if (tab.getPosition() == mList.size()-1) {

                    loaddLastScreen();

                }


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });*/



        // Get Started button click listener

  /*     btnGetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //open main activity

          Intent mainActivity = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(mainActivity);
                // also we need to save a boolean value to storage so next time when the user run the app
                // we could know that he is already checked the intro screen activity
                // i'm going to use shared preferences to that process
                savePrefsData();
                finish();

            }
        });
*/
        // skip button click listener



        tvSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                screenPager.setCurrentItem(mList.size());
            }
        });



    }


    // show the GETSTARTED Button and hide the indicator and the next button
    private void loaddLastScreen() {

        btnPrev.setVisibility(View.INVISIBLE);
      //  btnGetStarted.setVisibility(View.VISIBLE);
        tabIndicator.setVisibility(View.INVISIBLE);
        // TODO : ADD an animation the getstarted button
        // setup animation
   //     btnGetStarted.setAnimation(btnAnim);



    }
}