package minesweeper.model

class Mine(row: Int, col: Int): GridItem(row, col) {

    override fun getType(): GridItemType {
        return GridItemType.MINE
    }
}