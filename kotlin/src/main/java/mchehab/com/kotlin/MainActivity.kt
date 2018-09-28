package mchehab.com.kotlin

import kotlinx.android.synthetic.main.activity_main.*
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.NumberPicker

class MainActivity : AppCompatActivity(), NumberPicker.OnValueChangeListener {

    private lateinit var tfxor: TFXOR

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tfxor = TFXOR(applicationContext)
        setupPickerValues(numberPickerLeft)
        setupPickerValues(numberPickerRight)
        textViewResult.text = predict(0, 0)
    }

    private fun setupPickerValues(numberPicker: NumberPicker) {
        numberPicker.minValue = 0
        numberPicker.maxValue = 1
        numberPicker.descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS
        numberPicker.setOnValueChangedListener(this)
    }

    override fun onValueChange(picker: NumberPicker, oldVal: Int, newVal: Int) {
        val leftPicker = numberPickerLeft.value
        val rightPicker = numberPickerRight.value

        textViewResult.text = predict(leftPicker, rightPicker)
    }

    private fun predict(left: Int, right: Int): String {
        val data = floatArrayOf(left.toFloat(), right.toFloat())
        val result = tfxor.predict(data)
        val answer = Math.round(result[0])
        return left.toString() + " XOR " + right + " = " + answer
    }
}
