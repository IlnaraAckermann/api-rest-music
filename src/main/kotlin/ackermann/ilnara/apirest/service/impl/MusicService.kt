package ackermann.ilnara.apirest.service.impl

import ackermann.ilnara.apirest.model.Music
import ackermann.ilnara.apirest.queryProjections.AlbumProjection
import ackermann.ilnara.apirest.queryProjections.MusicPhotosProjection
import ackermann.ilnara.apirest.repository.MusicRepository
import ackermann.ilnara.apirest.service.IMusicService
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.net.URI

@Service
class MusicService(private val musicRepository: MusicRepository): IMusicService {
    override fun saveAll(musicList: List<Music>): List<Music> =
        this.musicRepository.saveAll(musicList)


    override fun saveAllFromURL(url: String): List<Music> {

        val restTemplate = RestTemplate()
        val uri = URI.create(url)

        val response: Array<Music>? = restTemplate.getForObject(uri, Array<Music>::class.java)


      return   response?.toList()?.let { musicRepository.saveAll(it) } ?: emptyList()
    }


    override fun findAllAlbuns(): List<AlbumProjection> =
        this.musicRepository.findAllAlbumId()

    override fun findAllPhotosByAlbum(albumId: Long): List<MusicPhotosProjection> =
        this.findAllPhotosByAlbum(albumId)

}