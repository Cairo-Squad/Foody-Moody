package data.errors

class ValidationException(message: String) : Exception(message)

class NoResultException(message: String) : Exception(message)

class NoSuchElementException(message: String): Exception(message)