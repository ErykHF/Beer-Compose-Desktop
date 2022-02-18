import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.*
import api.BeerInfo
import utils.Util.loadNetworkImage


@Composable
fun BeerGeneratorScreen(beerInfo: BeerInfo = BeerInfo()) {

    val beerName by remember { beerInfo.beerName }
    val beerFirstMade by remember { beerInfo.beerFirstMade }
    val beerTagline by remember { beerInfo.beerTagline }
    val beerDesc by remember { beerInfo.beerDesc }
    val beerImage by remember { beerInfo.beerImage }
    val refreshBeers = remember { beerInfo }

    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Column(modifier = Modifier.align(Alignment.Center)) {
            val imageModifier = Modifier
                .height(240.dp)
                .fillMaxWidth()
                .padding(top = 16.dp)
            Image(
                bitmap = loadNetworkImage(beerImage),
                contentDescription = "image",
                imageModifier,
                contentScale = ContentScale.Fit
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = beerName,
                modifier = Modifier.align(Alignment.CenterHorizontally).padding(2.dp),
                textAlign = TextAlign.Center
            )
            Text(
                text = beerTagline,
                modifier = Modifier.align(Alignment.CenterHorizontally).padding(2.dp),
                textAlign = TextAlign.Center
            )
            Text(
                text = beerFirstMade,
                modifier = Modifier.align(Alignment.CenterHorizontally).padding(2.dp),
                textAlign = TextAlign.Center
            )
            Text(
                text = beerDesc,
                modifier = Modifier.align(Alignment.CenterHorizontally)
                    .padding(top = 2.dp, bottom = 20.dp, start = 16.dp, end = 16.dp),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(32.dp))
            Button(onClick = {
                refreshBeers.getTheBeers()
            }, modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Text("Generate")
            }
        }
    }
}