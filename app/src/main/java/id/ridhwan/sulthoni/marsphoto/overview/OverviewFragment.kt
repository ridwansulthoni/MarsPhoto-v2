package id.ridhwan.sulthoni.marsphoto.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import id.ridhwan.sulthoni.marsphoto.databinding.FragmentOverviewBinding

//fragmen ini menunjukkan status transakis layanan foto mars
class OverviewFragment : Fragment() {

    private val viewModel: OverviewViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentOverviewBinding.inflate(inflater)
        //memungkin data binding untuk mengamati LiveData dengan siklus Fragmen
        binding.lifecycleOwner = this
        //memberikan akses binding ke OverviewViewModel
        binding.viewModel = viewModel
        //setting adaptor photogrid
        binding.photosGrid.adapter = PhotoGridAdapter()
        return binding.root
    }
}
