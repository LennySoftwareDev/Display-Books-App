package com.timetonic.displaybooksapp.data.api.modelresponse.getbooks

data class GetAllBooksResponseDto(
    val allBooks: AllBooks,
    val createdVNB: String,
    val req: String,
    val sstamp: Long,
    val status: String
)