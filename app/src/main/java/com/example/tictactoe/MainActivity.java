package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tictactoe.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityMainBinding binding;
    Button reset;
    TextView text;
    Button[][] board;
    boolean player = true;
    int count;
    boolean z = false;
    MediaPlayer x;
    MediaPlayer y;
    MediaPlayer w;
    MediaPlayer d;
    Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        create();
    }

    public void create() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        reset = findViewById(R.id.reset);
        text = findViewById(R.id.text);
        board = new Button[3][3];
        board[0] = new Button[]{binding.button1, binding.button2, binding.button3};
        board[1] = new Button[]{binding.button4, binding.button5, binding.button6};
        board[2] = new Button[]{binding.button7, binding.button8, binding.button9};
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++) {
                board[i][j].setOnClickListener(this);
            }
        x = MediaPlayer.create(this, R.raw.x);
        y = MediaPlayer.create(this, R.raw.y);
        w = MediaPlayer.create(this, R.raw.w);
        d = MediaPlayer.create(this, R.raw.d);
    }

    @Override
    public void onClick(View v) {

        if (player) {
            x.start();
            ((Button) v).setText("X");
            z = win("X");
        }
        if (!player) {
            y.start();
            ((Button) v).setText("O");
            z = win("O");
        }
        ((Button) v).setEnabled(false);

        count++;
        if (z == false) {
            if (count != 9) {
                player = !player;
                if (player) {
                    binding.text.setText(getResources().getString(R.string.xt));
                }
                if (!player)
                    binding.text.setText(getResources().getString(R.string.ot));


            } else {
                d.start();

                binding.text.setText("Match Drawn");
            }
        }
    }

    boolean win(String z) {
        if (board[0][0].getText() == board[1][1].getText() && board[2][2].getText() == board[0][0].getText() && board[0][0].getText().toString() == z) {
            w.start();
            if (z == "X")
                binding.text.setText(getResources().getString(R.string.x));
            if (z == "O")
                binding.text.setText(getResources().getString(R.string.o));
            disable();

            setColor(0, 0);
            setColor(1, 1);
            setColor(2, 2);
            return true;
        }

        if (board[2][0].getText().toString() == board[1][1].getText().toString() && board[2][0].getText().toString() == board[0][2].getText().toString() && board[2][0].getText().toString() == z) {
            w.start();
            if (z == "X")
                binding.text.setText(getResources().getString(R.string.x));
            if (z == "O")
                binding.text.setText(getResources().getString(R.string.o));
            disable();

            setColor(2, 0);
            setColor(1, 1);
            setColor(0, 2);
            return true;
        }


        for (int i = 0; i < 3; i++) {
            if (board[i][0].getText().toString() == board[i][1].getText().toString() && board[i][2].getText().toString() == board[i][0].getText().toString() && board[i][0].getText().toString() == z) {
                w.start();
                if (z == "X")
                    binding.text.setText(getResources().getString(R.string.x));
                if (z == "O")
                    binding.text.setText(getResources().getString(R.string.o));
                disable();
                setColor(i, 0);
                setColor(i, 1);
                setColor(i, 2);
                return true;
            }
        }


        for (int i = 0; i < 3; i++) {
            if (board[0][i].getText().toString() == board[1][i].getText().toString() && board[2][i].getText().toString() == board[0][i].getText().toString() && board[0][i].getText().toString() == z) {
                w.start();
                if (z == "X")
                    binding.text.setText(getResources().getString(R.string.x));
                if (z == "O")
                    binding.text.setText(getResources().getString(R.string.o));
                disable();
                setColor(0, i);
                setColor(1, i);
                setColor(2, i);
                return true;
            }

        }
        return false;
    }

    public void reset(View view) {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++) {

                board[i][j].setEnabled(true);
                board[i][j].setText("");

            }
        player = true;
        count = 0;
        z = false;
        binding.text.setText("Player X Turn");
        create();

    }


    public void disable() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++) {
                board[i][j].setEnabled(false);
            }
    }

    public void setColor(int x, int y) {
        board[x][y].setBackgroundColor(Color.CYAN);
    }
}