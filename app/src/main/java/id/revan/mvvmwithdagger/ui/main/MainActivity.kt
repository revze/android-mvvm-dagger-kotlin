package id.revan.mvvmwithdagger.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import id.revan.mvpwithdagger.adapter.PostAdapter
import id.revan.mvpwithdagger.extensions.gone
import id.revan.mvpwithdagger.extensions.visible
import id.revan.mvpwithdagger.helper.LayoutHelper
import id.revan.mvpwithdagger.model.Post
import id.revan.mvvmwithdagger.R
import id.revan.mvvmwithdagger.di.Injector
import id.revan.mvvmwithdagger.ui.base.BaseViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var layoutHelper: LayoutHelper

    private lateinit var vm: MainVM

    @Inject
    lateinit var viewModelFactory: BaseViewModelFactory<MainVM>

    private lateinit var postAdapter: PostAdapter
    private val posts = mutableListOf<Post>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Injector.create(this).inject(this)

        vm = ViewModelProviders.of(this, viewModelFactory).get(MainVM::class.java)
        vm.state.observe(this, observer)

        postAdapter = PostAdapter(posts)
        rv_post.adapter = postAdapter
        rv_post.layoutManager = LinearLayoutManager(this)
    }

    private val observer = Observer<MainState> {
        when (it) {
            is LoadingState -> {
                layout_wrapper.visible()
                rv_post.gone()
                layoutHelper.showLoader(layout_wrapper)
            }
            is SuccessState -> {
                layout_wrapper.gone()
                rv_post.visible()
                this.posts.clear()
                this.posts.addAll(it.posts)
                postAdapter.notifyDataSetChanged()
            }
            is FailedState -> {
                layout_wrapper.visible()
                rv_post.gone()
                layoutHelper.showLayoutError(layout_wrapper, it.message)
            }
            else -> {
                vm.getPosts()
            }
        }
    }
}
