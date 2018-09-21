import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.widget.EditText

fun EditText.text(): String = text.toString()

inline fun <reified T : ViewModel> AppCompatActivity.buildViewModel(factory: ViewModelProvider.Factory): T = ViewModelProviders.of(this, factory).get(T::class.java)

