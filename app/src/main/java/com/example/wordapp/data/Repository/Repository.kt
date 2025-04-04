package com.example.wordapp.data.Repository

import com.example.wordapp.data.room.Word
import com.example.wordapp.data.room.WordDB
import kotlinx.coroutines.flow.Flow

class Repository(private val WordDB:WordDB) {
    suspend fun  addWord(word: Word){
        WordDB.wordAppDao().addWord(word)
    }
    suspend fun  updateWord(word: Word){
        WordDB.wordAppDao().updateWord(word)
    }

    suspend fun  deleteWord(word: Word){
        WordDB.wordAppDao().deleteWord(word)
    }

    fun getAllWordsFromRoom():Flow<List<Word>>{
        return  WordDB.wordAppDao().getAllWords()
    }
}