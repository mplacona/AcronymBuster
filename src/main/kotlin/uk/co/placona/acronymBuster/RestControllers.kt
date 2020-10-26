package uk.co.placona.acronymBuster

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/acronym")
class AcronymController(private val repository: AcronymRepository) {

    @GetMapping("", "/")
    fun findAll() = repository.findAllByOrderByAddedAtDesc()

    @GetMapping("/{title}")
    fun findOne(@PathVariable title: String) =
            repository.findByTitleIgnoreCase(title) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "This acronym does not exist")

}

@RestController
@RequestMapping("/api/user")
class UserController(private val repository: UserRepository) {

    @GetMapping("", "/")
    fun findAll() = repository.findAll()

    @GetMapping("/{login}")
    fun findOne(@PathVariable login: String) =
            repository.findByLogin(login) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "This user does not exist")
}