package com.example.myjapanesenoteapplication

import android.graphics.Rect
import android.os.Bundle
import android.view.ContextThemeWrapper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.myjapanesenoteapplication.databinding.FragmentSecondBinding
import com.example.myjapanesenoteapplication.databinding.FragmentThirdBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class ThirdFragment : Fragment() {

    private var _binding: FragmentThirdBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var toast : Toast? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentThirdBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListener()
        initRecyclerView()

    }

    private fun initListener() {
        with(binding) {
            backButton.setOnClickListener {
                requireActivity().onBackPressed()
            }
        }
    }

    private fun initRecyclerView() {
        with(binding) {
            val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            val categoryAdapter = JapaneseCategoryAdapter()
            categoryAdapter.setItemOnClickListener(object: JapaneseCategoryAdapter.OnItemClickListener {
                override fun onExampleClick(item: JapaneseCategoryItem, position: Int) {
                    actionCategoryExample(item)
                }

                override fun onQuestionClick(item: JapaneseCategoryItem, position: Int) {
                    actionCategoryQuestion(item)
                }
            })
            categoryRecyclerView.layoutManager = layoutManager
            categoryRecyclerView.itemAnimator = null
            categoryRecyclerView.adapter = categoryAdapter
            (categoryRecyclerView.adapter as JapaneseCategoryAdapter).submitList(japaneseCategoryList)
        }
    }

    private fun actionCategoryExample(item: JapaneseCategoryItem) {
        when(item.제목) {
            "Chapter 1. 자기소개" -> {

            }
            else -> {
                requireActivity().runOnUiThread {
                    toast?.cancel()

                    toast = Toast.makeText(requireContext(), "준비중입니다.", Toast.LENGTH_SHORT)
                    toast?.show()
                }
            }
        }
    }

    private fun actionCategoryQuestion(item: JapaneseCategoryItem) {
        when(item.제목) {
            "Chapter 1. 자기소개" -> {

            }
            else -> {
                requireActivity().runOnUiThread {
                    toast?.cancel()

                    toast = Toast.makeText(requireContext(), "준비중입니다.", Toast.LENGTH_SHORT)
                    toast?.show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private val japaneseCategoryList = listOf(
        JapaneseCategoryItem(제목 = "Chapter 1. 자기소개"),
        JapaneseCategoryItem(제목 = "Chapter 2. 날씨"),
        JapaneseCategoryItem(제목 = "Chapter 3. 식사"),
        JapaneseCategoryItem(제목 = "Chapter 4. 직업"),
        JapaneseCategoryItem(제목 = "Chapter 5. 여행"),
        JapaneseCategoryItem(제목 = "Chapter 6. 호텔"),
        JapaneseCategoryItem(제목 = "Chapter 7. 쇼핑"),
        JapaneseCategoryItem(제목 = "Chapter 8. 교통수단"),
        JapaneseCategoryItem(제목 = "Chapter 9. 취미생활"),
        JapaneseCategoryItem(제목 = "Extra 1. 감사 표현"),
        JapaneseCategoryItem(제목 = "Extra 2. 거절 표현"),
        JapaneseCategoryItem(제목 = "Extra 3. 사과 및 부탁 표현"),
    )
}