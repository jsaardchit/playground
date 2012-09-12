package com.jordon

class Author {
    String name
    Integer age

    String toString() {
        return "${id}: ${name} - ${age} years old."
    }

    static hasMany = [ books:Book ]

    static constraints = {
        name( nullable: false, blank: false, maxSize: 64 )
        age( nullable: false, min: 18 )
    }
}
