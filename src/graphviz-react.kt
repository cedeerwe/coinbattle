 @file:JsModule("graphviz-react")

 import react.RClass
 import react.RProps

 @JsName("Graphviz")
 external val Graphviz: RClass<GraphvizProps>

 external interface GraphvizProps : RProps {
  var dot : String
  var options: GraphvizOptionProps?
 }

 external interface GraphvizOptionProps : RProps {
  var width : Number?
  var height: Number?
 }