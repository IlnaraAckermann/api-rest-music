package ackermann.ilnara.apirest.queryProjections

import jakarta.persistence.Column

interface AlbumProjection {
    fun getAlbumId(): Long
}