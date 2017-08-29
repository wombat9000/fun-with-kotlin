package iv_when

fun withWhen(x: Int) {

    when {
        x == 1 -> println("$x is one")
        x >= 100 -> println("$x is greater than or equal 100")
        x in 1..10 -> println("$x is between one and ten")
    }

    when(x) {
        in 1..10 -> println("$x is between one and ten")
        in listOf(1,2,3) -> println("$x contained in the list of 1,2,3")
    }
}
