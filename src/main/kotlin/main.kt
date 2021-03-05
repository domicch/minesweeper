import minesweeper.controller.GameController
import minesweeper.controller.UIController
import minesweeper.exceptions.GameException
import minesweeper.model.GameStatus

fun main(args: Array<String>) {
    val gameController = GameController()
    val inputReader = UIController(gameController)

    while (gameController.getGameStatus() != GameStatus.EXIT) {
        try {
            inputReader.run()
        }catch(e: GameException){
            println(e.message)
        }
    }

}
