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
package com.gartesk.mosbyx.mvi.sample.view.checkoutbutton

import com.gartesk.mosbyx.mvi.MviBasePresenter
import com.gartesk.mosbyx.mvi.sample.businesslogic.ShoppingCart
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import timber.log.Timber

/**
 * Presenter for  [CheckoutButtonView] that displays the sum of all items shopping cart
 */
class CheckoutButtonPresenter(private val shoppingCart: ShoppingCart) :
	MviBasePresenter<CheckoutButtonView, Double>() {

	override fun bindIntents() {
		val numberOfItemsInShoppingCart: Observable<Double> = intent { it.loadIntent() }
			.doOnNext { Timber.d("intent: load number of items in shopping cart") }
			.flatMap { shoppingCart.getItemsInShoppingCart() }
			.map { items ->
				items.map { it.price }
					.fold(0.0) { sum, price -> sum + price }
			}
			.observeOn(AndroidSchedulers.mainThread())

		subscribeViewState(numberOfItemsInShoppingCart) { view, sum ->
			view.render(sum)
		}
	}

}