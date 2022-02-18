import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import api.RetrofitClient
import io.ktor.client.*
import io.ktor.client.request.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import model.BeerData
import org.jetbrains.skia.Bitmap
import org.jetbrains.skia.Image
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream
import java.net.HttpURLConnection
import java.net.URL
import javax.imageio.ImageIO



class BeerInfo {

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    var beerName = mutableStateOf("")
    var beerDesc = mutableStateOf("")
    var beerFirstMade = mutableStateOf("")
    var beerImage = mutableStateOf<String?>("https://upload.wikimedia.org/wikipedia/commons/thumb/a/ac/No_image_available.svg/300px-No_image_available.svg.png")

    var bitMapImage = mutableStateOf<ImageBitmap?>(null)

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

//           bitMapImage.value = loadNetworkImage(result.first().image_url ?: "https://upload.wikimedia.org/wikipedia/commons/thumb/a/ac/No_image_available.svg/300px-No_image_available.svg.png")

        }
    }

//    fun getTheBeersCall() {
//
//        val callPunkApi: Call<List<BeerData>> = RetrofitClient.retrofit.loadImages()
//
//        callPunkApi.enqueue(object : Callback<List<BeerData>> {
//            override fun onResponse(call: Call<List<BeerData>>, response: Response<List<BeerData>>) {
//                response.let {
//                    val result: List<BeerData> = response.body()!!
//                    beerName.value = result.first().name
//                    beerDesc.value = result.first().description
//                    beerFirstMade.value = result.first().first_brewed
//                    beerImage.value = result.first().image_url
//                        ?: "https://upload.wikimedia.org/wikipedia/commons/thumb/a/ac/No_image_available.svg/300px-No_image_available.svg.png"
//
//                }
//            }
//
//            override fun onFailure(call: Call<List<BeerData>>, t: Throwable) {
//                TODO("Not yet implemented")
//            }
//
//        })
//    }

}

fun loadNetworkImage(link: String?): ImageBitmap {
    val url = URL(link)
    val connection = url.openConnection() as HttpURLConnection
    connection.connect()

    val inputStream = connection.inputStream
    val bufferedImage = ImageIO.read(inputStream)

    val stream = ByteArrayOutputStream()
    ImageIO.write(bufferedImage, "png", stream)
    val byteArray = stream.toByteArray()

    return Image.makeFromEncoded(byteArray).asImageBitmap()
}