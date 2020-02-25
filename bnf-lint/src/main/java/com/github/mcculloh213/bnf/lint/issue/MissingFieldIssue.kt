package com.github.mcculloh213.bnf.lint.issue

import com.android.tools.lint.detector.api.*
import org.jetbrains.uast.UElement

val MissingFieldIssueId = "MissingFieldId"
val MissingFieldDescription = "Missing elements might result in the grammar not compiling correctly..."

val MissingFieldIssue = Issue.create(
    id = MissingFieldIssueId,
    briefDescription = MissingFieldDescription,
    explanation = MissingFieldDescription,
    category = Category.CORRECTNESS,
    severity = Severity.WARNING,
    implementation = Implementation(
        MissingFieldDetector::class.java,
        Scope.JAVA_FILE_SCOPE
    )
)

class MissingFieldDetector : Detector(), SourceCodeScanner {
    override fun applicableAnnotations(): List<String> = listOf("")
    override fun getApplicableUastTypes(): List<Class<out UElement>>? {
        return super.getApplicableUastTypes()
    }
}