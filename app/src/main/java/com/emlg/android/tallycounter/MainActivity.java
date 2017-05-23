package com.emlg.android.tallycounter;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ViewSwitcher;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

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

        mCounterDisplay = 0;
        mCounterIncrement = 1;
        mCounterStartCount = 0;

        mCounterNameView = (ViewSwitcher) findViewById(R.id.counter_name);
        mCounterDisplayTextView = (TextView) findViewById(R.id.counter_display);
        mCounterDisplayTextView.requestFocus();

        findViewById(R.id.add_button).setOnClickListener(this);
        findViewById(R.id.sub_button).setOnClickListener(this);
        findViewById(R.id.reset).setOnClickListener(this);
        findViewById(R.id.setting).setOnClickListener(this);


        Button add = (Button)findViewById(R.id.add_button);
        Button sub = (Button)findViewById(R.id.sub_button);
        add.setText(String.valueOf(mCounterIncrement));
        sub.setText(String.valueOf(mCounterIncrement));


    }

    private void setCounterDisplayText() {
        mCounterDisplayTextView.setText(String.valueOf(mCounterDisplay));
    }

    public void alertdialog(){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        LayoutInflater inflater = this.getLayoutInflater();
        View dialogSettingsView = inflater.inflate(R.layout.dialog_settings,null);
        alert.setView(dialogSettingsView);
        mCounterStartEditText = (EditText) dialogSettingsView.findViewById(R.id.Start_Count);
        mCounterIncrementEditText = (EditText) dialogSettingsView.findViewById(R.id.Increment);
        mCounterStartEditText.setText(String.valueOf(mCounterStartCount));
        mCounterIncrementEditText.setText(String.valueOf(mCounterIncrement));


        alert.setTitle(R.string.settings_title);

        alert.setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //Confirm
                mCounterStartCount = Integer.parseInt(mCounterStartEditText.getText().toString());
                mCounterIncrement = Integer.parseInt(mCounterIncrementEditText.getText().toString());
                Button add = (Button)findViewById(R.id.add_button);
                Button sub = (Button)findViewById(R.id.sub_button);
                add.setText(String.valueOf(mCounterIncrement));
                sub.setText(String.valueOf(mCounterIncrement));
                mCounterDisplay = mCounterStartCount;
                setCounterDisplayText();
            }
        });

        alert.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // Cancel
            }
        });

        alert.show();
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
            case R.id.setting:{
                alertdialog();
                break;
            }
            default: {
                // no-op
                break;
            }
        }
    }
}
