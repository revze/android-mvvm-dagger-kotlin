package id.revan.mvvmwithdagger.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import id.revan.mvpwithdagger.helper.DialogHelper
import id.revan.mvpwithdagger.helper.LayoutHelper

@Module
class UIModule {
    @Provides
    fun provideLayoutHelper(context: Context): LayoutHelper {
        return LayoutHelper(context)
    }

    @Provides
    fun provideDialogHelper(context: Context): DialogHelper {
        return DialogHelper(context)
    }
}