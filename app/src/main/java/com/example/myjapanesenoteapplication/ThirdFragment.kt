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
import androidx.fragment.app.FragmentActivity
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
                (requireContext() as FragmentActivity).supportFragmentManager.showJapaneseExampleDialog(
                    list = japaneseChapter1List,
                    dismissCallback = {

                    }
                )
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
                (requireContext() as FragmentActivity).supportFragmentManager.showJapaneseQuestionDialog(
                    list = japaneseChapter1List,
                    dismissCallback = {

                    }
                )
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

    private var japaneseChapter1List = listOf(
        JapaneseMainItem("처음뵙겠습니다.", "하지메마시떼", "はじめまして。", R.raw.fifty_1),
        JapaneseMainItem("한국 서울에서 왔어요.", "칸코쿠노 소우루카라 키마시타", "韓国のソウルから来ました。", R.raw.fifty_1),
        JapaneseMainItem("제 이름은 OO입니다.", "와타시노 나마에와 00데쓰", "私の名前は○○です。", R.raw.fifty_1),
        JapaneseMainItem("감사합니다. 정말 기쁩니다.", "아리가토오고자이마스. 혼토니 우레시이데스", "ありがとうございます。本当に嬉しいです。", R.raw.fifty_1),
        JapaneseMainItem("정말 큰 도움이 됐어요.", "혼토니 다스카리마시타", "本当に助かりました。", R.raw.fifty_1),
        JapaneseMainItem("몇살이세요?", "오이쿠츠데스까", "おいくつですか？", R.raw.fifty_1),
        JapaneseMainItem("저는 한국인입니다.", "와타시와 칸코쿠진데스", "私は韓国人です。", R.raw.fifty_1),
        JapaneseMainItem("저는 스무살입니다.", "와타시와 하타치데스", "私は２０歳です。", R.raw.fifty_1),
        JapaneseMainItem("일본어는 조금밖에 못합니다.", "니혼고와 스코시 시카 데키마세", "日本語は少ししかできません。", R.raw.fifty_1),
        JapaneseMainItem("당신의 이름은 무엇입니까?", "아나따노 나마에와 난데스까", "あなたの名前はなんですか。", R.raw.fifty_1),
        JapaneseMainItem("저는 대학생입니다.", "와타시와 다이가쿠세에데스", "私は大学生です。", R.raw.fifty_1),
        JapaneseMainItem("앞으로 잘 부탁드립니다.", "코레까라 요로시꾸 오네가이시마쓰", "これからよろしくお願いします。", R.raw.fifty_1),
        JapaneseMainItem("진짜 멋져요.", "톳데모스바라시이", "とってもすばらしい。", R.raw.fifty_1),
        JapaneseMainItem("대단합니다.", "스고이데스", "すごいです。", R.raw.fifty_1),
        JapaneseMainItem("저는 회사원입니다.", "와타시와 카이샤잉데스", "私は会社員です。", R.raw.fifty_1),
        JapaneseMainItem("일본의 애니메이션과 만화를 좋아합니다.", "니혼노 아니메토 만가가 스키데스", "日本のアニメと漫画が好きです。", R.raw.fifty_1),
        JapaneseMainItem("일본어를 잘하게 되면 일본에서 일하고 싶습니다.", "니혼고가 조즈니 낫타라 니혼데 하타라키타이데스", "日本語が上手になったら日本で働きたいです。", R.raw.fifty_1),
        JapaneseMainItem("저의 취미는 영화감상과 음악 감상입니다.", "와타시노 슈미와 에에가토 온가쿠칸쇼오데스", "私の趣味は映画と音楽鑑賞です。", R.raw.fifty_1),
        JapaneseMainItem("전 일본어에 관심이 많습니다.", "와타시와 니혼고니 쿄오미가 아리마스", "私は日本語に興味があります。", R.raw.fifty_1),
        JapaneseMainItem("일본어를 마스터해서 일본인과 잘 대화할 수 있게 되는 것이 저의 바람입니다.",
            "니혼고오 마스타아시테 니혼진토 우마쿠 하나세루 요오니 나루 코토가 와타시노 네가이데스",
            "日本語をマスターして、日本人とうまく話せるようになることが私の願いです。", R.raw.fifty_1)
    )
}