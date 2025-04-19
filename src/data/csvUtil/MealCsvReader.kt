package data.csvUtil

import java.io.File

class MealCsvReader(private val file: File) {

    fun readCsvLines(): List<String> {
        if (file.exists()){
            val content = file.readText().replace("\r\n", "\n")
            val lines = buildList {
                var currentRow = StringBuilder()
                var inQuotes = false

                content.forEach { char ->
                    when {
                        char == '"' -> {
                            inQuotes = !inQuotes
                            currentRow.append(char)
                        }
                        char == '\n' && !inQuotes -> {
                            add(currentRow.toString())
                            currentRow = StringBuilder()
                        }
                        else -> currentRow.append(char)
                    }
                }
                if (currentRow.isNotEmpty()) {
                    add(currentRow.toString())
                }
            }

            return if (lines.isNotEmpty()) lines.subList(1, lines.size) else emptyList()
        }
        return emptyList()
    }
}