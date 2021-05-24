package com.rgb.example.newssampleapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.rgb.example.newssampleapp.data.model.Article
import com.rgb.example.newssampleapp.databinding.FragmentSavedBinding
import com.rgb.example.newssampleapp.presentation.adapter.NewsAdapter
import com.rgb.example.newssampleapp.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SavedFragment : Fragment() {
    private lateinit var viewBinding: FragmentSavedBinding
    @Inject
    lateinit var newsAdapter: NewsAdapter
    private lateinit var mViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_saved, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding = FragmentSavedBinding.bind(view)
        mViewModel = (activity as MainActivity).mViewModel

        initNewsList()
        viewSavedArticles()
    }

    private fun initNewsList() {
        newsAdapter.setOnClickCallback(::onNewsArticleClicked)
        viewBinding.rvSavedNews.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = newsAdapter
        }
        //Swipe action listener
        val itemTouchCallback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val article = newsAdapter.differ.currentList[position]
                mViewModel.deleteArticle(article)
                view?.let {
                    Snackbar.make(it, "Deleted article", Snackbar.LENGTH_LONG).apply {
                        setAction("Undo"){
                            mViewModel.saveArticle(article)
                        }
                        show()
                    }
                }
            }

        }
        // Link callback to news RV
        ItemTouchHelper(itemTouchCallback).attachToRecyclerView(viewBinding.rvSavedNews)
    }

    private fun onNewsArticleClicked(article: Article){
        val bundle =  Bundle().apply {
            putSerializable("article", article)
        }
        findNavController().navigate(R.id.action_savedFragment_to_infoFragment, bundle)
    }

    private fun viewSavedArticles() {
        mViewModel.getSavedArticles().observe(viewLifecycleOwner, {
            it?.let { list ->
                newsAdapter.differ.submitList(list)
            }
        })
    }

}