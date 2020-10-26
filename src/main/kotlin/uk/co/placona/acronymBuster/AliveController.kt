package uk.co.placona.acronymBuster

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping

@Controller
class AliveController {
    @GetMapping("/alive")
    fun alive(model: Model): String {
        model["title"] = "Alive"
        return "alive"
    }
}