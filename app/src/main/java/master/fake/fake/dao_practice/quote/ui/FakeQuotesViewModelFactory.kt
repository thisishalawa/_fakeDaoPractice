package master.fake.fake.dao_practice.quote.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import master.fake.fake.dao_practice.quote.repository.FakeQuoteRepository


/*
* Alright! Now get the dependency injection going,
*  then jump right into the QuotesActivity and make things work!
* , not yet.
*
*
* As you can see, QuotesViewModel requires a repository to function and that repository is passed
* into the constructor. The way that ViewModels are created / gotten from ViewModelProvider
*  (to prevent recreation on, say, orientation changes)  requires a ViewModelFactory class.
*  You simply cannot create ViewModels directly, instead, they are going to be created in a factory.
*
* */


// The same repository that's needed for QuotesViewModel
// is also passed to the factory
class FakeQuotesViewModelFactory(private val fakeQuoteRepository: FakeQuoteRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FakeQuotesViewModel(fakeQuoteRepository) as T
    }
}