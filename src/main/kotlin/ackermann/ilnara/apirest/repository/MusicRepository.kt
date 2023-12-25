package ackermann.ilnara.apirest.repository

import ackermann.ilnara.apirest.model.Music
import ackermann.ilnara.apirest.queryProjections.AlbumProjection
import ackermann.ilnara.apirest.queryProjections.MusicPhotosProjection
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

/**
 * Interface de Repositório para entidade 'Music'.
 */
@Repository
interface MusicRepository: JpaRepository<Music, Long> {

    /**
     * Retorna uma lista de projeções de fotos de músicas para um determinado álbum.
     *
     * @param id ID do álbum para o qual as fotos serão recuperadas.
     * @return Lista de projeções de fotos correspondente ao álbum.
     */
    @Query(value = "SELECT URL, THUMBNAIL_URL as thumbnailUrl FROM MUSIC WHERE ALBUM_ID = ?1", nativeQuery = true)
    fun findAllPhotosByAlbumId(id: Long): List<MusicPhotosProjection>

    /**
     * Retorna uma lista de IDs únicos de álbuns a partir da tabela 'Music'.
     *
     * @return Lista de IDs de álbuns.
     */
    @Query(value = "SELECT DISTINCT album_id as albumId FROM Music", nativeQuery = true)
    fun findAllAlbumId(): List<AlbumProjection>
}