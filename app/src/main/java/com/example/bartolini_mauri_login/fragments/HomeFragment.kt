package com.example.bartolini_mauri_login.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bartolini_mauri_login.R
import com.example.bartolini_mauri_login.ViewModels.MainViewModel
import com.example.bartolini_mauri_login.adapters.PolicyAdapter
import com.example.bartolini_mauri_login.models.policy.Policy

class HomeFragment : Fragment() {

    private val viewModel : MainViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.policies.observe(viewLifecycleOwner){policies ->

            val autoPolicies = ArrayList<Policy>()
            for (policy in policies){
                if(policy.title.equals("AUTO E MOTORI")){
                    autoPolicies.add(policy)
                }
            }

            val policiesRecycler = view.findViewById<RecyclerView>(R.id.auto_policies_recycler)
            val policiesAdapter = PolicyAdapter(autoPolicies)

            policiesRecycler.apply {
                adapter = policiesAdapter
                layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            }
        }
    }

}