package master.fake.fake.dao_practice.quote.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import master.fake.fake.dao_practice.quote.InjectorUtils
import master.fake.fake.dao_practice.quote.data.Quote
import master.fake.fake.databinding.FragmentQuoteBinding

class QuoteFragment : Fragment() {

    // binding
    private var _binding: FragmentQuoteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQuoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        initializeUi()
    }

    private fun initializeUi() {
        // Get the QuotesViewModelFactory with all of it's dependencies constructed
        val factory = InjectorUtils.provideQuotesViewModelFactory()

        val viewModel = ViewModelProvider(this, factory).get(
            FakeQuotesViewModel::class.java
        )

        // Observing LiveData from the QuotesViewModel which in turn observes
        // LiveData from the repository, which observes LiveData from the DAO ☺
        viewModel.getQuotes().observe(viewLifecycleOwner, Observer { quotes ->
            val stringBuilder = StringBuilder()
            quotes.forEach { quote ->
                stringBuilder.append("$quote\n")
            }

            binding.textView.text = stringBuilder.toString()

        })


        // When button is clicked, instantiate a Quote and add it to DB through the ViewModel
        binding.button.setOnClickListener {
            val quote = Quote(binding.editText1.text.toString(), binding.editText2.text.toString())
            viewModel.addQuote(quote)
            binding.editText2.setText("")
            binding.editText1.setText("")
        }

    }
}