package ackermann.ilnara.apirest.service

import ackermann.ilnara.apirest.model.Music
import ackermann.ilnara.apirest.queryProjections.AlbumProjection
import ackermann.ilnara.apirest.queryProjections.MusicPhotosProjection
import ackermann.ilnara.apirest.repository.MusicRepository
import ackermann.ilnara.apirest.service.impl.MusicService
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.context.ActiveProfiles
import org.springframework.web.client.RestTemplate
import java.net.URI

@ActiveProfiles("test")
@ExtendWith(MockKExtension::class)
class MusicServiceTest {

    @InjectMockKs
    lateinit var musicService: MusicService

    @MockK
    lateinit var musicRepository: MusicRepository

    @MockK
    lateinit var restTemplate: RestTemplate


    @Test
    fun `saveAllFromURL should save music list obtained from URL`() {
        val url = "https://jsonplaceholder.typicode.com/photos"
        val musicList = listOf(
            Music(1, "title1", "https://exemple/600/1", "https://exemple/150/1", 1),
            Music(2, "title2", "https://exemple/600/2", "https://exemple/150/2", 2),
            Music(3, "title3", "https://exemple/600/3", "https://exemple/150/3", 3)
        )
        val response: Array<Music> = musicList.toTypedArray()
        val uri = URI.create(url)
        every {
            restTemplate.getForObject(
            uri, Array<Music>::class.java
            )} returns response

        every { musicRepository.saveAll(any<List<Music>>()) } returns musicList

        val savedMusics = musicService.saveAllFromURL(url)

        assertThat(savedMusics).isNotNull
        assertThat(savedMusics).hasSize(3)
        assertThat(savedMusics).containsExactlyInAnyOrder(musicList[0],musicList[1],musicList[2])
        assertThat(savedMusics[0].id).isEqualTo(1)
        assertThat(savedMusics[1].title).isEqualTo("title2")
        assertThat(savedMusics[2].url).isEqualTo("https://exemple/600/3")
        assertThat(savedMusics[1].thumbnailUrl).isEqualTo("https://exemple/150/2")
        assertThat(savedMusics[0].albumId).isEqualTo(1)
        assertThat(savedMusics[0].id).isNotEqualTo(savedMusics[1].id)
    }

    @Test
    fun `findAllAlbuns should return list of AlbumProjection`() {

        val albumIdList = listOf(1L, 2L, 3L)
        val albumProjectionList = albumIdList.map {
            mockk<AlbumProjection> {
                every { getAlbumId() } returns it }
        }
        every { musicRepository.findAllAlbumId() } returns albumProjectionList

        val albuns = musicService.findAllAlbuns()

        assertThat(albuns).hasSize(3)
        assertThat(albuns[0].getAlbumId()).isEqualTo(1L)
        assertThat(albuns[1].getAlbumId()).isEqualTo(2L)
        assertThat(albuns[2].getAlbumId()).isEqualTo(3L)
    }


    @Test
    fun `findAllPhotosByAlbum should return list of MusicPhotosProjection`() {
        val albumId = 1L
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

        every { musicRepository.findAllPhotosByAlbumId(albumId) } returns photosList

        val result = musicService.findAllPhotosByAlbum(albumId)

        assertThat(result).isNotNull
        assertThat(result).isEqualTo(photosList)
        verify(exactly = 1) { musicRepository.findAllPhotosByAlbumId(albumId)}
    }
}