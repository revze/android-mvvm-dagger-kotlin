package id.revan.mvvmwithdagger

import android.app.Application
import id.revan.mvvmwithdagger.di.AppGraph
import id.revan.mvvmwithdagger.di.Injector

class App : Application() {
    lateinit var injector: AppGraph

    companion object {
        private lateinit var app: App

        fun get(): App {
            return app
        }
    }

    override fun onCreate() {
        super.onCreate()

        app = this
        injector = Injector.create(this)
    }
}