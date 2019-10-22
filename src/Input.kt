import kotlinx.html.InputType
import kotlinx.html.js.onChangeFunction
import org.w3c.dom.HTMLInputElement
import react.*
import react.dom.*

interface PlayerInputProps : RProps {
    var placeholder: String
    var value: String
    var onChangeSet : (String) -> Unit
}

class PlayerInput(props: PlayerInputProps) : RComponent<PlayerInputProps, RState>(props) {
    override fun RBuilder.render() {
        input {
            attrs {
                placeholder = props.placeholder
                type = InputType.text
                value = props.value
                onChangeFunction = {
                    val target = it.target as HTMLInputElement
                    props.onChangeSet(target.value)
                }
            }
        }
    }
}

fun RBuilder.playerInput(handler: PlayerInputProps.() -> Unit): ReactElement {
    return child(PlayerInput::class) {
        this.attrs(handler)
    }
}