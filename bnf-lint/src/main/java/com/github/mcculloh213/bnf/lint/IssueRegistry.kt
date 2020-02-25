package com.github.mcculloh213.bnf.lint

import com.android.tools.lint.client.api.IssueRegistry
import com.android.tools.lint.detector.api.CURRENT_API
import com.android.tools.lint.detector.api.Issue
import com.github.mcculloh213.bnf.lint.issue.MissingFieldIssue

class IssueRegistry : IssueRegistry() {
    override val issues: List<Issue>
        get() = listOf(MissingFieldIssue)
    override val api: Int
        get() = CURRENT_API
}