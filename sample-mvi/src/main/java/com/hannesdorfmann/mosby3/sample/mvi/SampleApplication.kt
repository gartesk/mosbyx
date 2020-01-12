/*
 * Copyright 2016 Hannes Dorfmann.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package com.hannesdorfmann.mosby3.sample.mvi

import android.app.Application
import android.content.Context
import com.hannesdorfmann.mosby3.sample.mvi.dependencyinjection.DependencyInjection
import timber.log.Timber
import timber.log.Timber.DebugTree

/**
 * A custom Application class mainly used to provide dependency injection
 */
class SampleApplication : Application() {

	companion object {
		fun getDependencyInjection(context: Context): DependencyInjection {
			return (context.applicationContext as SampleApplication).dependencyInjection
		}
	}

	private var dependencyInjection = DependencyInjection()

	override fun onCreate() {
		super.onCreate()
		Timber.plant(DebugTree())
	}
}