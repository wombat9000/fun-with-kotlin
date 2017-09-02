package v_iterating_and_collections.pets;

import v_iterating_and_collections.pets.skeletons.Person;
import v_iterating_and_collections.pets.skeletons.Pet;
import v_iterating_and_collections.pets.skeletons.PetFinder;

import java.util.List;

public class LoopedMetrics implements PetMetrics {

    private final PetFinder recoFetcher;

    LoopedMetrics(final PetFinder recoFetcher) {
        this.recoFetcher = recoFetcher;
    }

    @Override
    public int reptileAgeSum(final List<Person> people) {
        int sum = 0;

        for (Person person : people) {
            final List<Pet> pets = recoFetcher.findPetsOf(person);
            for (Pet pet : pets) {
                if(pet.isReptile()) {
                    sum += pet.getAge();
                }
            }
        }

        return sum;
    }
}
