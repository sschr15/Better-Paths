package com.example

import sschr15.tools.betterpaths.*

fun main() {
    val userDir = System.getProperty("user.dir").toPath()

    val example = userDir / "cheese.txt"

    if (example.exists() && example.readString().isNotBlank()) {
        println("Previous contents of cheese:")
        println(example.readAllLines().joinToString("\n"))
    }

    example.writeString("""
        gouda
        american
        swiss
    """.trimIndent())

    val doc = Path(userDir, "very-important-document.bin")

    doc.write(intArrayOf(0xCA, 0xFE, 0xBA, 0xBE).map { it.toByte() }.toByteArray())
}


