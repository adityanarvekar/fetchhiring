package com.example.fetchevaluation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fetchevaluation.api.ApiService
import com.example.fetchevaluation.model.Item
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ItemViewModel : ViewModel() {
    private val _items = MutableStateFlow<List<Item>>(emptyList())
    val items: StateFlow<List<Item>> = _items
    init {
        fetchItems()
    }
    private fun fetchItems() {
        viewModelScope.launch {
            val apiService = ApiService.create()
            val fetchedItems = apiService.getItems()
            _items.value = fetchedItems
        }
    }
}