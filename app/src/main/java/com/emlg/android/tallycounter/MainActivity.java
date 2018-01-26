package com.emlg.android.tallycounter;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewSwitcher;

public class MainActivity extends AppCompatActivity {
    private static final String PREFS_NAME = "prefs";
    private static final String PREF_DARK_THEME = "dark_theme";
    private static final String IS_CHECKED = "testcheck";
    private boolean darktheme = false;
    private LinearLayout mCounterList;

/*    private void toggleTheme(boolean darkTheme) {
        SharedPreferences.Editor editor = getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
        editor.putBoolean(PREF_DARK_THEME, darkTheme);
        editor.apply();

        Intent intent = getIntent();
        finish();

        startActivity(intent);
    }*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
/*        //MenuItem toggle = (MenuItem) findViewById(R.id.toggle_theme);
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        boolean useDarkTheme = preferences.getBoolean(PREF_DARK_THEME, false);

        if(useDarkTheme) {
            setTheme(R.style.AppTheme_Dark);
            //toggle.setChecked(true);
        }*/
        setTheme(R.style.AppTheme_Dark);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCounterList = (LinearLayout) findViewById(R.id.counter_list);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.add_row:
                mCounterList.addView(new CounterView(this));
            case R.id.toggle_theme:
               /* if (item.isChecked()){
                    item.setChecked(false);
                    toggleTheme(false);
                }
                if(!item.isChecked()) {
                    item.setChecked(true);
                    toggleTheme(true);
                }*/
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
