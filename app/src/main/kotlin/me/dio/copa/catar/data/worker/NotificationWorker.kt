package me.dio.copa.catar.data.worker

import android.Manifest
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresPermission
import androidx.core.content.ContextCompat
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.Worker
import androidx.work.WorkerParameters
import me.dio.copa.catar.MainActivity
import me.dio.copa.catar.extensions.hasNotificationPermission
import me.dio.copa.catar.extensions.showFootballNotifications
import java.util.concurrent.TimeUnit

class NotificationWorker(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {

    @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
    override fun doWork(): Result {
        applicationContext.showFootballNotifications()

        return Result.success()
    }

    companion object {
        private const val WORKER_NAME = "notification_worker_tag"

        fun start(context: Context) {
            val workManager = WorkManager.getInstance(context)

            val request = OneTimeWorkRequestBuilder<NotificationWorker>()
                .setInitialDelay(2, TimeUnit.MINUTES) // Exemplo: atraso inicial
                .build()

            WorkManager.Companion.getInstance(context).enqueueUniqueWork(
                WORKER_NAME,
                ExistingWorkPolicy.REPLACE, // Mude para REPLACE para testar se ele dispara agora
                request
            )
        }

        fun stop(context: Context) {
            WorkManager.Companion.getInstance(context).cancelUniqueWork(WORKER_NAME)
        }
    }
}