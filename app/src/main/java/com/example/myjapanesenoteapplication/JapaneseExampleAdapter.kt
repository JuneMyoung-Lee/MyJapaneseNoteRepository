package com.example.myjapanesenoteapplication

import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myjapanesenoteapplication.databinding.ItemJapaneseCategoryBinding
import com.example.myjapanesenoteapplication.databinding.ItemJapaneseExampleBinding
import com.example.myjapanesenoteapplication.databinding.ItemJapaneseMainBinding

class JapaneseExampleAdapter : ListAdapter<JapaneseMainItem, JapaneseExampleAdapter.ItemJapaneseExampleVH>(DIFF_CALLBACK) {

    private var mediaPlayer: MediaPlayer?= null

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ItemJapaneseExampleVH = LayoutInflater.from(viewGroup.context)
        .let { ItemJapaneseExampleBinding.inflate(it, viewGroup, false) }
        .let { ItemJapaneseExampleVH(it) }

    override fun onBindViewHolder(holder: ItemJapaneseExampleVH, position: Int) {

        val item = getItem(position)

        with(holder.binding) {
            koreanTitle.text = item.문제
            dictionTitle.text = item.발음
            japaneseTitle.text = item.일본어

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

    class ItemJapaneseExampleVH(val binding: ItemJapaneseExampleBinding) : RecyclerView.ViewHolder(binding.root)

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
