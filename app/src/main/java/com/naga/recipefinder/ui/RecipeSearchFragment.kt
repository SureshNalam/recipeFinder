package com.naga.recipefinder.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_recipe_search.*
import com.naga.recipefinder.R
import com.naga.recipefinder.adapters.OnSearchListener
import com.naga.recipefinder.adapters.RecipeRecyclerAdapter
import com.naga.recipefinder.models.Hit

class RecipeSearchFragment : Fragment(), OnSearchListener {

    private lateinit var recipeSearchViewModel: RecipeSearchViewModel
    private lateinit var recyclerAdapter: RecipeRecyclerAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        recipeSearchViewModel = ViewModelProvider(requireActivity()).get(RecipeSearchViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_recipe_search, container, false)
        initRecyclerView(root)
        recipeSearchViewModel.apply {
            val results1: LiveData<List<Hit>> = recipes()

            val loading: LiveData<Boolean> = recipesLoading()


            results1.observe(viewLifecycleOwner, Observer {
                if (it != null) {
                    recyclerAdapter.list = it
                }
            })

            loading.observe(viewLifecycleOwner, Observer {
                emptySearchView.visibility = View.GONE
                pbSearch.visibility = if (it) View.VISIBLE else View.GONE
            })
        }

        return root
    }

    private fun initRecyclerView(root: View) {
        val rv = root.findViewById<RecyclerView>(R.id.rvSearchResults)
        recyclerAdapter = RecipeRecyclerAdapter(this)
        rv.adapter = recyclerAdapter
        rv.layoutManager =
            GridLayoutManager(context, resources.getInteger(R.integer.search_span_count))
    }

    companion object {
        @JvmStatic
        fun newInstance(): RecipeSearchFragment {
            return RecipeSearchFragment()
        }
    }

    override fun onSearchResultClick(position: Int) {

        val intent = Intent(context, RecipeInfoActivity::class.java)
        recipeSearchViewModel =
        recipeSearchViewModel.apply {
            val data = recipes().value?.get(position)?.recipe
            intent.putExtra("recipe", data)
        }

        startActivity(intent)
    }
}
