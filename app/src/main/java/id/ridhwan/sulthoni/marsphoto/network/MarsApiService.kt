package id.ridhwan.sulthoni.marsphoto.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

//menambahkan URL dasar untuk layanan web.
private const val BASE_URL = "https://android-kotlin-fun-mars-server.appspot.com"

//Buat objek moshi dengan adaptor kotlin, yang akan digunakan Retrofit
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

/**
 * Buat objek retrofit dengan menggunakan MoshiConverterFactory
 * URI dasar untuk layanan web menggunakan metode base url
 */
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

//Interface publik yang digunakan untuk mengekspos metode getPhotos
interface MarsApiService {
    /**
     * Retrofit untuk menampilkan daftar objek MarsPhoto dari array JSON
     * Gunakan anotasi @GET untuk memberi tahu Retrofit bahwa ini adalah permintaan GET
     * Dan menunjukkan bahwa titik akhir "foto" akan diminta dengan GET
     * Metode HTTP
     */
    @GET("photos")
    suspend fun getPhotos() : List<MarsPhoto>
}

/**
 * Objek publik MarsApi yang mengekspos layanan Retrofit
 * Inisialisasi variabel retrofitService
 * Menggunakan metode retrofit.create() dengan antarmuka MarsApiService
 */
object MarsApi {
    val retrofitService: MarsApiService by lazy { retrofit.create(MarsApiService::class.java) }
}
