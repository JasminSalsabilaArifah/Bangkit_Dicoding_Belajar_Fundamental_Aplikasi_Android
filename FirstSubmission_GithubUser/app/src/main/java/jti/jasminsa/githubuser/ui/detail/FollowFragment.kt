package jti.jasminsa.githubuser.ui.detail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import jti.jasminsa.githubuser.databinding.FragmentFollowBinding
import jti.jasminsa.githubuser.ui.UserItemAdapter
import jti.jasminsa.githubuser.ui.main.MainViewModel

class FollowFragment : Fragment() {

    private lateinit var binding: FragmentFollowBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentFollowBinding.inflate(layoutInflater)
        return binding.getRoot();
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val index = arguments?.getInt(ARG_SECTION_NUMBER)
        val username = arguments?.getString(ARG_USERNAME)
        Log.e("fragment ", "findUser 2: ${index}")
        val mainViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            MainViewModel::class.java)
        binding.rvFollowList.layoutManager = LinearLayoutManager(requireActivity())
        if (username != null) {
            if (index != null) {
                mainViewModel.follow(username, index)
                mainViewModel.isLoading.observe(this, {
                    showLoading(it)
                })
            }
        }
        mainViewModel.datafollow().observe(viewLifecycleOwner) {
            val adapter = UserItemAdapter(it)
            binding.rvFollowList.adapter = adapter
        }
    }

    companion object {
        const val ARG_SECTION_NUMBER = "section_number"
        const val ARG_USERNAME = "username"
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}