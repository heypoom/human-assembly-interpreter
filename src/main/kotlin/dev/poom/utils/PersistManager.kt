package dev.poom.utils

import io.lettuce.core.*

class PersistManager {
    val client = RedisClient.create("redis://password@localhost:6379/0")
    val conn = client.connect()
    val cmd = conn.sync()
}
