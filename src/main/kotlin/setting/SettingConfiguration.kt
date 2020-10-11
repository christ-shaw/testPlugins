package setting

import com.intellij.openapi.options.BoundConfigurable
import com.intellij.openapi.ui.DialogPanel
import com.intellij.ui.CollectionComboBoxModel
import com.intellij.ui.layout.panel

import setting.SettingService.Companion.instance
class SettingConfiguration : BoundConfigurable("yapi setting")
{
    override fun createPanel(): DialogPanel {

            val comboBoxChoices = listOf("choice1", "choice2", "choice3")
            // val selection = if (savedSelection.isEmpty()) comboBoxChoices.first() else savedSelection
            val comboBoxModel = CollectionComboBoxModel(comboBoxChoices, comboBoxChoices.first())
            return panel {
                row("ComboBox / Drop down list") {
                    comboBox(comboBoxModel, instance.state::myStringChoice)
                }
            }
        }

}