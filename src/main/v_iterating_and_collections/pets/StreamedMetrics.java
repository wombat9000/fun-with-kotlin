package v_iterating_and_collections.pets;

import v_iterating_and_collections.pets.skeletons.Person;
import v_iterating_and_collections.pets.skeletons.Pet;
import v_iterating_and_collections.pets.skeletons.PetFinder;

import java.util.List;

public class StreamedMetrics implements FamilyMetrics {

    private final PetFinder petFinder;

    StreamedMetrics(final PetFinder petFinder) {
        this.petFinder = petFinder;
    }

    @Override
    public int reptileAgeSum(final List<Person> people) {
        return people.stream()
                .map(petFinder::findPetsOf)
                .flatMap(List::stream)
                .filter(Pet::isReptile)
                .map(Pet::getAge)
                .reduce(0, Integer::sum);
    }
}
