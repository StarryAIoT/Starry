package com.copite.starry.util

/**
 * 雪花算法（Snowflake）是一种分布式唯一ID生成算法，它生成的ID包含了时间戳、数据中心ID、机器ID和序列号等信息，
 * 可以保证在分布式环境下生成全局唯一的ID。
 *
 * @property datacenterId 数据中心ID，用于区分不同的数据中心
 * @property machineId 机器ID，用于区分同一数据中心下的不同机器
 */
class Snowflake(private val datacenterId: Long, private val machineId: Long) {

    // 静态属性和常量
    companion object {
        // 初始时间戳，这里假设为2021-05-03 00:00:00
        private const val EPOCH = 1704067200000L

        // 每部分占用的位数
        private const val DATA_CENTER_ID_BITS = 5
        private const val MACHINE_ID_BITS = 5
        private const val SEQUENCE_BITS = 12
        private const val MAX_DATA_CENTER_ID = (1L shl DATA_CENTER_ID_BITS) - 1
        private const val MAX_MACHINE_ID = (1L shl MACHINE_ID_BITS) - 1
        private const val MAX_SEQUENCE = (1L shl SEQUENCE_BITS) - 1

        private const val TIMESTAMP_SHIFT = DATA_CENTER_ID_BITS + MACHINE_ID_BITS + SEQUENCE_BITS
        private const val DATA_CENTER_ID_SHIFT = MACHINE_ID_BITS + SEQUENCE_BITS
        private const val MACHINE_ID_SHIFT = SEQUENCE_BITS

        private var lastTimestamp = -1L
        private var sequence = 0L
    }

    /**
     * 生成下一个唯一ID
     *
     * @return 唯一ID
     * @throws RuntimeException 如果时钟回退，抛出异常
     */
    @Synchronized
    fun nextId(): Long {
        var timestamp = System.currentTimeMillis()
        if (timestamp < lastTimestamp) {
            throw RuntimeException("Clock moved backwards. Refusing to generate ID for ${lastTimestamp - timestamp} milliseconds.")
        }
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) and MAX_SEQUENCE
            if (sequence == 0L) {
                timestamp = tilNextMillis(lastTimestamp)
            }
        } else {
            sequence = 0
        }
        lastTimestamp = timestamp
        return (timestamp - EPOCH shl TIMESTAMP_SHIFT) or
                (datacenterId shl DATA_CENTER_ID_SHIFT) or
                (machineId shl MACHINE_ID_SHIFT) or
                sequence
    }

    /**
     * 等待下一个毫秒
     *
     * @param lastTimestamp 上次生成ID的时间戳
     * @return 下一个毫秒的时间戳
     */
    private fun tilNextMillis(lastTimestamp: Long): Long {
        var timestamp = System.currentTimeMillis()
        while (timestamp <= lastTimestamp) {
            timestamp = System.currentTimeMillis()
        }
        return timestamp
    }
}
