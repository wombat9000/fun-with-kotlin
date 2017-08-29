package iii_nullables;

public class JavaHandleOptionals {

    public PersonOptional getMother(PersonOptional subject) {
        return subject.getMother()
                .orElseThrow(PersonNotFoundException::new);
    }

    public PersonOptional getCousin(PersonOptional subject) {
      return subject.getMother()
              .flatMap(PersonOptional::getBrother)
              .flatMap(PersonOptional::getDaughter)
              .orElseThrow(PersonNotFoundException::new);
    }



























    private class PersonNotFoundException extends RuntimeException {}
}
