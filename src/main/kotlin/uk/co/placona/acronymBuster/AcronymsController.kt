package uk.co.placona.acronymBuster

import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.server.ResponseStatusException

@Controller
class AcronymsController(private val repository: AcronymRepository, private val properties: AcronymBusterProperties) {
    @GetMapping("/")
    fun acronyms(model: Model): String {
        model["title"] = properties.title
        model["banner"] = properties.banner
        model["acronyms"] = repository.findAllByOrderByAddedAtDesc().map { it.render() }
        return "acronyms"
    }

    @GetMapping("/acronym/{title}")
    fun acronym(@PathVariable title: String, model: Model): String {
        val acronym = repository
                .findByTitleIgnoreCase(title)
                ?.render()
                ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "This acronym does not exist")
        model["title"] = acronym.title
        model["acronym"] = acronym
        return "acronym"
    }

    fun Acronym.render() = RenderedAcronym(
            title,
            description,
            usage,
            author,
            addedAt.format()
    )

    data class RenderedAcronym(
            val title: String,
            val description: String,
            val usage: String?,
            val author: User,
            val addedAt: String)
}