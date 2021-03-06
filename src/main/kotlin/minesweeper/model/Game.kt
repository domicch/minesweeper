package minesweeper.model

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



    fun startGame(inputRow: Int, inputCol: Int){
        checkBounds(inputRow, inputCol)
        grid.generateMines(inputRow, inputCol)
        grid.action(inputRow, inputCol)
        status = GameStatus.STARTED
    }

    fun action(row: Int, col: Int){
        checkBounds(row, col)
        grid.action(row, col)

        updateWinLose()
    }

    private fun updateWinLose(){
        if(grid.checkLose()){
            grid.openAllMines()
            status = GameStatus.LOSE
        }else if(grid.checkWin()){
            status = GameStatus.WIN
        }
    }

    fun toggleFlag(row:Int, col: Int){
        checkBounds(row, col)
        grid.toggleFlag(row, col)
        updateWinLose()
    }

    fun exitGame(){
        status = GameStatus.EXIT
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

//    private fun generateMines(inputRow: Int, inputCol: Int){
//        val range = rowCount * colCount
//        var remain = mineCount
//        val list = mutableListOf<Int>()
//
//        while(remain > 0){
//            val num = Random.nextInt(0, range)
//            if(num !in list && num != inputRow * colCount + inputCol){
//                val mine = Mine(num / colCount, num % colCount)
//                val row = num / colCount
//                val col = num % colCount
//                grid.setGridItem(row, col, mine)
//                list += num
//                remain--
//            }
//        }
//    }
}