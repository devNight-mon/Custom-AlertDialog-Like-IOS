package com.efesen.customalertdialog.activities

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.efesen.customalertdialog.databinding.ActivityMainBinding
import com.efesen.customalertdialog.utils.DialogType
import com.efesen.customalertdialog.utils.createCustomAlertDialog
import com.efesen.customalertdialog.utils.createCustomAlertDialogBuilder

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var inputEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Default Alert Dialog type Warning

        binding.warningButton.setOnClickListener {
            createCustomAlertDialog(
                "Are you sure",
                "Do you want to dismiss",
                "OK",
                "Cancel",
                positiveButtonAction = {
                    // no -Op
                },
                negativeButtonAction = {
                    // no - Op
                }
            ).show()
        }

        //  Error alert dialog has one button, dialog type error
        binding.errorButton.setOnClickListener {
            createCustomAlertDialog(
                "Alert",
                "You have been alerted",
                "OK",
                positiveButtonAction = {
                    // no -Op
                },
                alertType = DialogType.ERROR
            ).show()
        }

        // Input alert dialog dialog type Input

        binding.textInputButton.setOnClickListener {
            val customAlertDialogBuilder = createCustomAlertDialogBuilder(
                "Input Title",
                positiveButtonText = "OK",
                positiveButtonAction = {
                    val inputText = inputEditText.editableText?.toString()
                    println("Input message, $inputText")
                },
                negativeButtonText = "Cancel",
                negativeButtonAction = {
                    // no - Op
                },
                alertType = DialogType.INPUT
            )
            inputEditText = customAlertDialogBuilder.getInputEditText()
            customAlertDialogBuilder.create().show()
        }

    }
}