package setting

import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.Service
import com.intellij.openapi.components.ServiceManager
import com.intellij.openapi.components.Storage

@Service
@com.intellij.openapi.components.State(name = "KotlinDSLUISampleData", storages = [Storage("kotlinDSLUISampleData.xml")])
class SettingService : PersistentStateComponent<State> {
    companion object {
        val instance: SettingService
            get() = ServiceManager.getService(SettingService::class.java)
    }

    private var state = State()

    override fun getState(): State {
        return state
    }

    override fun loadState(persistentState: State) {
        state =  persistentState
    }

}