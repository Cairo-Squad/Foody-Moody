package model

data class Nutrition(
    val calories: Float? = null,
    val totalFat: Float? = null,
    val sugar: Float? = null,
    val sodium: Float? = null,
    val protein: Float? = null,
    val saturatedFat: Float? = null,
    val carbohydrates: Float? = null,
) {
    override fun toString(): String = buildString {
        appendLine("Nutrition Facts:-")
        appendLine("\t- Calories: $calories")
        appendLine("\t- Total Fat: ${totalFat}g")
        appendLine("\t- Sugar: ${sugar}g")
        appendLine("\t- Sodium: ${sodium}g")
        appendLine("\t- Protein: ${protein}g")
        appendLine("\t- Saturated Fat: ${saturatedFat}g")
        append("\t- Carbohydrates: ${carbohydrates}g")
    }
}
