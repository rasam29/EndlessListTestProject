package com.example.endlessproject.appList.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.endlessproject.appList.AppPlusMetaData
import com.example.endlessproject.databinding.ItemApplicationBinding

class AppListAdapter(diffCallBack: DiffUtil.ItemCallback<AppPlusMetaData>) :
    PagingDataAdapter<AppPlusMetaData, AppListAdapter.AppViewHolder>(diffCallBack) {

    override fun onBindViewHolder(holder: AppViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppViewHolder {
        val itemBinding =
            ItemApplicationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AppViewHolder(itemBinding)
    }

    class AppViewHolder(private val binding: ItemApplicationBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(appPlusMetaData: AppPlusMetaData?) {
            binding.data = appPlusMetaData
        }
    }
}
