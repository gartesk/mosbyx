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
package com.gartesk.mosbyx.mvi.sample.view.home.viewholder

import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.gartesk.mosbyx.sample.mvi.R

/**
 * Simply shows a progress bar. This is typically used for pagination to indicate that more items
 * are loading.
 */
class LoadingViewHolder private constructor(itemView: View) :
	RecyclerView.ViewHolder(itemView) {

	companion object {
        fun create(inflater: LayoutInflater): LoadingViewHolder =
			LoadingViewHolder(
				inflater.inflate(R.layout.item_loading, null, false)
			)
	}
}