package science.example.week7dota.data

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.*
import science.example.week7dota.model.HeroDota
import java.io.File
import java.io.IOException

interface ApiInterface {

    fun getAPIList(): LiveData<List<HeroDota>>
    fun getFileList(): LiveData<List<HeroDota>>
    fun getFile()
}

class Repository (context: Context) : ApiInterface{

    companion object {
        const val PHOTO_URL = "https://api.opendota.com"
        const val URL = "https://api.opendota.com/api/heroStats"
        const val FILE = "FileDota.txt"
    }

    private val client = OkHttpClient()
    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    private val listType = Types.newParameterizedType(List::class.java, HeroDota::class.java)
    private val jsonAdapter: JsonAdapter<List<HeroDota>> = moshi.adapter(listType)
    private val heroList = MutableLiveData<List<HeroDota>>()
    private val file = File(context.filesDir, FILE)

    override fun getAPIList(): LiveData<List<HeroDota>> {
        val request = Request.Builder()
            .url(URL)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                heroList.postValue(jsonAdapter.fromJson(response.body!!.string()))
            }

            override fun onFailure(call: Call, e: IOException) {
            }
        })
        return heroList
    }

    override fun getFileList(): LiveData<List<HeroDota>> {
        heroList.postValue(jsonAdapter.fromJson(file.readText()))
        return heroList
    }

    override fun getFile() {
        val dotaData = jsonAdapter.toJson(heroList.value)
        file.writeText(dotaData)
    }
}