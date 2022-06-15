package science.example.week7dota.model

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import science.example.week7dota.data.Repository
import science.example.week7dota.data.Repository.Companion.FILE
import java.io.File
import java.nio.channels.FileLockInterruptionException

class DotaModel : ViewModel() {

    var listOfDota: LiveData<List<HeroDota>> = MutableLiveData(listOf())

    fun onCreate(context: Context) {
        val repository = Repository(context)
        val getHero = GetHero(repository)
        val file = File(context.filesDir, FILE)

        viewModelScope.launch {
            if (file.exists()) {
                listOfDota = getHero.getAPIList()
                delay(1000L)
                getHero.getFile()
            }
            else {
                listOfDota = getHero.getFileList()
            }
        }
    }
}