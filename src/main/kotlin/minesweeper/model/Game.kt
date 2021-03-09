package minesweeper.model

import minesweeper.exceptions.GameException
import minesweeper.exceptions.InvalidInputException
import kotlin.jvm.Throws
import kotlin.random.Random

class Game (){

    private val rowCount = 10
    private val colCount = 10
    private val mineCount = 20

    private var score = 0
    private var status = GameStatus.INIT
    private var grid = Grid(rowCount, colCount, mineCount)

    @Throws(GameException::class)
    fun action(row: Int, col: Int){
        checkBounds(row, col)

        when(status){
            GameStatus.INIT -> {
                grid.generateMines(row, col)
                grid.action(row, col)
                status = GameStatus.STARTED
            }
            GameStatus.STARTED -> {
                grid.action(row, col)
                updateWinLose()
            }
            else -> throw GameException("Invalid Status")
        }
    }

    private fun updateWinLose(){
        if(grid.checkLose()){
            grid.openAllMines()
            status = GameStatus.LOSE
        }else if(grid.checkWin()){
            status = GameStatus.WIN
        }
    }

    @Throws(GameException::class)
    fun toggleFlag(row:Int, col: Int){
        if(status != GameStatus.STARTED)
            throw GameException("Invalid Status")

        checkBounds(row, col)
        grid.toggleFlag(row, col)
        updateWinLose()
    }

    @Throws(InvalidInputException::class)
    private fun checkBounds(rol: Int, col: Int) {
        if(rol !in 0 until rowCount || col !in 0 until colCount){
            throw InvalidInputException("Input out of bounds")
        }
    }

    // any better way of creating read-only properties (but still modifiable inside the class)?
    fun getScore() = score
    fun getStatus() = status
    fun getGrid() = grid
}