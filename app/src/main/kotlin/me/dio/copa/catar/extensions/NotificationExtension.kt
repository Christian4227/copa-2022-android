package me.dio.copa.catar.extensions

import android.Manifest
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresPermission
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import me.dio.copa.catar.R

private const val NOTIFICATION_ID = 1
private const val CHANNEL_ID = "new_channel_video"

fun Context.hasNotificationPermission(): Boolean {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        ContextCompat.checkSelfPermission(
            this, Manifest.permission.POST_NOTIFICATIONS
        ) == PackageManager.PERMISSION_GRANTED
    } else {
        true
    }
}

@RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
fun Context.showFootballNotifications() {
    showNotification()
}

@RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
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
    val name = getString(R.string.app_name)
    val importance = NotificationManager.IMPORTANCE_HIGH
    val channel = NotificationChannel(CHANNEL_ID, name, importance)

    (getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager)
        .createNotificationChannel(channel)

}

private fun Context.getNotification(): Notification {
    val notification = NotificationCompat
        .Builder(this, CHANNEL_ID)
        .setSmallIcon(R.drawable.iv_logo_catar_2022)
        .setContentTitle("Copa 2022")
        .setContentText("Acompanhe os jogos do Brasil!")
        .setPriority(NotificationCompat.PRIORITY_HIGH)
        .setCategory(NotificationCompat.CATEGORY_RECOMMENDATION)
        .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
        .setAutoCancel(true)

    return notification.build()
}
