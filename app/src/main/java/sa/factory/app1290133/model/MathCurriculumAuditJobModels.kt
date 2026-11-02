package sa.factory.app1290133.model

import kotlinx.serialization.Serializable

@Serializable
data class TahsiliQuestionLessonReviewItem(
    val id: String,
    val title: String,
    val description: String,
    val priceMinorUnits: Long,
    val iconVector: String = "verified"
)

@Serializable
data class MathCurriculumAuditJob(
    val requestId: String,
    val items: List<TahsiliQuestionLessonReviewItem>,
    val customerPhone: String,
    val totalAmountMinorUnits: Long,
    val status: MathCurriculumAuditJobStatus = MathCurriculumAuditJobStatus.PENDING,
    val createdAtEpochMs: Long = System.currentTimeMillis()
)

enum class MathCurriculumAuditJobStatus {
    PENDING,
    ACCEPTED,
    IN_PROGRESS,
    COMPLETED,
    CANCELLED
}
