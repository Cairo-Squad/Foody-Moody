# Foody-Moody
Food Change Mood
By following a similar structure to the Cost of Living app from Section 8, we need to build the Food Change Mood app based on the provided CSV file.
This is the only week in The Chance where you are not required to write Unit Tests. Please focus on building the features. We will continue with the same app next week, with a clear requirement to write unit tests—so don’t rush it this week!
Attention: Parsing the food.csv file may be slightly more challenging than the costOfLiving.csv file, as food.csvcontains arrays as values within a single column.
Read the following points carefully to understand the dataset:
- The Nutrition column contains an array of values in the following order: Calories, Total Fat, Sugar, Sodium, Protein, Saturated Fat, and Carbohydrates.
- 2% of meals in the dataset are null, so your code must support the case where a meal has no description.
After preparing your code structure and parsing the food.csv file, implement the following use cases in the Food Change Mood app:
1- Allow the user to get a list of healthy fast food meals that can be prepared in 15 minutes or less, with very low total fat, saturated fat, and carbohydrate values compared to other meals in the dataset.
2- Enable meal search by name. Keep in mind that users may not remember the full meal name. Using .contains() may fail if there is a typo in the keyword. Also, due to the large dataset, choose a fast text search algorithm. I recommend the Knuth-Morris-Pratt algorithm for its speed, but feel free to use a better alternative in terms of both performance and accuracy.
3- Identify Iraqi meals in the dataset. A meal is considered Iraqi if it is tagged with "iraqi" or if the description contains the word "Iraq".
4- Easy Food Suggestion: Like a fun game, this feature suggests 10 random meals that are easy to prepare. A meal is considered easy if it requires 30 minutes or less, has 5 ingredients or fewer, and can be prepared in 6 steps or fewer.
5- Guess Game: Show the user a random meal name and ask them to guess its preparation time. The user has 3 attempts. After each attempt, indicate whether the guessed time is correct, too low, or too high. If all attempts are incorrect, show the correct time.
6- Sweets with No Eggs: For users allergic to eggs, suggest one sweet (name and description) that contains no eggs. The user can either like it (to view full details) or dislike it (to get another random egg-free sweet). Ensure the same sweet is not suggested more than once.
7- Keto Diet Meal Helper: Using the same mechanism as in point 6, suggest one keto-friendly meal at a time (without repetition). Base your logic on nutritional information. Please research keto diet requirements before implementation.
8- Search Foods by Add Date: Use Kotlin’s Date class to represent the date in the meal entity. Let the user input a date and return a list of IDs and names of meals added on that date. The user should be able to view details of a specific meal by entering its ID. Handle exceptions for:
- Incorrect date format.
- No meals were found for the given date. Ensure different exceptions are used for both cases.
9- Gym Helper: Allow the user to input a desired amount of calories and protein, and return a list of meals that match or approximate those values.
10- Explore Other Countries' Food Culture: Let the user enter a country name, then search any relevant column to return up to 20 randomly ordered meals related to that country.
11- Ingredient Game: Display a meal name and three ingredient options (one correct, two incorrect). The user guesses once. A correct guess earns 1000 points; an incorrect guess ends the game. The game also ends after 15 correct answers. Display the final score at the end.
12- I Love Potato: Show a random list of 10 meals that include potatoes in their ingredients.
13- So Thin Problem: Suggest a meal with more than 700 calories using a logic similar to point 6.
14- Show a list of all seafood meals sorted by protein content, from highest to lowest. Each result should display the rank (starting from 1), meal name, and protein amount.
15- A large group of friends traveling to Italy want to share a meal suitable for "for-large-groups", and it must be an original Italian dish, write a function to help them by suggesting as many as possible Italian food that are suitable for large groups.

CSV File Link: https://drive.google.com/file/d/1px860X8gO_AFHNkcNFe64e_il_bDaKSI/view
