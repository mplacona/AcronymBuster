package uk.co.placona.acronymBuster

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.data.repository.findByIdOrNull

@DataJpaTest
class RepositoriesTests  @Autowired constructor (
        private val entityManager: TestEntityManager,
        private val userRepository: UserRepository,
        private val acronymRepository: AcronymRepository){

    @Test
    fun `When findByIdOrNull then return Acronym`() {
        val marcos = User("mplacona", "Marcos", "Placona", "+441234567890")
        entityManager.persist(marcos)
        val acronym = Acronym("TBD", "To Be Discussed", "Our plan of action is still TBD", marcos)
        entityManager.persist(acronym)
        entityManager.flush()
        val found = acronymRepository.findByIdOrNull(acronym.id!!)
        assertThat(found).isEqualTo(acronym)
    }

    @Test
    fun `When findByLogin then return User`() {
        val marcos = User("mplacona", "Marcos", "Placona", "+441234567890")
        entityManager.persist(marcos)
        entityManager.flush()
        val user = userRepository.findByLogin(marcos.login)
        assertThat(user).isEqualTo(marcos)
    }
}