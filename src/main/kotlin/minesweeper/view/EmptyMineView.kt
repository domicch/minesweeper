package minesweeper.view

import minesweeper.model.EmptyMine

class EmptyMineView(
    printer: ConsolePrinter
): GridItemView(printer) {

    fun print(emptyMine: EmptyMine) {
        if(emptyMine.opened())
            printer.print(emptyMine.hint?.toString() ?: " ")
        else
            super.printClosedItem(emptyMine)
    }

}