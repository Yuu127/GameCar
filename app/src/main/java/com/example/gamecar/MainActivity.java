package com.example.gamecar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView txtDiem;
    ImageButton ibtnPlay;
    CheckBox cbOne, cbTwo, cbThree;
    SeekBar skOne, skTwo, skThree;
    int soDiem = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnhXa();

        skOne.setEnabled(false);
        skTwo.setEnabled(false);
        skThree.setEnabled(false);

        txtDiem.setText(soDiem + "");

        final CountDownTimer countDownTimer = new CountDownTimer(60000, 600){
            public  void onTick(long l) {
                //random tốc độ chạy của xe trong khoảng 5 - 15
                int minSpeed = 5;
                int maxSpeed  = 15;
                Random random = new Random();
                int one = minSpeed + random.nextInt(maxSpeed - minSpeed + 1);
                int two = minSpeed + random.nextInt(maxSpeed - minSpeed + 1);
                int three = minSpeed + random.nextInt(maxSpeed - minSpeed + 1);

                if(skOne.getProgress() >= skOne.getMax()){
                    this.cancel();
                    ibtnPlay.setVisibility(View.VISIBLE); // sau khi win sẽ hiện lại start
                    //thông báo xe win
                    Toast.makeText(MainActivity.this, "One Win", Toast.LENGTH_SHORT).show();
                    //Kiểm tra đặt cược
                    if(cbOne.isChecked()){
                        soDiem += 10;
                        Toast.makeText(MainActivity.this, "Đặt cược thành công", Toast.LENGTH_SHORT).show();
                    }else{
                        soDiem -= 5;
                        Toast.makeText(MainActivity.this, "Đặt cược không thành công", Toast.LENGTH_SHORT).show();
                    }
                    txtDiem.setText(soDiem + "");
                    EnableCheckBox();
                }
                if(skTwo.getProgress() >= skTwo.getMax()){
                    this.cancel();
                    ibtnPlay.setVisibility(View.VISIBLE);
                    Toast.makeText(MainActivity.this, "Two Win", Toast.LENGTH_SHORT).show();
                    if(cbTwo.isChecked()){
                        soDiem += 10;
                        Toast.makeText(MainActivity.this, "Đặt cược thành công", Toast.LENGTH_SHORT).show();
                    }else{
                        soDiem -= 5;
                        Toast.makeText(MainActivity.this, "Đặt cược không thành công", Toast.LENGTH_SHORT).show();
                    }
                    txtDiem.setText(soDiem + "");
                    EnableCheckBox();
                }
                if(skThree.getProgress() >= skThree.getMax()){
                    this.cancel();
                    ibtnPlay.setVisibility(View.VISIBLE);
                    Toast.makeText(MainActivity.this, "Three Win", Toast.LENGTH_SHORT).show();
                    if(cbThree.isChecked()){
                        soDiem += 10;
                        Toast.makeText(MainActivity.this, "Đặt cược thành công", Toast.LENGTH_SHORT).show();
                    }else{
                        soDiem -= 5;
                        Toast.makeText(MainActivity.this, "Đặt cược không thành công", Toast.LENGTH_SHORT).show();
                    }
                    txtDiem.setText(soDiem + "");
                    EnableCheckBox();
                }

                skOne.setProgress(skOne.getProgress() + one);
                skTwo.setProgress(skTwo.getProgress() + two);
                skThree.setProgress(skThree.getProgress() + three);

            }
            public void onFinish(){

            }
        };

        ibtnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cbOne.isChecked() || cbTwo.isChecked() || cbThree.isChecked()) {
                    // sau khi có xe thắng hiện nút start, bấm vào sẽ quay lại từ đầu
                    skOne.setProgress(0);
                    skTwo.setProgress(0);
                    skThree.setProgress(0);

                    ibtnPlay.setVisibility(View.INVISIBLE);// khi bấm start sẽ ẩn đi
                    countDownTimer.start();

                    DisableCheckBox();
                }else {
                    Toast.makeText(MainActivity.this, "Vui lòng bấm cược xe sẽ thắng trước khi chơi! ", Toast.LENGTH_SHORT).show();
                }
            }
        });

        cbOne.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    //kiểm tra checkbox
                    cbTwo.setChecked(false);
                    cbThree.setChecked(false);
                }
            }
        });
        cbTwo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                cbOne.setChecked(false);
                cbThree.setChecked(false);
            }
        });
        cbThree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                cbOne.setChecked(false);
                cbTwo.setChecked(false);
            }
        });
    }

    private void EnableCheckBox(){
        cbOne.setEnabled(true);
        cbTwo.setEnabled(true);
        cbThree.setEnabled(true);
    }
    private void DisableCheckBox(){
        cbOne.setEnabled(false);
        cbTwo.setEnabled(false);
        cbThree.setEnabled(false);
    }

    private void AnhXa(){
        txtDiem  = (TextView) findViewById(R.id.textviewDiemSo);
        ibtnPlay = (ImageButton)  findViewById(R.id.buttonPlay);
        cbOne    = (CheckBox) findViewById(R.id.checkboxOne);
        cbTwo    = (CheckBox) findViewById(R.id.checkboxTwo);
        cbThree  = (CheckBox) findViewById(R.id.checkboxThree);
        skOne    = (SeekBar) findViewById(R.id.seekbarOne);
        skTwo    = (SeekBar) findViewById(R.id.seekbarTwo);
        skThree  = (SeekBar) findViewById(R.id.seekbarThree);

    }
}