package com.learn.books.home.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.learn.books.databinding.HomeFragmentBinding

class HomeFragment : Fragment() {
    lateinit var userName: String
    private val homeViewModel: HomeViewModel by viewModels()

    private var _binding: HomeFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var booksAdapter: BookAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userName = requireArguments().getString("userName", "")
        booksAdapter = BookAdapter(requireContext())

        binding.rvBooks.apply {
            layoutManager =
                GridLayoutManager(requireContext(), 2)
            adapter = booksAdapter
        }

        homeViewModel.books.observe(viewLifecycleOwner, Observer {
            if (it.isNotEmpty()) {
                Log.e("size", "".plus(it.size))
                binding.rvBooks.visibility = View.VISIBLE
                binding.txtTitle.visibility = View.VISIBLE
                booksAdapter.submitUpdate(it)
            }

        })

        homeViewModel.isLoading.observe(viewLifecycleOwner, Observer {
            when (it) {
                true -> binding.loaderView.visibility = View.VISIBLE
                false -> binding.loaderView.visibility = View.GONE
            }
        })

        homeViewModel.error.observe(viewLifecycleOwner) {
            showToast(it)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeViewModel.getBooks("summer")
    }

    private fun showToast(msg: String){
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
    }
}