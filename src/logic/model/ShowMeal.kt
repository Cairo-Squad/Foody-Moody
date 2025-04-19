package logic.model

data class ShowMeal(
    val rank: Int,
    val mealName: String?,
    val protein: Double
){
    override fun toString(): String {
        return "Rank: $rank, Name: $mealName, Protein: $protein"
    }
}

