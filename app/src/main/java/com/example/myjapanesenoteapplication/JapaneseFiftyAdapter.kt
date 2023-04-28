package com.example.myjapanesenoteapplication

import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myjapanesenoteapplication.databinding.ItemJapaneseMainBinding

class JapaneseFiftyAdapter : ListAdapter<JapaneseMainItem, JapaneseFiftyAdapter.ItemJapaneseFiftyVH>(DIFF_CALLBACK) {

    private var mediaPlayer: MediaPlayer ?= null

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ItemJapaneseFiftyVH = LayoutInflater.from(viewGroup.context)
        .let { ItemJapaneseMainBinding.inflate(it, viewGroup, false) }
        .let { ItemJapaneseFiftyVH(it) }

    override fun onBindViewHolder(holder: ItemJapaneseFiftyVH, position: Int) {

        val item = getItem(position)

        with(holder.binding) {
            questionText.text = item.문제
            countText.text = "${position+1}/${currentList.size}"
            dictionText.text = item.발음
            japaneseText.text = item.일본어

            answerButton.setOnClickListener {
                if(dictionText.visibility == View.VISIBLE) {
                    answerButton.text = "정답보기"
                } else {
                    answerButton.text = "정답숨기기"
                }
                setVisibility(dictionText)
                setVisibility(japaneseText)
                setVisibility(soundButton)
            }

            soundButton.setOnClickListener {
                soundButton.setImageResource(R.drawable.sound_white)
                mediaPlayer?.stop()
                mediaPlayer = MediaPlayer.create(root.context, item.음성파일)
                mediaPlayer?.setOnCompletionListener {
                    soundButton.setImageResource(R.drawable.sound_black)
                }
                mediaPlayer?.start()
            }
        }
    }

    fun stopSound() {
        mediaPlayer?.release()
        mediaPlayer = null
    }

    private fun setVisibility(view: View) {
        if(view.visibility == View.VISIBLE) {
            view.visibility = View.INVISIBLE
        } else {
            view.visibility = View.VISIBLE
        }
    }

    class ItemJapaneseFiftyVH(val binding: ItemJapaneseMainBinding) : RecyclerView.ViewHolder(binding.root)

    companion object {
        private val DIFF_CALLBACK = object: DiffUtil.ItemCallback<JapaneseMainItem>() {
            override fun areContentsTheSame(oldItem: JapaneseMainItem, newItem: JapaneseMainItem): Boolean {
                return oldItem == newItem && oldItem.hashCode() == newItem.hashCode()
            }

            override fun areItemsTheSame(oldItem: JapaneseMainItem, newItem: JapaneseMainItem): Boolean {
                return oldItem.발음 == newItem.발음
            }
        }
    }
}
