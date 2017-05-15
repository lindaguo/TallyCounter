package com.emlg.android.tallycounter;

import android.content.Context;
import android.os.PowerManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ViewSwitcher;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    protected PowerManager.WakeLock mWakeLock;

    private ViewSwitcher mCounterNameView;
    private EditText mCounterStartEditText;
    private EditText mCounterIncrementEditText;
    private TextView mCounterDisplayTextView;

    private int mCounterDisplay;
    private int mCounterStartCount;
    private int mCounterIncrement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        mWakeLock = pm.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK, "TallyCountWakeLock");
        mWakeLock.acquire();

        mCounterDisplay = 0;
        mCounterIncrement = 1;
        mCounterStartCount = 0;

        mCounterNameView = (ViewSwitcher) findViewById(R.id.counter_name);
        mCounterDisplayTextView = (TextView) findViewById(R.id.counter_display);
        mCounterDisplayTextView.requestFocus();

        findViewById(R.id.add_button).setOnClickListener(this);
        findViewById(R.id.sub_button).setOnClickListener(this);
        findViewById(R.id.reset).setOnClickListener(this);
        findViewById(R.id.counter_increment_set_button).setOnClickListener(this);
        findViewById(R.id.counter_start_set_button).setOnClickListener(this);

        mCounterStartEditText = (EditText) findViewById(R.id.counter_start);
        mCounterIncrementEditText = (EditText) findViewById(R.id.counter_increment);

    }

    private void setCounterDisplayText() {
        mCounterDisplayTextView.setText(String.valueOf(mCounterDisplay));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_button: {
                mCounterDisplay += mCounterIncrement;
                setCounterDisplayText();
                break;
            }
            case R.id.sub_button: {
                if (mCounterDisplay != mCounterStartCount) {
                    mCounterDisplay -= mCounterIncrement;
                    setCounterDisplayText();
                }
                break;
            }
            case R.id.reset: {
                mCounterDisplay = mCounterStartCount;
                setCounterDisplayText();
                break;
            }
            case R.id.counter_increment_set_button: {
                mCounterIncrement = Integer.parseInt(mCounterIncrementEditText.getText().toString());
                removeViewFocus(mCounterIncrementEditText);
                break;
            }
            case R.id.counter_start_set_button: {
                mCounterStartCount = Integer.parseInt(mCounterStartEditText.getText().toString());
                removeViewFocus(mCounterStartEditText);
                break;
            }
            default: {
                // no-op
                break;
            }
        }
    }

    private void removeViewFocus(View view) {
        view.clearFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        mCounterDisplayTextView.requestFocus();
    }

    @Override
    public void onStop() {
        mWakeLock.release();
        super.onStop();
    }
}
