package id.revan.mvvmwithdagger.ui.main

import id.revan.mvpwithdagger.model.Post

sealed class MainState
object UninitializedState : MainState()
object LoadingState : MainState()
class FailedState(val message: String) : MainState()
class SuccessState(val posts: List<Post>) : MainState()