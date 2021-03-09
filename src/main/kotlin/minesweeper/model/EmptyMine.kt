package minesweeper.model

class EmptyMine(row: Int, col: Int): GridItem(row, col) {
    var hint: Int? = null

    override fun getType(): GridItemType {
        return GridItemType.EMPTY_MINE
    }
}