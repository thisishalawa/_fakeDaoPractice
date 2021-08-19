package master.fake.fake.dao_practice.quote.ui

import androidx.lifecycle.ViewModel
import master.fake.fake.dao_practice.quote.data.Quote
import master.fake.fake.dao_practice.quote.repository.QuoteRepository

/*
*Getting closer to user interface
You have done quite a bit of work already. Now it’s time to connect what you created to the “view” part of MVVM,
* in this case the QuoteActivity. Activities and Fragments are merely for displaying things on
* the screen and for handling user input. All of the logic, data,
*  manipulation with the data goes to a ViewModel.
* Then the View only calls functions on the ViewModel. This way,
*  the data does not get reset when an orientation change occurs and so on.
*
*
* */

// QuoteRepository dependency will again be passed in the
// constructor using dependency injection
class QuotesViewModel(private val quoteRepository: QuoteRepository)
    : ViewModel() {

    fun getQuotes() = quoteRepository.getQuotes()

    fun addQuote(quote: Quote) = quoteRepository.addQuote(quote)
}