package ackermann.ilnara.apirest.controller

import ackermann.ilnara.apirest.model.Music
import ackermann.ilnara.apirest.queryProjections.AlbumProjection
import ackermann.ilnara.apirest.queryProjections.MusicPhotosProjection
import ackermann.ilnara.apirest.service.impl.MusicService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


/**
 * Controlador para lidar com requisições relacionadas a álbuns.
 */

@CrossOrigin(origins = ["http://localhost:3000/"])
@RestController
@RequestMapping("/api/music")
@Tag(name = "Música")
class MusicController(
    private val musicService: MusicService
) {

    /**
     * Endpoint para salvar músicas a partir de uma URL.
     *
     * @param url URL de onde as músicas serão obtidas.
     * @return Resposta HTTP com a lista de músicas salvas.
     */
    @PostMapping("/url")
    @Operation(summary = "Recebe uma URL com dados JSON de músicas e salva as músicas no banco de dados.")

    fun saveAllFromURL(@RequestParam url: String): ResponseEntity<List<Music>> {
        val musicList = this.musicService.saveAllFromURL(url)
        return ResponseEntity(
            (musicList), HttpStatus.CREATED
        )
    }

    /**
     * Endpoint para salvar uma lista de músicas a partir de uma requisição com formato JSON.
     *
     * @param musicList Lista de músicas a serem salvas.
     * @return Resposta HTTP com a lista de músicas salvas.
     */
    @PostMapping("/list")
    @Operation(summary = "Recebe uma lista de músicas no formato JSON e salva as músicas no banco de dados")
    fun saveAllFromJson(@RequestBody musicList: List<Music>): ResponseEntity<List<Music>> {
        val savedMusicList = this.musicService.saveAll(musicList)
        return ResponseEntity(
            (savedMusicList), HttpStatus.CREATED
        )
    }


    /**
     * Endpoint para buscar todos os álbuns.
     *
     * @return Resposta HTTP com uma lista de AlbumProjection.
     */
    @GetMapping("/albuns")
    @Operation(summary = "Lista todos os álbuns", description = "Retorna todos os AlbumId disponíveis.")
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
    @Operation(summary = "Busca fotos de um álbum por ID", description = "Retorna todos os as imagens disponíveis do AlbumId especifico.")
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