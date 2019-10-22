data class Node(val label : String)
data class Edge(val from : Node, val to: Node)
data class Graph(val nodes : List<Node>, val edges : List<Edge>)

fun Node.toLabel() : String = if (this.label == "") "_" else this.label

fun Graph.toDot(LR: Boolean = false) : String {
    var output = "digraph {\n${if (LR) "  rankdir=LR;\n" else ""}"
    for (edge in edges) {
        val constraint = if (edge.from.label.length < edge.to.label.length) "" else "[constraint=false]"
        output += "  ${edge.from.toLabel()} -> ${edge.to.toLabel()} $constraint;\n"
    }
    output += "}"
    return output
}