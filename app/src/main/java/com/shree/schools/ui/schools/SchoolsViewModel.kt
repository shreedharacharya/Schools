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

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.shree.schools.domain.schools.LoadSchoolsUseCase
import com.shree.schools.result.Result
import com.shree.schools.result.exception
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@FlowPreview
@ExperimentalCoroutinesApi
class SchoolsViewModel @ViewModelInject constructor(
    private val loadSchoolListUseCase: LoadSchoolsUseCase,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _schoolList = MutableLiveData<List<Any>>()
    val schoolList: LiveData<List<Any>> = _schoolList
    private val reload = MutableLiveData<Boolean>()
    private val schoolListFlow: Flow<Result<List<Any>>> = flowOf(
        reload.asFlow().flatMapLatest { loadSchoolListUseCase(getIdentifier().value.toString()) }
    ).flattenMerge(1)


    init {
        reload.value = true
        loadSchoolData()
    }

    private fun getIdentifier(): MutableLiveData<String> {
        return savedStateHandle.getLiveData(IDENTIFIER_KEY, IDENTIFIER_VALUE)
    }

    fun setIdentifier(num: Int) {
        savedStateHandle.set(IDENTIFIER_KEY, "")
    }

    fun retry() {
        reload.value = true
    }


    private fun loadSchoolData() {
        viewModelScope.launch {
            schoolListFlow.collectLatest {
                _schoolList.value = when (it) {
                    is Result.Loading -> listOf(LoadingIndicator)
                    is Result.Success -> it.data
                    else -> listOf(SchoolsEmpty(it.exception?.message ?: ""))
                }
            }
        }
    }

    companion object {
        private const val IDENTIFIER_KEY = "IDENTIFIER_KEY"
        private const val IDENTIFIER_VALUE = "97mf-9njv"
    }
}