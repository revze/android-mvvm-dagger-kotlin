package id.revan.mvvmwithdagger.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.revan.mvpwithdagger.api.ApiInterface
import id.revan.mvpwithdagger.model.Post
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MainVM @Inject constructor(private val apiInterface: ApiInterface) : ViewModel() {
    val state: MutableLiveData<MainState> by lazy {
        MutableLiveData<MainState>().also {
            it.value = UninitializedState
        }
    }

    fun getPosts() {
        state.value = LoadingState
        apiInterface.getPosts().enqueue(object : Callback<List<Post>> {
            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                state.value = FailedState("Failed to load post")
            }

            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                state.value = SuccessState(response.body()!!)
            }
        })
    }
}