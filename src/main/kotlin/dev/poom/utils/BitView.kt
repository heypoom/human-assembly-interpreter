package dev.poom.utils

class BitView(var bits: Int) {
    fun with(index: Int, value: Boolean): Int =
        if (value) bits or (1 shl index) else bits and (1 shl index).inv()

    fun get(index: Int) = (bits and (1 shl index) shr index) == 1

    fun set(index: Int, value: Boolean): BitView {
        bits = with(index, value)
        return this
    }
}

class FlagView(var bits: Int) {
    fun with(flag: Flag, value: Boolean): Int =
        BitView(bits).with(flag.i, value)

    fun get(flag: Flag): Boolean =
        BitView(bits).get(flag.i)

    fun set(flag: Flag, value: Boolean): FlagView {
        bits = with(flag, value)
        return this
    }
}
