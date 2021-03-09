package minesweeper.view

import minesweeper.model.EmptyMine
import minesweeper.model.Grid
import minesweeper.model.GridItemType
import minesweeper.model.Mine

class GridView(
    private val printer: ConsolePrinter
){
    private val mineView = MineView(printer)
    private val emptyMineView = EmptyMineView(printer)

    fun print(grid: Grid) {
        val openedMine = grid.getOpenedMine()
        val openedRow = openedMine?.row
        val openedCol = openedMine?.col

        printer.print("   ")
        for(col in 0 until grid.colCount){
            printer.print("$col  ")
        }
        printer.println("")

        for(row in 0 until grid.rowCount){
            printer.print("$row  ")

            for(col in 0 until grid.colCount){
                if(openedMine != null && row == openedRow && col == openedCol)
                    printer.print("X")
                else {
                    val gridItem = grid.getGridItem(row, col)

                    when (gridItem.getType()) {
                        GridItemType.MINE -> mineView.print(gridItem as Mine)
                        GridItemType.EMPTY_MINE -> emptyMineView.print(gridItem as EmptyMine)
                    }
                }
                printer.print("  ")
            }
            printer.println("")
        }
    }

    fun printDebug(grid: Grid){

        printer.print("   ")
        for(col in 0 until grid.colCount){
            printer.print("$col  ")
        }
        printer.println("")

        for(row in 0 until grid.rowCount){
            printer.print("$row  ")

            for(col in 0 until grid.colCount){
                val item = grid.getGridItem(row, col)

                if(item.getType() == GridItemType.MINE){
                    printer.print("B  ")
                }else {
                    emptyMineView.print(item as EmptyMine)
                    printer.print("  ")
                }
            }
            printer.println("")
        }
    }
}