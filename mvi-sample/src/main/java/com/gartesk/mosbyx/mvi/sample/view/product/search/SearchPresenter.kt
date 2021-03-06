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
package com.gartesk.mosbyx.mvi.sample.view.product.search

import com.gartesk.mosbyx.mvi.MviBasePresenter
import com.gartesk.mosbyx.mvi.sample.businesslogic.interactor.search.SearchInteractor
import com.gartesk.mosbyx.mvi.sample.businesslogic.interactor.search.SearchViewState
import com.gartesk.mosbyx.mvi.sample.businesslogic.interactor.search.SearchViewState.SearchNotStartedYet
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import timber.log.Timber

/**
 * MVI Presenter for [SearchView]
 */
class SearchPresenter(private val searchInteractor: SearchInteractor) :
	MviBasePresenter<SearchView, SearchViewState>(SearchNotStartedYet) {

	override fun bindIntents() {
		val search: Observable<SearchViewState> = intent { it.searchIntent() }
			.doOnNext { Timber.d("intent: Search '$it'") }
			.switchMap { searchInteractor.search(it) }
			.observeOn(AndroidSchedulers.mainThread())

		subscribeViewState(search) { view, viewState ->
			view.render(viewState)
		}
	}

}