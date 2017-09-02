package v_iterating_and_collections.pets;

import v_iterating_and_collections.pets.skeletons.Person;
import v_iterating_and_collections.pets.skeletons.Pet;
import v_iterating_and_collections.pets.skeletons.PetFinder;
import org.mockito.Mock;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.initMocks;
import static v_iterating_and_collections.pets.skeletons.AnimalKind.CAT;
import static v_iterating_and_collections.pets.skeletons.AnimalKind.LIZARD;

public class PetMetricsTest {

    @Mock
    private PetFinder petFinder;

    @DataProvider(name = "testSubjects")
    public Object[][] testSubjects() {
        initMocks(this);

        return new Object[][] {
                {new LoopedMetrics(petFinder)},
                {new StreamedMetrics(petFinder)},
                {new KotlinMetrics(petFinder)},
        };
    }

    @Test(dataProvider = "testSubjects")
    public void shouldHandleEmptyInput(final PetMetrics testee) {
        final int result = testee.reptileAgeSum(emptyList());

        assertThat(result, is(0));
    }

    @Test(dataProvider = "testSubjects")
    public void shouldWorkForSinglePerson(final PetMetrics testee) {
        final Person person = new Person();
        final List<Person> singlePerson = asList(person);

        final Pet petAged10 = new Pet(10, LIZARD);
        final List<Pet> pets = asList(petAged10);

        given(petFinder.findPetsOf(person)).willReturn(pets);

        final int result = testee.reptileAgeSum(singlePerson);
        assertThat(result, is(10));
    }

    @Test(dataProvider = "testSubjects")
    public void shouldWorkForMultiplePersons(final PetMetrics testee) {
        final Person person = new Person();
        final Person anotherPerson = new Person();
        final List<Person> twoPeople = asList(person, anotherPerson);

        final Pet petAged10 = new Pet(10, LIZARD);
        final List<Pet> firstSetOfPets = asList(petAged10);

        final Pet petAged20 = new Pet(20, LIZARD);
        final List<Pet> secondSetOfPets = asList(petAged20);

        given(petFinder.findPetsOf(person)).willReturn(firstSetOfPets);
        given(petFinder.findPetsOf(anotherPerson)).willReturn(secondSetOfPets);

        final int result = testee.reptileAgeSum(twoPeople);
        assertThat(result, is(30));
    }

    @Test(dataProvider = "testSubjects")
    public void shouldWorkForMultiplePetsForSamePerson(final PetMetrics testee) {
        final Person person = new Person();
        final List<Person> twoPeople = asList(person);

        final Pet petAged10 = new Pet(10, LIZARD);
        final Pet petAged20 = new Pet(20, LIZARD);
        final List<Pet> pets = asList(petAged10, petAged20);

        given(petFinder.findPetsOf(person)).willReturn(pets);

        final int result = testee.reptileAgeSum(twoPeople);
        assertThat(result, is(30));
    }

    @Test(dataProvider = "testSubjects")
    public void shouldOnlyConsiderReptiles(final PetMetrics testee) {
        final Person person = new Person();
        final List<Person> twoPeople = asList(person);

        final Pet alivePet = new Pet(10, LIZARD);
        final Pet deadPet = new Pet(20, CAT);
        final List<Pet> pets = asList(alivePet, deadPet);

        given(petFinder.findPetsOf(person)).willReturn(pets);

        final int result = testee.reptileAgeSum(twoPeople);
        assertThat(result, is(10));
    }
}