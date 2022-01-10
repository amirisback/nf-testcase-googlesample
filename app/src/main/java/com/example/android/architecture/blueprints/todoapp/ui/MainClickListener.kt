package com.example.android.architecture.blueprints.todoapp.ui

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

interface MainClickListener {

    fun onClickListener(data: Article)

}