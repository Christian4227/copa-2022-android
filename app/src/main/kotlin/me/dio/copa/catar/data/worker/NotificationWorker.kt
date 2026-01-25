package me.dio.copa.catar.data.worker

import android.Manifest
import android.content.Context
import androidx.annotation.RequiresPermission
import androidx.work.Data
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.Worker
import androidx.work.WorkerParameters
import me.dio.copa.catar.domain.model.Match
import me.dio.copa.catar.extensions.showFootballNotifications
import java.time.Duration
import java.time.LocalDateTime
import java.util.concurrent.TimeUnit

class NotificationWorker(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {

    @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
    override fun doWork(): Result {
        val title = inputData.getString(KEY_TITLE) ?: "Copa 2022"
        val content = inputData.getString(KEY_CONTENT) ?: "Acompanhe os jogos da Copa!"
        
        applicationContext.showFootballNotifications(title, content)

        return Result.success()
    }

    companion object {
        private const val WORKER_NAME = "notification_worker_tag"
        private const val KEY_TITLE = "KEY_TITLE"
        private const val KEY_CONTENT = "KEY_CONTENT"

        fun start(context: Context) {
            val workManager = WorkManager.getInstance(context)
            val request = OneTimeWorkRequestBuilder<NotificationWorker>()
                .setInitialDelay(2, TimeUnit.MINUTES)
                .build()

            workManager.enqueueUniqueWork(
                WORKER_NAME,
                ExistingWorkPolicy.REPLACE,
                request
            )
        }

        fun stop(context: Context) {
            WorkManager.getInstance(context).cancelUniqueWork(WORKER_NAME)
        }

        fun start(context: Context, match: Match) {
            val workManager = WorkManager.getInstance(context)

            val now = LocalDateTime.now()
            val delay = Duration.between(now, match.date).toMinutes()

            val inputData = Data.Builder()
                .putString(KEY_TITLE, match.name)
                .putString(KEY_CONTENT, "A partida no estádio ${match.stadium.name} vai começar!")
                .build()

            val request = OneTimeWorkRequestBuilder<NotificationWorker>()
                .setInitialDelay(if (delay > 0) delay else 0, TimeUnit.MINUTES)
                .setInputData(inputData)
                .build()

            workManager.enqueueUniqueWork(
                match.id,
                ExistingWorkPolicy.REPLACE,
                request
            )
        }

        fun stop(context: Context, match: Match) {
            WorkManager.getInstance(context).cancelUniqueWork(match.id)
        }
    }
}