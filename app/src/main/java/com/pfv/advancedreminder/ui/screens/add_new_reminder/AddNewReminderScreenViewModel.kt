package com.pfv.advancedreminder.ui.screens.add_new_reminder

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pfv.advancedreminder.R
import com.pfv.advancedreminder.constants.DaysOfWeek
import com.pfv.advancedreminder.tools.scheduleNotification
import com.pfv.advancedreminder.ui.screens.add_new_reminder.constants.ReminderType
import com.pfv.advancedreminder.ui.screens.add_new_reminder.event.AddNewReminderEvent
import com.pfv.advancedreminder.ui.screens.add_new_reminder.form.AddNewReminderScreenForm
import com.pfv.advancedreminder.ui.screens.add_new_reminder.nav_state.AddNewReminderNavState
import com.pfv.advancedreminder.ui.screens.add_new_reminder.ui_state.AddNewReminderUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.Date
import javax.inject.Inject

class AddNewReminderScreenViewModel @Inject constructor() : ViewModel() {

    private var _selectedReminderType = MutableStateFlow(ReminderType.BASE)
    var selectedReminderType: StateFlow<ReminderType> = _selectedReminderType.asStateFlow()

    private var _form = MutableStateFlow(AddNewReminderScreenForm())
    var form: StateFlow<AddNewReminderScreenForm> = _form.asStateFlow()

    private var _uiState: MutableStateFlow<AddNewReminderUiState> =
        MutableStateFlow(AddNewReminderUiState.InitState)
    var uiState: StateFlow<AddNewReminderUiState> = _uiState.asStateFlow()

    private var _navState: MutableStateFlow<AddNewReminderNavState> =
        MutableStateFlow(AddNewReminderNavState.InitState)
    var navState: StateFlow<AddNewReminderNavState> = _navState.asStateFlow()

    private var isTitleRealtimeValidationActive by mutableStateOf(false)
    private var isSelectTimeToRemindRealtimeValidationActive by mutableStateOf(false)

    fun reduceEvent(event: AddNewReminderEvent) {

        when (event) {
            is AddNewReminderEvent.ChangeReminderType -> updateReminderType(event.type)
            AddNewReminderEvent.DecrementTimesToRemind -> decrementTimesToRemind()
            AddNewReminderEvent.IncrementTimesToRemind -> incrementTimesToRemind()
            is AddNewReminderEvent.AddNewTime -> addNewTime(event.date)
            AddNewReminderEvent.ShowTimePicker_AddTime -> showTimePicker_AddTime()
            is AddNewReminderEvent.ShowTimePicker_EditTime -> showTimePicker_EditTime(
                event.index,
                event.time
            )

            is AddNewReminderEvent.RemoveTimeByIndex -> removeTimeByIndex(event.index)
            is AddNewReminderEvent.UpdateTimeByIndex -> updateTimeByIndex(event.date, event.index)
            AddNewReminderEvent.AddNewDate -> showDatePicker()
            is AddNewReminderEvent.UpdateDate -> updateDate(event.start, event.end)
            is AddNewReminderEvent.UpdateSelectableDayOfWeekToRemind -> handleUpdateSelectableDayOfWeekToRemindFlow(
                event.day
            )

            is AddNewReminderEvent.UpdateDescription -> updateDescription(event.text)
            is AddNewReminderEvent.UpdateTitle -> updateTitle(event.text)
            is AddNewReminderEvent.AddNewReminderClick -> handleAddNewReminderClick(event.context)
        }
    }

    private fun handleAddNewReminderClick(context: Context) {

        isTitleRealtimeValidationActive = true
        isSelectTimeToRemindRealtimeValidationActive = true

        validateFields()

        if (_form.value.isAllFieldsValid()) {
            processAddNewSimpleReminders(context)
        }
    }

    private fun processAddNewSimpleReminders(
        context: Context
    ){

        val calendar = Calendar.getInstance()

        val startDate = _form.value.startDate ?: calendar.time
        val endDate = _form.value.endDate ?: startDate

        var currentDate = startDate
        while (currentDate <= endDate) {

            val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)
            if (_form.value.selectableDaysOfWeekToRemind.map { it.day }.contains(dayOfWeek)) {

                _form.value.time.forEach { time ->

                    calendar.time = currentDate
                    calendar.set(Calendar.HOUR_OF_DAY, time.hours)
                    calendar.set(Calendar.MINUTE, time.minutes)
                    calendar.set(Calendar.SECOND, 0)

                    val delayMinutes = (calendar.timeInMillis - System.currentTimeMillis()) / 60000

                    scheduleNotification(context, delayMinutes, _form.value.title, _form.value.description)
                }
            }

            calendar.add(Calendar.DAY_OF_MONTH, 1)
            currentDate = calendar.time
        }

