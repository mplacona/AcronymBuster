package uk.co.placona.acronymBuster

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@WebMvcTest
class RestControllersTests(@Autowired val mockMvc: MockMvc) {

    @MockkBean
    private lateinit var userRepository: UserRepository

    @MockkBean
    private lateinit var acronymRepository: AcronymRepository

    @Test
    fun `List acronyms`() {
        val marcos = User("mplacona", "Marcos", "Placona", "+441234567890")
        val acronym1 = Acronym("TBD", "To Be Discussed", "Our plan of action is still TBD", marcos)
        val acronym2 = Acronym("TBC", "To Be Confirmed", "A date for this event is still TBC", marcos)
        every { acronymRepository.findAllByOrderByAddedAtDesc() } returns listOf(acronym1, acronym2)
        mockMvc.perform(get("/api/acronym/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("\$.[0].author.login").value(marcos.login))
                .andExpect(jsonPath("\$.[0].title").value(acronym1.title))
                .andExpect(jsonPath("\$.[1].author.login").value(marcos.login))
                .andExpect(jsonPath("\$.[1].title").value(acronym2.title))
    }

    @Test
    fun `List users`() {
        val marcos = User("mplacona", "Marcos", "Placona", "+441234567890")
        val matthew = User("mgilliard", "Matthew", "Gilliard", "+441234567890")
        every { userRepository.findAll() } returns listOf(marcos, matthew)
        mockMvc.perform(get("/api/user/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("\$.[0].login").value(marcos.login))
                .andExpect(jsonPath("\$.[1].login").value(matthew.login))
    }
}