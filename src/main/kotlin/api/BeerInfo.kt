package api

import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class BeerInfo {

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    var beerName = mutableStateOf("")
    var beerFirstMade = mutableStateOf("")
    var beerDesc = mutableStateOf("")
    var beerTagline = mutableStateOf("")
    var beerImage = mutableStateOf<String?>("https://upload.wikimedia.org/wikipedia/commons/thumb/a/ac/No_image_available.svg/300px-No_image_available.svg.png")

    init {
        getTheBeers()
    }

    fun getTheBeers() {
        coroutineScope.launch {
            val result = RetrofitClient.retrofit.loadImages()
            for (beerItem in result){
                beerName.value = beerItem.name
                beerFirstMade.value = beerItem.first_brewed
                beerDesc.value = beerItem.description
                beerTagline.value = beerItem.tagline
                beerImage.value = beerItem.image_url
                    ?: "https://upload.wikimedia.org/wikipedia/commons/thumb/a/ac/No_image_available.svg/300px-No_image_available.svg.png"
            }
        }
    }
}