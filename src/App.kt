import kotlinx.html.js.onClickFunction
import react.*
import react.dom.*

data class State(val player1Input: String, val player2Input: String, val player1: Coins?, val player2: Coins?)

interface AppState: RState {
    var store : State
}

class App: RComponent<RProps, AppState>() {
    override fun AppState.init() {
        store = State("", "", null, null)
    }

    override fun RBuilder.render() {
        h1 {
            +"Let the battle begin!"
        }
        p {
            +"Type a sequence of letters H & T corresponding to a sequence of results of coin tosses."
        }

        playerInput {
            value = state.store.player1Input
            placeholder = "First player"
            onChangeSet = { setState { store = store.copy(player1Input = it) } }
        }

        playerInput {
            value = state.store.player2Input
            placeholder = "Second player"
            onChangeSet = { setState { store = store.copy(player2Input = it) } }
        }

        button {
            +"Battle!"
            attrs {
                onClickFunction = {
                    setState {
                        store = store.copy(
                                player1 = coinsFromInput(store.player1Input),
                                player2 = coinsFromInput(store.player2Input)
                        )
                    }
                }
            }
        }

        state.store.let {
            when {
                (it.player1 == null) -> p { +"Something is wrong with the first input - use only letters H & T." }
                (it.player2 == null) -> p { +"Something is wrong with the second input - use only letters H & T." }
                (it.player1.sequence.isEmpty()) -> p { +"First sequence has to be non-empty." }
                (it.player2.sequence.isEmpty()) -> p { +"Second sequence has to be non-empty." }
                else -> Graphviz {
                    attrs.dot = it.player1.battleGraph(it.player2).toDot(LR = true)
                    attrs.options = object {
                        var width = null
                        var height = null
                    }
                }
            }
        }
    }
}