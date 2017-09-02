package v_iterating_and_collections.pets

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.MockitoAnnotations.initMocks
import org.testng.annotations.DataProvider
import org.testng.annotations.Test
import v_iterating_and_collections.pets.skeletons.AnimalKind.CAT
import v_iterating_and_collections.pets.skeletons.AnimalKind.LIZARD
import v_iterating_and_collections.pets.skeletons.Person
import v_iterating_and_collections.pets.skeletons.Pet
import v_iterating_and_collections.pets.skeletons.PetFinder

class FamilyMetriksTest {

    @Mock private
    lateinit var petFinder: PetFinder

    @DataProvider(name = "testSubjects")
    fun testSubjects(): Array<Array<Any>> {
        initMocks(this)

        return arrayOf(
                arrayOf<Any>(LoopedMetrics(petFinder)),
                arrayOf<Any>(StreamedMetrics(petFinder)),
                arrayOf<Any>(KotlinMetrics(petFinder)))
    }

    @Test(dataProvider = "testSubjects")
    fun shouldHandleEmptyInput(testee: FamilyMetrics) {
        val zeroPersons = listOf<Person>()

        val result = testee.reptileAgeSum(zeroPersons)

        assertThat(result, `is`(0))
    }

    @Test(dataProvider = "testSubjects")
    fun shouldWorkForSinglePerson(testee: FamilyMetrics) {
        val person = Person()
        val singlePerson = listOf(person)

        val petAged10 = Pet(10, LIZARD)
        val pets = listOf(petAged10)

        given(petFinder.findPetsOf(person)).willReturn(pets)

        val result = testee.reptileAgeSum(singlePerson)
        assertThat(result, `is`(10))
    }

    @Test(dataProvider = "testSubjects")
    fun shouldWorkForMultiplePersons(testee: FamilyMetrics) {
        val person = Person()
        val anotherPerson = Person()
        val twoPeople = listOf(person, anotherPerson)

        val petAged10 = Pet(10, LIZARD)
        val firstSetOfPets = listOf(petAged10)

        val petAged20 = Pet(20, LIZARD)
        val secondSetOfPets = listOf(petAged20)

        given(petFinder.findPetsOf(person)).willReturn(firstSetOfPets)
        given(petFinder.findPetsOf(anotherPerson)).willReturn(secondSetOfPets)

        val result = testee.reptileAgeSum(twoPeople)
        assertThat(result, `is`(30))
    }

    @Test(dataProvider = "testSubjects")
    fun shouldWorkForMultiplePetsForSamePerson(testee: FamilyMetrics) {
        val person = Person()
        val twoPets = listOf(person)

        val petAged10 = Pet(10, LIZARD)
        val petAged20 = Pet(20, LIZARD)
        val firstSetOfRecos = listOf(petAged10, petAged20)

        given(petFinder.findPetsOf(person)).willReturn(firstSetOfRecos)

        val result = testee.reptileAgeSum(twoPets)
        assertThat(result, `is`(30))
    }

    @Test(dataProvider = "testSubjects")
    fun shouldOnlyConsiderReptiles(testee: FamilyMetrics) {
        val person = Person()
        val twoPersons = listOf(person)

        val petAged10 = Pet(10, LIZARD)
        val petAged20 = Pet(20, CAT)
        val firstSetOfPets = listOf(petAged10, petAged20)

        given(petFinder.findPetsOf(person)).willReturn(firstSetOfPets)

        val result = testee.reptileAgeSum(twoPersons)
        assertThat(result, `is`(10))
    }
}