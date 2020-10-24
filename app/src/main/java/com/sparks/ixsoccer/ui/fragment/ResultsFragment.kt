package com.sparks.ixsoccer.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sparks.ixsoccer.R
import com.sparks.ixsoccer.databinding.FragmentResultsBinding
import com.sparks.ixsoccer.ui.adapter.SoccerResultsAdapter
import com.sparks.ixsoccer.viewmodel.SoccerViewModel

class ResultsFragment : Fragment(R.layout.fragment_results) {
    private lateinit var viewModel: SoccerViewModel
    private var fragmentFirstBinding: FragmentResultsBinding? = null
    private var viewAdapter: SoccerResultsAdapter? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(
            SoccerViewModel::class.java
        )

        viewModel.results.observe(viewLifecycleOwner, Observer {
            it?.let {
                viewAdapter?.updateResults(it)
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentResultsBinding.bind(view)
        fragmentFirstBinding = binding
        setUpUI()
    }

    private fun setUpUI() {
        val viewManager = LinearLayoutManager(activity)
        viewAdapter = SoccerResultsAdapter()
        fragmentFirstBinding?.resultsRecyclerView?.apply {
            // use a linear layout manager
            layoutManager = viewManager

            // specify an viewAdapter
            adapter = viewAdapter
        }
    }

    override fun onDestroyView() {
        // Consider not storing the binding instance in a field
        // if not needed.
        fragmentFirstBinding = null
        super.onDestroyView()
    }
}