        viewModelScope.launch {
            _navState.emit(AddNewReminderNavState.NavigateBack)
        }
    }

    private fun validateFields() {
        validateSelectTimeToRemind()
        validateTitle()
    }

    private fun validateSelectTimeToRemind() {

        _form.update {
            it.copy(
                selectTimeToRemindError = if ((_form.value.timesToRemind > _form.value.time.size) && isSelectTimeToRemindRealtimeValidationActive)
                    R.string.select_more_times_to_remind
                else null
            )
        }
    }

    private fun validateTitle() {

        _form.update {
            it.copy(
                titleError = if (_form.value.title.isBlank() && isTitleRealtimeValidationActive)
                    R.string.this_field_is_required
                else null
            )
        }
    }

    private fun updateTitle(text: String) {

        _form.update {
            it.copy(
                title = text
            )
        }

        validateTitle()
    }

    private fun updateDescription(text: String) {

        _form.update {
            it.copy(
                description = text
            )
        }
    }

    private fun handleUpdateSelectableDayOfWeekToRemindFlow(day: DaysOfWeek) {

        val needRemove = _form.value.selectableDaysOfWeekToRemind.contains(day)
        val updatedList = _form.value.selectableDaysOfWeekToRemind.toMutableList()

        updatedList.apply {
            if (needRemove) {
                remove(day)
            } else {
                add(day)
            }
        }

        _form.update {
            it.copy(
                selectableDaysOfWeekToRemind = updatedList
            )
        }
    }

    private fun updateDate(start: Date?, end: Date?) {

        _form.update {
            it.copy(
                startDate = start ?: end,
                endDate = end ?: start
            )
        }
    }

    private fun showDatePicker() {

        viewModelScope.launch {
            _uiState.emit(AddNewReminderUiState.AddNewDate)
        }
    }

    private fun updateTimeByIndex(date: Date, index: Int) {

        _form.update {

            val updatedTime = it.time.toMutableList()
            updatedTime[index] = date

            it.copy(
                time = updatedTime
            )
        }
    }

    private fun showTimePicker_EditTime(index: Int, time: Date) {
        viewModelScope.launch {
            _uiState.emit(AddNewReminderUiState.EditTimeByIndex(index, time))
        }
    }

    private fun showTimePicker_AddTime() {
        viewModelScope.launch {
            _uiState.emit(AddNewReminderUiState.AddNewTime)
        }
    }

    private fun updateReminderType(type: ReminderType) {
        viewModelScope.launch {
            _selectedReminderType.emit(type)
        }
    }

    private fun addNewTime(date: Date) {
        _form.update {
            it.copy(
                time = it.time + date
            )
        }

        validateSelectTimeToRemind()
    }

    private fun removeTimeByIndex(index: Int) {

        _form.update {

            val updatedTime = it.time.toMutableList()
            updatedTime.removeAt(index)

            it.copy(
                time = updatedTime
            )
        }

        validateSelectTimeToRemind()
    }

    private fun incrementTimesToRemind() {
        _form.update {
            it.copy(
                timesToRemind = it.timesToRemind.plus(1)
            )
        }

        validateSelectTimeToRemind()
    }

    private fun decrementTimesToRemind() {
        _form.update {

            val updatedList = it.time
                .toMutableList()

            if (it.timesToRemind == it.time.size) {

                updatedList.removeLast()
            }

            it.copy(
                time = updatedList,
                timesToRemind = it.timesToRemind.minus(1)
            )
        }
        validateSelectTimeToRemind()
    }

    fun resetSelectableDaysOfWeekToRemind() {
        _form.update {
            it.copy(
                selectableDaysOfWeekToRemind = DaysOfWeek.entries
            )
        }
    }

    fun resetUiState() {
        viewModelScope.launch {
            _uiState.emit(AddNewReminderUiState.InitState)
        }
    }

}