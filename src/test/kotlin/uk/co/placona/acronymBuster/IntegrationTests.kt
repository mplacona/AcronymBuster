package uk.co.placona.acronymBuster

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.http.HttpStatus

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class IntegrationTests(@Autowired val restTemplate: TestRestTemplate) {
    @BeforeAll
    fun setup() {
        println(">> Setup")
    }

    @Test
    fun `Assert alive page title, content and status code`() {
        println(">> Assert acronym page title, content and status code")
        val entity = restTemplate.getForEntity<String>("/alive")
        assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(entity.body).contains("<h1>Alive</h1>")
    }

    @Test
    fun `Assert acronyms page title, content and status code`() {
        println(">> Assert acronyms page title, content and status code")
        val entity = restTemplate.getForEntity<String>("/")
        assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(entity.body).contains("<h1>Acronyms Buster</h1>", "MQL")
    }

    @Test
    fun `Assert acronym page title, content and status code`() {
        println(">> Assert acronym page title, content and status code")
        val title = "TBD"
        val entity = restTemplate.getForEntity<String>("/acronym/${title}")
        assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(entity.body).contains(title, "TBD", "To be Discussed")
    }

    @AfterAll
    fun teardown() {
        println(">> Tear down")
    }
}