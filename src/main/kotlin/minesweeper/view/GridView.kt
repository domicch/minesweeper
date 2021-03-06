package minesweeper.view

import minesweeper.model.Grid
import minesweeper.model.GridItemType

class GridView(
    private val grid: Grid
): Printable {

    private var gridViewArray = arrayOf<Array<GridItemView>>()

    init {

        for(row in 0 until grid.rowCount){
            var colArr = arrayOf<GridItemView>()
            for(col in 0 until grid.colCount){
                colArr += GridItemViewFactory.createGridItemView(grid.getGridItem(row, col))
            }
            gridViewArray += colArr
        }
    }

    override fun print(printer: ConsolePrinter) {
        val openedMine = grid.getOpenedMine()
        val openedRow = openedMine?.row
        val openedCol = openedMine?.col

        printer.print("   ")
        for(col in 0 until grid.colCount){
            printer.print("$col  ")
        }
        printer.println("")

        for(row in gridViewArray.indices){
            printer.print("$row  ")

            for(col in gridViewArray[row].indices){
                if(openedMine != null && row == openedRow && col == openedCol)
                    printer.print("X")
                else
                    gridViewArray[row][col].print(printer)
                printer.print("  ")
            }
            printer.println("")
        }
    }

    fun printDebug(printer: ConsolePrinter){
        printer.print("   ")
        for(col in 0 until grid.colCount){
            printer.print("$col  ")
        }
        printer.println("")

        for(row in gridViewArray.indices){
            printer.print("$row  ")

            for(col in gridViewArray[row].indices){
                val item = grid.getGridItem(row, col)

                if(item.getType() == GridItemType.MINE){
                    printer.print("B  ")
                }else {
                    gridViewArray[row][col].print(printer)
                    printer.print("  ")
                }
            }
            printer.println("")
        }
    }
}