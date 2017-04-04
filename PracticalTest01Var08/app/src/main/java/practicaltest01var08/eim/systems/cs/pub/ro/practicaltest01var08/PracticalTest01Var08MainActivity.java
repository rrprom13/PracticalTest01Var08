package practicaltest01var08.eim.systems.cs.pub.ro.practicaltest01var08;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PracticalTest01Var08MainActivity extends AppCompatActivity {

    private static final int SECONDARY_ACTIVITY_REQUEST_CODE = 1;

    ButtonListener listener = new ButtonListener();
    EditText editText1;
    EditText editText2;
    EditText editText3;
    EditText editText4;

    private class ButtonListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            String text1 = editText1.getText().toString();
            String text2 = editText2.getText().toString();
            String text3 = editText3.getText().toString();
            String text4 = editText4.getText().toString();
            try {
                int int1 =  Integer.parseInt(text1);
                int int2 =  Integer.parseInt(text2);
                int int3 =  Integer.parseInt(text3);
                int int4 =  Integer.parseInt(text4);

                Intent intent = new Intent(getApplicationContext(), PracticalTest01Var08SecondaryActivity.class);
                intent.putExtra("first", int1);
                intent.putExtra("second", int2);
                intent.putExtra("third", int3);
                intent.putExtra("fourth", int4);
                startActivityForResult(intent, SECONDARY_ACTIVITY_REQUEST_CODE);
            } catch (NumberFormatException e) {
               return;
            }
        }
    }

    private MessageBroadcastReceiver messageBroadcastReceiver = new MessageBroadcastReceiver();
    private IntentFilter intentFilter = new IntentFilter();
    private class MessageBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String first = intent.getStringExtra("first");
            String second = intent.getStringExtra("second");
            String third = intent.getStringExtra("third");
            String fourth = intent.getStringExtra("fourth");

            Log.i("MAIN", first + " " + second + " " + third + " " + fourth);

            editText1.setText(first);
            editText2.setText(second);
            editText3.setText(third);
            editText4.setText(fourth);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var08_main);

        editText1 = (EditText) findViewById(R.id.textField1);
        editText2 = (EditText) findViewById(R.id.textField2);
        editText3 = (EditText) findViewById(R.id.textField3);
        editText4 = (EditText) findViewById(R.id.textField4);

        Button setButton = (Button) findViewById(R.id.setButton);
        setButton.setOnClickListener(listener);

        intentFilter.addAction("eimsimulationtest01.systems.cs.pub.ro.simulationtest01.string");

        Intent intent = new Intent(getApplicationContext(), PracticalTest01Var08Service.class);
        getApplicationContext().startService(intent);
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putString("first", editText1.getText().toString());
        savedInstanceState.putString("second", editText2.getText().toString());
        savedInstanceState.putString("third", editText3.getText().toString());
        savedInstanceState.putString("fourth", editText4.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            return;
        }
        if (savedInstanceState.containsKey("first")) {
            editText1.setText(savedInstanceState.getString("first"));
        } else {
            editText1.setText(String.valueOf(0));
        }
        if (savedInstanceState.containsKey("second")) {
            editText2.setText(savedInstanceState.getString("second"));
        } else {
            editText2.setText(String.valueOf(0));
        }
        if (savedInstanceState.containsKey("third")) {
            editText3.setText(savedInstanceState.getString("third"));
        } else {
            editText3.setText(String.valueOf(0));
        }
        if (savedInstanceState.containsKey("fourth")) {
            editText4.setText(savedInstanceState.getString("fourth"));
        } else {
            editText4.setText(String.valueOf(0));
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("MAIN", "onRestart() method was invoked");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("MAIN", "onStart() method was invoked");
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(messageBroadcastReceiver, intentFilter);
        Log.i("MAIN", "onResume() method was invoked");
    }

    @Override
    protected void onPause() {
        unregisterReceiver(messageBroadcastReceiver);
        super.onPause();
        Log.i("MAIN", "onPause() method was invoked");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("MAIN", "onStop() method was invoked");
    }

    @Override
    protected void onDestroy() {
        Intent intent = new Intent(this, PracticalTest01Var08Service.class);
        stopService(intent);
        super.onDestroy();
        Log.i("Main activity", "onDestroy() method was invoked");
    }
}
