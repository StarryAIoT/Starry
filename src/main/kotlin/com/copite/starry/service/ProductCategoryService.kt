package com.copite.starry.service

import com.copite.starry.model.product.ProductCategory
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface ProductCategoryService {
    fun create(body: ProductCategory): Mono<ProductCategory>

    fun deleteById(id: String): Mono<Boolean>

    fun update(body: ProductCategory): Mono<ProductCategory>

    fun queryById(id: String): Mono<ProductCategory?>

    fun queryAll(): Flux<ProductCategory>
}
