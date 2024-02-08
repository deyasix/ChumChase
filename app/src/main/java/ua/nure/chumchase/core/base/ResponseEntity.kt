package ua.nure.chumchase.core.base

interface ResponseEntity<T> {
    fun toDomainModel(): T
}