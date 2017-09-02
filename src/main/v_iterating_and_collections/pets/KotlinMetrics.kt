package v_iterating_and_collections.pets

import v_iterating_and_collections.pets.skeletons.Person
import v_iterating_and_collections.pets.skeletons.PetFinder

class KotlinMetrics(private val petFinder: PetFinder) : PetMetrics {
    override fun reptileAgeSum(people: List<Person>): Int {
        return people.asSequence()
                .flatMap { petFinder.findPetsOf(it).asSequence() }
                .filter { it.isReptile }
                .sumBy { it.age }
    }
}
