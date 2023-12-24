package ackermann.ilnara.apirest.repository

import ackermann.ilnara.apirest.model.Music
import ackermann.ilnara.apirest.queryProjections.AlbumProjection
import ackermann.ilnara.apirest.queryProjections.MusicPhotosProjection
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface MusicRepository: JpaRepository<Music, Long> {
    @Query(value = "SELECT URL, TUMBNAIL_URL FROM MUSIC WHERE ALBUM_ID = ?1", nativeQuery = true)
    fun findAllPhotosByAlbumId(id: Long): List<MusicPhotosProjection>
    @Query(value = "SELECT DISTINCT album_id FROM Music", nativeQuery = true)
    fun findAllAlbumId(): List<AlbumProjection>
}