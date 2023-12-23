package ackermann.ilnara.apirest.repository

import ackermann.ilnara.apirest.model.Music
import org.springframework.data.jpa.repository.JpaRepository

interface MusicRepository: JpaRepository<Music, Long> {
}