package com.efesen.customalertdialog.component

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import androidx.cardview.widget.CardView
import com.efesen.customalertdialog.R
import com.efesen.customalertdialog.utils.DialogType

/**
 * Created by Efe Şen on 12.10.2023.
 */
class CustomAlertDialog(context:Context): AlertDialog.Builder(context) {
    private var titleText: TextView
    private var messageText: TextView
    private var inputEditText: EditText
    private var positiveButton: AppCompatButton
    private var negativeButton: AppCompatButton
    private var alertDialogButtonContainer: LinearLayout
    private var verticalLine: CardView
    private var mPositiveClickListener: (() -> Unit)? = null
    private var mNegativeClickListener: (() -> Unit)? = null
    private var dialogType: DialogType = DialogType.WARNING

    init {
        val dialogView = LayoutInflater.from(context).inflate(R.layout.ios_style_alert_dialog,null)
        setView(dialogView)

        titleText = dialogView.findViewById(R.id.textViewTitle) as TextView
        messageText = dialogView.findViewById(R.id.textViewMessage) as TextView
        inputEditText = dialogView.findViewById(R.id.editTextMessage) as EditText
        positiveButton = dialogView.findViewById(R.id.positiveButton) as AppCompatButton
        negativeButton = dialogView.findViewById(R.id.negativeButton) as AppCompatButton
        alertDialogButtonContainer = dialogView.findViewById(R.id.alertDialogButtonContainer) as LinearLayout
        verticalLine = dialogView.findViewById(R.id.vertical_view)
    }

    override fun setTitle(title: CharSequence?): AlertDialog.Builder {
        titleText.text = title?.toString()
        return this
    }

    override fun setMessage(message: CharSequence?): AlertDialog.Builder {
        if (titleText.text.isNullOrBlank()) {
            titleText.visibility = View.GONE
        }
        messageText.text = message?.toString()
        return super.setMessage(message)
    }

    fun setPositiveButton(text: CharSequence?, positiveButtonAction: (() -> Unit)? = null): (() -> Unit)? {
     positiveButton.apply {
         this.text = text?.toString()
         visibility = View.VISIBLE
     }
        mPositiveClickListener = positiveButtonAction
        return mPositiveClickListener
    }

    fun setNegativeButton(text: CharSequence?, negativeButtonAction: (() -> Unit)? = null): (() -> Unit)? {
        negativeButton.apply {
            this.text = text?.toString()
            visibility = View.VISIBLE
        }
        mNegativeClickListener = negativeButtonAction
        return mNegativeClickListener
    }

    fun setAlertType(alertDialogType: DialogType) {
        dialogType = alertDialogType
        when(alertDialogType) {
            DialogType.WARNING -> {
                alertDialogButtonContainer.visibility = View.VISIBLE
                positiveButton.visibility = View.VISIBLE
                negativeButton.visibility = View.VISIBLE
                verticalLine.visibility = View.VISIBLE
            }
            DialogType.ERROR -> {
                verticalLine.visibility = View.VISIBLE
                alertDialogButtonContainer.visibility = View.VISIBLE
                positiveButton.visibility = View.VISIBLE
                negativeButton.visibility = View.GONE
            }
            DialogType.INPUT -> {
                alertDialogButtonContainer.visibility = View.VISIBLE
                positiveButton.visibility = View.VISIBLE
                negativeButton.visibility = View.VISIBLE
                verticalLine.visibility = View.VISIBLE
                inputEditText.visibility = View.VISIBLE
                messageText.visibility =  View.GONE
            }
        }
    }

    fun setIsAllCaps(isAllCapsArg: Boolean) {
        positiveButton.let {
            positiveButton.isAllCaps = isAllCapsArg
        }
        negativeButton.let {
            negativeButton.isAllCaps = isAllCapsArg
        }
    }

    fun getInputEditText() = inputEditText

    override fun create(): AlertDialog {
        val alertDialog = super.create()

        alertDialog.window!!.setBackgroundDrawableResource(R.color.transparent)
        if (dialogType == DialogType.WARNING) {
            alertDialog.setCanceledOnTouchOutside(true)
        }

        positiveButton.setOnClickListener {
            alertDialog.dismiss()
            mPositiveClickListener?.invoke()
        }

        negativeButton.setOnClickListener {
            alertDialog.dismiss()
            mNegativeClickListener?.invoke()
        }
        return alertDialog
    }
}