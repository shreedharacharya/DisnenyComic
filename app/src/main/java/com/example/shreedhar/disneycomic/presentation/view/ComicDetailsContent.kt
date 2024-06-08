package com.example.shreedhar.disneycomic.presentation.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlurEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size.Companion.ORIGINAL
import com.example.shreedhar.disneycomic.app.theme.DisneyComicTheme
import com.example.shreedhar.disneycomic.data.model.ComicDetailsDto

@Composable
fun ComicDetailsContent(
    dto: ComicDetailsDto,
    modifier: Modifier = Modifier
) {
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(dto.coverImageUrl)
            .size(ORIGINAL)
            .build(),
        contentScale = ContentScale.FillBounds
    )

    Box(modifier = Modifier.fillMaxSize()) {

        Image(
            painter = painter,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer {
                    renderEffect = BlurEffect(400f, 400f)
                }
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState())
                .background(Color.Transparent)
        ) {

            MyCard(
                modifier = modifier
                    .padding(top = 40.dp)
                    .height(300.dp)
                    .width(200.dp)
                    .align(Alignment.CenterHorizontally)

            ) {
                Image(
                    painter = painter,
                    contentDescription = "cover image",
                    contentScale = ContentScale.FillBounds,
                )
            }
            Text(
                text = dto.title,
                fontSize = 16.sp,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
                color = Color.White,
                modifier = modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth()
            )

            if (dto.description.isNotEmpty()) {
                var showAll by remember { mutableStateOf(false) }
                Text(
                    text = dto.description.let {
                        if (it.length > 200) {
                            return@let if (showAll) it else "${it.substring(0, 200)}..."
                        }
                        it
                    },
                    fontSize = 13.sp,
                    fontFamily = FontFamily.SansSerif,
                    textAlign = TextAlign.Left,
                    color = Color.White,
                    modifier = modifier
                        .padding(top = 8.dp)
                        .fillMaxWidth()
                        .clickable {
                            showAll = !showAll
                        }
                )
            }

            LazyRow(
                modifier = modifier
                    .padding(vertical = 16.dp)
                    .align(Alignment.CenterHorizontally),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(dto.images) { imageUrl ->
                    MyCard(
                        modifier = modifier
                            .height(200.dp)
                            .width(130.dp)
                    ) {
                        AsyncImage(
                            model = imageUrl,
                            contentDescription = null,
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun CharacterDetailScreenPreview() {
    DisneyComicTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            ComicDetailsContent(
                ComicDetailsDto(
                    id = 1,
                    title = "Title",
                    description = "Description",
                    coverImageUrl = "https://example.com/image.jpg"
                )
            )
        }
    }
}