package com.example.converter

import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.view.View
import android.view.View.OnTouchListener
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.converter.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var canAddOperator = false
    var CalcuisOrFerenheit = false
    var canAddDecimal = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var text1 = findViewById<TextView>(R.id.workingTV) as TextView
        var result1 = findViewById<TextView>(R.id.resultTV) as TextView
        text1.addTextChangedListener(textWatcher)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            text1.showSoftInputOnFocus.not()
        }

        text1.setOnTouchListener(OnTouchListener { v, event ->
            val inType: Int = text1.getInputType() // backup the input type
            text1.setInputType(InputType.TYPE_NULL) // disable soft input
            text1.onTouchEvent(event) // call native handler
            text1.setInputType(inType) // restore input type
            true // consume touch even
        })
    }

    fun toastMsg(msg: String?) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    // textWatcher is for watching any changes in editText
    var textWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            // this function is called before text is edited
        }

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            // this function is called when text is edited

            var num1 = binding.workingTV.text.toString().toFloat()

            if (binding.unitSymbol1.text.toString() == "C") {
                var result = ((num1 * 9 / 5) + 32)
                binding.resultTV.text = result.toString()
            } else {
                var result = ((num1 - 32 ) * 5 / 9)
                binding.resultTV.text = result.toString()
            }
        }

        override fun afterTextChanged(s: Editable) {
            // this function is called after text is edited
        }
    }

    fun backSpaceAction(view: View){
        try {
            val length = binding.workingTV.text.length
            if (length > 0){
                binding.workingTV.text = binding.workingTV.text.subSequence(0, length - 1)
            }
        } catch (e: Exception) {
            Toast.makeText(this@MainActivity, "Some Error Occured", Toast.LENGTH_SHORT).show()
        }
    }
    fun clearAllAction(view: View){
        binding.workingTV.text = "0"
        binding.resultTV.text = "0"
    }
    fun numberAction(view: View)
    {
        var start = binding.workingTV.selectionStart;

        if(view is Button)
        {
            if (view.text == ".")
            {

                    binding.workingTV.append(view.text)
            }
            else
                binding.workingTV.append(view.text)

            canAddOperator = true
        }
    }
    fun swap(view: View){
        try {
            binding.workingTV.setText("0")
            binding.resultTV.text = "0"

            if (binding.unitSymbol1.text.toString() == "C") {
                binding.unitSymbol1.text = "F"
                binding.unitSymbol2.text = "C"
            } else {
                binding.unitSymbol1.text = "C"
                binding.unitSymbol2.text = "F"
            }
        } catch (e: Exception){
            Toast.makeText(this@MainActivity,e.message, Toast.LENGTH_SHORT).show()
        }
    }

    fun equalAction(view: View){

    }

}