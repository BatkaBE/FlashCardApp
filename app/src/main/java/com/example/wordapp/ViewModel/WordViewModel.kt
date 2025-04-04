package com.example.wordapp.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wordapp.data.Repository.Repository
import com.example.wordapp.data.SettingsDataStore
import com.example.wordapp.data.room.Word
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WordViewModel(private val repository: Repository, private val dataStore: SettingsDataStore) : ViewModel() {
    val words: Flow<List<Word>> = repository.getAllWordsFromRoom()

    private val _selectedOption = MutableStateFlow("Монгол болон гадаад үгийг зэрэг харуулах")
    val selectedOption: StateFlow<String> = _selectedOption

    init {
        viewModelScope.launch {
            dataStore.selectedOption.collect { option ->
                _selectedOption.value = option
            }
        }
    }

    fun setSelectedOption(option: String) {
        viewModelScope.launch {
            dataStore.saveSelectedOption(option)
        }
    }

    fun addWord(word: Word) {
        viewModelScope.launch { repository.addWord(word) }
    }

    fun updateWord(word: Word) {
        viewModelScope.launch { repository.updateWord(word) }
    }

    fun deleteWord(word: Word) {
        viewModelScope.launch { repository.deleteWord(word) }
    }
}
