package id.revan.mvvmwithdagger.di

import android.content.Context
import id.revan.mvvmwithdagger.di.module.AppModule

class Injector {
    companion object {
        fun create(context: Context): AppGraph {
            return DaggerAppComponent.builder().appModule(
                AppModule(
                    context
                )
            ).build()
        }
    }
}