package com.example.myjapanesenoteapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myjapanesenoteapplication.databinding.ItemJapaneseCategoryBinding
import com.example.myjapanesenoteapplication.databinding.ItemJapaneseMainBinding

class JapaneseCategoryAdapter : ListAdapter<JapaneseCategoryItem, JapaneseCategoryAdapter.ItemJapaneseCategoryVH>(DIFF_CALLBACK) {

    private var onItemClickListener: OnItemClickListener?= null

    interface OnItemClickListener {
        fun onExampleClick(item: JapaneseCategoryItem, position: Int)
        fun onQuestionClick(item: JapaneseCategoryItem, position: Int)
    }

    fun setItemOnClickListener(listener: OnItemClickListener) {
        onItemClickListener = listener
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ItemJapaneseCategoryVH = LayoutInflater.from(viewGroup.context)
        .let { ItemJapaneseCategoryBinding.inflate(it, viewGroup, false) }
        .let { ItemJapaneseCategoryVH(it) }

    override fun onBindViewHolder(holder: ItemJapaneseCategoryVH, position: Int) {

        val item = getItem(position)

        with(holder.binding) {
            categoryTitle.text = item.제목

            exampleButton.setOnClickListener {
                onItemClickListener?.onExampleClick(item, position)
            }
            questionButton.setOnClickListener {
                onItemClickListener?.onQuestionClick(item, position)
            }
        }
    }

    class ItemJapaneseCategoryVH(val binding: ItemJapaneseCategoryBinding) : RecyclerView.ViewHolder(binding.root)

    companion object {
        private val DIFF_CALLBACK = object: DiffUtil.ItemCallback<JapaneseCategoryItem>() {
            override fun areContentsTheSame(oldItem: JapaneseCategoryItem, newItem: JapaneseCategoryItem): Boolean {
                return oldItem == newItem && oldItem.hashCode() == newItem.hashCode()
            }

            override fun areItemsTheSame(oldItem: JapaneseCategoryItem, newItem: JapaneseCategoryItem): Boolean {
                return oldItem.제목 == newItem.제목
            }
        }
    }
}
