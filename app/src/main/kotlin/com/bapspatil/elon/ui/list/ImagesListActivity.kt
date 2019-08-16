package com.bapspatil.elon.ui.list

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.bapspatil.elon.R
import com.bapspatil.elon.model.NasaImage
import com.bapspatil.elon.ui.base.BaseActivity
import com.bapspatil.elon.ui.list.adapter.ImagesAdapter
import com.bapspatil.elon.util.io
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
        initStatusAndNavBar()
        initRecyclerView()
        initListeners()
        initViews()
    }

    /**
     * Init Statusbar and Navbar
     */
    private fun initStatusAndNavBar() {
        window.statusBarColor = ContextCompat.getColor(this, R.color.colorPrimary)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            window.navigationBarColor = ContextCompat.getColor(this, R.color.colorPrimary)
        }
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
        reloadTextView.setOnClickListener { viewModel.onReload() }
    }

    /**
     * Init original state of views
     */
    private fun initViews() {
        checkLoadingState(false)
        recyclerView.visibility = View.VISIBLE
        reloadTextView.visibility = View.GONE
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
        initViews()
        when (viewState) {
            is ImagesListViewState.ShowLoading -> checkLoadingState(true)
            is ImagesListViewState.DisplayImagesList -> imagesAdapter.update(viewState.images)
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
        } else {
            // llState.visibility = View.GONE
            recyclerView.visibility = View.VISIBLE
            reloadTextView.visibility = View.GONE
        }
    }

    /**
     * Inform the user to that he lost the internet connexion
     */
    private fun noInternet() {
//        llState.visibility = View.VISIBLE
//        tvStateTitle.text = getString(R.string.error_no_internet_connexion)
//        animation.setAnimation(R.raw.error_animation)
        reloadTextView.visibility = View.VISIBLE
//        animation.playAnimation()
        hideRecyclerView()
    }

    /**
     * open the detail of a image in the different view
     * @param image image  to display the detail from
     */
    private fun openImageDetail(image: NasaImage) {
        Log.d("IMAGE_CLICKED", image.toString())
        // ImageDetailActivity.start(this, image)
    }

    /**
     * Inform the user to the error
     */
    private fun displayError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
        hideRecyclerView()
    }

    /**
     * Inform the user to that the list of images is empty
     */
    private fun displayEmptyListMessage() {
        hideRecyclerView()
    }

    /**
     * Hide RecyclerView
     */
    private fun hideRecyclerView() {
        recyclerView.visibility = View.INVISIBLE
    }

}
