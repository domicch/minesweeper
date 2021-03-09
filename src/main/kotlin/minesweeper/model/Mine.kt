package minesweeper.model

data class Mine(
    override val row: Int,
    override val col: Int
): GridItem() {

    override fun getType(): GridItemType {
        return GridItemType.MINE
    }
}