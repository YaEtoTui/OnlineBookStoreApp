package sazhin.onlinebookstoreapp.di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import sazhin.onlinebookstoreapp.data.mapper.BookResponseToEntityMapper
import sazhin.onlinebookstoreapp.data.repository.BookRepository
import sazhin.onlinebookstoreapp.domain.IBookRepository
import sazhin.onlinebookstoreapp.viewModel.BookInCartViewModel
import sazhin.onlinebookstoreapp.viewModel.BookViewModel

val rootModule = module {
    single<IBookRepository> { BookRepository(get(), get(), get()) }
    factory { BookResponseToEntityMapper() }
    viewModel { BookViewModel(get(), get()) }
    viewModel { BookInCartViewModel(get()) }
}