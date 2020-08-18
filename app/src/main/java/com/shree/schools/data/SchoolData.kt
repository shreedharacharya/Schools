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

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(
    tableName = "school_data",
    indices = [Index("id")]
)
data class SchoolData(
    @PrimaryKey
    @SerializedName("dbn")
    @ColumnInfo(name = "id")
    val schoolId: String,
    @SerializedName("overview_paragraph")
    val schoolDes: String,
    @SerializedName("primary_address_line_1")
    val primaryAddress1: String,
    @SerializedName("school_name")
    val schoolName: String,
    @SerializedName("phone_number")
    val schoolPhone: String,
    @SerializedName("website")
    val schoolWebsite: String
)

