package com.copite.starry.service.impl

import com.copite.starry.extension.mapTo
import com.copite.starry.model.product.ProductCategory
import com.copite.starry.repository.ProductCategoryRepository
import com.copite.starry.service.ProductCategoryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import kotlin.jvm.optionals.getOrNull

@Service
class ProductCategoryServiceImpl : ProductCategoryService {
    @Autowired
    private lateinit var categoryRepo: ProductCategoryRepository

    override fun create(body: ProductCategory): Mono<ProductCategory> {
        val entity = categoryRepo.save(body.mapTo())
        return Mono.just(entity.mapTo())
    }

    override fun deleteById(id: String): Mono<Boolean> {
        return Mono.fromCallable { categoryRepo.deleteById(id) }.thenReturn(true)
    }

    override fun update(body: ProductCategory): Mono<ProductCategory> {
        val entity = categoryRepo.save(body.mapTo())
        return Mono.just(entity).map { it.mapTo() }
    }

    override fun queryById(id: String): Mono<ProductCategory?> {
        val entity = categoryRepo.findById(id)
        if (entity.isEmpty) return Mono.empty()
        return Mono.just(entity.get()).map { it.mapTo() }
    }

    override fun queryAll(): Flux<ProductCategory> {
        val entityList = categoryRepo.findAll()
        return Flux.fromIterable(entityList).map { it.mapTo() }
    }
}
