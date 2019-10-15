package id.revan.mvvmwithdagger.ui.postdetail

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import id.revan.mvpwithdagger.extensions.gone
import id.revan.mvpwithdagger.extensions.visible
import id.revan.mvpwithdagger.helper.LayoutHelper
import id.revan.mvvmwithdagger.R
import id.revan.mvvmwithdagger.di.Injector
import id.revan.mvvmwithdagger.ui.base.BaseViewModelFactory
import kotlinx.android.synthetic.main.activity_post_detail.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import javax.inject.Inject

class PostDetailActivity : AppCompatActivity(), AnkoLogger {

    @Inject
    lateinit var layoutHelper: LayoutHelper

    private lateinit var vm: PostDetailVM

    @Inject
    lateinit var viewModelFactory: BaseViewModelFactory<PostDetailVM>

    companion object {
        const val ID = "id"
    }

    private var id = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_detail)

        id = intent.getStringExtra(ID) ?: ""

        Injector.create(this).inject(this)

        vm = ViewModelProviders.of(this, viewModelFactory)
            .get(PostDetailVM::class.java)
        vm.state.observe(this, observer)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private val observer = Observer<PostDetailState> {
        info(it)
        when (it) {
            is LoadingState -> {
                layout_wrapper.visible()
                sv_post_detail.gone()
                layoutHelper.showLoader(layout_wrapper)
            }
            is SuccessState -> {
                layout_wrapper.gone()
                sv_post_detail.visible()
                tv_title.text = it.post.title
                tv_body.text = it.post.body
            }
            is FailedState -> {
                layout_wrapper.visible()
                sv_post_detail.gone()
                layoutHelper.showLayoutError(layout_wrapper, it.message)
            }
            else -> {
                vm.getPost(id)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
