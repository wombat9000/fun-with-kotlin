package iii_nullables

fun getMother(subject: PersonNullable): PersonNullable {
    return subject.mother?:
            throw PersonNotFoundException()
}

fun getCousin(subject: PersonNullable): PersonNullable {
    return subject.mother?.brother?.son?:
            throw PersonNotFoundException()
}


















class PersonNotFoundException : RuntimeException()