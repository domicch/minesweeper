package minesweeper.model

abstract class GridItem(
    val row: Int,
    val col: Int
    ) {

    protected var opened: Boolean = false
    protected var flagged: Boolean = false

    abstract fun getType(): GridItemType

    open fun open(){
        this.opened = true
    }

    fun opened(): Boolean{
        return this.opened
    }

    fun flag(){
        this.flagged = true
    }

    fun unflag(){
        this.flagged = false
    }

    fun flagged(): Boolean{
        return this.flagged
    }

}