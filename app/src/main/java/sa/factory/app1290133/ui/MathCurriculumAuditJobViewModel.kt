package sa.factory.app1290133.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import sa.factory.app1290133.data.MathCurriculumAuditJobRepository
import sa.factory.app1290133.model.MathCurriculumAuditJob
import sa.factory.app1290133.model.TahsiliQuestionLessonReviewItem
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MathCurriculumAuditJobViewModel(
    private val repository: MathCurriculumAuditJobRepository
) : ViewModel() {
    val catalog: StateFlow<List<TahsiliQuestionLessonReviewItem>> = repository.getAvailableCatalog()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val activeRequest: StateFlow<MathCurriculumAuditJob?> = repository.getActiveRequest()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    fun submitNewOrder(request: MathCurriculumAuditJob) {
        viewModelScope.launch {
            repository.submitRequest(request)
        }
    }
}
