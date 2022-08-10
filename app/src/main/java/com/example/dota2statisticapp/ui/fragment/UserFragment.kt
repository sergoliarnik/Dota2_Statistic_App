package com.example.dota2statisticapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.dota2statisticapp.databinding.FragmentUserBinding
import com.example.dota2statisticapp.ui.viewmodel.UserViewModel


class UserFragment : Fragment() {

    private lateinit var binding: FragmentUserBinding
    private lateinit var viewModel: UserViewModel
    // View
    private lateinit var userNameTextView: TextView
    private lateinit var userIdEditText: EditText
    private lateinit var findUserButton: Button
    private lateinit var showHeroesButton: Button



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserBinding.inflate(inflater, container, false)
        val view = binding.root
        userNameTextView = binding.userNameTextView
        userIdEditText = binding.userIdEditText
        findUserButton = binding.findUserButton
        showHeroesButton = binding.showHeroesButton
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        findUserButton.setOnClickListener {
            viewModel.getUser(userIdEditText.text.toString())
            viewModel.user.observe(viewLifecycleOwner) {
                userNameTextView.text = it.body()!!.profile.personaname
            }
        }
        showHeroesButton.setOnClickListener {
            val action = UserFragmentDirections
                .actionUserFragmentToHeroesListFragment()
            requireView().findNavController().navigate(action)
        }
    }
}