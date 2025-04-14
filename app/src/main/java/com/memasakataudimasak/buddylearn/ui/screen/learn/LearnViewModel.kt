package com.memasakataudimasak.buddylearn.ui.screen.learn

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

suspend fun getLearn(): List<LearnUiState> {
    val db = FirebaseFirestore.getInstance()

    return try {
        val snapshot = db.collection("Section")
            .document("h7UK6KDmF64wAMYQiLYg")
            .collection("Subsection")
            .get()
            .await()

        snapshot.documents.mapNotNull { doc ->
            doc.toObject(LearnUiState::class.java)
        }
    } catch (e: Exception) {
        Log.d("Error", "getLearn: $e")
        emptyList()
    }
}
