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
package com.gartesk.mosbyx.sample.mvi.view.detail

import com.gartesk.mosbyx.mvi.MviView
import com.gartesk.mosbyx.sample.mvi.businesslogic.interactor.details.ProductDetailsViewState
import com.gartesk.mosbyx.sample.mvi.businesslogic.model.FeedItem.Product
import io.reactivex.Observable

interface ProductDetailsView : MviView {
	/**
	 * The intent to load details of a certain product
	 */
	fun loadDetailsIntent(): Observable<Int>

	/**
	 * The intent to add a product to the shopping cart
	 */
	fun addToShoppingCartIntent(): Observable<Product>

	/**
	 * the intent to remove a product from the shopping cart
	 */
	fun removeFromShoppingCartIntent(): Observable<Product>

	/**
	 * Render the state n the UI
	 */
	fun render(state: ProductDetailsViewState)
}