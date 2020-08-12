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
import com.shree.schools.data.SchoolData
import com.shree.schools.domain.schools.LoadSchoolsUseCase
import com.shree.schools.result.data
import kotlinx.coroutines.launch

class SchoolsViewModel @ViewModelInject constructor(
    private val loadSchoolListUseCase: LoadSchoolsUseCase,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _schoolList = MutableLiveData<List<SchoolData>>()
    val schoolList: LiveData<List<SchoolData>> = _schoolList

    init {
        viewModelScope.launch {
            _schoolList.value = loadSchoolListUseCase(getIdentifier().value.toString()).data
        }
    }

    private fun getIdentifier(): MutableLiveData<String> {
        return savedStateHandle.getLiveData(IDENTIFIER_KEY, IDENTIFIER_VALUE)
    }

    fun setIdentifier(num: Int) {
        savedStateHandle.set(IDENTIFIER_KEY, "")
    }

    companion object {
        private const val IDENTIFIER_KEY = "IDENTIFIER_KEY"
        private const val IDENTIFIER_VALUE = "97mf-9njv"
    }
}