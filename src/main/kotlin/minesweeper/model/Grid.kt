package minesweeper.model

import minesweeper.exceptions.GameException
import kotlin.random.Random

class Grid(
    val rowCount: Int,
    val colCount: Int,
    private val mineCount: Int
    ) {


    private var gridArray = arrayOf<Array<GridItem>>()
    private var closedItemCount = rowCount * colCount
    private var flaggedCount = 0
    private var openedMine: GridItem? = null
    private var mineList = mutableListOf<GridItem>()

    init {
        // TODO("Any better way of using 2D array?")
        for(row in 0 until rowCount){
            var colArr = arrayOf<GridItem>()
            for(col in 0 until colCount){
                colArr += EmptyMine(row, col, this)
            }
            gridArray += colArr
        }
    }

    fun getGridItem(row: Int, col: Int): GridItem{
        return gridArray[row][col]
    }

//    fun setGridItem(row: Int, col: Int, item: GridItem){
//        gridArray[row][col] = item
//    }

    fun generateMines(inputRow: Int, inputCol: Int){
        val range = rowCount * colCount
        var remain = mineCount
        val list = mutableListOf<Int>()

        while(remain > 0){
            val num = Random.nextInt(0, range)
            if(num !in list && num != inputRow * colCount + inputCol){
                val mine = Mine(num / colCount, num % colCount, this)
                val row = num / colCount
                val col = num % colCount
                gridArray[row][col] = mine
                list += num
                mineList.add(mine)
                remain--
            }
        }
    }

    fun action(row: Int, col: Int){
        val item = gridArray[row][col]

        if(item.opened()){
            throw GameException("Item is already opened")
        }

        if(item.flagged())
            throw GameException("Item is flagged")

        when(item.getType()){
            GridItemType.EMPTY_MINE -> handleEmptyMine(item)
            GridItemType.MINE -> handleMine(item as Mine)
        }
    }

    fun toggleFlag(row: Int, col: Int){
        val item = gridArray[row][col]

        if(item.opened()){
            throw GameException("Item is already opened")
        }

        if(item.flagged()){
            item.unflag()
            flaggedCount--
        }else{
            item.flag()
            flaggedCount++
        }
    }

    fun checkLose(): Boolean {
        return openedMine != null
    }

    fun checkWin(): Boolean{
        if(closedItemCount - flaggedCount == 0 && flaggedCount == mineList.size){
            return mineList.all{mine -> mine.flagged()}
        }
        return false
    }

    fun openAllMines(){
        mineList.forEach{it.open()}
    }

    fun inBounds(rol: Int, col: Int): Boolean {
        return rol in 0 until rowCount && col in 0 until colCount
    }

    fun getOpenedMine(): GridItem?{
        return openedMine
    }

    /**
     * things to do:
     * 1. update mine count of myself. If > 0 that's it
     * 2. if mine count == 0, recursively do mine count around direct neighbours
     */
    private fun handleEmptyMine(item: GridItem) {
        item.open()
        closedItemCount--
    }

    private fun handleMine(item: Mine) {
        item.open()
        openedMine = item
    }


}