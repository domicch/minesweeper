package minesweeper.view

import minesweeper.model.GridItem
import minesweeper.model.Mine

class MineView(printer: ConsolePrinter
): GridItemView(printer) {

    fun print(mine: Mine){
        if(mine.opened()){
            printer.print("B")
        }else{
            super.printClosedItem(mine)
        }
    }

//    override fun printTypeSpecific() {
//        printer.print("B")
//    }

}