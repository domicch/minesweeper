package minesweeper.view

import minesweeper.model.Game

class GameView(
    private var game: Game
): Printable{

    private var gridView: GridView = GridView(game.getGrid())

    override fun print(printer: ConsolePrinter) {
        printer.println("Status: ${game.getStatus()}")
        printer.println("Score: ${game.getScore()}")
        gridView.print(printer)
    }

    fun printDebug(printer: ConsolePrinter){
        printer.println("Status: ${game.getStatus()}")
        printer.println("Score: ${game.getScore()}")
        gridView.printDebug(printer)
    }
}