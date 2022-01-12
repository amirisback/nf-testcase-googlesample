package com.example.android.architecture.blueprints.todoapp

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.architecture.blueprints.todoapp.data.Article
import com.example.android.architecture.blueprints.todoapp.data.ArticleResponse
import com.example.android.architecture.blueprints.todoapp.data.source.remote.ConsumeNewsApi
import com.example.android.architecture.blueprints.todoapp.data.source.remote.NutriResponse
import com.example.android.architecture.blueprints.todoapp.databinding.ActivityMainBinding
import com.example.android.architecture.blueprints.todoapp.ui.MainAdapter
import com.example.android.architecture.blueprints.todoapp.ui.MainClickListener
import com.example.android.architecture.blueprints.todoapp.util.NewsConstant
import com.example.android.architecture.blueprints.todoapp.util.NewsUrl
import com.google.gson.Gson

class MainActivity : BaseActivity<ActivityMainBinding>(), MainClickListener {

    private val mainAdapter = MainAdapter(this)

    override fun setupViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        title = "MainActivity"
        requestAPI("susu", false)

    }

    private fun requestAPI(query: String?, isRepeateRequest: Boolean) {
        val consumeNewsApi = ConsumeNewsApi("537d2afe5a594e28b967e1118c7c38d3")
        consumeNewsApi.getEverythings(
            query,
            null,
            null,
            null,
            null,
            null,
            null,
            NewsConstant.COUNTRY_ID,
            null,
            null,
            null,
            object : NutriResponse.DataResponse<ArticleResponse> {
                override fun onShowProgress() {
                    runOnUiThread {
                        binding.progressView.visibility = View.VISIBLE
                    }
                }

                override fun onHideProgress() {
                    runOnUiThread {
                        binding.progressView.visibility = View.GONE
                    }
                }

                override fun onEmpty() {

                }

                override fun onSuccess(data: ArticleResponse) {
                    runOnUiThread {
                        data.articles?.let { setupRv(it, true) }
                    }
                }

                override fun onFailed(statusCode: Int, errorMessage: String?) {
                    runOnUiThread {
                        errorMessage?.let { showToast(it) }
                    }
                }

            })
    }

    private fun setupRv(data: List<Article>, isRepeateRequest: Boolean) {

        if (isRepeateRequest) {
            mainAdapter.clearData()
        }

        if (data.isNotEmpty()) {
            mainAdapter.setContent(data)
        }

        binding.rvMain.apply {
            adapter = mainAdapter
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
        }

    }

    override fun onClickListener(data: Article) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_DATA, Gson().toJson(data))
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)

        val searchItem: MenuItem? = menu.findItem(R.id.action_search)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView: SearchView = searchItem?.actionView as SearchView

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { Log.d("Query Searched", it) }
                requestAPI(query, true)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { Log.d("Query Searched", it) }
                requestAPI(newText, true)
                return true
            }

        })
        return super.onCreateOptionsMenu(menu)
    }

}