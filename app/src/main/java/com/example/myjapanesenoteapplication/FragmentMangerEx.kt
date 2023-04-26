package com.example.myjapanesenoteapplication

import androidx.fragment.app.FragmentManager

fun FragmentManager.showJapaneseExampleDialog(
    list: List<JapaneseMainItem>,
    dismissCallback: () -> Unit
) {
    val transaction = beginTransaction()
    val prev = findFragmentByTag("dialog")
    if (prev != null) {
        transaction.remove(prev)
    }
    transaction.addToBackStack(null)

    JapaneseExampleDialogFragment().show(transaction, list, dismissCallback)
}

fun FragmentManager.showJapaneseQuestionDialog(
    list: List<JapaneseMainItem>,
    dismissCallback: () -> Unit
) {
    val transaction = beginTransaction()
    val prev = findFragmentByTag("dialog")
    if (prev != null) {
        transaction.remove(prev)
    }
    transaction.addToBackStack(null)

    JapaneseQuestionDialogFragment().show(transaction, list, dismissCallback)
}