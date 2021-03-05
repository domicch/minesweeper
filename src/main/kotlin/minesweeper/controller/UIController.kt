package minesweeper.controller

import minesweeper.exceptions.GameException
import minesweeper.exceptions.InvalidInputException
import minesweeper.model.GameStatus
import minesweeper.view.ConsolePrinter
import kotlin.jvm.Throws

class UIController(
    private val gameController: GameController
) {
    private val inputRegex = Regex("^([of]) (\\d) (\\d)$")
    private val printer = ConsolePrinter()

    init {
        clearConsole()
    }

    fun run() {

        gameController.printGame(printer)

        println(
            """
                        
                        Win condition: All mines flagged and all other tiles opened
                        Lose condition: Mine opened
                        
                        Supported commands:
                        Action                           Template           E.g.             
                        Exit game                        q
                        Restart game                     r
                        Show mines (for debugging)       d
                    """.trimIndent()
        )
        when (gameController.getGameStatus()) {
            GameStatus.INIT -> {
                println(
                    """
                        Open tile                        o row column       o 5 5
                    """.trimIndent()
                )
            }
            GameStatus.STARTED -> {
                println(
                    """
                        Open tile                        o row column       o 5 5
                        Toggle flag tile                 f row column       f 5 5
                    """.trimIndent()
                )
            }
            GameStatus.WIN -> {
                println("""
                        You won!!!!!!!!
                        Press r to restart game
                """.trimIndent())
            }
            GameStatus.LOSE -> {
                println("""
                        You lost!!!!!!!!
                        Press r to restart game
                """.trimIndent())
            }
        }

        val input = readLine()
        processInput(input!!)

    }

    private fun clearConsole(){
        print("\u001b[H\u001b[2J")
    }

    private fun processInput(input: String) {
        var valid = true
        var trimmedInput = input.trim()

        clearConsole()

        validateCommand(trimmedInput)

        if (processCommonInput(trimmedInput)) {
            return
        }

        val commands = trimmedInput.split(" ")

        when (gameController.getGameStatus()) {
            GameStatus.INIT -> {
                when (commands[0]) {
                    "o" -> {
                        gameController.startGame(
                            commands[1].toInt(),
                            commands[2].toInt()
                        )
                    }
                    else -> {
                        valid = false;
                    }
                }
            }
            GameStatus.STARTED -> {
                when (commands[0]) {
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
                    else -> {
                        valid = false;
                    }
                }
            }
        }

        if (!valid)
            throw GameException("Invalid input")
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

//        val matchResult = inputRegex.find(input)
//        if (matchResult != null) {
//            if (matchResult.groups[1]!!.value == "o") {
//                val row = matchResult.groups[2]!!.value.toInt()
//                val col = matchResult.groups[3]!!.value.toInt()
//
//                gameController.startGame(row, col)
//            } else {
//                valid = false;
//            }
//        } else {
//            valid = false;
//        }
    }

    private fun processCommonInput(input: String): Boolean {
        when (input) {
            "d" -> {
                gameController.printDebug(printer)
                return true
            }
            "q" -> {
                gameController.exitGame()
                return true
            }
            "r" -> {
                gameController.resetGame()
                return true
            }
        }


        return false
    }
}