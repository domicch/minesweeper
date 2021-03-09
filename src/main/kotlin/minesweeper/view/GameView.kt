package minesweeper.view

import minesweeper.model.Game

class GameView(
    private val printer: ConsolePrinter
){
    private val gridView = GridView(printer)

    fun print(game: Game) {
        printer.println("Status: ${game.getStatus()}")
        printer.println("Score: ${game.getScore()}")
        gridView.print(game.getGrid())
    }

    fun printDebug(game: Game){
        printer.println("Status: ${game.getStatus()}")
        printer.println("Score: ${game.getScore()}")
        gridView.printDebug(game.getGrid())
    }
}