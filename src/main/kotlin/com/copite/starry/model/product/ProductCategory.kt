package com.copite.starry.model.product

import com.copite.starry.annotation.NoArgs
import java.time.LocalDateTime

@NoArgs
class ProductCategory(
    var ident: String = "",
    var name: String = "",
    var sort: Int = 1,
    var description: String? = null,
    var createdAt: LocalDateTime? = null,
    var updatedAt: LocalDateTime? = null
)
