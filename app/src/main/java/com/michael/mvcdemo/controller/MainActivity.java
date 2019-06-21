package com.michael.mvcdemo.controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.michael.mvcdemo.R;
import com.michael.mvcdemo.model.AirConditioner;

/**
 * Controller
 * */
public class MainActivity extends AppCompatActivity
{
    //View
    private TextView tvTemperatureAndWindLevel;
    private SeekBar sbTemperature;
    private Button btnChangeWindLevel;

    //Model
    private AirConditioner airConditioner;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iniComponent();
    }

    private void iniComponent()
    {
        //Model的初始化
        airConditioner = new AirConditioner();

        //View的初始化
        tvTemperatureAndWindLevel = findViewById(R.id.tvTemperatureAndWindLevel);
        sbTemperature = findViewById(R.id.sbTemperature);
        btnChangeWindLevel = findViewById(R.id.btnChangeWindLevel);

        sbTemperature.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                //用户通过与View的交互，修改了温度，这个事件被Controller（Activity）接收到
                //Controller对Model进行了修改
                //修改完Model后，Controller再对View进行更新
                changeTemperature(progress);
                updateUI();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {

            }
        });

        btnChangeWindLevel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //用户通过与View的交互，修改了风力，这个事件被Controller（Activity）接收到
                //Controller对Model进行了修改
                //修改完Model后，Controller再对View进行更新
                changeWindLevel();
                updateUI();
            }
        });

        updateUI();
    }

    /**
     * 在Controller（Activity）中对Model进行设置
     * */
    private void changeTemperature(int temperature)
    {
        airConditioner.changeTemperature(temperature);
    }

    /**
     * 在Controller（Activity）中对Model进行设置
     * */
    private void changeWindLevel()
    {
        airConditioner.changeWindLevel();
    }

    /**
     * 在Controller（Activity）中对View进行设置
     * */
    private void updateUI()
    {
        tvTemperatureAndWindLevel.setText(airConditioner.getCurrentCondition());
    }
}
