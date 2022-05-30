package com.adityadev.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {
    public static final String Player = "com.adityadev.tictactoe.GameType";
    public static final String Sound = "com.adityadev.tictactoe.Sound";
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        SharedPreferences preference = getSharedPreferences("SoundPref",0);
        int SoundOn = preference.getInt("sound",3);
        if(SoundOn==1)
            ((RadioButton)findViewById(R.id.On)).setChecked(true);
        else if(SoundOn==0)
            ((RadioButton)findViewById(R.id.Off)).setChecked(true);

         editor = preference.edit();
        ((RadioGroup)findViewById(R.id.GameSound)).setOnCheckedChangeListener((group, checkedId) ->{
            if(checkedId==R.id.On)
                editor.putInt("sound",1);
            else if(checkedId==R.id.Off)
                editor.putInt("sound",0);
            editor.commit();
        } );
    }

    public void onePlyr(View v)
    {
        Intent intent = new Intent(this,PlayGame.class);
        intent.putExtra(Player,1);
        if(((RadioButton)findViewById(R.id.On)).isChecked())
            intent.putExtra(Sound,1);
        else
            intent.putExtra(Sound,0);
        startActivity(intent);
    }
    public void twoPlyr(View v)
    {
        Intent intent = new Intent(this,PlayGame.class);
        intent.putExtra(Player,2);
        if(((RadioButton)findViewById(R.id.On)).isChecked())
            intent.putExtra(Sound,1);
        else
            intent.putExtra(Sound,0);
        startActivity(intent);
    }
}