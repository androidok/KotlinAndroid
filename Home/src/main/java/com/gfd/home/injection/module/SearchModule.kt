package com.gfd.home.injection.module

import com.gfd.home.mvp.SearchContract
import com.gfd.home.service.SearchService
import com.gfd.home.service.impl.SearchServiceImpl
import dagger.Module
import dagger.Provides

/**
 * @Author : 郭富东
 * @Date ：2018/8/7 - 16:31
 * @Email：878749089@qq.com
 * @description：Dagger2-Module
 */
@Module
class SearchModule(var view: SearchContract.View) {

    @Provides
    fun provideView(): SearchContract.View {
        return view
    }

    @Provides
    fun provideSearchService(): SearchService {
        return SearchServiceImpl()
    }

}