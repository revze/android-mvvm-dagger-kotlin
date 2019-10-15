package id.revan.mvvmwithdagger.di.module

import dagger.Module
import dagger.Provides
import id.revan.mvvmwithdagger.ui.base.BaseViewModelFactory
import id.revan.mvvmwithdagger.ui.main.MainVM
import id.revan.mvvmwithdagger.ui.postdetail.PostDetailVM

@Module
class ViewModelModule {
    @Provides
    fun provideMainViewModel(viewModel: MainVM): BaseViewModelFactory<MainVM> {
        return BaseViewModelFactory { viewModel }
    }

    @Provides
    fun providePostDetailViewModel(viewModel: PostDetailVM): BaseViewModelFactory<PostDetailVM> {
        return BaseViewModelFactory { viewModel }
    }
}