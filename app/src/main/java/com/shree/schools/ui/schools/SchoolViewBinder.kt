/*
 * Copyright 2020 Shreedhar Acharya
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.shree.schools.ui.schools

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.shree.schools.BR
import com.shree.schools.R
import com.shree.schools.data.SchoolData
import com.shree.schools.databinding.ItemFeedSchoolsEmptyBinding
import com.shree.schools.databinding.SchoolItemBinding

// For school items
class SchoolViewBinder :
    FeedItemViewBinder<SchoolData, SchoolsViewHolder>(SchoolData::class.java) {

    override fun createViewHolder(parent: ViewGroup): SchoolsViewHolder {
        return SchoolsViewHolder(
            SchoolItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun bindViewHolder(model: SchoolData, viewHolder: SchoolsViewHolder) {
        viewHolder.bind(schoolData = model)
    }

    override fun getFeedItemType(): Int = R.layout.school_item

    override fun areItemsTheSame(oldItem: SchoolData, newItem: SchoolData): Boolean =
        oldItem.schoolId == newItem.schoolId


    override fun areContentsTheSame(oldItem: SchoolData, newItem: SchoolData): Boolean =
        oldItem == newItem
}

class SchoolsViewHolder(
    private val binding: ViewDataBinding
) : ViewHolder(binding.root) {

    fun bind(schoolData: SchoolData) {
        binding.setVariable(BR.school, schoolData)
        binding.executePendingBindings()
    }
}

//shown while loading Schools
object LoadingIndicator

class LoadingViewHolder(itemView: View) : ViewHolder(itemView)

class SchoolsLoadingViewBinder :
    FeedItemViewBinder<LoadingIndicator, LoadingViewHolder>(LoadingIndicator::class.java) {
    override fun createViewHolder(parent: ViewGroup): LoadingViewHolder {
        return LoadingViewHolder(
            LayoutInflater.from(parent.context).inflate(getFeedItemType(), parent, false)
        )
    }

    override fun bindViewHolder(model: LoadingIndicator, viewHolder: LoadingViewHolder) {}

    override fun getFeedItemType(): Int = R.layout.item_feed_school_loading

    override fun areItemsTheSame(oldItem: LoadingIndicator, newItem: LoadingIndicator): Boolean =
        true

    override fun areContentsTheSame(oldItem: LoadingIndicator, newItem: LoadingIndicator): Boolean =
        true
}

// Shown if there are no Schools or fetching Schools fails
data class SchoolsEmpty(val message: String)

class EmptyViewHolder(
    private val binding: ViewDataBinding
) : ViewHolder(binding.root) {


    fun bind(errorMsg: String, retryCallBack: () -> Unit) {
        binding.setVariable(BR.message, errorMsg)
        (binding as ItemFeedSchoolsEmptyBinding).retryButton.setOnClickListener {
            retryCallBack()
        }

        binding.executePendingBindings()
    }
}


class SchoolsEmptyViewBinder(private val retryCallBack: () -> Unit) :
    FeedItemViewBinder<SchoolsEmpty, EmptyViewHolder>(
        SchoolsEmpty::class.java
    ) {

    override fun createViewHolder(parent: ViewGroup): EmptyViewHolder {
        return EmptyViewHolder(
            ItemFeedSchoolsEmptyBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun bindViewHolder(model: SchoolsEmpty, viewHolder: EmptyViewHolder) {
        viewHolder.bind(model.message, retryCallBack)
    }

    override fun getFeedItemType() = R.layout.item_feed_schools_empty

    override fun areItemsTheSame(oldItem: SchoolsEmpty, newItem: SchoolsEmpty) = true

    override fun areContentsTheSame(oldItem: SchoolsEmpty, newItem: SchoolsEmpty) = true
}