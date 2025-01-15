package com.example.fetchevaluation.ui.theme

import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
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

    // Group the item list by listID first. Sort it by listId. Then sort the individual listId lists based on their ids.
    val groupedItems = filteredItems.groupBy{it.listId}.mapValues{ entry -> entry.value.sortedBy { it.id } }.toSortedMap()

    // Display the Grouped items
    MainScreenContent(groupedItems)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreenContent(groupedItems: Map<Int, List<Item>>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
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
        LazyColumn(
            modifier = Modifier.padding(16.dp)
        ) {
            groupedItems.forEach { (listID, itemList) ->
                //Experimental api usage for cosmetic purpose
                stickyHeader {
                    Column {
                        Text(
                            text = "List ID: $listID",
                            style = TextStyle(fontWeight = FontWeight.Bold),
                            modifier = Modifier
                                .background(Color.LightGray)
                                .fillMaxWidth(),
                            fontSize = 16.sp,
                        )
                        ItemHeader()
                    }
                }
                items(itemList) { item ->
                    item.name?.takeIf { it.isNotEmpty() }?.let { name ->
                        ItemRow(item, name)
                    }
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
