package science.example.week7dota.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class HeroDota(
    @Json(name = "id")
    val id: Int,
    @Json(name = "localized_name")
    val localizedName: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "img")
    val img: String,
    @Json(name = "icon")
    val icon: String,
    @Json(name = "attack_type")
    val attackType: String,
    @Json(name = "primary_attr")
    val primaryAttr: String,
    @Json(name = "base_str")
    val baseStr: String,
    @Json(name = "base_agi")
    val baseAgi: String,
    @Json(name = "base_int")
    val baseInt: String
) : Parcelable
