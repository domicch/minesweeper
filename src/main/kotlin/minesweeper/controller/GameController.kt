package minesweeper.controller

import minesweeper.model.Game
import minesweeper.model.GameStatus
import minesweeper.view.ConsolePrinter
import minesweeper.view.GameView

class GameController {
    private var game = Game()
    private var gameView = GameView(game)

    fun getGameStatus(): GameStatus{
        return game.getStatus()
    }

    fun printGame(printer: ConsolePrinter){
        gameView.print(printer)
    }

    fun startGame(row: Int, col: Int){
        game.startGame(row, col)
        // since model reference is updated inside GridView after placing mines, create GameView again
        gameView = GameView(game)
    }

    fun resetGame(){
        game = Game()
        gameView = GameView(game)
    }

    fun action(row: Int, col: Int){
        game.action(row, col)
    }

    fun toggleFlag(row: Int, col: Int){
        game.toggleFlag(row, col)
    }

    fun exitGame(){
        game.exitGame()
    }

    fun printDebug(printer: ConsolePrinter){
        gameView.printDebug(printer)
    }
}