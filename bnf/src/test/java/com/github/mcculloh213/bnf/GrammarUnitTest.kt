package com.github.mcculloh213.bnf

import org.junit.Assert.*
import org.junit.Test

class GrammarUnitTest {
    @Test
    fun grammar_compiles() {
        assertNotNull("The requested object is null", createGrammar())
    }
    @Test
    fun grammar_isCorrect() {
        val bnf = createGrammar()
        assertEquals("The two values are not equal", bnf?.toString(), BnfString)
    }

    private fun createGrammar() = grammar {
        header { +"BNF+EM V2.0" }

        definition("grammar") { +"Commands" }
        definition("start") { +"<Commands>" }

        expression {
            token { +"Commands" }

            tokenRule { +"global_commands" }
            compound {
                tokenRule { +"Hour" }
                tokenRule {
                    isOptional = true
                    +"Minute"
                }
            }
        }
        expression {
            token { +"Minute" }
            rule { +"1" }
            rule { +"2" }
            rule { +"3" }
            rule { +"4" }
            rule { +"5" }
            rule { +"6" }
            rule { +"7" }
            rule { +"8" }
            rule { +"9" }
            rule { +"10" }
            rule { +"11" }
            rule { +"12" }
            rule { +"13" }
            rule { +"14" }
            rule { +"15" }
            rule { +"16" }
            rule { +"17" }
            rule { +"18" }
            rule { +"19" }
            rule { +"20" }
            rule { +"21" }
            rule { +"22" }
            rule { +"23" }
            rule { +"24" }
            rule { +"25" }
            rule { +"26" }
            rule { +"27" }
            rule { +"28" }
            rule { +"29" }
            rule { +"30" }
            rule { +"31" }
            rule { +"32" }
            rule { +"33" }
            rule { +"34" }
            rule { +"35" }
            rule { +"36" }
            rule { +"37" }
            rule { +"38" }
            rule { +"39" }
            rule { +"40" }
            rule { +"41" }
            rule { +"42" }
            rule { +"43" }
            rule { +"44" }
            rule { +"45" }
            rule { +"46" }
            rule { +"47" }
            rule { +"48" }
            rule { +"49" }
            rule { +"50" }
            rule { +"51" }
            rule { +"52" }
            rule { +"53" }
            rule { +"54" }
            rule { +"55" }
            rule { +"56" }
            rule { +"57" }
            rule { +"58" }
            rule { +"59" }
        }
        expression {
            token { +"Hour" }
            rule { +"1" }
            rule { +"2" }
            rule { +"3" }
            rule { +"4" }
            rule { +"5" }
            rule { +"6" }
            rule { +"7" }
            rule { +"8" }
            rule { +"9" }
            rule { +"10" }
            rule { +"11" }
            rule { +"12" }
            rule { +"13" }
            rule { +"14" }
            rule { +"15" }
            rule { +"16" }
            rule { +"17" }
            rule { +"18" }
            rule { +"19" }
            rule { +"20" }
            rule { +"21" }
            rule { +"22" }
            rule { +"23" }
            rule { +"24" }
        }
    }
}
private val BnfString = "#BNF+EM V2.0;\n" +
        "!grammar Commands;\n" +
        "!start <Commands>;\n" +
        "<Commands>:<global_commands>|<Hour> !optional(<Minute>);\n" +
        "<Minute>:1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|35|36|37|38|39|40|41|42|43|44|45|46|47|48|49|50|51|52|53|54|55|56|57|58|59;\n" +
        "<Hour>:1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24;"