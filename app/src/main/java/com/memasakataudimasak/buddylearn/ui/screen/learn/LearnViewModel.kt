package com.memasakataudimasak.buddylearn.ui.screen.learn

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import com.memasakataudimasak.buddylearn.data.LearnItem
import com.memasakataudimasak.buddylearn.ui.screen.settings.SettingsUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class LearnViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(LearnUiState())
    val uiState: StateFlow<LearnUiState> = _uiState.asStateFlow()

    init {
        fetchLearn()
    }

    private fun fetchLearn() {
        viewModelScope.launch {
            try {
                val snapshot = FirebaseFirestore.getInstance()
                    .collection("Section")
                    .document("h7UK6KDmF64wAMYQiLYg")
                    .collection("Subsection")
                    .get()
                    .await()

                val list = snapshot.documents.mapNotNull {
                    it.toObject(LearnItem::class.java)
                }

                Log.d("learn content list", list.toString())

                _uiState.value = _uiState.value.copy(
                    learnList = list,
                )
            } catch (e: Exception) {
                Log.e("error on viewmodel", e.toString())
            }
        }
    }

    fun setCurrentIndex(index: Int) {
        _uiState.value = _uiState.value.copy(currentIndex = index)
    }

}
