package uk.co.placona.acronymBuster

import org.springframework.data.repository.CrudRepository

interface AcronymRepository : CrudRepository<Acronym, Long> {
    fun findByTitleIgnoreCase(title: String): Acronym?
    fun findAllByOrderByAddedAtDesc(): Iterable<Acronym>
}

interface UserRepository : CrudRepository<User, Long> {
    fun findByLogin(login: String): User?
}