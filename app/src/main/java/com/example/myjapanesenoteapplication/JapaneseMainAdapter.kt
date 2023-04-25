package com.example.myjapanesenoteapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class JapaneseMainAdapter : ListAdapter<JapaneseMainItem, JapaneseMainAdapter.JapaneseMainViewHolder>(DIFF_CALLBACK) {

    private var onItemClickListener: OnItemClickListener?= null

    interface OnItemClickListener {
        fun onItemClick(item: JapaneseMainItem, position: Int)
    }

    fun setItemOnClickListener(listener: OnItemClickListener) {
        onItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JapaneseMainViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_japanese_main, parent, false)

        return JapaneseMainViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: JapaneseMainViewHolder,
        position: Int
    ) {
        if(currentList.size > 0)
            holder.onBind(getItem(position % currentList.size), position, currentList.size, onItemClickListener!!)
    }

    class JapaneseMainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val questionText: TextView = itemView.findViewById(R.id.questionText)
        private val countText: TextView = itemView.findViewById(R.id.countText)
        private val dictionText: TextView = itemView.findViewById(R.id.dictionText)
        private val japaneseText: TextView = itemView.findViewById(R.id.japaneseText)
        private val leftSwipeText: TextView = itemView.findViewById(R.id.leftSwipeText)
        private val rightSwipeText: TextView = itemView.findViewById(R.id.rightSwipeText)
        private val answerButton: Button = itemView.findViewById(R.id.answerButton)

        fun onBind(item: JapaneseMainItem,
                   position: Int,
                   size: Int,
                   listener: OnItemClickListener
        ) {
            questionText.text = item.문제
            countText.text = "${position+1}/50"
            dictionText.text = item.발음
            japaneseText.text = item.일본어
            if(position == 0) {
                leftSwipeText.visibility = View.GONE
            }
            if(position == size - 1) {
                rightSwipeText.visibility = View.GONE
            }

            answerButton.setOnClickListener {
                if(dictionText.visibility == View.VISIBLE) {
                    dictionText.visibility = View.INVISIBLE
                    answerButton.text = "정답보기"
                } else {
                    dictionText.visibility = View.VISIBLE
                    answerButton.text = "정답숨기기"
                }

                if(japaneseText.visibility == View.VISIBLE) {
                    japaneseText.visibility = View.INVISIBLE
                } else {
                    japaneseText.visibility = View.VISIBLE
                }
            }
        }
    }

    override fun getCurrentList(): MutableList<JapaneseMainItem> {
        return super.getCurrentList()
    }

    override fun getItemCount(): Int {
        return if(currentList.size == 1) 1 else Int.MAX_VALUE
    }

    companion object {
        private val DIFF_CALLBACK = object: DiffUtil.ItemCallback<JapaneseMainItem>() {
            override fun areContentsTheSame(oldItem: JapaneseMainItem, newItem: JapaneseMainItem): Boolean {
                return oldItem == newItem && oldItem.hashCode() == newItem.hashCode()
            }

            override fun areItemsTheSame(oldItem: JapaneseMainItem, newItem: JapaneseMainItem): Boolean {
                return oldItem.문제 == newItem.문제
            }
        }
    }

}
