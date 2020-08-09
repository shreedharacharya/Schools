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

plugins {
    id("java-platform")
}

val core = "1.3.1"
val appcompat = "1.2.0"
val material = "1.2.0"
val constraintLayout = "1.1.3"
val junitExt = "1.1.1"
val junit = "4.13"
val espresso = "3.2.0"

dependencies {
    constraints {
        api("${Libs.CORE_KTX}:$core")
        api("${Libs.APPCOMPAT}:$appcompat")
        api("${Libs.MATERIAL}:$material")
        api("${Libs.CONSTRAINT_LAYOUT}:$constraintLayout")
        api("${Libs.ESPRESSO_CORE}:$espresso")
        api("${Libs.EXT_JUNIT}:$junitExt")
        api("${Libs.JUNIT}:$junit")
        api("${Libs.NAVIGATION_FRAGMENT_KTX}:${Versions.NAVIGATION}")
        api("${Libs.NAVIGATION_UI_KTX}:${Versions.NAVIGATION}")
    }
}