package com.kunal.learnandroid.paging.data.mappers

import com.kunal.learnandroid.paging.data.local.BeerEntity
import com.kunal.learnandroid.paging.data.remote.BeerDto
import com.kunal.learnandroid.paging.domain.Beer

fun BeerDto.toBeerEntity(): BeerEntity {
    return BeerEntity(
        id = id,
        name = name,
        tagline = tagline,
        description = description,
        firstBrewed = first_brewed,
        imageUrl = image_url
    )
}

fun BeerEntity.toBeer(): Beer {
    return Beer(
        id = id,
        name = name,
        tagline = tagline,
        description = description,
        firstBrewed = firstBrewed,
        imageUrl = imageUrl
    )
}