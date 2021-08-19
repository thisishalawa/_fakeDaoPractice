package master.fake.fake.dao_practice.quote

import master.fake.fake.dao_practice.quote.data.FakeDatabase
import master.fake.fake.dao_practice.quote.repository.QuoteRepository
import master.fake.fake.dao_practice.quote.ui.QuotesViewModelFactory


/*
* Dependency injection ?


*  dependency injection is a way to modularize code.
* In production, you want your ViewModel to operate with a real repository
* which is fetching data from servers and all that. However,
* when you are just testing your ViewModel,
*  it’s a good idea to provide it with only some dummy data so that you can control everything
*  in your tests.

If you were to instantiate objects directly in each class,
*  providing dummy data for testing (aka mocking) would be hard if not impossible.
* You would need to change every single constructor call in every class you want to test.
*  Instead, you can create all of the dependencies in one place. Then if you need to test something,
*  you know where and what to change – only one class which constructs all of the dependencies.

This is a simple but at the same time effective form of dependency injection.
* Feel free to use a framework like Dagger2 for really complex projects.
*
*
*
* */
// Finally a singleton which doesn't need anything passed to the constructor
object InjectorUtils {

    // This will be called from QuotesActivity
    fun provideQuotesViewModelFactory(): QuotesViewModelFactory {



        // ViewModelFactory needs a repository, which in turn needs a DAO from a database
        // The whole dependency tree is constructed right here, in one place
        val quoteRepository = QuoteRepository.getInstance(FakeDatabase.getInstance().quoteDao)
        return QuotesViewModelFactory(quoteRepository)


    }
}