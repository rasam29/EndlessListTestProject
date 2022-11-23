package com.example.endlessproject.appList.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.endlessproject.R
import com.example.endlessproject.appList.UiModel
import com.example.endlessproject.databinding.ItemApplicationBinding
import com.example.endlessproject.databinding.ItemProgressBinding
import com.example.endlessproject.databinding.ItemSeperatorBinding

class AppListAdapter(diffCallBack: DiffUtil.ItemCallback<UiModel>) :
    PagingDataAdapter<UiModel, ViewHolder>(diffCallBack) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when(getItem(position)){
            is UiModel.AppPlusMetaData->{
                (holder as AppViewHolder).bind(getItem(position) as UiModel.AppPlusMetaData)
            }
            else ->{}
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            R.layout.item_application -> {
                val itemBinding =
                    ItemApplicationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                 AppViewHolder(itemBinding)
            }
            R.layout.item_seperator -> {
                SeparatorViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_seperator,parent,false))
            }
            else -> throw IllegalArgumentException()
        }

    }

    class AppViewHolder(private val binding: ItemApplicationBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(appPlusMetaData: UiModel.AppPlusMetaData?) {
            binding.data = appPlusMetaData
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when(peek(position)){
            is UiModel.AppPlusMetaData->R.layout.item_application
            is UiModel.Separator ->R.layout.item_seperator
            else-> throw RuntimeException()
        }
    }
    class SeparatorViewHolder(itemView: View):ViewHolder(itemView)
}

class ProgressAdapter : LoadStateAdapter<ProgressAdapter.LoadStateViewHolder>() {


    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_progress, parent, false)
        return LoadStateViewHolder(ItemProgressBinding.bind(view).root)
    }

    class LoadStateViewHolder(itemView: View) : ViewHolder(itemView) {
        init {

        }
        fun bind(state:LoadState) {
        }
    }
}
