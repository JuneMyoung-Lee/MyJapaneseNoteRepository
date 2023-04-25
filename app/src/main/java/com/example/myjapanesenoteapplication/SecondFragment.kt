package com.example.myjapanesenoteapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
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

        initViewPager()
    }

    private fun initViewPager() {
        with(binding) {
            val subBannerLinearLayoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            val adapter = JapaneseMainAdapter()
            randomViewPager.adapter = adapter
            adapter.setItemOnClickListener(object : JapaneseMainAdapter.OnItemClickListener{
                override fun onItemClick(item: JapaneseMainItem, position: Int) {

                }
            })
            subBannerLinearLayoutManager.isItemPrefetchEnabled = false

            val onPageChangeCallback = object: ViewPager2.OnPageChangeCallback(){
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)


                }
            }
            randomViewPager.unregisterOnPageChangeCallback(onPageChangeCallback)
            randomViewPager.registerOnPageChangeCallback(onPageChangeCallback)
            adapter.submitList(japaneseMainList.shuffled())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private var japaneseMainList = listOf(
        JapaneseMainItem("안녕하세요(아침)", "오하요-고자이마스", "おはようございます。"),
        JapaneseMainItem("안녕하세요(점심)", "코온니치와", "こんにちは。"),
        JapaneseMainItem("안녕하세요(저녁)", "코오바앙와", "こんばんは。"),
        JapaneseMainItem("잘 자", "오야스미", "おやすみ。"),
        JapaneseMainItem("안녕히 주무세요", "오야스미나사이", "おやすみなさい。"),
        JapaneseMainItem("처음뵙겠습니다", "하지메 마시떼", "はじめまして。"),
        JapaneseMainItem("오랜만입니다", "오히사시부리데스", "お久しぶりです。"),
        JapaneseMainItem("부디 잘 부탁드립니다", "도-조, 요로시꾸 오네가이시마스", "どうぞ、よろしくお願いします。"),
        JapaneseMainItem("잘가", "사요-나라", "さようなら。"),
        JapaneseMainItem("그럼 내일 만나요", "데와 마따 아시따", "では、また明日。"),

        JapaneseMainItem("먼저 실례하겠습니다", "오사끼니 시쯔레-시마스", "お先に失礼します。"),
        JapaneseMainItem("잘 지내세요?", "오겡끼데스까?", "お元気ですか。"),
        JapaneseMainItem("어서오세요", "이랏샤이마세", "いらっしゃいませ。"),
        JapaneseMainItem("다녀오겠습니다", "잇떼 키마스", "いってきます。"),
        JapaneseMainItem("다녀오세요", "잇떼 랏샤이", "いってらっしゃい。"),
        JapaneseMainItem("다녀왔습니다", "타다이마", "ただいま。"),
        JapaneseMainItem("다녀오셨어요", "오카에리나사이", "おかえりなさい。"),
        JapaneseMainItem("축하합니다", "오메데또-고자이마스", "おめでとうございます。"),
        JapaneseMainItem("축하해", "오메데또", "おめでとう。"),
        JapaneseMainItem("새해 복 많이 받으세요", "아께마시떼 오메데또-고자이마스", "明けましておめでとうございます。"),

        JapaneseMainItem("수고하셨습니다", "오쯔까레사마데시따", "おつかれさまでした。"),
        JapaneseMainItem("안녕(아침)", "오하요", "おはよう。"),
        JapaneseMainItem("수고하세요", "간밧테 구다사이", "がんばってください。"),
        JapaneseMainItem("안녕히가세요", "오키오츠케테", "お気をつけて。"),
        JapaneseMainItem("별일 없으시죠?", "오카와리 아리마센카?", "お変わりありませんか?"),
        JapaneseMainItem("요즘 뭐하고 지내세요?", "코노 코로 나니오 시테마스카?", "この頃、何をしてますか。"),
        JapaneseMainItem("살펴가세요", "오키오츠케테", "お気をつけて。"),
        JapaneseMainItem("이따 봐요", "마타 아토데 아이마쇼오", "また後で会いましょう。"),
        JapaneseMainItem("또 봅시다", "데와 마타", "では、また。"),
        JapaneseMainItem("잘 지내세요", "오겐키데", "お元気で。"),

        JapaneseMainItem("여행 잘 하세요", "료코오 타노신데쿠다사이", "旅行楽しんでください。"),
        JapaneseMainItem("힘내세요", "겐키 다시테 구다사이", "元気出してください。"),
        JapaneseMainItem("애도를 표합니다", "아이토-노 이오 효-시마스", "哀悼の意を表します。"),
        JapaneseMainItem("잘가", "쟈아네", "じゃあね。"),
        JapaneseMainItem("또 보자", "마타네", "またね。"),
        JapaneseMainItem("내일 또 보자!", "자 마타 아시타!", "じゃ、またあした！"),
        JapaneseMainItem("잘가 (바이바이)", "바이바이", "バイバイ。"),
        JapaneseMainItem("그동안 연락을 못 드렸습니다", "고부사타시테오리마스", "ご無沙汰しております。"),
        JapaneseMainItem("그럼 고생해", "고쿠로사마", "ご苦労様。"),
        JapaneseMainItem("집에서 편히 쉬세요", "이에데윳쿠리야슨데쿠다사이", "家でゆっくり休んでください。"),

        JapaneseMainItem("이번일로 상심이 크시겠습니다", "고노타비와 고슈쇼사마데스", "この度はご愁傷様です。"),
        JapaneseMainItem("실례하겠습니다", "시츠레시마스", "失礼します。"),
        JapaneseMainItem("오래 기다리셨습니다", "오마타세시마시타", "お待たせしました。"),
        JapaneseMainItem("오랜만!", "히사시부리!", "久しぶり!"),
        JapaneseMainItem("오랜만입니다", "오히사시부리데스", "お久しぶりです。"),
        JapaneseMainItem("잘 지내?", "겐키?", "元気？"),
        JapaneseMainItem("요즘 어때?", "사이킨 도오?", "最近どう？"),
        JapaneseMainItem("결혼 축하해", "켓콘 오메데토", "結婚おめでとう"),
        JapaneseMainItem("졸업 축하해", "소츠교 오메데토", "卒業おめでとう"),
        JapaneseMainItem("생일 축하해", "오탄조비 오메데토", "お誕生日おめでとう"),
    )
}