package com.example.nimbletasksdkimplementation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher

import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.doublethisarray.DoubleThisArray

class MainActivity : AppCompatActivity() {

    private lateinit var arr: FloatArray
    private var index = 0
    private var arraySize = 0
    private var arraySizeEditTextInput: EditText? = null
    private var valueInputEditText: EditText? = null
    private var addValueButton: Button? = null
    private var calculateValueButton: Button? = null
    private var arrayStackTextView: TextView? = null
    private var resultArrayStackTextView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        arraySizeEditTextInput = findViewById(R.id.arraySizeEditTextInput)
        valueInputEditText = findViewById(R.id.valueInputEditText)
        addValueButton = findViewById(R.id.addValueButton)
        calculateValueButton = findViewById(R.id.calculateValueButton)
        arrayStackTextView = findViewById(R.id.arrayStackTextView)
        resultArrayStackTextView = findViewById(R.id.resultArrayStackTextView)


        arr = FloatArray(0)

        arraySizeEditTextInput?.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {
                if (s.isNotEmpty()) {
                    arraySize = arraySizeEditTextInput?.text.toString().toInt()
                    arr = FloatArray(arraySize)
                    index = 0
                    printArray()
                    clearResultArray()
                }

            }

            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {


            }
        })

        addValueButton?.setOnClickListener {

            if (index < arraySize) {
                if (valueInputEditText?.text?.trim()?.isNotEmpty() == true) {
                    arr[index] = valueInputEditText?.text.toString().toFloat()
                    index++
                    printArray()
                }

            } else {
                Toast.makeText(this, "array size limit exceeded", Toast.LENGTH_LONG).show()
            }

            valueInputEditText?.text?.clear()
        }

        calculateValueButton?.setOnClickListener {

            val doubleThisArray = DoubleThisArray()
            val answer = doubleThisArray.doubleTheArray(arr)

            resultArrayStackTextView?.text = "Resultant Array: \n${answer.contentToString()}"
        }

        printArray()


    }

    private fun printArray() {
        arrayStackTextView?.text = "Input Array: \n${arr.contentToString()}"
    }

    private fun clearResultArray() {
        resultArrayStackTextView?.text = ""
    }
}