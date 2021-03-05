package minesweeper.view

import minesweeper.model.EmptyMine
import minesweeper.model.GridItem
import minesweeper.model.GridItemType
import minesweeper.model.Mine
import java.lang.IllegalArgumentException

object GridItemViewFactory {
    fun createGridItemView(item: GridItem): GridItemView{
        return when(item.getType()) {
            GridItemType.MINE -> MineView(item as Mine)
            GridItemType.EMPTY_MINE -> EmptyMineView(item as EmptyMine)
            else -> {throw IllegalArgumentException("Invalid type")}
        }
    }
}