package id.ridhwan.sulthoni.marsphoto.network

import com.squareup.moshi.Json

/**
 * kelas data ini mendefinisikan foto mars yang menyertakan id dan url gambar
 * nama properti ini digunakan kelas data ini untuk digunakan oleh moshi
 * untuk mencocokan nama nilai di json
 */
data class MarsPhoto(
    val id: String,
    //digunakan untuk memetakan img_src dari json ke imgSrcUrl
    @Json(name = "img_src") val imgSrcUrl: String
)