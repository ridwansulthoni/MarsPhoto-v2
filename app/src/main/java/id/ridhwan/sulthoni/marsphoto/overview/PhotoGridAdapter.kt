package id.ridhwan.sulthoni.marsphoto.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import id.ridhwan.sulthoni.marsphoto.databinding.GridViewItemBinding
import id.ridhwan.sulthoni.marsphoto.network.MarsPhoto

/**
 *kelas ini mengimplementasikan recyclerview dan listadapter
 *menggunakan data binding untuk menampilkan list data
 */
class PhotoGridAdapter : ListAdapter<MarsPhoto,
        PhotoGridAdapter.MarsPhotoViewHolder>(DiffCallback) {
    /**
     * MarsPhotoViewHolder ini mengambil variable binding yang terkait
     * GridView ini memberikan akses ke informasi MarsPhoto yang lengkap
     */
    class MarsPhotoViewHolder(private var binding:
                              GridViewItemBinding
    ):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(MarsPhoto: MarsPhoto) {
            binding.photo = MarsPhoto
            /**
             * penggunaan data binding ini untuk segera dieksekusi
             * yang memungkinkan recyclerview membuat pengukuran ukuran tampilan
             * yang benar
             */
            binding.executePendingBindings()
        }

    }

    /**
     * RecyclerView untuk menentukan item mana yang telah berubah saat menjadi list
     * dari MarsPhoto yang telah diperbarui
     */
    companion object DiffCallback : DiffUtil.ItemCallback<MarsPhoto>() {
        override fun areItemsTheSame(oldItem: MarsPhoto, newItem: MarsPhoto): Boolean {
            return oldItem.id == newItem.id
        }
        override fun areContentsTheSame(oldItem: MarsPhoto, newItem: MarsPhoto): Boolean {
            return oldItem.imgSrcUrl == newItem.imgSrcUrl
        }
    }

    //menampilkan item recyclervie yang baru
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoGridAdapter.MarsPhotoViewHolder {
        return MarsPhotoViewHolder(GridViewItemBinding.inflate(
            LayoutInflater.from(parent.context)))
    }

    //mengganti konten tampilan
    override fun onBindViewHolder(holder: PhotoGridAdapter.MarsPhotoViewHolder, position: Int) {
        val marsPhoto = getItem(position)
        holder.bind(marsPhoto)
    }

}
