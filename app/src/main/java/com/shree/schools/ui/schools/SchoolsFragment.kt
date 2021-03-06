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

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.shree.schools.data.SchoolData
import com.shree.schools.databinding.FragmentSchoolsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.InternalCoroutinesApi

@AndroidEntryPoint
@ExperimentalCoroutinesApi
@FlowPreview
class SchoolListFragment : Fragment() {
    private val viewModel: SchoolsViewModel by viewModels()
    private lateinit var adapter: SchoolsAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentSchoolsBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
        }
        binding.viewModel = viewModel

        adapter = createAdapter()
        binding.recyclerView.adapter = adapter
        return binding.root
    }

    private fun createAdapter(): SchoolsAdapter {
        val schoolsViewBinder = SchoolViewBinder()
        val schoolEmptyViewBinder = SchoolsEmptyViewBinder {
            viewModel.retry()
        }
        val schoolLoadingViewBinder = SchoolsLoadingViewBinder()

        @Suppress("UNCHECKED_CAST")
        return SchoolsAdapter(
            mapOf(
                Pair(
                    schoolsViewBinder.modelClass,
                    schoolsViewBinder as FeedItemBinder
                ),
                Pair(
                    schoolEmptyViewBinder.modelClass,
                    schoolEmptyViewBinder as FeedItemBinder
                ),
                Pair(
                    schoolLoadingViewBinder.modelClass,
                    schoolLoadingViewBinder as FeedItemBinder
                )
            )

        )
    }
}

@BindingAdapter(value = ["schoolItems"])
fun schoolItems(recyclerView: RecyclerView, schoolList: List<Any>?) {
    (recyclerView.adapter as SchoolsAdapter).submitList(schoolList)
}