import react.*
import react.dom.*

interface AppState: RState {
    var player1Input: String
    var player2Input: String
}

class App: RComponent<RProps, AppState>() {
    override fun AppState.init() {
        player1Input = ""
        player2Input = ""
    }

    override fun RBuilder.render() {
        h1 {
            +"Let the battle begin!"
        }
        p {
            +"Type a sequence of letters H & T corresponding to a sequence of results of coin tosses."
        }

        playerInput {
            value = state.player1Input
            placeholder = "First player"
            onChangeSet = { setState {player1Input = it} }
        }

        playerInput {
            value = state.player2Input
            placeholder = "Second player"
            onChangeSet = { setState {player2Input = it} }
        }

        val player1 = coinsFromInput(state.player1Input)
        val player2 = coinsFromInput(state.player2Input)

        when {
            (player1 == null) -> p {+"Something is wrong with the first input - use only letters H & T."}
            (player2 == null) -> p {+"Something is wrong with the second input - use only letters H & T."}
            (player1.sequence.isEmpty()) -> p {+"First sequence has to be non-empty."}
            (player2.sequence.isEmpty()) -> p {+"Second sequence has to be non-empty."}
            else -> Graphviz {
                attrs.dot = player1.battleGraph(player2).toDot(LR=true)
                attrs.options = object {
                    var width = null
                    var height = null
                }
            }
        }
    }
}