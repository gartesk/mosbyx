/*
 * Copyright 2020 MosbyX contributors
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
 */
package com.gartesk.mosbyx.mvi.integrationtest.lifecycle.viewgroup

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gartesk.mosbyx.mvi.integrationtest.R

class ViewGroupFinishOnCreateContainerActivity : AppCompatActivity() {

	val layout: ViewGroupFinishOnCreateLayout
		get() = findViewById(R.id.mviLayout)

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_view_group_finish_on_create_container)
		finish()
	}
}