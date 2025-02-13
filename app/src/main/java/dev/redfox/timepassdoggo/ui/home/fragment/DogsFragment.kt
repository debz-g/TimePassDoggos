package dev.redfox.timepassdoggo.ui.home.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.redfox.timepassdoggo.R
import dev.redfox.timepassdoggo.databinding.FragmentDogsBinding

@AndroidEntryPoint
class DogsFragment : Fragment() {

    private val binding: FragmentDogsBinding by lazy {
        FragmentDogsBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
    }

    private fun setupClickListeners() {
        binding.apply {
            btnGenerateDogsFragment.setOnClickListener {
                findNavController().navigate(R.id.action_dogsFragment_to_generateDogsFragment)
            }

            btnRecentDogsFragment.setOnClickListener {
                findNavController().navigate(R.id.action_dogsFragment_to_recentDogsFragment)
            }
        }
    }
}