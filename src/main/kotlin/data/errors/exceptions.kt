package data.errors

class ValidationException(message: String) : Exception(message)

class NoResultException(message: String) : Exception(message)

class EasyFoodMealsNotFoundException(message: String): Exception(message)