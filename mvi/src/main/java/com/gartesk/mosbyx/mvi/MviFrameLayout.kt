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
package com.gartesk.mosbyx.mvi

import android.annotation.SuppressLint
import android.content.Context
import android.os.Parcelable
import android.util.AttributeSet
import android.widget.FrameLayout
import com.gartesk.mosbyx.mvi.delegate.viewgroup.ViewGroupMviDelegate
import com.gartesk.mosbyx.mvi.delegate.viewgroup.ViewGroupMviDelegateCallback
import com.gartesk.mosbyx.mvi.delegate.viewgroup.ViewGroupMviDelegateImpl

/**
 * A FrameLayout that can be used as View with an Presenter to implement MVI
 */
abstract class MviFrameLayout<V : MviView, P : MviPresenter<V, *>>
@JvmOverloads constructor(
	context: Context,
	attrs: AttributeSet? = null,
	defStyleAttr: Int = 0,
	defStyleRes: Int = 0
) : FrameLayout(context, attrs, defStyleAttr, defStyleRes),
	ViewGroupMviDelegateCallback<V, P>,
	MviView {

	private var isRestoringViewState = false

	/**
	 * Get the mvi delegate. This is internally used for creating presenter, attaching and detaching
	 * view from presenter etc.
	 *
	 * **Please note that only one instance of mvi delegate should be used per android.view.View
	 * instance**.
	 *
	 * Only override this property if you really know what you are doing.
	 *
	 * @return [ViewGroupMviDelegate]
	 */
	protected val mviDelegate: ViewGroupMviDelegate<V, P> by lazy {
		ViewGroupMviDelegateImpl(
			this,
			this,
			true
		)
	}

	public override fun onAttachedToWindow() {
		super.onAttachedToWindow()
		mviDelegate.onAttachedToWindow()
	}

	public override fun onDetachedFromWindow() {
		super.onDetachedFromWindow()
		mviDelegate.onDetachedFromWindow()
	}

	@SuppressLint("MissingSuperCall")
	public override fun onSaveInstanceState(): Parcelable {
		return mviDelegate.onSaveInstanceState()!!
	}

	@SuppressLint("MissingSuperCall")
	public override fun onRestoreInstanceState(state: Parcelable) {
		mviDelegate.onRestoreInstanceState(state)
	}

	/**
	 * Instantiate a presenter instance
	 *
	 * @return The [MviPresenter] for this view
	 */
	abstract override fun createPresenter(): P

	override val mviView: V
		get() = this as V

	override fun superOnSaveInstanceState(): Parcelable? {
		return super.onSaveInstanceState()
	}

	override fun superOnRestoreInstanceState(state: Parcelable?) {
		super.onRestoreInstanceState(state)
	}

	override fun setRestoringViewState(restoringViewState: Boolean) {
		isRestoringViewState = restoringViewState
	}

	protected fun isRestoringViewState(): Boolean {
		return isRestoringViewState
	}
}