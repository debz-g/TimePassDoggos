package dev.redfox.timepassdoggo.ui.generateDogs.fragment

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import dev.redfox.timepassdoggo.data.viewmodels.GenerateDogsViewModel
import dev.redfox.timepassdoggo.databinding.FragmentGenerateDogsBinding
import dev.redfox.timepassdoggo.utils.Resource
import dev.redfox.timepassdoggo.utils.hide
import dev.redfox.timepassdoggo.utils.setOnSingleClickListener
import dev.redfox.timepassdoggo.utils.show

@AndroidEntryPoint
class GenerateDogsFragment : Fragment() {
    private val binding: FragmentGenerateDogsBinding by lazy {
        FragmentGenerateDogsBinding.inflate(layoutInflater)
    }
    private val viewModel by viewModels<GenerateDogsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
        initObservers()
    }

    private fun initObservers() {
        viewModel.randomImg.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> setupSuccess(it.data)
                is Resource.Loading -> setupLoading(true)
                is Resource.Error -> setupError(it.message)
            }

        }
    }

    private fun initListeners() {
        binding.btnGenerateDogs.setOnSingleClickListener {
            viewModel.getRandomDogImage()
        }
    }

    private fun setupSuccess(data: Bitmap?) {
        setupLoading(false)
        data?.let {
            binding.ivGenerateDogs.setImageBitmap(it)
        }
    }

    private fun setupLoading(isLoading: Boolean) {
        binding.apply {
            if (isLoading) {
                ivGenerateDogs.hide()
                pbGenerateDogs.show()
            } else {
                pbGenerateDogs.hide()
                ivGenerateDogs.show()
            }
        }
    }

    private fun setupError(message: String?) {
        setupLoading(false)
        Snackbar.make(binding.root, message ?: "Something Went Wrong", Snackbar.LENGTH_SHORT).show()
    }
}