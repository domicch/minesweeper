package minesweeper.model

class Mine(row: Int, col: Int, grid: Grid): GridItem(row, col, grid) {

    override fun getType(): GridItemType {
        return GridItemType.MINE
    }
}