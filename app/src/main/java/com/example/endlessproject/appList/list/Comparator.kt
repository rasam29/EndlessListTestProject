package com.example.endlessproject.appList.list

import androidx.recyclerview.widget.DiffUtil
import com.example.endlessproject.appList.AppPlusMetaData

object Comparator : DiffUtil.ItemCallback<AppPlusMetaData>() {
    override fun areItemsTheSame(oldItem: AppPlusMetaData, newItem: AppPlusMetaData): Boolean {
        return oldItem.packageName == newItem.packageName
    }

    override fun areContentsTheSame(oldItem: AppPlusMetaData, newItem: AppPlusMetaData): Boolean {
        return oldItem == newItem
    }
}