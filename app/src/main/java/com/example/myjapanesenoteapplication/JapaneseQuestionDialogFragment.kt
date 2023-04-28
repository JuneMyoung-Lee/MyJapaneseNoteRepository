package com.example.myjapanesenoteapplication

import android.app.Dialog
import android.content.DialogInterface
import android.content.res.Configuration
import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myjapanesenoteapplication.databinding.DialogJapaneseExampleBinding
import com.example.myjapanesenoteapplication.databinding.DialogJapaneseQuestionBinding


class JapaneseQuestionDialogFragment : DialogFragment() {
    private lateinit var questionList: List<JapaneseMainItem>
    private lateinit var dismissCallback: () -> Unit

    private var _binding : DialogJapaneseQuestionBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dialog?.window?.statusBarColor = ContextCompat.getColor(requireContext(), R.color.bg_03)
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
        _binding = DialogJapaneseQuestionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()

        dialog?.setCanceledOnTouchOutside(true)
    }

    private fun init() {
        with(binding){
            val linearLayoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            val adapter = JapaneseFiftyAdapter()
            linearLayoutManager.isItemPrefetchEnabled = false
            randomViewPager.adapter = adapter
            randomViewPager.offscreenPageLimit = 25
            val currentVisibleItemPx = 40.px
            randomViewPager.addItemDecoration(object: RecyclerView.ItemDecoration() {
                override fun getItemOffsets(
                    outRect: Rect,
                    view: View,
                    parent: RecyclerView,
                    state: RecyclerView.State
                ) {
                    outRect.right = currentVisibleItemPx
                    outRect.left = currentVisibleItemPx
                }
            })
            val nextVisibleItemPx = 20.px
            val pageTranslationX = nextVisibleItemPx + currentVisibleItemPx
            randomViewPager.setPageTransformer { page, position ->
                page.translationX = -pageTranslationX * (position)
            }

            adapter.submitList(questionList.shuffled())
        }
    }

    fun show(
        fragmentTransaction: FragmentTransaction,
        list: List<JapaneseMainItem>,
        dismissCallback: () -> Unit
    ) {
        this.questionList = list
        this.dismissCallback = dismissCallback
        show(fragmentTransaction, dialogTag)
    }

    fun close() {
        this.dismiss()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = object : Dialog(requireActivity(), theme) {
            override fun setOnDismissListener(listener: DialogInterface.OnDismissListener?) {
                if(this@JapaneseQuestionDialogFragment::dismissCallback.isInitialized) {
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

    override fun onStart() {
        super.onStart()

        // dialog 딤처리 제거
        val window = dialog?.window
        val windowParams = window!!.attributes
        windowParams.dimAmount = 0.0f
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        window.attributes = windowParams
    }

    override fun onResume() {
        super.onResume()
        initLayoutMaxWidth()
    }

    companion object {
        const val dialogTag = "JapaneseQuestionDialog"
    }

    override fun onStop() {
        (binding.randomViewPager.adapter as JapaneseFiftyAdapter).stopSound()
        super.onStop()
    }
}
