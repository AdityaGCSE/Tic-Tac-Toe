package com.adityadev.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Vector;

public class PlayGame extends AppCompatActivity {
    int TypeOfGame, soundOn=1;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);
        Intent intent = getIntent();
        TypeOfGame = intent.getIntExtra(MainActivity.Player, 1);
        soundOn = intent.getIntExtra(MainActivity.Sound, 1);
        TextView status = findViewById(R.id.status);
        TextView youWin = findViewById(R.id.YouWin);
        TextView YouLost = findViewById(R.id.YouLost);
        ImageView hide = findViewById(R.id.hide);
        RadioButton e = findViewById(R.id.Easy);
        RadioButton m = findViewById(R.id.Medium);
        RadioButton h = findViewById(R.id.Hard);
        RadioButton ex = findViewById(R.id.Expert);
        if (TypeOfGame == 1) {
            status.setText("Your Turn: O");
            youWin.setText("You Won");
            YouLost.setText("You Lost");
            m.setChecked(true);
        } else if (TypeOfGame == 2) {
            status.setText("O's Turn");
            hide.setImageResource(R.drawable.white);
            youWin.setText("O wins");
            YouLost.setText("X wins");
            e.setEnabled(false);
            m.setEnabled(false);
            h.setEnabled(false);
            ex.setEnabled(false);
        }
        ((RadioGroup)findViewById(R.id.difficulty)).setOnCheckedChangeListener((group, checkedId) -> {
            restart();
            onePlayerGameNo = 0;
            ImageView GameType = findViewById(R.id.Player);
            GameType.setImageResource(0);
            ImageView NewGm = findViewById(R.id.PlayAgain);
            NewGm.setImageResource(0);

            ((TextView) findViewById(R.id.status)).setText("Your Turn: O");
        });
    }

    int ActivePlayer = 0;
    int[] GamePosition = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int onePlayerGameNo = 0;
    int NewGame = 0;
    int YouWin = 0, YouLost = 0;
    int oWin = 0, xWin = 0;
    int oneDraw = 0, twoDraw = 0, move = 0, next = -1;

    public void SetOWinLine(int WinType)
    {
        if(WinType==1)
            ((ImageView)findViewById(R.id.HorizontalOne)).setImageResource(R.drawable.horigentalline);else  if(WinType==2)
            ((ImageView)findViewById(R.id.HorizontalTwo)).setImageResource(R.drawable.horigentalline);else  if(WinType==3)
            ((ImageView)findViewById(R.id.HorizontalThree)).setImageResource(R.drawable.horigentalline);else  if(WinType==4)
            ((ImageView)findViewById(R.id.VerticalOne)).setImageResource(R.drawable.verticalline);else  if(WinType==5)
            ((ImageView)findViewById(R.id.VerticalTwo)).setImageResource(R.drawable.verticalline);else  if(WinType==6)
            ((ImageView)findViewById(R.id.VerticalThree)).setImageResource(R.drawable.verticalline);else  if(WinType==7)
            ((ImageView)findViewById(R.id.DiagonalOne)).setImageResource(R.drawable.diagonalone);else  if(WinType==8)
            ((ImageView)findViewById(R.id.DiagonalTwo)).setImageResource(R.drawable.diagonaltwo);
    }

    public void SetXWinLine(int WinType)
    {
        if(WinType==-1)
            ((ImageView)findViewById(R.id.HorizontalOne)).setImageResource(R.drawable.horigentalline);else  if(WinType==-2)
            ((ImageView)findViewById(R.id.HorizontalTwo)).setImageResource(R.drawable.horigentalline);else  if(WinType==-3)
            ((ImageView)findViewById(R.id.HorizontalThree)).setImageResource(R.drawable.horigentalline);else  if(WinType==-4)
            ((ImageView)findViewById(R.id.VerticalOne)).setImageResource(R.drawable.verticalline);else  if(WinType==-5)
            ((ImageView)findViewById(R.id.VerticalTwo)).setImageResource(R.drawable.verticalline);else  if(WinType==-6)
            ((ImageView)findViewById(R.id.VerticalThree)).setImageResource(R.drawable.verticalline);else  if(WinType==-7)
            ((ImageView)findViewById(R.id.DiagonalOne)).setImageResource(R.drawable.diagonalone);else  if(WinType==-8)
            ((ImageView)findViewById(R.id.DiagonalTwo)).setImageResource(R.drawable.diagonaltwo);
    }


    public void OpSetImRes(int j)
    {
        final int o;
        if(ActivePlayer==0)
            o = R.drawable.o;
        else
            o = R.drawable.x;
        if(j==0) ((ImageView)findViewById(R.id.imageView0)).setImageResource(o);
        else if(j==1) ((ImageView)findViewById(R.id.imageView1)).setImageResource(o);
        else if(j==2) ((ImageView)findViewById(R.id.imageView2)).setImageResource(o);
        else if(j==3) ((ImageView)findViewById(R.id.imageView3)).setImageResource(o);
        else if(j==4) ((ImageView)findViewById(R.id.imageView4)).setImageResource(o);
        else if(j==5) ((ImageView)findViewById(R.id.imageView5)).setImageResource(o);
        else if(j==6) ((ImageView)findViewById(R.id.imageView6)).setImageResource(o);
        else if(j==7) ((ImageView)findViewById(R.id.imageView7)).setImageResource(o);
        else if(j==8) ((ImageView)findViewById(R.id.imageView8)).setImageResource(o);
    }

    @SuppressLint("SetTextI18n")
    public void tap(View v) throws InterruptedException {
        ImageView img = (ImageView) v;
        TextView status = findViewById(R.id.status);
        ImageView PlayAgain = findViewById(R.id.PlayAgain);
        ImageView New = findViewById(R.id.Player);
        TextView winNo = findViewById(R.id.winNo);
        TextView drawNo= findViewById(R.id.DrawNo);
        TextView lostNo = findViewById(R.id.lostNo);
        int i = Integer.parseInt(img.getTag().toString());

        if (GamePosition[i] !=2)
            return;
        MediaPlayer tap = MediaPlayer.create(this,R.raw.tapsound);
        if(soundOn==1)
            tap.start();
        tap.setOnCompletionListener(MediaPlayer::release);
        if(TypeOfGame==1)
        {
            move++;
            if(ActivePlayer==0)
                img.setImageResource(R.drawable.o);
            else
                img.setImageResource(R.drawable.x);
            GamePosition[i]=ActivePlayer;
            ActivePlayer=1-ActivePlayer;
            if(CheckWin()!=0)
            {
                if(ActivePlayer==1)
                    SetOWinLine(CheckWin());
                else
                    SetXWinLine(CheckWin());
                makeInactive();
                NewGame=1;
                PlayAgain.setImageResource(R.drawable.playagain);
                New.setImageResource(R.drawable.twoplayers);
                status.setText("You Won!");
                MediaPlayer music = MediaPlayer.create(this,R.raw.tictactoeaudio);
                if(soundOn==1)
                    music.start();
                music.setOnCompletionListener(MediaPlayer::release);
                YouWin++;
                winNo.setText(Integer.toString(YouWin));
            }
            else if(CheckDraw()==1)
            {
                makeInactive();
                NewGame=1;
                PlayAgain.setImageResource(R.drawable.playagain);
                New.setImageResource(R.drawable.twoplayers);
                status.setText("Game Draw!");
                MediaPlayer music = MediaPlayer.create(this,R.raw.tictactoeaudio);
                if(soundOn==1)
                  music.start();
                music.setOnCompletionListener(MediaPlayer::release);
                oneDraw++;
                drawNo.setText(Integer.toString(oneDraw));
            }
            else
            {
                int j = GeneratePos();
                Thread.sleep(200);
                OpSetImRes(j);
                tap.release();
                MediaPlayer OppTap = MediaPlayer.create(this,R.raw.tapsound);
                if(soundOn==1)
                  OppTap.start();
                OppTap.setOnCompletionListener(MediaPlayer::release);
                GamePosition[j]=ActivePlayer;
                ActivePlayer=1-ActivePlayer;
                move++;

                if(CheckWin()!=0)
                {
                    if(ActivePlayer==0)
                        SetXWinLine(CheckWin());
                    else
                        SetOWinLine(CheckWin());
                    makeInactive();
                    NewGame=1;
                    PlayAgain.setImageResource(R.drawable.playagain);
                    New.setImageResource(R.drawable.twoplayers);
                    status.setText("You Lost!");
                    MediaPlayer music = MediaPlayer.create(this,R.raw.tictactoeaudio);
                    music.setOnCompletionListener(MediaPlayer::release);
                    if(soundOn==1)
                      music.start();
                    YouLost++;
                    lostNo.setText(Integer.toString(YouLost));
                }
                else if(CheckDraw()==1)
                {
                    makeInactive();
                    NewGame=1;
                    PlayAgain.setImageResource(R.drawable.playagain);
                    New.setImageResource(R.drawable.twoplayers);
                    status.setText("Game Draw!");
                    MediaPlayer music = MediaPlayer.create(this,R.raw.tictactoeaudio);
                    if(soundOn==1)
                      music.start();
                    music.setOnCompletionListener(MediaPlayer::release);
                    oneDraw++;
                    drawNo.setText(Integer.toString(oneDraw));
                }
            }
        }
        else if(TypeOfGame==2) {
            GamePosition[i] = ActivePlayer;
            if (ActivePlayer == 0) {
                img.setImageResource(R.drawable.o);
                status.setText("X's Turn");
            } else {
                img.setImageResource(R.drawable.x);
                status.setText("O's Turn");
            }
            ActivePlayer=1-ActivePlayer;
            if (CheckWin() >0) {
                SetOWinLine(CheckWin());
                makeInactive();
                NewGame=1;
                status.setText("O Won!");
                MediaPlayer music = MediaPlayer.create(this,R.raw.tictactoeaudio);
                if(soundOn==1)
                music.start();
                music.setOnCompletionListener(MediaPlayer::release);
                oWin++;
                winNo.setText(Integer.toString(oWin));
                PlayAgain.setImageResource(R.drawable.playagain);
                New.setImageResource(R.drawable.oneplayer);
            }
            else if (CheckWin() <0) {
                SetXWinLine(CheckWin());
                makeInactive();
                NewGame=1;
                status.setText("X Won!");
                MediaPlayer music = MediaPlayer.create(this,R.raw.tictactoeaudio);
                if(soundOn==1)
                music.start();
                music.setOnCompletionListener(MediaPlayer::release);
                xWin++;
                lostNo.setText(Integer.toString(xWin));
                PlayAgain.setImageResource(R.drawable.playagain);
                New.setImageResource(R.drawable.oneplayer);
            }
            else if (CheckDraw() == 1) {
                makeInactive();
                NewGame=1;
                status.setText("Game Draw!");
                MediaPlayer music = MediaPlayer.create(this,R.raw.tictactoeaudio);
                if(soundOn==1)
                  music.start();
                music.setOnCompletionListener(MediaPlayer::release);
                twoDraw++;
                drawNo.setText(Integer.toString(twoDraw));
                PlayAgain.setImageResource(R.drawable.playagain);
                New.setImageResource(R.drawable.oneplayer);
            }
        }
    }

    public int isEmpty()
    {
        for(int i=0;i<4;i++)
        {
            if(GamePosition[i]!=2)
                return 0;
        }
        for(int i=5;i<9;i++)
        {
            if(GamePosition[i]!=2)
                return 0;
        }
        if(GamePosition[4]!=2)
            return 4;
        return 1;
    }

    public int GeneratePos()
    {
        RadioButton Easy = findViewById(R.id.Easy);
        RadioButton Hard = findViewById(R.id.Hard);
        RadioButton Expert = findViewById(R.id.Expert);

        if(!Easy.isChecked())
        {
            for(int i=0;i<9;i++)
            {
                if(GamePosition[i]==2)
                {
                    GamePosition[i] = ActivePlayer;
                    if(ActivePlayer==0)
                    {
                        if (CheckWin()>0) {
                            GamePosition[i] = 2;
                            return i;
                        }
                    }
                    else
                    {
                        if (CheckWin()<0) {
                            GamePosition[i] = 2;
                            return i;
                        }
                    }
                    GamePosition[i]=2;
                }
            }
            for(int i=0;i<9;i++)
            {
                if(GamePosition[i]==2)
                {
                    GamePosition[i] = 1-ActivePlayer;
                    if(ActivePlayer==0)
                    {
                        if(CheckWin()<0)
                        {
                            GamePosition[i]=2;
                            return i;
                        }
                    }
                    else
                    {
                        if(CheckWin()>0)
                        {
                            GamePosition[i]=2;
                            return i;
                        }
                    }
                    GamePosition[i]=2;
                }
            }
        }

        if(Hard.isChecked() || Expert.isChecked())
        {
            if(onePlayerGameNo==0)
            {
                if(GamePosition[4]==2)
                    return 4;
                else if(isEmpty()==4)
                {
                    int[]a = {0,2,6,8};
                    return a[(int)(4*Math.random())];
                }
            }
            if(Expert.isChecked())
            {
                if(onePlayerGameNo==0 && move==3)
                {
                    if(GamePosition[4]==0)
                    {
                        if(GamePosition[0]!=2 && GamePosition[8]!=2)
                        {
                            int[] a = {2,6};
                            return a[(int)(2*Math.random())];
                        }
                        else if(GamePosition[2]!=2 && GamePosition[6]!=2)
                        {
                            int[] a = {0,8};
                            return a[(int)(2*Math.random())];
                        }
                    }
                    if((GamePosition[0]!=2 && GamePosition[8]!=2)||(GamePosition[2]!=2 && GamePosition[6]!=2))
                    {
                        return (1+2*((int)(4*Math.random())));
                    }
                    else if((GamePosition[0]!=2 || GamePosition[2]!=2) && GamePosition[7]!=2)
                    {
                        int[]a = {3,5,6,8};
                        return a[(int)(4*Math.random())];
                    }                    else if((GamePosition[6]!=2 || GamePosition[8]!=2)&& GamePosition[1]!=2)
                    {
                        int[]a = {0,2,3,5};
                        return a[(int)(4*Math.random())];
                    }                    else if((GamePosition[0]!=2 || GamePosition[6]!=2)&& GamePosition[5]!=2)
                    {
                        int[]a = {1,2,7,8};
                        return a[(int)(4*Math.random())];
                    }                    else if((GamePosition[2]!=2 || GamePosition[8]!=2) && GamePosition[3]!=2)
                    {
                        int[]a = {0,1,6,7};
                        return a[(int)(4*Math.random())];
                    }
                                         else if((GamePosition[1]!=2 && GamePosition[3]!=2)||(GamePosition[5]!=2 && GamePosition[7]!=2))
                    {
                        int[]a = {2,6};
                        return a[(int)(2*Math.random())];
                    }                     else if((GamePosition[1]!=2 && GamePosition[5]!=2)||(GamePosition[3]!=2 && GamePosition[7]!=2))
                    {
                        int[]a = {0,8};
                        return a[(int)(2*Math.random())];
                    }
                }
                if(onePlayerGameNo==1)
                {
                    if(isEmpty()==1)
                    {
                        int[] a= {0,2,4,6,8};
                        return a[(int)(5*Math.random())];
                    }
                    if(move==4)
                    {
                        if(next!=-1)
                        {
                            int temp = next;
                            next=-1;
                            return temp;
                        }
                    }
                    if(move==2)
                    {
                        if(GamePosition[0]==0)
                        {
                            if(GamePosition[5]!=2 || GamePosition[7]!=2)
                            {
                                int[] a = {2,4,6};
                                int k = a[(int)(3*Math.random())];
                                if(k==2 || k==6)
                                    next = 4;
                                else
                                {
                                    int[] b= {2,6};
                                    next = b[(int)(2*Math.random())];
                                }
                                return k;
                            }
                            else if(GamePosition[1]!=2)
                            {
                                int[] a = {3,4,6};
                                int k = a[(int)(3*Math.random())];
                                if(k==3 || k==6)
                                    next = 4;
                                else
                                {
                                    int[] b= {3,6};
                                    next = b[(int)(2*Math.random())];
                                }
                                return k;
                            }
                            else if(GamePosition[3]!=2)
                            {
                                int[] a = {1,2,4};
                                int k = a[(int)(3*Math.random())];
                                if(k==1 || k==2)
                                    next = 4;
                                else
                                {
                                    int[] b= {1,2};
                                    next = b[(int)(2*Math.random())];
                                }
                                return k;
                            }
                            else if(GamePosition[2]!=2)
                            {
                                int[] a = {6,8};
                                int k = a[(int)(2*Math.random())];
                                if(k==6)
                                    next=8;
                                else next = 6;
                                return k;
                            }
                            else if(GamePosition[6]!=2)
                            {
                                int[] a = {2,8};
                                int k = a[(int)(2*Math.random())];
                                if(k==2)
                                    next=8;
                                else next = 2;
                                return k;
                            }
                            else if(GamePosition[8]!=2)
                            {
                                int[] a = {2,6};
                                int k = a[(int)(2*Math.random())];
                                if(k==2)
                                    next=6;
                                else next = 2;
                                return k;
                            }
                            else if(GamePosition[4]!=2)
                            {
                                int[] a = {5,7,8};
                                int k = a[(int)(3*Math.random())];
                                if(k==5)
                                    next = 2;
                                else if(k==7)
                                    next = 6;
                                return k;
                            }
                        }
                        else if(GamePosition[2]==0)
                        {
                            if(GamePosition[3]!=2 || GamePosition[7]!=2)
                            {
                                int[] a = {0,4,8};
                                int k = a[(int)(3*Math.random())];
                                if(k==0 || k==8)
                                    next = 4;
                                else
                                {
                                    int[] b= {0,8};
                                    next = b[(int)(2*Math.random())];
                                }
                                return k;
                            }
                            else if(GamePosition[1]!=2)
                            {
                                int[] a = {4,5,8};
                                int k = a[(int)(3*Math.random())];
                                if(k==5 || k==8)
                                    next = 4;
                                else
                                {
                                    int[] b= {5,8};
                                    next = b[(int)(2*Math.random())];
                                }
                                return k;
                            }
                            else if(GamePosition[5]!=2)
                            {
                                int[] a = {0,1,4};
                                int k = a[(int)(3*Math.random())];
                                if(k==1 || k==0)
                                    next = 4;
                                else
                                {
                                    int[] b= {1,0};
                                    next = b[(int)(2*Math.random())];
                                }
                                return k;
                            }
                            else if(GamePosition[0]!=2)
                            {
                                int[] a = {6,8};
                                int k = a[(int)(2*Math.random())];
                                if(k==6)
                                    next=8;
                                else next = 6;
                                return k;
                            }
                            else if(GamePosition[6]!=2)
                            {
                                int[] a = {0,8};
                                int k = a[(int)(2*Math.random())];
                                if(k==0)
                                    next=8;
                                else next = 0;
                                return k;
                            }
                            else if(GamePosition[8]!=2)
                            {
                                int[] a = {0,6};
                                int k = a[(int)(2*Math.random())];
                                if(k==0)
                                    next=6;
                                else next = 0;
                                return k;
                            }
                            else if(GamePosition[4]!=2)
                            {
                                int[] a = {3,6,7};
                                int k = a[(int)(3*Math.random())];
                                if(k==3)
                                    next = 0;
                                else if(k==7)
                                    next = 8;
                                return k;
                            }
                        }
                        else if(GamePosition[4]==0)
                        {
                            if(GamePosition[1]!=2)
                            {

                                int[] a = {0,2,6,8};
                                int k = a[(int)(4*Math.random())];
                                if(k==0)
                                    next=6;
                                else if(k==2)
                                    next=8;
                                return k;
                            }
                            else if(GamePosition[7]!=2)
                            {

                                int[] a = {0,2,6,8};
                                int k = a[(int)(4*Math.random())];
                                if(k==6)
                                    next=0;
                                else if(k==8)
                                    next=2;
                                return k;
                            }
                            else if(GamePosition[3]!=2)
                            {

                                int[] a = {0,2,6,8};
                                int k = a[(int)(4*Math.random())];
                                if(k==0)
                                    next=6;
                                else if(k==6)
                                    next=0;
                                return k;
                            }
                            else if(GamePosition[5]!=2)
                            {

                                int[] a = {0,2,6,8};
                                int k = a[(int)(4*Math.random())];
                                if(k==2)
                                    next=8;
                                else if(k==8)
                                    next=2;
                                return k;
                            }
                            else if(GamePosition[0]!=2)
                            {
                                return 8;
                            }
                            else if(GamePosition[2]!=2)
                            {
                                return 6;
                            }
                            else if(GamePosition[6]!=2)
                            {
                                return 2;
                            }
                            else if(GamePosition[8]!=2)
                            {
                                return 0;
                            }
                        }
                        else if(GamePosition[6]==0)
                        {
                            if(GamePosition[5]!=2 || GamePosition[1]!=2)
                            {
                                int[] a = {0,4,8};
                                int k = a[(int)(3*Math.random())];
                                if(k==0 || k==8)
                                    next = 4;
                                else
                                {
                                    int[] b= {0,8};
                                    next = b[(int)(2*Math.random())];
                                }
                                return k;
                            }
                            else if(GamePosition[3]!=2)
                            {
                                int[] a = {4,7,8};
                                int k = a[(int)(3*Math.random())];
                                if(k==7 || k==8)
                                    next = 4;
                                else
                                {
                                    int[] b= {7,8};
                                    next = b[(int)(2*Math.random())];
                                }
                                return k;
                            }
                            else if(GamePosition[7]!=2)
                            {
                                int[] a = {0,3,4};
                                int k = a[(int)(3*Math.random())];
                                if(k==0 || k==3)
                                    next = 4;
                                else
                                {
                                    int[] b= {0,3};
                                    next = b[(int)(2*Math.random())];
                                }
                                return k;
                            }
                            else if(GamePosition[2]!=2)
                            {
                                int[] a = {0,8};
                                int k = a[(int)(2*Math.random())];
                                if(k==0)
                                    next=8;
                                else next = 0;
                                return k;
                            }
                            else if(GamePosition[0]!=2)
                            {
                                int[] a = {2,8};
                                int k = a[(int)(2*Math.random())];
                                if(k==2)
                                    next=8;
                                else next = 2;
                                return k;
                            }
                            else if(GamePosition[8]!=2)
                            {
                                int[] a = {0,2};
                                int k = a[(int)(2*Math.random())];
                                if(k==0)
                                    next=2;
                                else next = 0;
                                return k;
                            }
                            else if(GamePosition[4]!=2)
                            {
                                int[] a = {1,2,5};
                                int k = a[(int)(3*Math.random())];
                                if(k==1)
                                    next = 0;
                                else if(k==5)
                                    next = 8;
                                return k;
                            }
                        }
                        else if(GamePosition[8]==0)
                        {
                            if(GamePosition[3]!=2 || GamePosition[1]!=2)
                            {
                                int[] a = {2,4,6};
                                int k = a[(int)(3*Math.random())];
                                if(k==2 || k==6)
                                    next = 4;
                                else
                                {
                                    int[] b= {2,6};
                                    next = b[(int)(2*Math.random())];
                                }
                                return k;
                            }
                            else if(GamePosition[5]!=2)
                            {
                                int[] a = {4,6,7};
                                int k = a[(int)(3*Math.random())];
                                if(k==7 || k==6)
                                    next = 4;
                                else
                                {
                                    int[] b= {7,6};
                                    next = b[(int)(2*Math.random())];
                                }
                                return k;
                            }
                            else if(GamePosition[7]!=2)
                            {
                                int[] a = {2,4,5};
                                int k = a[(int)(3*Math.random())];
                                if(k==2 || k==5)
                                    next = 4;
                                else
                                {
                                    int[] b= {2,5};
                                    next = b[(int)(2*Math.random())];
                                }
                                return k;
                            }
                            else if(GamePosition[2]!=2)
                            {
                                int[] a = {0,6};
                                int k = a[(int)(2*Math.random())];
                                if(k==6)
                                    next=0;
                                else next = 6;
                                return k;
                            }
                            else if(GamePosition[6]!=2)
                            {
                                int[] a = {0,2};
                                int k = a[(int)(2*Math.random())];
                                if(k==0)
                                    next=2;
                                else next = 0;
                                return k;
                            }
                            else if(GamePosition[0]!=2)
                            {
                                int[] a = {2,6};
                                int k = a[(int)(2*Math.random())];
                                if(k==6)
                                    next=2;
                                else next = 6;
                                return k;
                            }
                            else if(GamePosition[4]!=2)
                            {
                                int[] a = {0,1,3};
                                int k = a[(int)(3*Math.random())];
                                if(k==1)
                                    next = 2;
                                else if(k==3)
                                    next = 6;
                                return k;
                            }
                        }
                    }
                }
            }
        }

        Vector<Integer> vec = new Vector<>();
        for(int i=0;i<9;i++)
        {
            if(GamePosition[i]==2)
                vec.add(i);
        }
        int size = vec.size();
        int j = (int)(size*Math.random());
        return vec.get(j);
    }

    public void restart()
    {
        for (int i = 0; i < 9; i++)
            GamePosition[i] = 2;
        ActivePlayer = 0;
        NewGame=0;
        move=0;
        ((ImageView)findViewById(R.id.HorizontalOne)).setImageResource(0);
        ((ImageView)findViewById(R.id.HorizontalTwo)).setImageResource(0);
        ((ImageView)findViewById(R.id.HorizontalThree)).setImageResource(0);
        ((ImageView)findViewById(R.id.VerticalOne)).setImageResource(0);
        ((ImageView)findViewById(R.id.VerticalTwo)).setImageResource(0);
        ((ImageView)findViewById(R.id.VerticalThree)).setImageResource(0);
        ((ImageView)findViewById(R.id.DiagonalOne)).setImageResource(0);
        ((ImageView)findViewById(R.id.DiagonalTwo)).setImageResource(0);

        ((ImageView)findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);
    }

    public void reSet()
    {
        restart();
        if(TypeOfGame==1)
            onePlayerGameNo=1-onePlayerGameNo;
    }

    @SuppressLint("SetTextI18n")
    public void ReMatch(View v){
        if(NewGame==1)
        {
            reSet();
            ((ImageView)v).setImageResource(0);
            ImageView GameType = findViewById(R.id.Player);
            TextView status = findViewById(R.id.status);
            GameType.setImageResource(0);
            if(TypeOfGame==1)
            {
                if(onePlayerGameNo==0)
                    status.setText("Your Turn: O");
                else
                    status.setText("Your Turn: X");
            }

            else
                status.setText("O's Turn");
            if(TypeOfGame==1 && onePlayerGameNo==1)
            {
                int j = GeneratePos();
                OpSetImRes(j);
                MediaPlayer tap = MediaPlayer.create(this,R.raw.tapsound);
                if(soundOn==1)
                  tap.start();
                tap.setOnCompletionListener(MediaPlayer::release);
                GamePosition[j]=ActivePlayer;
                ActivePlayer=1-ActivePlayer;
                move++;
            }
        }
    }

    @SuppressLint("SetTextI18n")
    public void GameType(View v) {

        if(NewGame==1) {
            TextView winNo = findViewById(R.id.winNo);
            TextView drawNo= findViewById(R.id.DrawNo);
            TextView lostNo = findViewById(R.id.lostNo);
            TextView status = findViewById(R.id.status);
            TextView youWin = findViewById(R.id.YouWin);
            TextView youLost = findViewById(R.id.YouLost);

            RadioButton easy = findViewById(R.id.Easy);
            RadioButton medium = findViewById(R.id.Medium);
            RadioButton hard = findViewById(R.id.Hard);
            RadioButton expert = findViewById(R.id.Expert);

            reSet();
            ImageView img = (ImageView) v;
            ImageView im = findViewById(R.id.hide);
            if (TypeOfGame == 2) {
                TypeOfGame = 1;
                easy.setEnabled(true);
                medium.setEnabled(true);
                hard.setEnabled(true);
                expert.setEnabled(true);
                easy.setChecked(false);
                medium.setChecked(true);
                hard.setChecked(false);
                expert.setChecked(false);
                img.setImageResource(R.drawable.twoplayers);
                im.setImageResource(0);
                status.setText("Your Turn: O");
                youWin.setText("You Won");
                youLost.setText("You Lost");
                winNo.setText(Integer.toString(YouWin));
                drawNo.setText(Integer.toString(oneDraw));
                lostNo.setText(Integer.toString(YouLost));
            } else {
                TypeOfGame = 2;
                img.setImageResource(R.drawable.oneplayer);
                im.setImageResource(R.drawable.white);
                status.setText("O's Turn");
                youWin.setText("O Wins");
                youLost.setText("X Wins");
                winNo.setText(Integer.toString(oWin));
                drawNo.setText(Integer.toString(twoDraw));
                lostNo.setText(Integer.toString(xWin));

                easy.setEnabled(false);
                medium.setEnabled(false);
                hard.setEnabled(false);
                expert.setEnabled(false);
            }
            img.setImageResource(0);
            ImageView NewGm = findViewById(R.id.PlayAgain);
            NewGm.setImageResource(0);
        }
    }

    public int CheckWin()
    {
        if(GamePosition[0]==GamePosition[1] && GamePosition[1]==GamePosition[2] && GamePosition[2]==0)
            return 1;else if(GamePosition[3]==GamePosition[4] && GamePosition[4]==GamePosition[5] && GamePosition[5]==0)
            return 2;else if(GamePosition[6]==GamePosition[7] && GamePosition[7]==GamePosition[8] && GamePosition[8]==0)
            return 3;else if(GamePosition[0]==GamePosition[3] && GamePosition[3]==GamePosition[6] && GamePosition[6]==0)
            return 4;else if(GamePosition[1]==GamePosition[4] && GamePosition[4]==GamePosition[7] && GamePosition[7]==0)
            return 5;else if(GamePosition[2]==GamePosition[5] && GamePosition[5]==GamePosition[8] && GamePosition[8]==0)
            return 6;else if(GamePosition[2]==GamePosition[4] && GamePosition[4]==GamePosition[6] && GamePosition[6]==0)
            return 7;else if(GamePosition[0]==GamePosition[4] && GamePosition[4]==GamePosition[8] && GamePosition[8]==0)
            return 8;else if(GamePosition[0]==GamePosition[1] && GamePosition[1]==GamePosition[2] && GamePosition[2]==1)
            return -1;else if(GamePosition[3]==GamePosition[4] && GamePosition[4]==GamePosition[5] && GamePosition[5]==1)
            return -2;else if(GamePosition[6]==GamePosition[7] && GamePosition[7]==GamePosition[8] && GamePosition[8]==1)
            return -3;else if(GamePosition[0]==GamePosition[3] && GamePosition[3]==GamePosition[6] && GamePosition[6]==1)
            return -4;else if(GamePosition[1]==GamePosition[4] && GamePosition[4]==GamePosition[7] && GamePosition[7]==1)
            return -5;else if(GamePosition[2]==GamePosition[5] && GamePosition[5]==GamePosition[8] && GamePosition[8]==1)
            return -6;else if(GamePosition[2]==GamePosition[4] && GamePosition[4]==GamePosition[6] && GamePosition[6]==1)
            return -7;else if(GamePosition[0]==GamePosition[4] && GamePosition[4]==GamePosition[8] && GamePosition[8]==1)
            return -8;
            return 0;
    }

    public int CheckDraw()
    {
        for (int j = 0; j < 9; j++)
        {
            if (GamePosition[j] == 2)
                return 0;
        }
        return 1;
    }

    public void makeInactive()
    {
        for(int i=0;i<9;i++)
            GamePosition[i]=-1;
    }
}