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
package com.gartesk.mosbyx.mvi.sample.view.menu

import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gartesk.mosbyx.sample.mvi.R
import com.gartesk.mosbyx.mvi.sample.businesslogic.model.MainMenuItem

/**
 * Simple ViewHolder to display a list of main menu items
 */
class MainMenuViewHolder private constructor(
	itemView: View,
	listener: MainMenuSelectionListener
) : RecyclerView.ViewHolder(itemView) {

	companion object {
		fun create(inflater: LayoutInflater, listener: MainMenuSelectionListener): MainMenuViewHolder =
			MainMenuViewHolder(
				inflater.inflate(R.layout.item_main_menu, null, false),
				listener
			)
	}

	private val name: TextView = itemView.findViewById(R.id.name)

	init {
		itemView.setOnClickListener { listener.onItemSelected(name.text.toString()) }
	}

	fun bind(item: MainMenuItem) {
		name.text = item.name
	}

	interface MainMenuSelectionListener {
		fun onItemSelected(categoryName: String)
	}
}