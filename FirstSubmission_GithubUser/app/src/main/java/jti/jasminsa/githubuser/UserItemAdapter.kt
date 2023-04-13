package jti.jasminsa.githubuser

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import jti.jasminsa.githubuser.databinding.UserItemBinding

class UserItemAdapter(private val listUser: List<ItemsItem>) : RecyclerView.Adapter<UserItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int) : ViewHolder =
        ViewHolder(LayoutInflater.from(viewGroup.context).inflate(R.layout.user_item, viewGroup, false))

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val user = listUser[position]
        viewHolder.tvUser.text = user.login
        Glide.with(viewHolder.itemView.context)
            .load(user.avatarUrl)
            .into(viewHolder.ivUser)// URL Gambar
    }

    override fun getItemCount() = listUser.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivUser: ImageView = view.findViewById(R.id.iv_profile)
        val tvUser: TextView = view.findViewById(R.id.tv_username)
    }
}