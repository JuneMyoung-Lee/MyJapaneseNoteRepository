package com.example.myjapanesenoteapplication

import android.graphics.Rect
import android.os.Bundle
import android.view.ContextThemeWrapper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.myjapanesenoteapplication.databinding.FragmentSecondBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().window.statusBarColor = ContextCompat.getColor(requireContext(), R.color.bg_03)
        initViewPager()

    }

    private fun initViewPager() {
        with(binding) {
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

            adapter.submitList(japaneseMainList.shuffled())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onStop() {
        (binding.randomViewPager.adapter as JapaneseFiftyAdapter).stopSound()
        super.onStop()
    }

    private var japaneseMainList = listOf(
        JapaneseMainItem("안녕하세요(아침)", "오하요-고자이마스", "おはようございます。", R.raw.fifty_1),
        JapaneseMainItem("안녕하세요(점심)", "코온니치와", "こんにちは。", R.raw.fifty_2),
        JapaneseMainItem("안녕하세요(저녁)", "코오바앙와", "こんばんは。", R.raw.fifty_3),
        JapaneseMainItem("잘 자", "오야스미", "おやすみ。", R.raw.fifty_4),
        JapaneseMainItem("안녕히 주무세요", "오야스미나사이", "おやすみなさい。", R.raw.fifty_5),
        JapaneseMainItem("처음뵙겠습니다", "하지메 마시떼", "はじめまして。", R.raw.fifty_6),
        JapaneseMainItem("오랜만입니다", "오히사시부리데스", "お久しぶりです。", R.raw.fifty_7),
        JapaneseMainItem("부디 잘 부탁드립니다", "도-조, 요로시꾸 오네가이시마스", "どうぞ、よろしくお願いします。", R.raw.fifty_8),
        JapaneseMainItem("잘가", "사요-나라", "さようなら。", R.raw.fifty_9),
        JapaneseMainItem("그럼 내일 만나요", "데와 마따 아시따", "では、また明日。", R.raw.fifty_10),

        JapaneseMainItem("먼저 실례하겠습니다", "오사끼니 시쯔레-시마스", "お先に失礼します。", R.raw.fifty_11),
        JapaneseMainItem("잘 지내세요?", "오겡끼데스까?", "お元気ですか。", R.raw.fifty_12),
        JapaneseMainItem("어서오세요", "이랏샤이마세", "いらっしゃいませ。", R.raw.fifty_13),
        JapaneseMainItem("다녀오겠습니다", "잇떼 키마스", "いってきます。", R.raw.fifty_14),
        JapaneseMainItem("다녀오세요", "잇떼 랏샤이", "いってらっしゃい。", R.raw.fifty_15),
        JapaneseMainItem("다녀왔습니다", "타다이마", "ただいま。", R.raw.fifty_16),
        JapaneseMainItem("다녀오셨어요", "오카에리나사이", "おかえりなさい。", R.raw.fifty_17),
        JapaneseMainItem("축하합니다", "오메데또-고자이마스", "おめでとうございます。", R.raw.fifty_18),
        JapaneseMainItem("축하해", "오메데또", "おめでとう。", R.raw.fifty_19),
        JapaneseMainItem("새해 복 많이 받으세요", "아께마시떼 오메데또-고자이마스", "明けましておめでとうございます。", R.raw.fifty_20),

        JapaneseMainItem("수고하셨습니다", "오쯔까레사마데시따", "おつかれさまでした。", R.raw.fifty_21),
        JapaneseMainItem("안녕(아침)", "오하요", "おはよう。", R.raw.fifty_22),
        JapaneseMainItem("수고하세요", "간밧테 구다사이", "がんばってください。", R.raw.fifty_23),
        JapaneseMainItem("안녕히가세요", "오키오츠케테", "お気をつけて。", R.raw.fifty_24),
        JapaneseMainItem("별일 없으시죠?", "오카와리 아리마센카?", "お変わりありませんか?", R.raw.fifty_25),
        JapaneseMainItem("요즘 뭐하고 지내세요?", "코노 코로 나니오 시테마스카?", "この頃、何をしてますか。", R.raw.fifty_26),
        JapaneseMainItem("살펴가세요", "오키오츠케테", "お気をつけて。", R.raw.fifty_24),
        JapaneseMainItem("이따 봐요", "마타 아토데 아이마쇼오", "また後で会いましょう。", R.raw.fifty_28),
        JapaneseMainItem("또 봅시다", "데와 마타", "では、また。", R.raw.fifty_29),
        JapaneseMainItem("잘 지내세요", "오겐키데", "お元気で。", R.raw.fifty_30),

        JapaneseMainItem("여행 잘 하세요", "료코오 타노신데쿠다사이", "旅行楽しんでください。", R.raw.fifty_31),
        JapaneseMainItem("힘내세요", "겐키 다시테 구다사이", "元気出してください。", R.raw.fifty_32),
        JapaneseMainItem("애도를 표합니다", "아이토-노 이오 효-시마스", "哀悼の意を表します。", R.raw.fifty_33),
        JapaneseMainItem("잘가", "쟈아네", "じゃあね。", R.raw.fifty_34),
        JapaneseMainItem("또 보자", "마타네", "またね。", R.raw.fifty_35),
        JapaneseMainItem("내일 또 보자!", "자 마타 아시타!", "じゃ、またあした！", R.raw.fifty_36),
        JapaneseMainItem("잘가 (바이바이)", "바이바이", "バイバイ。", R.raw.fifty_37),
        JapaneseMainItem("그동안 연락을 못 드렸습니다", "고부사타시테오리마스", "ご無沙汰しております。", R.raw.fifty_38),
        JapaneseMainItem("그럼 고생해", "고쿠로사마", "ご苦労様。", R.raw.fifty_39),
        JapaneseMainItem("집에서 편히 쉬세요", "이에데윳쿠리야슨데쿠다사이", "家でゆっくり休んでください。", R.raw.fifty_40),

        JapaneseMainItem("이번일로 상심이 크시겠습니다", "고노타비와 고슈쇼사마데스", "この度はご愁傷様です。", R.raw.fifty_41),
        JapaneseMainItem("실례하겠습니다", "시츠레시마스", "失礼します。", R.raw.fifty_42),
        JapaneseMainItem("오래 기다리셨습니다", "오마타세시마시타", "お待たせしました。", R.raw.fifty_43),
        JapaneseMainItem("오랜만!", "히사시부리!", "久しぶり!", R.raw.fifty_44),
        JapaneseMainItem("오랜만입니다", "오히사시부리데스", "お久しぶりです。", R.raw.fifty_45),
        JapaneseMainItem("잘 지내?", "겐키?", "元気？", R.raw.fifty_46),
        JapaneseMainItem("요즘 어때?", "사이킨 도오?", "最近どう？", R.raw.fifty_47),
        JapaneseMainItem("결혼 축하해", "켓콘 오메데토", "結婚おめでとう", R.raw.fifty_48),
        JapaneseMainItem("졸업 축하해", "소츠교 오메데토", "卒業おめでとう", R.raw.fifty_49),
        JapaneseMainItem("생일 축하해", "오탄조비 오메데토", "お誕生日おめでとう", R.raw.fifty_50),
    )
}