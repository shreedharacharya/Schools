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

package com.shree.schools.domain.schools

import com.shree.schools.data.SchoolData
import com.shree.schools.data.SchoolRepository
import com.shree.schools.di.IoDispatcher
import com.shree.schools.domain.UseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

/**
 * Loads the schools data and parameters is the
 * identifier in the url
 */
class LoadSchoolsUseCase @Inject constructor(
    private val repository: SchoolRepository,
    @IoDispatcher ioDispatcher: CoroutineDispatcher
) : UseCase<String, List<SchoolData>>(ioDispatcher) {

    override suspend fun execute(parameters: String): List<SchoolData> =
        repository.getSchoolData(parameters)
}