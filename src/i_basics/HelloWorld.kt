package i_basics

import java.util.*


// main as top level function
fun main(args: Array<String>) {
    println("Hello World")
}


// main as method on class
class Main {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println("Hello World from class")
        }
    }
}







































// if expression
// "Java style"
fun min(a: Int, b: Int): Int {
    if (a < b)
        return a
    else
        return b
}














// - functions can be declared on one line without {}
fun min3(a: Int, b: Int): Int = if (a < b) a else b


















































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





















class Dog(val name: String, var age: Int, val breed: String) {

    // methods are public by default
    fun eat(food: String) {
        happyBark()
    }

    private fun happyBark() {
        println("woof!")
    }
}

// explicit constructor for when you need visibility modifier / annotations
class DogOwner /*@Annotion*/ private constructor(val dog: Dog) {

    fun feedDog() {
        dog.eat("salmon lasagna")
    }
}

fun usingMyClass(dog: Dog) {

}







































data class Destructurable(val someProp: Int, val anotherProp: String)

fun destructureThis() {
    val destr = Destructurable(2, "two")
    val (x: Int, y: String) = destr

    println(x) // 2
    println(y) // "two"

    // ignore values with _
    val (z: Int, _) = destr
}


