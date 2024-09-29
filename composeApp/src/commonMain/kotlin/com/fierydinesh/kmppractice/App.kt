package com.fierydinesh.kmppractice

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fierydinesh.kmppractice.model.ProductX
import com.fierydinesh.kmppractice.ui.ProductDetailsScreen
import com.fierydinesh.kmppractice.ui.ProductItem
import com.fierydinesh.kmppractice.ui.SearchScreen
import com.fierydinesh.kmppractice.utils.toggleiOSProductDetails
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        ProductScreen()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductScreen(productViewModel: ProductScreenViewModel = viewModel { ProductScreenViewModel() }) {
    val products = productViewModel.products.collectAsState()

    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }

    BoxWithConstraints {
        val cols = if (maxWidth > 840.dp) 3 else 2
        val scrollState = rememberLazyGridState()

        val onClickProduct: (ProductX) -> Unit = { product ->
            if (getPlatformType() == PlatformType.IOS) {
                //only for iOS :)
                toggleiOSProductDetails?.invoke(product)
            } else {
                if ((product.price ?: 0.0) > 30.0) {
                    //for testing purpose :)
                    productViewModel.selectedProduct = product
                } else productViewModel.selectedProduct = null
                showBottomSheet = true
            }
        }

        if (getPlatformType() != PlatformType.IOS) {
            if (showBottomSheet) {
                ModalBottomSheet(
                    modifier = Modifier
                        .fillMaxHeight(0.5f),
                    containerColor = Color.White,
                    onDismissRequest = {
                        showBottomSheet = false
                    },
                    sheetState = sheetState,
                    windowInsets = WindowInsets(bottom = 0.dp)
                ) {
                    productViewModel.selectedProduct?.let {
                        ProductDetailsScreen(it)
                    } ?: run {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                modifier = Modifier.padding(vertical = 20.dp),
                                text = "Product Not Found.",
                                fontWeight = FontWeight.Bold,
                                fontSize = 24.sp
                            )
                            val brush = Brush.linearGradient(listOf(Color.Magenta, Color.Red))
                            Button(
                                modifier = Modifier
                                    .padding(horizontal = 8.dp)
                                    .fillMaxWidth()
                                    .background(brush, shape = ButtonDefaults.shape),
                                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                                onClick = {
                                    scope.launch {
                                        sheetState.hide()
                                    }.invokeOnCompletion {
                                        if (!sheetState.isVisible) {
                                            showBottomSheet = false
                                        }
                                    }
                                }
                            ) {
                                Text("Click to Close", fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                }
            }
        }

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            SearchScreen(
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                products = products.value
            )

            LazyVerticalGrid(
                columns = GridCells.Fixed(cols),
                state = scrollState,
                contentPadding = PaddingValues(16.dp)
            ) {
                items(
                    items = products.value,
                    key = { product -> product.id.toString() }) { product ->
                    ProductItem(product = product, onClick = onClickProduct)
                }
            }
        }
    }
}