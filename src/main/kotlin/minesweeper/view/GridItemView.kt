package minesweeper.view

import minesweeper.model.GridItem

abstract class GridItemView(
    private val gridItem: GridItem
): Printable {

    override fun print(printer: ConsolePrinter) {
        if(gridItem.opened()){
            printTypeSpecific(printer)
            return;
        }

        if(gridItem.flagged()){
            printFlagged(printer)
        }else{
            printClosed(printer)
        }
    }

    private fun printFlagged(printer: ConsolePrinter){
        printer.print("F")
    }

    private fun printClosed(printer: ConsolePrinter){
        printer.print("?")
    }

    protected abstract fun printTypeSpecific(printer: ConsolePrinter)
}