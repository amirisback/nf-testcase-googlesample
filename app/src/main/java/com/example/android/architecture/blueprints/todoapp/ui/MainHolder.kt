package com.example.android.architecture.blueprints.todoapp.ui

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
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

class MainHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun bindItem(data: Article, listener: MainClickListener) {
        view.findViewById<TextView>(R.id.nutri_rv_list_type_11_tv_title).text = data.title
        view.findViewById<TextView>(R.id.nutri_rv_list_type_11_tv_desc).text =
            data.description
        view.findViewById<TextView>(R.id.nutri_rv_list_type_11_tv_subtitle).text =
            data.source?.name ?: "Unknowm"
        Glide.with(view.context).load(data.urlToImage)
            .into(view.findViewById(R.id.nutri_rv_list_type_11_iv_poster))
        itemView.setOnClickListener { listener.onClickListener(data) }
    }

}