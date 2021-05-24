package com.rgb.example.newssampleapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.rgb.example.newssampleapp.data.model.Article
import com.rgb.example.newssampleapp.databinding.FragmentInfoBinding
import com.rgb.example.newssampleapp.presentation.viewmodel.MainViewModel

class InfoFragment : Fragment() {

    private lateinit var binding: FragmentInfoBinding
    private lateinit var article: Article
    private lateinit var mViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentInfoBinding.bind(view)
        mViewModel = (activity as MainActivity).mViewModel

        val args: InfoFragmentArgs by navArgs()
        article = args.article
        loadArticleOnWeb()
        initFab()
    }

    private fun initFab() {
        binding.fabSave.setOnClickListener {
            mViewModel.saveArticle(article)
            view?.let { root ->
                Snackbar.make(root,"Saved Successfully!",Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    private fun loadArticleOnWeb() {
        binding.wvArticle.apply {
            webViewClient = WebViewClient()
            article.url?.let {
                this.loadUrl(it)
            }
        }
    }

}