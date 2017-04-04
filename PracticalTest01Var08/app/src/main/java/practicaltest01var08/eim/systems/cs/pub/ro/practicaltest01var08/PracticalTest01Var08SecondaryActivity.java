package practicaltest01var08.eim.systems.cs.pub.ro.practicaltest01var08;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PracticalTest01Var08SecondaryActivity extends AppCompatActivity {

    ButtonListener listener = new ButtonListener();

    private class ButtonListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            int first = Integer.parseInt(((EditText) findViewById(R.id.textField11)).getText().toString());
            int second = Integer.parseInt(((EditText) findViewById(R.id.textField22)).getText().toString());
            int third = Integer.parseInt(((EditText) findViewById(R.id.textField33)).getText().toString());
            int fourth = Integer.parseInt(((EditText) findViewById(R.id.textField44)).getText().toString());
            if (v.getId() == R.id.sumButton) {
                int sum = first + second + third + fourth;
                Log.i("Secondary Activity sum", String.valueOf(sum));
            }
            if (v.getId() == R.id.productButton) {
                int product = first * second * third * fourth;
                Log.i("Secondary Activity prod", String.valueOf(product));
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var08_secondary);

        EditText editText1 = (EditText) findViewById(R.id.textField11);
        EditText editText2 = (EditText) findViewById(R.id.textField22);
        EditText editText3 = (EditText) findViewById(R.id.textField33);
        EditText editText4 = (EditText) findViewById(R.id.textField44);

        Intent intent = getIntent();
        if (intent != null && intent.getExtras().containsKey("first")) {
            int first = intent.getIntExtra("first", -1);
            int second = intent.getIntExtra("second", -1);
            int third = intent.getIntExtra("third", -1);
            int fourth = intent.getIntExtra("fourth", -1);

            editText1.setText(String.valueOf(first));
            editText2.setText(String.valueOf(second));
            editText3.setText(String.valueOf(third));
            editText4.setText(String.valueOf(fourth));
        }

        Button sumButton = (Button) findViewById(R.id.sumButton);
        Button productButton = (Button) findViewById(R.id.productButton);

        sumButton.setOnClickListener(listener);
        productButton.setOnClickListener(listener);
    }
}
