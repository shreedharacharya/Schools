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

package com.shree.schools.data

import com.shree.schools.api.SchoolApi
import com.shree.schools.result.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Single point of access to school list data for presentation layer.
 */
interface SchoolRepository {
    fun getSchoolData(identifier: String): Flow<Result<List<SchoolData>>>
}

class DefaultSchoolRepository(private val schoolApi: SchoolApi) : SchoolRepository {

    override fun getSchoolData(identifier: String): Flow<Result<List<SchoolData>>> {
        return flow {
            emit(Result.Loading)
            emit(Result.Success(schoolApi.getSchools(identifier)))
        }
    }

}