package com.abdulaziz.nuntium.ui.fregment.bookmark

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.abdulaziz.nuntium.App
import com.abdulaziz.nuntium.R
import com.abdulaziz.nuntium.ui.adapters.NewsAdapter
import com.abdulaziz.nuntium.data.local.dao.NewsDao
import com.abdulaziz.nuntium.data.models.news_by_category.News
import com.abdulaziz.nuntium.databinding.FragmentBookmarkBinding
import com.abdulaziz.nuntium.themes.MyAppTheme
import com.abdulaziz.nuntium.ui.fregment.article.ArticleFragment
import com.abdulaziz.nuntium.ui.activity.MainView
import com.dolatkia.animatedThemeManager.AppTheme
import com.dolatkia.animatedThemeManager.ThemeFragment
import javax.inject.Inject

class BookmarkFragment : ThemeFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = NewsAdapter(object : NewsAdapter.OnItemClickListener {
            override fun onItemClicked(news: News) {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, ArticleFragment::class.java, bundleOf(Pair("news", news)))
                    .addToBackStack("article")
                    .commit()
            }
        })
    }

    private var _binding: FragmentBookmarkBinding? = null
    private val binding get() = _binding!!
    @Inject lateinit var newsDao: NewsDao
    private lateinit var adapter: NewsAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBookmarkBinding.inflate(layoutInflater)
        App.appComponent.inject(this)
        (activity as MainView?)?.showBottomBar()


        binding.rv.adapter = adapter
        loadData()

        return binding.root
    }

    private fun loadData() {

        val allNews = newsDao.getAllNews()
        val savedList = arrayListOf<News>()
        allNews.forEach {
            if (it.isSaved){
                savedList.add(it)
            }
        }
        if (savedList.isNotEmpty()) {
            adapter.submitList(savedList)
            binding.notHaveLayout.visibility = View.GONE
        } else {
            adapter.submitList(emptyList())
            binding.notHaveLayout.visibility = View.VISIBLE
        }

    }

    override fun syncTheme(appTheme: AppTheme) {
        val myAppTheme = appTheme as MyAppTheme
        context?.let {
            // set background color
            binding.root.setBackgroundColor(myAppTheme.fragmentBackgroundColor(it))
            binding.titleTv.setTextColor(myAppTheme.fragmentLargeTextColor(it))
            binding.descTv.setTextColor(myAppTheme.fragmentSmallTextColor(it))
            binding.notHaveBookTv.setTextColor(myAppTheme.fragmentSmallTextColor(it))
            binding.bookCard.setCardBackgroundColor(myAppTheme.activityBottomNavViewBackColor(it))
        }
    }

}