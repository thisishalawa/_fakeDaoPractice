package master.fake.fake.dao_practice.quote.repository

import master.fake.fake.dao_practice.quote.data.FakeQuoteDao
import master.fake.fake.dao_practice.quote.data.Quote

/*
* Repository as a point of control
Repositories are classes which do all of the decision making regarding app’s data.
*  Should you fetch new data from the server or is it enough to use the local data?
* Do you need to keep 5 days of weather data localy
*  or only 3 days? Making such decisions is the job of a repository.
I know, having a repository class in this particular project may seem redundant
* – you only have a single DAO and you don’t even have a backend. While this is true,
* this does not stop you from learning about the core work repositories have to do –
*  being the single source of truth for all the data which ViewModels request.
Again, it does not make sense to have multiple repository objects, so it will be a singleton.
*  This time you need to pass in the FakeQuoteDao for repository to fulfill its role.
*  You will use dependency injection to supply this FakeQuoteDao instance to the repository.
*
* */
// FakeQuoteDao must be passed in - it is a dependency
// You could also instantiate the DAO right inside the class without all the fuss, right?
// No. This would break testability - you need to be able to pass a mock version of a DAO
// to the repository (e.g. one that upon calling getQuotes() returns a dummy list of quotes for testing)
// This is the core idea behind DEPENDENCY INJECTION - making things completely modular and independent.

class QuoteRepository private constructor(private val quoteDao: FakeQuoteDao) {

    // This may seem redundant.
    // Imagine a code which also updates and checks the backend.
    fun addQuote(quote: Quote) {
        quoteDao.addQuote(quote)
    }

    fun getQuotes() = quoteDao.getQuotes()

    companion object {
        // Singleton instantiation you already know and love
        @Volatile
        private var instance: QuoteRepository? = null

        fun getInstance(quoteDao: FakeQuoteDao) =
            instance ?: synchronized(this) {
                instance ?: QuoteRepository(quoteDao).also { instance = it }
            }
    }


}