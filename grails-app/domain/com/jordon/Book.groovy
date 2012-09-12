package com.jordon

class Book {
    String title
    Date published
    Integer pages

    String toString() {
        return "${id}: ${title} - published ${published}"
    }

    static belongsTo = [author:Author]

    static constraints = {
        title( nullable: false, maxSize: 64 )
        published( nullable: false )
        pages( nullable: false )
    }
}
