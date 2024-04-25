package com.copite.starry.extension


import kotlin.reflect.KProperty1
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.primaryConstructor

// 少对多：缺失的需要实现默认参数
// 多对少：跳过无用的字段
inline fun <reified T : Any, reified R : Any> T.mapTo(): R {
    val sourceInstance = this
    val sourceProperties = T::class.declaredMemberProperties
    val sourceMapper = sourceProperties.associate {
        Pair(it.name, it.get(sourceInstance))
    }
    // val construct = R::class.primaryConstructor
    // val targetInstance = construct?.callBy(emptyMap()) ?: R::class.java.newInstance()
    val targetInstance = R::class.java.newInstance()
    val targetProperties = R::class.declaredMemberProperties
    targetProperties.forEach { prop ->
        val value = getPropertyValue(targetInstance, prop, sourceMapper[prop.name])
        setPropertyValue(targetInstance, prop, value)
    }
    return targetInstance
}

fun getPropertyValue(instance: Any, property: KProperty1<*, *>, value: Any?): Any? {
    if (value == null) {
        val field = instance.javaClass.getDeclaredField(property.name)
        field.isAccessible = true
        return field.get(instance)
    }
    return value
}

fun setPropertyValue(instance: Any, property: KProperty1<*, *>, value: Any?) {
    val field = instance.javaClass.getDeclaredField(property.name)
    field.isAccessible = true
    field.set(instance, value)
}
