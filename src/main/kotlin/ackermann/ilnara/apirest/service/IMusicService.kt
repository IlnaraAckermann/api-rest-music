package ackermann.ilnara.apirest.service

import ackermann.ilnara.apirest.model.Music
import ackermann.ilnara.apirest.queryProjections.AlbumProjection
import ackermann.ilnara.apirest.queryProjections.MusicPhotosProjection

interface IMusicService {

    fun saveAll(musicList: List<Music>): List<Music>

    fun findAllAlbuns(): List<AlbumProjection>

    fun saveAllFromURL(url: String): List<Music>

    fun findAllPhotosByAlbum(albumId: Long): List<MusicPhotosProjection>


}