package uk.co.placona.acronymBuster

import org.springframework.boot.Banner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(AcronymBusterProperties::class)
class AcronymBusterApplication

fun main(args: Array<String>) {
	runApplication<AcronymBusterApplication>(*args){
		setBannerMode(Banner.Mode.OFF)
	}
}
