package id.ridhwan.sulthoni.marsphoto

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import id.ridhwan.sulthoni.marsphoto.network.MarsPhoto
import id.ridhwan.sulthoni.marsphoto.overview.MarsApiStatus
import id.ridhwan.sulthoni.marsphoto.overview.PhotoGridAdapter

//ini digunakan untuk memperbarui data yang ditampilkan pada recyclerview
@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView,
                     data: List<MarsPhoto>?) {
    val adapter = recyclerView.adapter as PhotoGridAdapter
    adapter.submitList(data)
}

//menggunakan libery coli untuk memuat gambar dengan url ke dalam imageview
@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        imgView.load(imgUri) {
            placeholder(R.drawable.loading_animation)
            error(R.drawable.ic_broken_image)
        }
    }
}

/**
 * adaptor ini menampilkan MarsApiStatus dari permintaan jaringan dalam menampilkan gambar
 * terdapat beberapa saat terjadi, ketika sedang loading maka akan menampilkan loading_animation
 * ketika permintaan terjadi error maka akan menampilkan gambar rusak
 * dan terakhir ketika permintaan done maka akan menyembunyikan tampilan gambar
 */
@BindingAdapter("marsApiStatus")
fun bindStatus(statusImageView: ImageView,
               status: MarsApiStatus?) {
    when (status) {
        MarsApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        MarsApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        MarsApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}
