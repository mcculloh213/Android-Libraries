package com.github.mcculloh213.bnf

interface Appendable {
    operator fun String.unaryPlus()
}
interface Buildable<T> {
    fun build(): T?
}
interface Printable {
    fun toString(builder: StringBuilder)
}

data class Grammar(
    val header: Header,
    val definitions: List<Definition>,
    val expressions: List<Expression>
) : Printable {
    override fun toString() = StringBuilder().apply {
        toString(this)
    }.toString().trim()
    override fun toString(builder: StringBuilder) = builder.run {
        header.toString(this)
        definitions.forEach { definition -> definition.toString(this) }
        expressions.forEach { expression -> expression.toString(this) }
    }
}
data class Header(val line: String) : Printable {
    override fun toString(builder: StringBuilder) {
        builder.append('#').append(line).append(';').append('\n')
    }
}
data class Definition(
    val type: String,
    val def: String
) : Printable {
    override fun toString(builder: StringBuilder) {
        builder.append('!').append(type).append(' ').append(def).append(';').append('\n')
    }
}
data class Expression(
    val token: Token,
    val op: Operator,
    val rules: List<Rule>
) : Printable {
    override fun toString(builder: StringBuilder) {
        token.toString(builder)
        op.toString(builder)
        val separators = rules.size - 1
        rules.forEachIndexed { i, rule ->
            rule.toString(builder)
            if (separators - i > 0) { builder.append('|') }
        }
        builder.append(';').append('\n')
    }
}
data class Token(val text: String) : Printable {
    override fun toString(builder: StringBuilder) {
        builder.append('<').append(text).append('>')
    }
}
data class Operator(val op: String) : Printable {
    override fun toString(builder: StringBuilder) {
        builder.append(op)
    }
}
abstract class Rule : Printable
data class TerminalRule(val rule: String) : Rule() {
    override fun toString(builder: StringBuilder) { builder.append(rule) }
}
data class TokenRule(
    val token: Token,
    val isOptional: Boolean
): Rule() {
    override fun toString(builder: StringBuilder) {
        if (isOptional) { builder.append('!').append("optional").append('(') }
        token.toString(builder)
        if (isOptional) { builder.append(')') }
    }
}
data class CompoundRule(val segments: List<Rule>): Rule() {
    override fun toString(builder: StringBuilder) {
        val separators = segments.size - 1
        segments.forEachIndexed { i, segment ->
            segment.toString(builder)
            if (separators - i > 0) { builder.append(' ') }
        }
    }
}
