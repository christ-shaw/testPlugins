import com.intellij.ide.BrowserUtil
import com.intellij.notification.*
import org.jetbrains.annotations.NotNull
import java.time.LocalDateTime
import javax.swing.event.HyperlinkEvent

object ReminderNotification {
    private val group = NotificationGroup("10bis Plugin", NotificationDisplayType.STICKY_BALLOON, false)

    private val notificationListener = NotificationListener {
            notification: Notification, event: HyperlinkEvent ->
        if (event != null)
        {
            if (event.url != null)
            {
                BrowserUtil.browse(event.url)
            }
        }
        notification?.expire()
    }

    fun notifyUser()
    {
        val title = "Did you remember to order food?"

        val content = escapeString(
            "<a href='www.baidu.com'>Order Now</a>" + // order button
                    "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + // six spaces
                    "<a href='dismiss'>Dismiss</a>" // dismiss buton
        )!!

        val notification = group.createNotification(title, content, NotificationType.WARNING, notificationListener)

        // whenever a notification is dismissed
        notification.whenExpired {
            // update last reminder to now
            ReminderPersistentStateComponent.instance.state?.lastReminder = LocalDateTime.now()
        }

        // ping user, not need for project object
        notification.notify(null)
    }

    private fun escapeString(string: String?): String? {
        return if (string == null || !string.contains("\n")) {
            string
        } else string.replace("\n".toRegex(), "\n<br />")
    }
}