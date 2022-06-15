package science.example.week7dota.model

import androidx.lifecycle.LiveData
import science.example.week7dota.data.ApiInterface

class GetHero(private val repository: ApiInterface) {

    fun getAPIList(): LiveData<List<HeroDota>> {
        return repository.getAPIList()
    }

    fun getFileList(): LiveData<List<HeroDota>> {
        return repository.getFileList()
    }

    fun getFile() {
        return repository.getFile()
    }
}