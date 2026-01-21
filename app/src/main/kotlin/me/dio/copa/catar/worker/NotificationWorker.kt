package me.dio.copa.catar.worker

import android.content.Context
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.Worker
import androidx.work.WorkerParameters
import me.dio.copa.catar.ui.extensions.showFootballNotifications
import java.util.concurrent.TimeUnit

class NotificationWorker(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {

    override fun doWork(): Result {
        // O contexto aqui já é fornecido pelo Worker
        applicationContext.showFootballNotifications()

        // Se você quiser que ele agende a PRÓXIMA notificação logo após esta:
        // NotificationWorker.start(applicationContext)

        return Result.success()
    }

    companion object {
        private const val WORKER_NAME = "notification_worker_tag"

        fun start(context: Context) {
            val request = OneTimeWorkRequestBuilder<NotificationWorker>()
                .setInitialDelay(2, TimeUnit.MINUTES) // Exemplo: atraso inicial
                .build()

            WorkManager.getInstance(context).enqueueUniqueWork(
                WORKER_NAME,
                ExistingWorkPolicy.REPLACE, // Mude para REPLACE para testar se ele dispara agora
                request
            )
        }

        fun stop(context: Context) {
            WorkManager.getInstance(context).cancelUniqueWork(WORKER_NAME)
        }
    }
}