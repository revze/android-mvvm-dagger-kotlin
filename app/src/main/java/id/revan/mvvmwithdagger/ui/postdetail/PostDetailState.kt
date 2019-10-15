package id.revan.mvvmwithdagger.ui.postdetail

import id.revan.mvpwithdagger.model.Post

sealed class PostDetailState
object UninitializedState : PostDetailState()
object LoadingState : PostDetailState()
class FailedState(val message: String) : PostDetailState()
class SuccessState(val post: Post) : PostDetailState()