package minesweeper.model

data class EmptyMine(
    override val row: Int,
    override val col: Int
    ): GridItem() {
    var hint: Int? = null

    override fun getType(): GridItemType {
        return GridItemType.EMPTY_MINE
    }
}