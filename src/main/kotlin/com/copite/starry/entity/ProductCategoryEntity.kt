package com.copite.starry.entity

import com.copite.starry.annotation.NoArgs
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime

@NoArgs
@Entity
@Table(name = "product_category")
class ProductCategoryEntity(
    @Id
    var ident: String = "",
    @Column(name = "name")
    var name: String = "",
    @Column(name = "sort")
    var sort: Int = 1,
    @Column(name = "description")
    var description: String? = null,
    @Column(name = "created_at")
    var createdAt: LocalDateTime = LocalDateTime.now(),
    @Column(name = "updated_at")
    var updatedAt: LocalDateTime = LocalDateTime.now(),
)
