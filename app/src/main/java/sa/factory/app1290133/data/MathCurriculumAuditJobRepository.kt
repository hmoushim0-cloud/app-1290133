package sa.factory.app1290133.data

import sa.factory.app1290133.model.MathCurriculumAuditJob
import sa.factory.app1290133.model.TahsiliQuestionLessonReviewItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

interface MathCurriculumAuditJobRepository {
    fun getAvailableCatalog(): Flow<List<TahsiliQuestionLessonReviewItem>>
    fun getActiveRequest(): Flow<MathCurriculumAuditJob?>
    suspend fun submitRequest(request: MathCurriculumAuditJob): Result<String>
}

class InMemoryMathCurriculumAuditJobRepository : MathCurriculumAuditJobRepository {
    private val _catalog = MutableStateFlow<List<TahsiliQuestionLessonReviewItem>>(listOf(
        TahsiliQuestionLessonReviewItem("1", "حزمة تدقيق أسئلة أول ثانوي", "مراجعة شاملة للحلول والخطوات الرياضية ومطابقتها للمقرر المعتمد", 5000L, "verified"),
        TahsiliQuestionLessonReviewItem("2", "حزمة مراجعة دروس ثاني ثانوي التفاعلية", "فحص البنية المفاهيمية والشروحات والرسوم الهندسية للتأكد من خلوها من الأخطاء", 9500L, "assignment")
    ))
    private val _active = MutableStateFlow<MathCurriculumAuditJob?>(null)

    override fun getAvailableCatalog(): Flow<List<TahsiliQuestionLessonReviewItem>> = _catalog.asStateFlow()
    override fun getActiveRequest(): Flow<MathCurriculumAuditJob?> = _active.asStateFlow()

    override suspend fun submitRequest(request: MathCurriculumAuditJob): Result<String> {
        _active.value = request
        return Result.success(request.requestId)
    }
}
