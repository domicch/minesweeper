package minesweeper.model

class EmptyMine(row: Int, col: Int, grid: Grid): GridItem(row, col, grid) {
    var hint: Int? = null

    private val checkVector = arrayOf(
        arrayOf(-1,-1),
        arrayOf(-1,0),
        arrayOf(-1,1),
        arrayOf(0,1),
        arrayOf(1,1),
        arrayOf(1,0),
        arrayOf(1,-1),
        arrayOf(0,-1)
    )

    override fun getType(): GridItemType {
        return GridItemType.EMPTY_MINE
    }

    override fun open() {
        if(opened() || flagged())
            return

        this.opened = true
        val mineCount = countMinesAround(grid)

        if(mineCount > 0){
            hint = mineCount
        }else{
            checkVector.forEach {
                val checkRow = row + it[0]
                val checkCol = col + it[1]

                if(grid.inBounds(checkRow, checkCol)){
                    val item = grid.getGridItem(checkRow, checkCol)

                    if(!item.opened() && !item.flagged() &&
                        item.getType() != GridItemType.MINE)
                        grid.action(checkRow, checkCol)
                }
            }
        }
    }

    private fun countMinesAround(grid: Grid): Int{
        var mineCount = 0

        checkVector.forEach {
            val checkRow = row + it[0]
            val checkCol = col + it[1]

            if(grid.inBounds(checkRow, checkCol)){
                if(grid.getGridItem(checkRow, checkCol).getType() == GridItemType.MINE)
                    mineCount++
            }
        }

        return mineCount
    }
}