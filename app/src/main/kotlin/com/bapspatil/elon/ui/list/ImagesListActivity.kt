package com.bapspatil.elon.ui.list

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.bapspatil.elon.R
import com.bapspatil.elon.model.NasaImage
import com.bapspatil.elon.ui.base.BaseActivity
import com.bapspatil.elon.ui.detail.ImageDetailActivity
import com.bapspatil.elon.ui.list.adapter.ImagesAdapter
import com.bapspatil.elon.util.io
import com.bapspatil.elon.util.setNavBarColor
import com.bapspatil.elon.util.setStatusBarColor
import kotlinx.android.synthetic.main.activity_images_list.*
import javax.inject.Inject

/**
 * View associated to the list of images
 */
class ImagesListActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ImagesListViewModel.Factory
    @Inject
    lateinit var imagesAdapter: ImagesAdapter

    private lateinit var viewModel: ImagesListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_images_list)
        init()
        initViewModel()
    }

    /**
     * Init the View
     */
    private fun init() {
        initBars()
        initRecyclerView()
        initListeners()
        initViews()
    }

    /**
     * Init StatusBar and NavBar
     */
    private fun initBars() {
        setSupportActionBar(toolbar)
        setStatusBarColor(R.color.colorPrimary)
        setNavBarColor(R.color.colorPrimary)
    }

    /**
     * Init RecyclerView
     */
    private fun initRecyclerView() {
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = imagesAdapter
        disposables.add(imagesAdapter.getClickedImage().subscribe { viewModel.onRowClicked(it) })
    }

    /**
     * Init listeners
     */
    private fun initListeners() {
        swipeRefreshLayout.setOnRefreshListener { viewModel.onReload() }
    }

    /**
     * Init original state of views
     */
    private fun initViews() {
        checkLoadingState(true)
    }

    /**
     * Init ViewModel
     */
    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ImagesListViewModel::class.java)
        lifecycle.addObserver(viewModel)
        disposables.add(
                viewModel.viewStateObservable
                        .io()
                        .subscribe(this::render, Throwable::printStackTrace)
        )
    }

    /**
     * Render the view with the actions given the current state of the view
     * @param viewState current state of the view
     */
    private fun render(viewState: ImagesListViewState) {
        when (viewState) {
            is ImagesListViewState.ShowLoading -> checkLoadingState(true)
            is ImagesListViewState.DisplayImagesList -> {
                imagesAdapter.update(viewState.images)
                checkLoadingState(false)
            }
            is ImagesListViewState.NoInternet -> noInternet()
            is ImagesListViewState.OpenImageDetail -> openImageDetail(viewState.image)
            is ImagesListViewState.Error -> viewState.error?.let { displayError(it) }
            is ImagesListViewState.DisplayEmptyListMessage -> displayEmptyListMessage()
        }
    }

    /**
     * Check the state of the loading animation
     * @param isLoading true if the request is still loading, false otherwise
     */
    private fun checkLoadingState(isLoading: Boolean) {
        if (isLoading) {
            // Show loading state
            hideRecyclerView()
            stateAnimationView.setAnimation(R.raw.rocket_fast)
            stateAnimationView.playAnimation()
        } else {
            // Hide loading state
            showRecyclerView()
            swipeRefreshLayout.isRefreshing = false
        }
    }

    /**
     * Inform the user to that he lost the internet connexion
     */
    private fun noInternet() {
        hideRecyclerView()
        swipeRefreshLayout.isRefreshing = false
        stateAnimationView.setAnimation(R.raw.network_lost)
        stateAnimationView.playAnimation()
    }

    /**
     * open the detail of a image in the different view
     * @param image image  to display the detail from
     */
    private fun openImageDetail(image: NasaImage) {
        ImageDetailActivity.start(this, image)
    }

    /**
     * Inform the user to the error
     */
    private fun displayError(error: String) {
        hideRecyclerView()
        swipeRefreshLayout.isRefreshing = false
        stateAnimationView.setAnimation(R.raw.error_loading)
        stateAnimationView.playAnimation()
        Log.d("ERROR_IMAGES", error)
    }

    /**
     * Inform the user to that the list of images is empty
     */
    private fun displayEmptyListMessage() {
        hideRecyclerView()
        swipeRefreshLayout.isRefreshing = false
        stateAnimationView.setAnimation(R.raw.empty_box)
        stateAnimationView.playAnimation()
    }

    /**
     * Hide the RecyclerView
     */
    private fun hideRecyclerView() {
        recyclerView.visibility = View.GONE
        stateAnimationView.visibility = View.VISIBLE
    }

    /**
     * Show the RecyclerView
     */
    private fun showRecyclerView() {
        recyclerView.visibility = View.VISIBLE
        stateAnimationView.visibility = View.GONE
    }
}
