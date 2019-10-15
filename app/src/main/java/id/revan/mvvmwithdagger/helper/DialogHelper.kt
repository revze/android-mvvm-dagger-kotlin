package id.revan.mvpwithdagger.helper

import android.content.Context
import org.jetbrains.anko.*
import javax.inject.Inject

class DialogHelper @Inject constructor(val context: Context) : AnkoLogger {
    fun showDialog() {
        context.alert("Hi, I'm Roy", "Have you tried turning it off and on again?") {
            yesButton { context.toast("Oh…") }
            noButton {}
        }.show()
    }

}