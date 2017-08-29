package iii_nullables;

import java.util.Optional;

public class PersonOptional {

    private PersonOptional father;
    private PersonOptional mother;
    private PersonOptional daughter;
    private PersonOptional son;
    private PersonOptional sister;
    private PersonOptional brother;

    public Optional<PersonOptional> getFather() {
        return Optional.of(father);
    }

    public Optional<PersonOptional> getMother() {
        return Optional.of(mother);
    }

    public Optional<PersonOptional> getDaughter() {
        return Optional.of(daughter);
    }

    public Optional<PersonOptional> getSon() {
        return Optional.of(son);
    }

    public Optional<PersonOptional> getSister() {
        return Optional.of(sister);
    }

    public Optional<PersonOptional> getBrother() {
        return Optional.of(brother);
    }
}
