package com.example.dota2statisticapp.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.dota2statisticapp.data.model.User
import com.example.dota2statisticapp.databinding.FragmentUserBinding
import com.example.dota2statisticapp.ui.viewmodel.UserViewModel
import com.example.dota2statisticapp.util.Util
import com.squareup.picasso.Picasso
import java.util.*


class UserFragment : Fragment() {

    private lateinit var binding: FragmentUserBinding
    private lateinit var viewModel: UserViewModel

    // View
    private lateinit var userNameTextView: TextView
    private lateinit var userCountryTextView: TextView
    private lateinit var userIdEditText: EditText
    private lateinit var findUserButton: Button
    private lateinit var showHeroesButton: Button
    private lateinit var avatarImageView: ImageView
    private lateinit var mmrTextView: TextView


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
        avatarImageView = binding.avatarImageView
        userCountryTextView = binding.userCountryTextView
        mmrTextView = binding.mmrTextView
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        findUserButton.setOnClickListener {
            viewModel.getUser(userIdEditText.text.toString())
            viewModel.user.observe(viewLifecycleOwner) {
                val user = it.body()!!
                Log.e("test", Util.getCountry(user))
                Log.e("test", user.toString())

                userNameTextView.text = user.profile.personaname
                userCountryTextView.text = Util.getCountry(user)
                mmrTextView.text = "MMR: ${user.mmr_estimate.estimate}"

                Picasso.with(view.context)
                    .load(user.profile.avatarfull)
                    .into(avatarImageView)
            }


        }
        showHeroesButton.setOnClickListener {
            val action = UserFragmentDirections
                .actionUserFragmentToHeroesListFragment()
            requireView().findNavController().navigate(action)
        }
    }

}