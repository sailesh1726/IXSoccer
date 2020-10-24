package com.sparks.ixsoccer.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sparks.ixsoccer.R
import com.sparks.ixsoccer.databinding.FragmentSoccerDetailsBinding
import com.sparks.ixsoccer.ui.adapter.SoccerFixturesAdapter
import com.sparks.ixsoccer.util.XISoccerUtils
import com.sparks.ixsoccer.viewmodel.SoccerViewModel


class FixturesFragment : Fragment(R.layout.fragment_soccer_details){
    private lateinit var viewModel: SoccerViewModel
    private var fragmentSoccerDetailsBinding: FragmentSoccerDetailsBinding? = null
    private var viewAdapter: SoccerFixturesAdapter? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(
            SoccerViewModel::class.java
        )

        viewModel.fixtures.observe(viewLifecycleOwner, Observer {
            it?.let {
                viewAdapter?.updateList(it)
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentSoccerDetailsBinding.bind(view)
        fragmentSoccerDetailsBinding = binding
        setUpUI()
    }
    var flag:Boolean= false
    private fun setUpUI() {
        val viewManager = LinearLayoutManager(activity)
        viewAdapter = SoccerFixturesAdapter()
        fragmentSoccerDetailsBinding?.resultsRecyclerView?.apply {
            // use a linear layout manager
            layoutManager = viewManager

            // specify an viewAdapter
            adapter = viewAdapter
        }

        fragmentSoccerDetailsBinding?.floatingActionButton?.setOnClickListener {
            if(flag){
                flag=false
                filterListByMonth()
            }else{
                flag = true
                filterListByLeague()
            }
        }
    }

    override fun onDestroyView() {
        // Consider not storing the binding instance in a field
        // if not needed.
        fragmentSoccerDetailsBinding = null
        super.onDestroyView()
    }

    private fun filterListByLeague() {
        if(!viewModel.cacheFixtureList.isNullOrEmpty())
            viewAdapter?.updateList(XISoccerUtils.getListItemsByLeague(viewModel.cacheFixtureList))
    }

    private fun filterListByMonth() {
        if(!viewModel.cacheFixtureList.isNullOrEmpty())
            viewAdapter?.updateList(XISoccerUtils.getListItems(viewModel.cacheFixtureList))
    }
}
