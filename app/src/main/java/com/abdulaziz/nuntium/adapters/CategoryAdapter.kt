package com.abdulaziz.nuntium.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abdulaziz.nuntium.databinding.ItemTopicBinding
import com.abdulaziz.nuntium.utils.Topics.getTopicsList
import com.abdulaziz.nuntium.utils.Topics.getTopicsListText

class CategoryAdapter : RecyclerView.Adapter<CategoryAdapter.TFVH>() {

    private var selectedList = arrayListOf<String>()
    private var topics = arrayListOf<String>()
    private var list = arrayListOf<String>()
    private var selectedTopics: String = ""


    inner class TFVH(private val itemBinding: ItemTopicBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun onBind(topic: String, position: Int) {
            itemBinding.apply {

                if (selectedTopics.contains(topics[position].lowercase())) {
                    checkbox.isChecked = true
                }

                checkbox.text = topic

                checkbox.setOnCheckedChangeListener { p0, p1 ->
                    if (p1) {
                        selectedList.add(topics[position].lowercase())
                    } else selectedList.remove(topics[position].lowercase())
                }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TFVH {
        return TFVH(ItemTopicBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: TFVH, position: Int) {
        holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int = list.size

    fun setSelectedTopics(mlist: ArrayList<String>) {

        selectedList = mlist
        topics = getTopicsListText()
        list = getTopicsList()

        val stringBuilder = StringBuilder()
        mlist.forEach {
            stringBuilder.append(it)
        }
        selectedTopics = stringBuilder.toString()
        notifyDataSetChanged()
    }

    fun getSelectedTopics(): List<String> {
        return selectedList
    }

}