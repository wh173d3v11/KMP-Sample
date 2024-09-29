package com.fierydinesh.kmppractice.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.fierydinesh.kmppractice.model.ProductX

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(modifier: Modifier, products: List<ProductX>) {
    //for search
    var text by rememberSaveable { mutableStateOf("") }
    var active by rememberSaveable { mutableStateOf(false) }

    val filteredList = text.let { query ->
        products.takeIf { query.isNotEmpty() }
            ?.filter {
                it.title?.contains(query, ignoreCase = true) ?: false
            } ?: listOf()
    }

    val onClickClose = {
        text = ""
        active = false
    }

    SearchBar(
        modifier = modifier,
        query = text,
        onQueryChange = {
            text = it
        },
        active = active,
        onSearch = {
            active = false
        },
        onActiveChange = {
            active = it
        },
        trailingIcon = {
            if (active) {
                Icon(
                    modifier = Modifier.clickable {
                        onClickClose.invoke()
                    },
                    imageVector = Icons.Rounded.Close,
                    contentDescription = "Close"
                )
            } else {
                Icon(
                    imageVector = Icons.Rounded.Search,
                    contentDescription = "Search"
                )
            }
        },
        placeholder = { Text("Search Product...") }
    ) {
        if (text.isNotEmpty() && filteredList.isEmpty()) {
            Text("No products found...")
        } else if (filteredList.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.White),
                contentPadding = PaddingValues(16.dp),
                state = rememberLazyListState()
            ) {
                items(
                    filteredList, key = { item -> item.id?.toString() ?: "" }
                ) { item ->
                    ProductSearchItem(item)
                }
            }
        }
    }
}