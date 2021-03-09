package minesweeper.view

import minesweeper.model.GridItem

abstract class GridItemView(
    protected val printer: ConsolePrinter
) {

    protected fun printClosedItem(gridItem: GridItem) {
        if(gridItem.flagged()){
            printFlagged()
        }else{
            printClosed()
        }
    }

    private fun printFlagged(){
        printer.print("F")
    }

    private fun printClosed(){
        printer.print("?")
    }
}