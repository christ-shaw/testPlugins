import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.ServiceManager
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import java.time.LocalDateTime


@State
    (
    name = "ReminderPersistentStateComponent",
    storages = [Storage("1-plugin.xml")]
)
public class ReminderPersistentStateComponent
    : PersistentStateComponent<ReminderPersistentStateComponent.ReminderState> {
    companion object {
        val instance: ReminderPersistentStateComponent
            get() = ServiceManager.getService(ReminderPersistentStateComponent::class.java)!!
    }

    var reminderState = ReminderState()


    override fun getState(): ReminderState {
        return reminderState
    }

    override fun loadState(state: ReminderState) {
        reminderState = state
    }


    class ReminderState {
        var lastReminder = LocalDateTime.now().minusDays(1)
        var reminderHour = 11
        var reminderMinutes = 0


    }
}
