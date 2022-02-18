package api

import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class BeerInfo {

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    var beerName = mutableStateOf("")
    var beerDesc = mutableStateOf("")
    var beerFirstMade = mutableStateOf("")
    var beerImage =
        mutableStateOf<String?>("https://upload.wikimedia.org/wikipedia/commons/thumb/a/ac/No_image_available.svg/300px-No_image_available.svg.png")

    init {
        getTheBeers()
    }

    fun getTheBeers() {
        coroutineScope.launch {
            val result = RetrofitClient.retrofit.loadImages()
            beerName.value = result.first().name
            beerDesc.value = result.first().description
            beerFirstMade.value = result.first().first_brewed
            beerImage.value = result.first().image_url
                ?: "https://upload.wikimedia.org/wikipedia/commons/thumb/a/ac/No_image_available.svg/300px-No_image_available.svg.png"
        }
    }
}