package dev.poom.utils

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class ComputeManagerTest {
    @Test
    fun start() {
        val manager = ComputeManager("""
            mov eax, 10
            add eax, eax
            add eax, 50
            sub eax, 20
        """)

        manager.start()
    }
}