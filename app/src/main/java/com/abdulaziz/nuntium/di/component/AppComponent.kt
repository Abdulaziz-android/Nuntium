package com.abdulaziz.nuntium.di.component

import com.abdulaziz.nuntium.di.module.DatabaseModule
import com.abdulaziz.nuntium.di.module.NetworkModule
import com.abdulaziz.nuntium.ui.fregment.article.ArticleFragment
import com.abdulaziz.nuntium.ui.fregment.bookmark.BookmarkFragment
import com.abdulaziz.nuntium.ui.fregment.category.CategoryFragment
import com.abdulaziz.nuntium.ui.fregment.home.HomeFragment
import com.abdulaziz.nuntium.ui.activity.MainActivity
import com.abdulaziz.nuntium.ui.fregment.profile.ProfileFragment
import com.abdulaziz.nuntium.ui.fregment.welcome.SplashFragment
import com.abdulaziz.nuntium.ui.fregment.welcome.TopicFragment
import com.abdulaziz.nuntium.ui.fregment.search.SearchFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, DatabaseModule::class])
interface AppComponent {

    fun inject(mainActivity: MainActivity)

    fun inject(homeFragment: HomeFragment)

    fun inject(articleFragment: ArticleFragment)

    fun inject(searchFragment: SearchFragment)

    fun inject(topicFragment: TopicFragment)

    fun inject(splashFragment: SplashFragment)

    fun inject(bookmarkFragment: BookmarkFragment)

    fun inject(categoryFragment: CategoryFragment)

    fun inject(profileFragment: ProfileFragment)


}