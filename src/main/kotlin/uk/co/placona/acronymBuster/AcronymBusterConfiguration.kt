package uk.co.placona.acronymBuster

import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AcronymBusterConfiguration {
    @Bean
    fun databaseInitializer(userRepository: UserRepository,
                            acronymRepository: AcronymRepository) = ApplicationRunner {

        val mplacona = userRepository.save(User("mplacona", "Marcos", "Placona", "+44 1234567890"))
        acronymRepository.save(Acronym(
                title = "TBD",
                description = "To be Discussed",
                usage = "Our next steps are TBD",
                author = mplacona
        ))
        acronymRepository.save(Acronym(
                title = "MQL",
                description = "Marketing Qualified Leads",
                usage = "Someone in this team is really going after all those MQLs",
                author = mplacona
        ))
    }
}