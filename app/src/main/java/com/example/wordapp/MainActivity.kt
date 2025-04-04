package com.example.wordapp

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.navigation.compose.rememberNavController
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.wordapp.ViewModel.WordViewModel
import com.example.wordapp.data.Repository.Repository
import com.example.wordapp.data.SettingsDataStore
import com.example.wordapp.data.room.WordDB
import com.example.wordapp.ui.components.WordApp
import com.example.wordapp.ui.theme.WordappTheme
import com.example.wordapp.workers.NotificationWorker
import java.util.concurrent.TimeUnit

private const val TAG = "MainActivity"
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WordappTheme {
                val db = WordDB.getDatabase(LocalContext.current)
                val repository = Repository(db)
                val context = LocalContext.current
                val dataStore = SettingsDataStore(context)
                val viewModel = WordViewModel(repository, dataStore)
                val navController = rememberNavController()
                val layoutDirection = LocalLayoutDirection.current
                Surface (
                    modifier = Modifier
                        .padding(
                            start = WindowInsets.safeDrawing.asPaddingValues()
                                .calculateStartPadding(layoutDirection),
                            end = WindowInsets.safeDrawing.asPaddingValues()
                                .calculateEndPadding(layoutDirection)
                        )

                ){
                    val windowSize = calculateWindowSizeClass(this)

                WordApp(navController, viewModel, windowSize = windowSize.widthSizeClass)
            }}
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "Апп -аас гарлаа.")
    }
    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart Called")
        cancelNotification()
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume Called")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart Called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause Called")
    }

    override fun onStop() {
        super.onStop()
        scheduleNotification()
        Log.d(TAG, "onStop Called")

    }
    private fun scheduleNotification() {
        val workRequest = OneTimeWorkRequestBuilder<NotificationWorker>()
            .setInitialDelay(24, TimeUnit.HOURS)
            .build()

        WorkManager.getInstance(this).enqueueUniqueWork(
            "daily_notification",
            ExistingWorkPolicy.REPLACE,
            workRequest
        )
    }

    private fun cancelNotification() {
        WorkManager.getInstance(this).cancelUniqueWork("daily_notification")
    }
}

