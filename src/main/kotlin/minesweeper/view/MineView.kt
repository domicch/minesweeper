package minesweeper.view

import minesweeper.model.Mine

class MineView(
    mine: Mine
): GridItemView(mine) {

    override fun printTypeSpecific(printer: ConsolePrinter) {
        printer.print("B")
    }

}