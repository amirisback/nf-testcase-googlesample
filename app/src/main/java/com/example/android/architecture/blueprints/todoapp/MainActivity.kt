package com.example.android.architecture.blueprints.todoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

    override fun setupViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        title = "MainActivity"

        val consumeNewsApi = ConsumeNewsApi(NewsUrl.API_KEY)
        consumeNewsApi.getTopHeadline(
            null,
            null,
            null,
            NewsConstant.COUNTRY_ID,
            null,
            null,
            object : NutriResponse.DataResponse<ArticleResponse> {
                override fun onShowProgress() {

                }

                override fun onHideProgress() {

                }

                override fun onEmpty() {

                }

                override fun onSuccess(data: ArticleResponse) {
                    runOnUiThread {
                        data.articles?.let { setupRv(it) }
                    }
                }

                override fun onFailed(statusCode: Int, errorMessage: String?) {
                    errorMessage?.let { showToast(it) }
                }

            })

    }

    private fun setupRv(data: List<Article>) {
        val mainAdapter = MainAdapter(this)
        mainAdapter.setContent(data)

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


}