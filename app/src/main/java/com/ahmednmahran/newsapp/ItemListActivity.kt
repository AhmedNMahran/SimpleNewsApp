package com.ahmednmahran.newsapp

import androidx.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProviders
import com.ahmednmahran.newsapp.ViewModel.NewsViewModel
import com.ahmednmahran.newsapp.ViewModel.NewsViewModelFactory
import com.ahmednmahran.newsapp.model.Article

import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_item_list.*
import kotlinx.android.synthetic.main.item_list_content.view.*
import kotlinx.android.synthetic.main.item_list.*

/**
 * An activity representing a list of Pings. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a [ItemDetailActivity] representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
class ItemListActivity : AppCompatActivity() {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private var twoPane: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_list)
//create instance of view model factory
        val viewModelFactory = NewsViewModelFactory()
        //Use view ModelFactory to initialize view model
        val newsViewModel = ViewModelProviders.of(this@ItemListActivity, viewModelFactory)
            .get(NewsViewModel::class.java)
        //get latest news from view model
        newsViewModel.getLatestNews()
        //observe viewModel live data
        newsViewModel.newsLiveData.observe(this, Observer {
            //bind your ui here
            setupRecyclerView(item_list, it)
        })



        setSupportActionBar(toolbar)
        toolbar.title = title

        if (item_detail_container != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            twoPane = true
        }

    }

    private fun setupRecyclerView(recyclerView: androidx.recyclerview.widget.RecyclerView, list: MutableList<Article>) {
        recyclerView.adapter = NewsItemRecyclerViewAdapter(this, list, twoPane)
    }

    class NewsItemRecyclerViewAdapter(
        private val parentActivity: ItemListActivity,
        private val values: List<Article>,
        private val twoPane: Boolean
    ) :
        androidx.recyclerview.widget.RecyclerView.Adapter<NewsItemRecyclerViewAdapter.ViewHolder>() {

        private val onClickListener: View.OnClickListener

        init {
            onClickListener = View.OnClickListener { v ->
                val item = v.tag as Article
                if (twoPane) {
                    val fragment = ItemDetailFragment().apply {
                        arguments = Bundle().apply {
                            putString(ItemDetailFragment.ARG_ITEM_ID, Gson().toJson( item))
                        }
                    }
                    parentActivity.supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.item_detail_container, fragment)
                        .commit()
                } else {
                    val intent = Intent(v.context, ItemDetailActivity::class.java).apply {
                        putExtra(ItemDetailFragment.ARG_ITEM_ID, Gson().toJson( item))
                    }
                    v.context.startActivity(intent)
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list_content, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = values[position]
            holder.idView.text = item.title
            holder.contentView.text = item.author
//            Picasso.get().load(item.urlToImage).error(R.mipmap.ic_launcher).placeholder(R.mipmap.ic_launcher)
//                .into(holder.imgThumbnail)
            with(holder.itemView) {
                tag = item
                setOnClickListener(onClickListener)
            }
        }

        override fun getItemCount() = values.size

        inner class ViewHolder(view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {
            val idView: TextView = view.id_text
            val contentView: TextView = view.content
//            val imgThumbnail: ImageView = view.img_thumb
        }
    }
}
