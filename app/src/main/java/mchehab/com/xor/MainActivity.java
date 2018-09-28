package mchehab.com.xor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.NumberPicker;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements NumberPicker.OnValueChangeListener {

    private TFXOR tfxor;

    private NumberPicker numberPickerLeft;
    private NumberPicker numberPickerRight;
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tfxor = new TFXOR(getApplicationContext());

        initUI();
        textViewResult.setText(predict(0, 0));
    }

    private void initUI(){
        textViewResult = findViewById(R.id.textViewResult);
        numberPickerLeft = findViewById(R.id.numberPickerLeft);
        numberPickerRight = findViewById(R.id.numberPickerRight);
        setupPickerValues(numberPickerLeft);
        setupPickerValues(numberPickerRight);
    }

    private void setupPickerValues(NumberPicker numberPicker){
        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(1);
        numberPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        numberPicker.setOnValueChangedListener(this);
    }

    @Override
    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
        int leftPicker = numberPickerLeft.getValue();
        int rightPicker = numberPickerRight.getValue();

        textViewResult.setText(predict(leftPicker, rightPicker));
    }

    private String predict(int left, int right){
        float[]data = {left, right};
        float[] result = tfxor.predict(data);
        int answer = Math.round(result[0]);
        return left + " XOR " + right + " = " + answer;
    }
}