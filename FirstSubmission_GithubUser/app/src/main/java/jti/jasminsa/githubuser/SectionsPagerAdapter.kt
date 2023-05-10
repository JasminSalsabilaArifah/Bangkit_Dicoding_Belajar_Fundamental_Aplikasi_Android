package jti.jasminsa.githubuser

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class SectionsPagerAdapter(fragment: DetailUserActivity) : FragmentStateAdapter(fragment) {
    var username: String = ""

    override fun createFragment(position: Int): Fragment {
        val fragment = FollowFragment()
        fragment.arguments = Bundle().apply {
            putInt(FollowFragment.ARG_SECTION_NUMBER, position + 1)
            putString(FollowFragment.ARG_USERNAME, username)
        }
        return fragment
    }

    override fun getItemCount(): Int {
        return 2
    }
}