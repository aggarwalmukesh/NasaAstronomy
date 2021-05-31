import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nasa.wmart.viewmodel.NasaViewModel

class MainViewModelFactory(private val sharedPreferences: SharedPreferences) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NasaViewModel(sharedPreferences) as T
    }
}