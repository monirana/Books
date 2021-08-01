package com.learn.books.home.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.learn.books.R
import com.learn.books.home.domain.entities.Volume

class BookAdapter(private val context: Context) : RecyclerView.Adapter<BookAdapter.ViewHolder>() {
    private val books: ArrayList<Volume> = arrayListOf()

    override fun getItemCount(): Int {
        return books.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_book_tile,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        books[position].also { book ->
            book.volumeInfo.imageUrl?.let { imageUrl ->
                Glide.with(context)
                    .load(imageUrl)
                    .into(holder.ivBookCover)
                holder.tvBookName.text = book.volumeInfo.title
                holder.tvBookAuthors.text = "by".plus(book.volumeInfo.authors.joinToString())
            } ?: kotlin.run {
                Glide.with(context)
                    .load(R.drawable.ic_launcher_background)
                    .into(holder.ivBookCover)
                holder.tvBookName.text = ""
                holder.tvBookAuthors.text = ""
            }

        }
    }

    fun submitUpdate(update: List<Volume>) {
        val callback = BooksDiffCallback(books, update)
        val diffResult: DiffUtil.DiffResult = DiffUtil.calculateDiff(callback)

        books.clear()
        books.addAll(update)
        diffResult.dispatchUpdatesTo(this)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvBookName: TextView = view.findViewById(R.id.txt_book_title)
        val tvBookAuthors: TextView = view.findViewById(R.id.txt_book_author)
        val ivBookCover: ImageView = view.findViewById(R.id.img_book_cover)
    }

    class BooksDiffCallback(
        private val oldBooks: List<Volume>,
        private val newBooks: List<Volume>,
    ) :
        DiffUtil.Callback() {
        override fun getOldListSize(): Int {
            return oldBooks.size
        }

        override fun getNewListSize(): Int {
            return newBooks.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldBooks[oldItemPosition].id == newBooks[newItemPosition].id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldBooks[oldItemPosition].volumeInfo.imageUrl == newBooks[newItemPosition].volumeInfo.imageUrl
        }
    }
}