package dev.redfox.timepassdoggo.data.viewmodels

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.redfox.timepassdoggo.network.DogsRepository
import dev.redfox.timepassdoggo.utils.Resource
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenerateDogsViewModel @Inject constructor(
    private val repository: DogsRepository
) : ViewModel() {

    private val _randomImg = MutableLiveData<Resource<Bitmap>>()
    val randomImg: LiveData<Resource<Bitmap>> = _randomImg

    fun getRandomDogImage() = viewModelScope.launch {
        _randomImg.postValue(Resource.Loading())
        try {
            val url = repository.getRandomDogImgUrl()

            val bmp = repository.getBitmapFromUrl(url)
            if (bmp == null) {
                _randomImg.postValue(Resource.Error("Bitmap is null"))
                return@launch
            }
            _randomImg.postValue(Resource.Success(bmp))
        } catch (e: Exception) {
            _randomImg.postValue(Resource.Error(e.localizedMessage))
        }
    }
}