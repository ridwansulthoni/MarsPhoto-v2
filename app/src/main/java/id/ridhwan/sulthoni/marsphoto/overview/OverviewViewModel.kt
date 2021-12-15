package id.ridhwan.sulthoni.marsphoto.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.ridhwan.sulthoni.marsphoto.network.MarsApi
import id.ridhwan.sulthoni.marsphoto.network.MarsPhoto
import kotlinx.coroutines.launch

enum class MarsApiStatus { LOADING, ERROR, DONE }

//OverviewViewModel yang dilampirkan ViewModel
class OverviewViewModel : ViewModel() {
    //MutableLiveData secara internal ini digunakan untuk menyimpan status permintaan terbaru
    private val _status = MutableLiveData<MarsApiStatus>()
    //LiveData secara external ini tidak dapat merubah status permintaan
    val status: LiveData<MarsApiStatus> = _status

    //MutableLiveData secara internal ini digunakan untuk memperbarui daftar MarsPhoto
    private val _photos = MutableLiveData<List<MarsPhoto>>()
    //LiveData secara external ini tidak dapat merubah
    val photos: LiveData<List<MarsPhoto>> = _photos

    //dengan memanggil getMarsPhoto pada init dapat segera menampilkan status
    init {
        getMarsPhotos()
    }

    //mendaptkan informasi foto Mars dari layanan Mars Api dan memperbaruinya
    private fun getMarsPhotos() {
        viewModelScope.launch {
            _status.value = MarsApiStatus.LOADING
            try {
                _photos.value = MarsApi.retrofitService.getPhotos()
                _status.value = MarsApiStatus.DONE
            } catch (e: Exception) {
                _status.value = MarsApiStatus.ERROR
                _photos.value = listOf()
            }
        }
    }
}

