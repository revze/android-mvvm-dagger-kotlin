package id.revan.mvvmwithdagger.di

import dagger.Component
import id.revan.mvvmwithdagger.di.module.ApiModule
import id.revan.mvvmwithdagger.di.module.AppModule
import id.revan.mvvmwithdagger.di.module.UIModule
import id.revan.mvvmwithdagger.di.module.ViewModelModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ApiModule::class, UIModule::class, ViewModelModule::class])
interface AppComponent : AppGraph