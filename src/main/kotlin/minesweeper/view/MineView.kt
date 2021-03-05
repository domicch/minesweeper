package minesweeper.view

import minesweeper.model.Mine

class MineView(
    private val mine: Mine
): GridItemView(mine) {

    override fun printTypeSpecific(printer: ConsolePrinter) {
        if(mine.stepped)
            printer.print("X")
        else
            printer.print("B")
    }

}