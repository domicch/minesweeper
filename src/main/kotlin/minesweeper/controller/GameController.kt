package minesweeper.controller

import minesweeper.model.Game
import minesweeper.model.GameStatus
import minesweeper.view.ConsolePrinter
import minesweeper.view.GameView

class GameController {
    private var game = Game()
    private val printer = ConsolePrinter()
    private var gameView = GameView(printer)

//    fun getGameStatus(): GameStatus{
//        return game.getStatus()
//    }

    fun printGame(){
        gameView.print(game)
        printExpectedCommands()
    }

    private fun printExpectedCommands(){
        printer.println(
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
        when (game.getStatus()) {
            GameStatus.INIT -> {
                printer.println(
                    """
                        Open tile                        o row column       o 5 5
                    """.trimIndent()
                )
            }
            GameStatus.STARTED -> {
                printer.println(
                    """
                            Open tile                        o row column       o 5 5
                            Toggle flag tile                 f row column       f 5 5
                        """.trimIndent()
                )
            }
            GameStatus.WIN -> {
                printer.println(
                    """
                            You won!!!!!!!!
                            Press r to restart game
                    """.trimIndent()
                )
            }
            GameStatus.LOSE -> {
                printer.println(
                    """
                            You lost!!!!!!!!
                            Press r to restart game
                    """.trimIndent()
                )
            }
        }
    }

//    fun startGame(row: Int, col: Int){
//        game.startGame(row, col)
//    }

    fun resetGame(){
        game = Game()
    }

    fun action(row: Int, col: Int){
        game.action(row, col)
    }

    fun toggleFlag(row: Int, col: Int){
        game.toggleFlag(row, col)
    }

    fun printDebug(){
        gameView.printDebug(game)
    }
}