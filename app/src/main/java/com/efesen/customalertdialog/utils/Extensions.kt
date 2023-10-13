package com.efesen.customalertdialog.utils

import android.app.Activity
import androidx.appcompat.app.AlertDialog
import com.efesen.customalertdialog.component.CustomAlertDialog

/**
 * Created by Efe Şen on 12.10.2023.
 */

fun Activity.createCustomAlertDialog(
    title: CharSequence? = null,
    message: CharSequence? = null,
    positiveButtonText: CharSequence? = null,
    negativeButtonText: CharSequence? = null,
    positiveButtonAction: (() -> Unit)? = null,
    negativeButtonAction: (() -> Unit)? = null,
    alertType: DialogType? = DialogType.WARNING,
    isAllCaps: Boolean = false
): AlertDialog = createCustomAlertDialogBuilder(
    title,
    message,
    positiveButtonText,
    negativeButtonText,
    positiveButtonAction,
    negativeButtonAction,
    alertType,
    isAllCaps
).create()

/*
 * Creates custom alert dialog builder
 */
fun Activity.createCustomAlertDialogBuilder(
    title: CharSequence? = null,
    message: CharSequence? = null,
    positiveButtonText: CharSequence? = null,
    negativeButtonText: CharSequence? = null,
    positiveButtonAction: (() -> Unit)? = null,
    negativeButtonAction: (() -> Unit)? = null,
    alertType: DialogType? = DialogType.WARNING,
    isAllCaps: Boolean = false
): CustomAlertDialog {
    return CustomAlertDialog(this).apply {

        setIsAllCaps(isAllCaps)

        title?.let {
            setTitle(title)
        }
        message?.let {
            setMessage(message)
        }

        positiveButtonText?.let {
            setPositiveButton(positiveButtonText) {
                positiveButtonAction?.invoke()
            }
        }

        negativeButtonText?.let {
            setNegativeButton(negativeButtonText) {
                negativeButtonAction?.invoke()
            }
        }
        alertType?.let {
            setAlertType(alertType)
        }
    }
}