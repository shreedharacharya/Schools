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
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.shree.schools.R
import com.shree.schools.data.SchoolData
import com.shree.schools.databinding.SchoolItemBinding

class SchoolsAdapter : ListAdapter<SchoolData, SchoolsViewHolder>(SchoolDataDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SchoolsViewHolder {
        return SchoolsViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.school_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SchoolsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class SchoolsViewHolder(
    private val binding: SchoolItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(schoolData: SchoolData) {
        //binding.setVariable() todo
        binding.school = schoolData
        binding.executePendingBindings()
    }

}

object SchoolDataDiffCallback : DiffUtil.ItemCallback<SchoolData>() {
    override fun areItemsTheSame(oldItem: SchoolData, newItem: SchoolData): Boolean =
        oldItem.schoolId == newItem.schoolId

    override fun areContentsTheSame(oldItem: SchoolData, newItem: SchoolData): Boolean =
        oldItem == newItem
}