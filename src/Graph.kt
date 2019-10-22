data class Node(val label : String)
data class Edge(val from : Node, val to: Node)
data class Graph(val nodes : List<Node>, val edges : List<Edge>)

fun Node.toLabel() : String = if (this.label == "") "_" else this.label

fun Graph.toDot(LR: Boolean = false) : String {
    var output = "digraph {\n${if (LR) "  rankdir=LR;\n" else ""}"
    for (edge in this.edges) {
        output += "  ${edge.from.toLabel()} -> ${edge.to.toLabel()};\n"
    }
    output += "}"
    return output
}