package ackermann.ilnara.apirest.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

/**
 *Representa a entidade musica
 * @author ilnara
 */

@Entity
@Table(name="Music")
data class Music(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?=null,
    @Column( nullable = false)
    var title: String="",
    @Column( nullable = false)
    var url: String = "",
    @Column( nullable = false)
    var tumbnailURL: String = "",
    @Column( nullable = false)
    var album: String = ""
)
