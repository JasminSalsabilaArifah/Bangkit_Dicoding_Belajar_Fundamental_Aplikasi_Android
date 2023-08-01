package jti.jasminsa.githubuser.ui.favorite

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import jti.jasminsa.githubuser.data.remote.response.ItemsItem
import jti.jasminsa.githubuser.databinding.UserItemBinding
import jti.jasminsa.githubuser.ui.detail.DetailUserActivity

class FavoriteAdapter(private val listUser: List<ItemsItem>) : RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int) : ViewHolder {
        val bin = UserItemBinding.inflate(LayoutInflater.from(viewGroup.context),viewGroup,false)
        return ViewHolder(bin)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val user = listUser[position]
        viewHolder.tvUser.text = user.login
        Glide.with(viewHolder.itemView.context)
            .load(user.avatarUrl)
            .into(viewHolder.ivUser)// URL Gambar
        val username = listUser[viewHolder.adapterPosition].login
        viewHolder.itemView.setOnClickListener{
            val intent = Intent(viewHolder.itemView.context, DetailUserActivity::class.java)
            intent.putExtra(DetailUserActivity.USERNAME, username)
            viewHolder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount() = listUser.size

    class ViewHolder(view: UserItemBinding) : RecyclerView.ViewHolder(view.root) {
        val ivUser: ImageView = view.ivProfile
        val tvUser: TextView = view.tvUsername
    }
}