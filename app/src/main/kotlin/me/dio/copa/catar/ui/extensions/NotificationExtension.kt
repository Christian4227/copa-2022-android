package me.dio.copa.catar.ui.extensions

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import me.dio.copa.catar.R


private const val NOTIFICATION_ID = 1
private const val CHANNEL_ID = "new_channel_video"

fun Context.showFootballNotifications() {
    showNotification()
}

private fun Context.showNotification() {
    createNotificationChannel()

    val notification = getNotification()

    NotificationManagerCompat
        .from(this)
        .notify(
            NOTIFICATION_ID,
            notification
        )
}

private fun Context.createNotificationChannel() {
    val name = getString(
        1
    )
    val importance = NotificationManager.IMPORTANCE_HIGH
    val channel = NotificationChannel(CHANNEL_ID, name, importance)

    (getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager)
        .createNotificationChannel(channel)

}

private fun Context.getNotification(
): Notification {

    val notification = NotificationCompat
        .Builder(this, CHANNEL_ID)
        .setSmallIcon(R.drawable.iv_logo_catar_2022)
        .setContentTitle("teste")
        .setContentText("Teste")
        .setPriority(NotificationCompat.PRIORITY_HIGH)
        .setCategory(NotificationCompat.CATEGORY_RECOMMENDATION)
//        .setContentIntent(null)
        .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
        .setAutoCancel(true)

    return notification.build()
}

//private fun Context.getPendingIntent(): PendingIntent {
//    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(videoUrl))
//
//    return PendingIntent.getActivity(
//        this,
//        0,
//        intent,
//        PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
//    )
//}