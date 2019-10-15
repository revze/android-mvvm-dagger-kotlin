package id.revan.mvvmwithdagger.ui.postdetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.revan.mvpwithdagger.api.ApiInterface
import id.revan.mvpwithdagger.model.Post
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class PostDetailVM @Inject constructor(private val apiInterface: ApiInterface) : ViewModel() {
    val state: MutableLiveData<PostDetailState> by lazy {
        MutableLiveData<PostDetailState>().also {
            it.value = UninitializedState
        }
    }

    fun getPost(id: String) {
        state.value = LoadingState
        apiInterface.getPost(id).enqueue(object : Callback<Post> {
            override fun onFailure(call: Call<Post>, t: Throwable) {
                state.value = FailedState("Failed to load post")
            }

            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                state.value = SuccessState(response.body()!!)
            }
        })
    }
}