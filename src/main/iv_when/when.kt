package iv_when

fun withWhen(x: Int) {

    when {
        x == 1 -> println("$x is one")
        x >= 100 -> println("$x is greater than or equal 100")
        x in 1..10 -> println("$x is between one and ten")
    }

    when(x) {
        13, 21 -> println("$x is 13 or 21")
        in 1..10 -> println("$x is between one and ten")
        in listOf(19, 5, 123) -> println("$x contained in the list of 19, 5, 123")
    }
}
