package com.github.mcculloh213.bnf

@DslMarker
annotation class GrammarDsl

fun grammar(block: GrammarCompiler.() -> Unit) = GrammarCompiler().apply(block).build()

@GrammarDsl
class GrammarCompiler :
    Buildable<Grammar> {
    private var header: Header? = null
    private val definitions: MutableList<Definition> = mutableListOf()
    private val expressions: MutableList<Expression> = mutableListOf()
    fun header(block: HeaderCompiler.() -> Unit) {
        header = HeaderCompiler().apply(block).build()
    }
    fun definition(type: String, block: DefinitionCompiler.() -> Unit) {
        DefinitionCompiler(type).apply(block).build()?.let { definitions.add(it) }
    }
    fun expression(block: ExpressionCompiler.() -> Unit) {
        ExpressionCompiler().apply(block).build()?.let { expressions.add(it) }
    }
    override fun build(): Grammar? {
        return Grammar(
            header = header ?: return null,
            definitions = definitions,
            expressions = expressions
        )
    }
    override fun toString(): String = StringBuilder().apply {
        append(header?.toString(this))
        definitions.forEach { definition -> definition.toString(this) }
        expressions.forEach { expression -> expression.toString(this) }
    }.toString()
}

@GrammarDsl
class HeaderCompiler : Appendable,
    Buildable<Header> {
    var line: String? = null
    override operator fun String.unaryPlus() { line = this }
    override fun build(): Header? {
        return Header(line = line ?: return null)
    }
}
@GrammarDsl
class DefinitionCompiler(val type: String): Appendable,
    Buildable<Definition> {
    var def: String? = null
    override operator fun String.unaryPlus() { def = this }
    override fun build(): Definition? {
        return Definition(
            type = type,
            def = def ?: return null
        )
    }
}
@GrammarDsl
class ExpressionCompiler :
    Buildable<Expression> {
    private var token: Token? = null
    private var op: Operator =
        Operator(op = ":")
    private val rules: MutableList<Rule> = mutableListOf()
    fun token(block: TokenCompiler.() -> Unit) { token = TokenCompiler().apply(block).build() }
    fun rule(block: TerminalRuleCompiler.() -> Unit) {
        TerminalRuleCompiler().apply(block).build()?.let { rules.add(it) }
    }
    fun tokenRule(block: TokenRuleCompiler.() -> Unit) {
        TokenRuleCompiler().apply(block).build()?.let { rules.add(it) }
    }
    fun compound(block: CompoundRuleCompiler.() -> Unit) {
        CompoundRuleCompiler().apply(block).build()?.let { rules.add(it) }
    }
    override fun build(): Expression? {
        return Expression(
            token = token ?: return null,
            op = op,
            rules = rules
        )
    }
}

@GrammarDsl
class TokenCompiler : Appendable,
    Buildable<Token> {
    private var t: String? = null
    override operator fun String.unaryPlus() { t = this }
    override fun build(): Token? {
        return Token(text = t ?: return null)
    }
}

abstract class RuleCompiler : Appendable,
    Buildable<Rule>
@GrammarDsl
class TerminalRuleCompiler : RuleCompiler() {
    private var r: String? = null
    override operator fun String.unaryPlus() { r = this }
    override fun build(): Rule? {
        return TerminalRule(rule = r ?: return null)
    }
}
@GrammarDsl
class TokenRuleCompiler : RuleCompiler() {
    var isOptional: Boolean = false
    private var t: Token? = null
    override operator fun String.unaryPlus() { t = TokenCompiler().apply { +this@unaryPlus }.build()}
    override fun build(): Rule? {
        return TokenRule(
            token = t ?: return null,
            isOptional = isOptional
        )
    }
}
@GrammarDsl
class CompoundRuleCompiler : RuleCompiler() {
    private val parts: MutableList<Rule> = mutableListOf()
    fun rule(block: TerminalRuleCompiler.() -> Unit) {
        TerminalRuleCompiler().apply(block).build()?.let { parts.add(it) }
    }
    fun tokenRule(block: TokenRuleCompiler.() -> Unit) {
        TokenRuleCompiler().apply(block).build()?.let { parts.add(it) }
    }
    override operator fun String.unaryPlus() {
        TerminalRuleCompiler().apply { +this@unaryPlus }.build()?.let { parts.add(it) }
    }
    override fun build(): Rule? {
        return CompoundRule(segments = parts)
    }
}