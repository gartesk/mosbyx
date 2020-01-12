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
package com.hannesdorfmann.mosby3.sample.mvi.businesslogic

import com.hannesdorfmann.mosby3.sample.mvi.businesslogic.model.FeedItem.Product
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

/**
 * Holds a list of items that has been added to the shopping cart
 */
class ShoppingCart {
	val itemsInShoppingCart: BehaviorSubject<List<Product>> =
		BehaviorSubject.createDefault<List<Product>>(emptyList())

	/**
	 * An observable to observe the items in the shopping cart
	 */
	fun getItemsInShoppingCart(): Observable<List<Product>> = itemsInShoppingCart.hide()

	/**
	 * Adds a product to the shopping cart
	 */
	fun addProduct(product: Product): Completable =
		Completable.fromAction {
			val oldItems = itemsInShoppingCart.value ?: emptyList()
			itemsInShoppingCart.onNext(oldItems + product)
		}

	/**
	 * Remove a product to the shopping cart
	 */
	fun removeProduct(product: Product): Completable =
		Completable.fromAction {
			val oldItems = itemsInShoppingCart.value ?: emptyList()
			itemsInShoppingCart.onNext(oldItems - product)
		}

	/**
	 * Remove a list of Products from the shopping cart
	 */
	fun removeProducts(products: List<Product>): Completable =
		Completable.fromAction {
			val oldItems = itemsInShoppingCart.value ?: emptyList()
			itemsInShoppingCart.onNext(oldItems - products)
		}
}