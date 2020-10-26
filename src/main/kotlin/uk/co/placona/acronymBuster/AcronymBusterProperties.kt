package uk.co.placona.acronymBuster

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties("acronymbuster")
data class AcronymBusterProperties(var title: String, val banner: Banner) {
    data class Banner(val title: String? = null, val content: String)
}