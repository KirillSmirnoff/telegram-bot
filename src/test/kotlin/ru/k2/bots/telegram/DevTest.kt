package ru.k2.bots.telegram

import org.junit.jupiter.api.Test
import java.util.regex.Pattern

class DevTest {

    @Test
    fun `test regex`() {

        val regex = "/result(/\\w*)"
//        val regex = "(/\\w*)"
        val string = "/result/anotherBbot/mup/"

        val pattern = Pattern.compile(regex, Pattern.MULTILINE)
        val matcher = pattern.matcher(string)

//        while (matcher.find()) {
        matcher.find()
            println("Full match: " + matcher.group(1))

//            for (i in 1..matcher.groupCount()) {
//                println("Group " + i + ": " + matcher.group(i))
//            }
//        }
    }
}