package com.example.myjapanesenoteapplication

import android.app.Dialog
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.*
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myjapanesenoteapplication.databinding.DialogJapaneseExampleBinding


class JapaneseExampleDialogFragment : DialogFragment() {
    private lateinit var exampleList: List<JapaneseMainItem>
    private lateinit var dismissCallback: () -> Unit

    private var _binding : DialogJapaneseExampleBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setStyle(STYLE_NO_FRAME, R.style.DialogFragmentTheme)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DialogJapaneseExampleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()

        dialog?.setCanceledOnTouchOutside(true)
    }

    private fun init() {
        with(binding){
            val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            val categoryAdapter = JapaneseExampleAdapter()
            exampleRecyclerView.layoutManager = layoutManager
            exampleRecyclerView.itemAnimator = null
            exampleRecyclerView.adapter = categoryAdapter
            (exampleRecyclerView.adapter as JapaneseExampleAdapter).submitList(exampleList)
        }
    }

    fun show(
        fragmentTransaction: FragmentTransaction,
        list: List<JapaneseMainItem>,
        dismissCallback: () -> Unit
    ) {
        this.exampleList = list
        this.dismissCallback = dismissCallback
        show(fragmentTransaction, dialogTag)
    }

    fun close() {
        this.dismiss()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = object : Dialog(requireActivity(), theme) {
            override fun setOnDismissListener(listener: DialogInterface.OnDismissListener?) {
                if(this@JapaneseExampleDialogFragment::dismissCallback.isInitialized) {
                    dismissCallback()
                }
            }
        }
        dialog.setOnKeyListener { dialog, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                this.close()
            }
            true
        }
        dialog.window?.requestFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL, WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL)
        dialog.window?.setFlags(WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH, WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH)
        return dialog
    }

    override fun onStart() {
        super.onStart()

        // dialog 딤처리 제거
        val window = dialog?.window
        val windowParams = window!!.attributes
        windowParams.dimAmount = 0.0f
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        window.attributes = windowParams
    }

    private fun initLayoutMaxWidth() {
        dialog?.let {
            it.window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        }
        setLayoutMaxWidth()
    }

    private fun setLayoutMaxWidth() {
        val displayMetrics = DisplayMetrics()
        requireActivity().windowManager.defaultDisplay.getMetrics(displayMetrics)
        val widthPixel = displayMetrics.widthPixels

        dialog?.let { dialog ->
            dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        }
    }

    override fun onResume() {
        super.onResume()
        initLayoutMaxWidth()
    }

    companion object {
        const val dialogTag = "JapaneseExampleDialog"
    }
}
