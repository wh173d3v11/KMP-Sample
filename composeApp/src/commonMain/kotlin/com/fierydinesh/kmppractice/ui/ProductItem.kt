package com.fierydinesh.kmppractice.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.fierydinesh.kmppractice.generateImageLoader
import com.fierydinesh.kmppractice.model.ProductX
import com.fierydinesh.kmppractice.repo.ImageWithMockPreview
import com.seiko.imageloader.rememberImagePainter

@Composable
fun ProductItem(product: ProductX, onClick: (ProductX) -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable { onClick.invoke(product) },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 4.dp,
            pressedElevation = 4.dp
        ),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            ImageWithMockPreview(
                painter = rememberImagePainter(
                    url = product.image ?: "",
                    imageLoader = remember { generateImageLoader() }
                ),
                modifier = Modifier
                    .height(130.dp)
                    .padding(8.dp),
                alignment = Alignment.Center,
                contentDescription = product.title
            )

            Text(
                modifier = Modifier.padding(8.dp),
                text = product.title ?: "No Title",
                maxLines = 2,
                minLines = 2,
                textAlign = TextAlign.Center,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                modifier = Modifier.padding(horizontal = 8.dp).padding(bottom = 8.dp),
                text = "$${product.price}",
                maxLines = 1,
                fontWeight = FontWeight.Bold,
                color = Color.DarkGray,
                textAlign = TextAlign.Center,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
fun ProductSearchItem(product: ProductX) {
    Card(
        modifier = Modifier.padding(vertical = 8.dp).fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 4.dp,
            pressedElevation = 4.dp
        ),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {

        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            ImageWithMockPreview(
                painter = rememberImagePainter(
                    url = product.image ?: "",
                    imageLoader = remember { generateImageLoader() }
                ),
                modifier = Modifier
                    .height(130.dp)
                    .fillMaxWidth(0.3f)
                    .padding(8.dp),
                alignment = Alignment.Center,
                contentDescription = product.title
            )
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = product.title ?: "No Title",
                    maxLines = 2,
                    minLines = 2,
                    textAlign = TextAlign.Start,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    modifier = Modifier.padding(horizontal = 8.dp).padding(bottom = 8.dp),
                    text = "$${product.price}",
                    maxLines = 1,
                    fontWeight = FontWeight.Bold,
                    color = Color.DarkGray,
                    textAlign = TextAlign.Center,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Composable
fun ProductDetailsScreen(product: ProductX) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(8.dp),
            text = product.title ?: "No Title",
            maxLines = 3,
            minLines = 2,
            textAlign = TextAlign.Center,
            overflow = TextOverflow.Ellipsis
        )

        ImageWithMockPreview(
            painter = rememberImagePainter(
                url = product.image ?: "",
                imageLoader = remember { generateImageLoader() }
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(130.dp)
                .padding(8.dp),
            alignment = Alignment.Center,
            contentDescription = product.title
        )

        Text(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .padding(bottom = 8.dp),
            text = "${product.description}",
            maxLines = 3,
            fontWeight = FontWeight.Normal,
            color = Color.DarkGray,
            textAlign = TextAlign.Center,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .padding(bottom = 8.dp),
            text = "$${product.price}",
            maxLines = 1,
            fontWeight = FontWeight.Bold,
            color = Color.DarkGray,
            textAlign = TextAlign.Center,
            fontSize = MaterialTheme.typography.headlineMedium.fontSize,
            overflow = TextOverflow.Ellipsis
        )
        val brush = Brush.linearGradient(listOf(Color.Yellow, Color.Red))
        Button(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .fillMaxWidth()
                .background(brush, shape = ButtonDefaults.shape),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
            onClick = {}
        ) {
            Text("Buy Now", fontWeight = FontWeight.Bold)
        }
    }
}