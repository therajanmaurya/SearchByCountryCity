package com.task.countriescities

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.task.countriescities.databinding.FragmentCountriesCitiesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CountriesCitiesFragment : Fragment() {

    private var _binding: FragmentCountriesCitiesBinding? = null
    private val binding get() = _binding!!

    val viewModel: CountriesCitiesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCountriesCitiesBinding.inflate(inflater, container, false).apply {
            fragment = this@CountriesCitiesFragment
            countriesCitiesViewModel = viewModel
            lifecycleOwner = this@CountriesCitiesFragment
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchCountriesData()
        viewModel.countries.observe(viewLifecycleOwner) {
            it.let {
                Log.d("Fetch Countries", it.msg)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
        val searchItem: MenuItem? = menu.findItem(R.id.action_search)
        val searchView: SearchView = searchItem?.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                // Toast like print
                // Run Search query here
                if (!searchView.isIconified) searchView.isIconified = true
                searchItem.collapseActionView()
                return false
            }

            override fun onQueryTextChange(s: String): Boolean {

                return false
            }
        })
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_search -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}