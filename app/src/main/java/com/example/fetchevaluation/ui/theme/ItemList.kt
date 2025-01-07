package com.example.fetchevaluation.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import com.example.fetchevaluation.viewmodel.ItemViewModel
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fetchevaluation.model.Item

@Composable
fun ItemListScreen(itemViewModel: ItemViewModel = viewModel()) {
    val items = itemViewModel.items.collectAsState().value
    // Filter empty or null names
    val filteredItems = items.filter { !it.name.isNullOrEmpty() }
    // Sort the list based on listId first and then by name
    val sortedItems = filteredItems.sortedWith(compareBy<Item> { it.listId }
        .thenBy { it.name })
    // Display the Items
    MainScreenContent(sortedItems)
}

@Composable
fun MainScreenContent(sortedItems: List<Item>) {
    Column(
        modifier = Modifier.fillMaxSize()
            .padding(
                top = 50.dp,
                bottom = 30.dp,
                )
    ) {
        Text(
            text = "Content",
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 8.dp,
                ),
            fontSize = 20.sp,
        )
        ItemHeader()
        LazyColumn {
            items(sortedItems) { item ->
                item.name?.takeIf { it.isNotEmpty() }?.let {
                    ItemRow(item, it)
                }
            }
        }
    }
}

@Composable
fun ItemHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF2196F3)),
        horizontalArrangement = Arrangement.Start,
    ) {
        Text(
            text = "ID",
            modifier = Modifier
                .weight(1.1f)
                .padding(start = 8.dp),
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.bodyLarge,
            color = Color.White,
        )
        Text(
            text = "List ID",
            modifier = Modifier.weight(2f),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge,
            color = Color.White,
        )
        Text(
            text = "Item Name",
            modifier = Modifier
                .weight(7.9f)
                .padding(end = 8.dp),
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.bodyLarge,
            color = Color.White,
        )
    }
}

@Composable
fun ItemRow(item: Item, name: String) {
    Column(modifier = Modifier.wrapContentSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.Start,
        ) {
            Text(
                text = "${item.id}",
                modifier = Modifier.weight(1.1f),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Black,
            )
            Text(
                text = "${item.listId}",
                modifier = Modifier.weight(2f),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Black,
            )
            Text(
                text = name,
                modifier = Modifier.weight(7.9f),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Black,
            )
        }
        Divider(color = Color.LightGray, thickness = 1.dp)
    }
}
