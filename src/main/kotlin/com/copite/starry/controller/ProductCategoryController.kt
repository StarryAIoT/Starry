package com.copite.starry.controller

import com.copite.starry.model.product.ProductCategory
import com.copite.starry.service.ProductCategoryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/product/category")
class ProductCategoryController {
    @Autowired
    private lateinit var categorySrv: ProductCategoryService

    @PostMapping("/create")
    fun create(@RequestBody body: ProductCategory): Mono<ProductCategory> {
        return categorySrv.create(body)
    }

    @PostMapping("/delete/{categoryId:.+}")
    fun deleteById(@PathVariable categoryId: String): Mono<Boolean> {
        return categorySrv.deleteById(categoryId)
    }

    @PostMapping("/update")
    fun update(@RequestBody body: ProductCategory): Mono<ProductCategory> {
        return categorySrv.update(body)
    }

    @GetMapping("/query/{categoryId:.+}")
    fun queryById(@PathVariable categoryId: String): Mono<ProductCategory?> {
        return categorySrv.queryById(categoryId)
    }

    @GetMapping("/query")
    fun queryAll(): Flux<ProductCategory> {
        return categorySrv.queryAll()
    }
}
