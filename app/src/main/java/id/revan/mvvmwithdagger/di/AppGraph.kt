package id.revan.mvvmwithdagger.di

import id.revan.mvvmwithdagger.ui.main.MainActivity
import id.revan.mvvmwithdagger.ui.postdetail.PostDetailActivity

interface AppGraph {
    fun inject(activity: MainActivity)

    fun inject(activity: PostDetailActivity)
}