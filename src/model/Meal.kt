package model

import java.time.LocalDate


data class Meal(
    val mealName: String? = null,
    val mealId: Int? = null,
    val mealDescription: String? = null,
    val contributorId: Int? = null,
    val minutes: Int? = null,
    val submitted: LocalDate? = null,
    val tags: List<String>? = null,
    val nutrition: Nutrition? = null,
    val numberOfSteps: String? = null,
    val steps: List<String>? = null,
    val ingredients: List<String>? = null,
    val numberOfIngredients: Int? = null,
) {
    override fun toString(): String = buildString {
        appendLine("Name: $mealName")
        appendLine("ID: $mealId")
        appendLine("Description: $mealDescription")
        appendLine("Contributor ID: $contributorId")
        appendLine("Minutes: $minutes")
        appendLine("Submitted Date: $submitted")
        appendLine("Tags: $tags")
        appendLine("$nutrition")
        appendLine("Number of Ingredients: $numberOfIngredients")
        appendLine("Ingredients: $ingredients")
        appendLine("Number of Steps: $numberOfSteps")
        appendLine("Steps:-")
        append(steps?.getPrintStepsListString())
    }
}

// TODO: Where to put this function?
fun List<String>.getPrintStepsListString(): String = buildString {
    this@getPrintStepsListString.forEachIndexed { index, step ->
        appendLine("\t${index + 1}- ${step.trim()}")
    }
}