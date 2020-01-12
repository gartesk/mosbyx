/*
 * Copyright 2017 Hannes Dorfmann.
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
package com.hannesdorfmann.mosby3.sample.mvi.view.checkoutbutton

import android.content.Context
import android.os.Parcelable
import android.util.AttributeSet
import android.view.AbsSavedState
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.hannesdorfmann.mosby3.ViewGroupMviDelegate
import com.hannesdorfmann.mosby3.ViewGroupMviDelegateCallback
import com.hannesdorfmann.mosby3.ViewGroupMviDelegateImpl
import com.hannesdorfmann.mosby3.sample.mvi.SampleApplication
import io.reactivex.Observable
import timber.log.Timber
import java.util.*

class CheckoutButton(
	context: Context,
	attrs: AttributeSet?
) : Button(context, attrs),
	CheckoutButtonView,
	ViewGroupMviDelegateCallback<CheckoutButtonView, CheckoutButtonPresenter> {

	private val mviDelegate: ViewGroupMviDelegate<CheckoutButtonView, CheckoutButtonPresenter> =
		ViewGroupMviDelegateImpl(this, this, true)

	override val mvpView: CheckoutButtonView
		get() = this

	init {
		setOnClickListener {
			Toast.makeText(
				context,
				"This is just a demo app. You can't purchase any items.",
				Toast.LENGTH_LONG
			)
				.show()
		}
	}

	override fun createPresenter(): CheckoutButtonPresenter {
		Timber.d("create presenter")
		return SampleApplication.getDependencyInjection(context).newCheckoutButtonPresenter()
	}

	override fun superOnSaveInstanceState(): Parcelable? = super.onSaveInstanceState()

	override fun superOnRestoreInstanceState(state: Parcelable?) {
		super.onRestoreInstanceState(state)
	}

	override fun loadIntent(): Observable<Unit> = Observable.just(Unit)

	override fun render(sum: Double) {
		val priceString = String.format(Locale.US, "%.2f", sum)
		Timber.d("render $priceString")
		if (sum == 0.0) {
			visibility = View.INVISIBLE
		} else {
			text = "Checkout $ $priceString"
			visibility = View.VISIBLE
		}
	}

	override fun onAttachedToWindow() {
		super.onAttachedToWindow()
		mviDelegate.onAttachedToWindow()
	}

	override fun onDetachedFromWindow() {
		super.onDetachedFromWindow()
		mviDelegate.onDetachedFromWindow()
	}

	override fun onSaveInstanceState(): Parcelable =
		mviDelegate.onSaveInstanceState() ?: AbsSavedState.EMPTY_STATE

	override fun onRestoreInstanceState(state: Parcelable) {
		mviDelegate.onRestoreInstanceState(state)
	}

	override fun setRestoringViewState(restoringViewState: Boolean) = Unit
}