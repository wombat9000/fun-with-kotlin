package v_iterating_and_collections.pets.skeletons;

public class Pet {

    private final int age;
    private final AnimalKind kind;

    public Pet(final int age, final AnimalKind kind) {
        this.age = age;
        this.kind = kind;
    }

    public int getAge() {
        return age;
    }

    public boolean isReptile() {
        return AnimalKind.LIZARD.equals(kind);
    }
}
