package dev.redfox.timepassdoggo.data.viewmodels

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.redfox.timepassdoggo.network.DogsRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecentDogsViewModel @Inject constructor(
    private val repository: DogsRepository
) : ViewModel() {

    private val _dogImages = MutableLiveData<List<Bitmap>>()
    val dogImagesObserver: LiveData<List<Bitmap>> = _dogImages

    init {
        getRecentlyViewedDogBitmaps()
    }

    private fun getRecentlyViewedDogBitmaps() = viewModelScope.launch {
        val bitmaps = repository.getAllBitmapsFromCache()
        _dogImages.postValue(bitmaps)
    }

    fun clearDogs() {
        repository.clearDogImagesCache()
        getRecentlyViewedDogBitmaps()
    }
}