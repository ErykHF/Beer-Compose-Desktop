// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.material.MaterialTheme
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
@Preview
fun App(beerInfo: BeerInfo = BeerInfo()) {


    val coroutineScope = rememberCoroutineScope()

    val beerName by remember { beerInfo.beerName }
    val beerDesc by remember { beerInfo.beerDesc }
    val beerFirstMade by remember { beerInfo.beerFirstMade }
    val beerImage by remember { beerInfo.beerImage }
    val refreshBeers = remember { beerInfo }

    MaterialTheme {


        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            Column(modifier = Modifier.align(Alignment.Center)) {
                val imageModifier = Modifier
                    .height(240.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))



                    Image(
                        bitmap = loadNetworkImage(beerImage),
                        contentDescription = "image",
                        imageModifier,
                        contentScale = ContentScale.Fit
                    )


                Spacer(modifier = Modifier.height(16.dp))
                Text(text = beerName, modifier = Modifier.align(Alignment.CenterHorizontally).padding(6.dp))
                Text(
                    text = beerDesc,
                    modifier = Modifier.align(Alignment.CenterHorizontally).padding(6.dp),
                    textAlign = TextAlign.Center
                )
                Text(text = beerFirstMade, modifier = Modifier.align(Alignment.CenterHorizontally).padding(6.dp))
                Spacer(modifier = Modifier.height(16.dp))
            }
            Button(onClick = {
                refreshBeers.getTheBeers()
            }, modifier = Modifier.align(Alignment.BottomCenter)) {
                Text("Generate")
            }
        }
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}
