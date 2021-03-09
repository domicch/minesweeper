package minesweeper.controller

import minesweeper.exceptions.GameException
import minesweeper.exceptions.InvalidInputException
import minesweeper.model.GameStatus
import minesweeper.view.ConsolePrinter
import kotlin.jvm.Throws

class UIController(

) {
    private val gameController = GameController()
    private val printer = ConsolePrinter()
    private var exitProgram = false

    init {
        clearConsole()
    }

    fun run() {
        while(!exitProgram) {
            try{
                mainLoop()
            }catch(e: Exception){
                println(e.message)
            }
        }
    }

    private fun mainLoop(){
        gameController.printGame()
        val input = readLine()
        processInput(input!!)
    }

    private fun clearConsole(){
        print("\u001b[H\u001b[2J")
    }

    private fun processInput(input: String) {
        val trimmedInput = input.trim()

        clearConsole()

        validateCommand(trimmedInput)

        val commands = trimmedInput.split(" ")

        when (commands[0]) {
            "d" -> {
                gameController.printDebug()
            }
            "q" -> {
                exitProgram = true
            }
            "r" -> {
                gameController.resetGame()
            }
            "o" -> {
                gameController.action(
                    commands[1].toInt(),
                    commands[2].toInt()
                )
            }
            "f" -> {
                gameController.toggleFlag(
                    commands[1].toInt(),
                    commands[2].toInt()
                )
            }
        }
    }

    @Throws(InvalidInputException::class)
    private fun validateCommand(input: String){

        val commands = input.split(" ")

        if(commands.isEmpty()){
            throw InvalidInputException("Invalid input")
        }

        when(commands[0]){
            "o", "f" -> {
                if(commands.size != 3){
                    throw InvalidInputException("Invalid input")
                }

                try{
                    commands[1].toInt()
                    commands[2].toInt()
                }catch(e:  NumberFormatException){
                    throw InvalidInputException("Invalid argument")
                }
            }
            "q", "d", "r" -> {
                if(commands.size != 1){
                    throw InvalidInputException("Invalid argument")
                }
            }
            else -> {
                throw InvalidInputException("Invalid input")
            }
        }

    }
}