package me.dio.copa.catar.worker

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class NotificationWorker(private val context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {
    override fun doWork(): Result {

        // Indicate whether the work finished successfully with the Result
        return Result.success()
    }

    companion object {
        private const val TAG = "NotificationWorker"
    }
}