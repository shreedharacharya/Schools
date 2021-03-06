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

package com.shree.schools.di

import android.content.Context
import com.shree.schools.api.SchoolApi
import com.shree.schools.data.DefaultSchoolRepository
import com.shree.schools.data.SchoolRepository
import com.shree.schools.data.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class AppModule {

    @Singleton
    @Provides
    fun providesSchoolApi(): SchoolApi {
        return SchoolApi.create()
    }

    @Singleton
    @Provides
    fun provideSchoolDataRepository(schoolApi: SchoolApi, database: AppDatabase): SchoolRepository {
        return DefaultSchoolRepository(schoolApi, database)
    }

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.buildDatabase(context)
    }
}