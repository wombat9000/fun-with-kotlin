package iii_nullables

data class PersonNullable(val father: PersonNullable?,
                          val mother: PersonNullable?,
                          val brother: PersonNullable?,
                          val sister: PersonNullable?,
                          val daughter: PersonNullable?,
                          val son: PersonNullable?)

