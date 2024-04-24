package com.timetonic.displaybooksapp.data.api.modelresponse.getbooks

data class AllBooks(
    val books: List<Book>,
    val contacts: List<Any>,
    val nbBooks: Int,
    val nbContacts: Int
)