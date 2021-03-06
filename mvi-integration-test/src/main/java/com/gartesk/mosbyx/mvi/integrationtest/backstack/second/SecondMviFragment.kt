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
package com.gartesk.mosbyx.mvi.integrationtest.backstack.second

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gartesk.mosbyx.mvi.MviFragment
import com.gartesk.mosbyx.mvi.integrationtest.R
import com.gartesk.mosbyx.mvi.integrationtest.backstack.BackstackActivity

class SecondMviFragment : MviFragment<SecondView, SecondPresenter>(), SecondView {

	override fun createPresenter(): SecondPresenter {
		BackstackActivity.createSecondPresenterCalls.incrementAndGet()
		return BackstackActivity.secondPresenter
	}

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		return inflater.inflate(R.layout.fragment_backstack_second, container, false)
	}
}