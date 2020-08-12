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

/**
 * Single point of access to school list data for presentation layer.
 */
interface SchoolRepository {
    suspend fun getSchoolData(identifier: String): List<SchoolData>
}

class DefaultSchoolRepository(private val schoolApi: SchoolApi) : SchoolRepository {

    override suspend fun getSchoolData(identifier: String): List<SchoolData> {
        return schoolApi.getSchools(identifier)
    }

}