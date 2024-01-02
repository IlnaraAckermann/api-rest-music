package ackermann.ilnara.apirest.controler

import ackermann.ilnara.apirest.controller.MusicController
import ackermann.ilnara.apirest.model.Music
import ackermann.ilnara.apirest.queryProjections.AlbumProjection
import ackermann.ilnara.apirest.queryProjections.MusicPhotosProjection
import ackermann.ilnara.apirest.service.impl.MusicService
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.http.HttpStatus
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles("test")
@ExtendWith(MockKExtension::class)
class MusicControllerTest {
    @InjectMockKs
    lateinit var musicController: MusicController
    @MockK
    lateinit var musicService: MusicService

    @Test
    fun `Test saveAllFromURL`(){
        val url = "https://jsonplaceholder.typicode.com/photos"
        val musicList = listOf(
            Music(1, "title1", "https://exemple/600/1", "https://exemple/150/1", 1),
            Music(2, "title2", "https://exemple/600/2", "https://exemple/150/2", 2),
            Music(3, "title3", "https://exemple/600/3", "https://exemple/150/3", 3)
        )
        every {
            musicService.saveAllFromURL(url)
        } returns musicList

        val responseEntity =
            musicController.saveAllFromURL(url)

        assertThat(responseEntity).isNotNull
        assertThat(responseEntity.statusCode).isEqualTo(HttpStatus.CREATED)
        assertThat(responseEntity.body).isEqualTo(musicList)

    }

    @Test
    fun `Test findAllAlbuns`(){
        val albumIdList = listOf(1L, 2L, 3L)
        val albumProjectionList = albumIdList.map {
            mockk<AlbumProjection> {
                every { getAlbumId() } returns it }
        }
        every { musicService.findAllAlbuns() } returns albumProjectionList
        val responseEntity =
            musicController.findAllAlbuns()
        assertThat(responseEntity).isNotNull
        assertThat(responseEntity.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(responseEntity.body).isEqualTo(albumProjectionList)
    }

    @Test
    fun `Test findAllPhotosByAlbumId`(){
        val pathId = 1L
        val photosList = listOf(
            mockk<MusicPhotosProjection> {
                every { getUrl() } returns "http://example.com/photo1"
                every { getThumbnailURL() } returns "http://example.com/thumbnail1"
            },
            mockk<MusicPhotosProjection> {
                every { getUrl() } returns "http://example.com/photo2"
                every { getThumbnailURL() } returns "http://example.com/thumbnail2"
            }
        )
        every {musicService.findAllPhotosByAlbum(pathId)} returns photosList
        val responseEntity = musicController.findAllPhotosByAlbumId(pathId)

        assertThat(responseEntity).isNotNull
        assertThat(responseEntity.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(responseEntity.body).isEqualTo(photosList)
    }
}