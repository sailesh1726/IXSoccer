package com.sparks.ixsoccer.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sparks.ixsoccer.R
import com.sparks.ixsoccer.databinding.FragmentFixturesBinding
import com.sparks.ixsoccer.databinding.FragmentResultsBinding
import com.sparks.ixsoccer.ui.adapter.SoccerFixturesAdapter
import com.sparks.ixsoccer.ui.adapter.SoccerResultsAdapter
import com.sparks.ixsoccer.viewmodel.SoccerViewModel


class FixturesFragment : Fragment(R.layout.fragment_fixtures) {
    private lateinit var viewModel: SoccerViewModel
    private var fragmentFixturesBinding: FragmentFixturesBinding? = null
    private var viewAdapter: SoccerFixturesAdapter? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(
            SoccerViewModel::class.java
        )

        viewModel.fixtures.observe(viewLifecycleOwner, Observer {
            it?.let {
                viewAdapter?.updateFixtures(it)
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentFixturesBinding.bind(view)
        fragmentFixturesBinding = binding
        setUpUI()
    }

    private fun setUpUI() {
        val viewManager = LinearLayoutManager(activity)
        viewAdapter = SoccerFixturesAdapter()
        fragmentFixturesBinding?.resultsRecyclerView?.apply {
            // use a linear layout manager
            layoutManager = viewManager

            // specify an viewAdapter
            adapter = viewAdapter
        }
    }

    override fun onDestroyView() {
        // Consider not storing the binding instance in a field
        // if not needed.
        fragmentFixturesBinding = null
        super.onDestroyView()
    }
}