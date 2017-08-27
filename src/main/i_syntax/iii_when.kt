package i_syntax

import java.util.*


// "when" is a more versatile version of switch
// - is not restricted to primitives & Enums:
fun whenDasWörtchenWhenNichtWär(someCollection: List<String>): String {
    when(someCollection) {
        is LinkedList -> return "I'm a linked list!"
        is ArrayList -> return "I'm an array list!"
    //potentially expensive: creates throwaway object
        listOf("one", "two") -> return "I'm a linked list!"
    // else branch compulsory when return value is relied upon
        else -> throw RuntimeException("I don't know what you are!")
    }
}





// - can evaluate boolean expressions; replaces if-elseif chains
fun whenDasWörtchenWhenNichtWär2(someCollection: List<String>, a: Int, b: Int): String {
    return when {
        someCollection.isEmpty() -> "I'm pretty empty!"
        someCollection.contains("thisString") -> "I contain that string!"
        (a > b) -> "a is greater!"
        else -> throw RuntimeException("hmm nothing matched!")
    }
}



