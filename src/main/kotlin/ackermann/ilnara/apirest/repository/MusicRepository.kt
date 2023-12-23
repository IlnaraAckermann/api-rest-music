package ackermann.ilnara.apirest.repository

import ackermann.ilnara.apirest.model.Music
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MusicRepository: JpaRepository<Music, Long> {
}