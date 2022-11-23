package com.example.endlessproject.appList.list

import androidx.recyclerview.widget.DiffUtil
import com.example.endlessproject.appList.UiModel
import com.example.endlessproject.appList.UiModel.AppPlusMetaData

object Comparator : DiffUtil.ItemCallback<UiModel>() {
    override fun areItemsTheSame(oldItem: UiModel, newItem: UiModel): Boolean {
        return false
    }

    override fun areContentsTheSame(oldItem: UiModel, newItem: UiModel): Boolean {
        return oldItem == newItem
    }
}
