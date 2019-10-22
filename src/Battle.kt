data class Coins(val sequence: String)
data class BattleStates(val states: Set<Node>)

val BaseNode = Node("")
const val HEADS = 'H'
const val TAILS = 'T'

fun coinsFromInput(inp : String): Coins? {
    return if ((inp.filter {it != HEADS && it != TAILS}).isEmpty()) {
        Coins(inp)
    }
    else null
}

fun Coins.allStates(): Set<Node> {
    return if (this.sequence.isEmpty()) {
        setOf(BaseNode)
    } else {
        setOf(Node(this.sequence)) + Coins(this.sequence.dropLast(1)).allStates()
    }
}

fun Coins.battleStates(opponent: Coins): BattleStates {
    return BattleStates(this.allStates() + opponent.allStates())
}

fun Coins.battleGraph(opponent: Coins): Graph {
    val states = this.battleStates(opponent)
    val edges = mutableListOf<Edge>()
    for (state in states.states) {
        if (state.label !in listOf(this.sequence, opponent.sequence))
            for (coin in listOf(HEADS, TAILS)) {
                edges.add(Edge(Node(state.label), states.nextState(state, coin)))
            }
    }
    return Graph(states.toNodes(), edges)
}

fun BattleStates.findState(state: Node): Node {
    return if (state in this.states) {
        state
    } else {
        return this.findState(Node(state.label.drop(1)))
    }
}

fun BattleStates.nextState(state: Node, coin: Char): Node {
    val nextNode = Node(state.label + coin)
    return this.findState(nextNode)
}

fun BattleStates.toNodes(): List<Node> = this.states.toList()
