package minesweeper.view

import minesweeper.model.EmptyMine

class EmptyMineView(
    private val emptyMine: EmptyMine
): GridItemView(emptyMine) {

    override fun printTypeSpecific(printer: ConsolePrinter) {
        printer.print(emptyMine.hint?.toString() ?: " ")
    }

}