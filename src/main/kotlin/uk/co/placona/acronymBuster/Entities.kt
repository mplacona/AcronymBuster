package uk.co.placona.acronymBuster

import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
class Acronym(
        var title: String,
        var description: String,
        var usage: String? = null,
        @ManyToOne var author: User,
        var addedAt: LocalDateTime = LocalDateTime.now(),
        @Id @GeneratedValue var id: Long? = null)

@Entity
class User(
        var login: String,
        var firstname: String,
        var lastname: String,
        var phoneNumber: String,
        @Id @GeneratedValue var id: Long? = null)