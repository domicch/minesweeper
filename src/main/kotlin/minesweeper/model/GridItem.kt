package minesweeper.model

abstract class GridItem{

    abstract val row: Int
    abstract val col: Int
    private var opened: Boolean = false
    private var flagged: Boolean = false

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