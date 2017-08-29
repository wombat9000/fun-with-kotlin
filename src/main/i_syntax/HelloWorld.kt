package i_syntax

// as top level function
fun main(args: Array<String>) {
    println("Hello World")
}

// as method on class
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


