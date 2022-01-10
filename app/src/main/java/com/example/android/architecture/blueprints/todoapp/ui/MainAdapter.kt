package com.example.android.architecture.blueprints.todoapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.architecture.blueprints.todoapp.R
import com.example.android.architecture.blueprints.todoapp.data.Article


/*
 * Created by faisalamir on 06/01/22
 * nf-testcase-app-no-framework
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * -----------------------------------------
 * Copyright (C) 2022 FrogoBox Inc.      
 * All rights reserved
 *
 */

class MainAdapter(private val listener: MainClickListener) : RecyclerView.Adapter<MainHolder>() {

    private val listData = mutableListOf<Article>()

    fun setContent(data: List<Article>) {
        listData.addAll(data)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        return MainHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_main, parent, false))
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        holder.bindItem(listData[position], listener)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

}