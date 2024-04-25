package com.copite.starry.repository

import com.copite.starry.entity.ProductCategoryEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ProductCategoryRepository : JpaRepository<ProductCategoryEntity, String> {

}
