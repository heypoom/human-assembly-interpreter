package dev.poom.utils

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class BitViewTest {
    @Test
    fun `set bit`() {
        val n = BitView(0)
            .set(0, true)
            .set(2, true)
            .set(4, true)
            .bits

        assertEquals(0b10101, n)
    }

    @Test
    fun `set flag`() {
        val flag = FlagView(0)
            .set(Flag.ZERO, true)
            .set(Flag.PARITY, true)
            .bits

        assertEquals(0b1000100, flag)
    }
}