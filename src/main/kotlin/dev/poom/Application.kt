package dev.poom

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import dev.poom.plugins.*

import dev.poom.utils.ComputeManager
import dev.poom.utils.OperationInterpreter
import dev.poom.utils.Register

fun main() {
    val code = """
        mov eax, 10
        add eax, eax
        add eax, 50
        sub eax, 20
        jmp 1
    """

    val manager = ComputeManager(code)
    manager.start()

    if (manager.isCorrect) {
        println("That is correct! +300 social credits.")
    } else {
        println("Incorrect Answer! -500 social credits.")
    }

    // embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
    //     configureRouting()
    // }.start(wait = true)
}
