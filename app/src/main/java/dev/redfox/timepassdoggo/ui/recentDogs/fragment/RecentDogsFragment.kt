package dev.redfox.timepassdoggo.ui.recentDogs.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.HORIZONTAL
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import dev.redfox.timepassdoggo.data.viewmodels.RecentDogsViewModel
import dev.redfox.timepassdoggo.databinding.FragmentRecentDogsBinding
import dev.redfox.timepassdoggo.ui.recentDogs.adapter.RecentDogsAdapter
import dev.redfox.timepassdoggo.utils.hide
import dev.redfox.timepassdoggo.utils.setOnSingleClickListener
import dev.redfox.timepassdoggo.utils.show

@AndroidEntryPoint
class RecentDogsFragment : Fragment() {

    private val binding: FragmentRecentDogsBinding by lazy {
        FragmentRecentDogsBinding.inflate(layoutInflater)
    }
    private val viewModel by viewModels<RecentDogsViewModel>()
    private val adapter by lazy { RecentDogsAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        setupClickListeners()
        setupObservers()
    }

    private fun initUI() {
        binding.rvRecentDogs.apply {
            adapter = this@RecentDogsFragment.adapter
            layoutManager = LinearLayoutManager(requireContext(), HORIZONTAL, false)
            scrollToPosition(0)
        }
    }

    private fun setupClickListeners() {
        binding.btnClearDogs.setOnSingleClickListener {
            viewModel.clearDogs()
            Snackbar.make(binding.root, "All Dog Images Cleared", Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun setupObservers() {
        viewModel.dogImagesObserver.observe(viewLifecycleOwner) {
            updateContentVisibility(it.isNotEmpty())
            adapter.submitList(it.reversed())
        }
    }

    private fun updateContentVisibility(isContentVisible: Boolean) {
        binding.apply {

            if (isContentVisible) {
                tvErrorMessage.hide()
                rvRecentDogs.show()
                btnClearDogs.show()
            } else {
                rvRecentDogs.hide()
                btnClearDogs.hide()
                tvErrorMessage.show()
            }
        }
    }
}