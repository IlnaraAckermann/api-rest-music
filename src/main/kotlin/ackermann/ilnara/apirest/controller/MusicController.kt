package ackermann.ilnara.apirest.controller

import ackermann.ilnara.apirest.model.Music
import ackermann.ilnara.apirest.queryProjections.AlbumProjection
import ackermann.ilnara.apirest.queryProjections.MusicPhotosProjection
import ackermann.ilnara.apirest.service.impl.MusicService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


/**
 * Controlador para lidar com requisições relacionadas a álbuns.
 */
@RestController
@RequestMapping("/api/music")
class MusicController(
    private val musicService: MusicService
) {

    @PostMapping("/url")
    fun saveAllFromURL(@RequestParam url: String): ResponseEntity<List<Music>> {
        val musicList = this.musicService.saveAllFromURL(url)
        return ResponseEntity(
            (musicList), HttpStatus.OK
        )
    }

    @PostMapping("/list")
    fun saveAllFromJson(@RequestBody musicList: List<Music>): ResponseEntity<List<Music>> {
        val savedMusicList = this.musicService.saveAll(musicList)
        return ResponseEntity(
            (savedMusicList), HttpStatus.OK
        )
    }


    /**
     * Endpoint para buscar todos os álbuns.
     *
     * @return Resposta HTTP com uma lista de AlbumProjection.
     */
    @GetMapping("/albuns")
    fun findAllAlbuns(): ResponseEntity<List<AlbumProjection>> {
        return ResponseEntity(
            (
                    this.musicService.findAllAlbuns()
                    ), HttpStatus.OK
        )
    }

    /**
     * Endpoint para buscar todas as fotos de um álbum específico.
     *
     * @param albumId ID do álbum para o qual as fotos serão recuperadas.
     * @return Resposta HTTP com uma lista de MusicPhotosProjection correspondente ao álbum.
     */
    @GetMapping("/album/{albumId}")
    fun findAllPhotosByAlbumId(
        @PathVariable albumId: Long
    ): ResponseEntity<List<MusicPhotosProjection>> {
        return ResponseEntity(
            (
                    this.musicService.findAllPhotosByAlbum(albumId)
                    ), HttpStatus.OK
        )
    }

}