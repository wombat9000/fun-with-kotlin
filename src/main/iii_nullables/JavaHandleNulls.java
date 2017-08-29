package iii_nullables;

public class JavaHandleNulls {

    public PersonNullable getSister(PersonNullable subject) {
        if (subject.getSister() != null) {
            return subject.getSister();
        }

        throw new PersonNotFoundException();
    }

    public PersonNullable getCousin(PersonNullable subject) {
        if (subject.getMother() != null
                && subject.getMother().getBrother() != null
                && subject.getMother().getBrother().getDaughter() != null) {
            return subject.getMother().getBrother().getDaughter();
        }

        throw new PersonNotFoundException();
    }

    private class PersonNotFoundException extends RuntimeException {}
}
