package minesweeper.model

class Mine(row: Int, col: Int): GridItem(row, col) {
    var stepped = false

    override fun getType(): GridItemType {
        return GridItemType.MINE
    }
}