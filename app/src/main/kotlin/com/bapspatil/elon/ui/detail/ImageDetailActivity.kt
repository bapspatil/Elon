package com.bapspatil.elon.ui.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.MenuItem
import android.view.View
import androidx.annotation.VisibleForTesting
import androidx.core.app.NavUtils
import androidx.core.content.ContextCompat
import androidx.core.text.HtmlCompat
import androidx.lifecycle.ViewModelProviders
import com.bapspatil.elon.R
import com.bapspatil.elon.model.NasaImage
import com.bapspatil.elon.ui.base.BaseActivity
import com.bapspatil.elon.util.io
import com.bapspatil.elon.util.setImageWithGlide
import com.bapspatil.elon.util.setNavBarColor
import com.bapspatil.elon.util.setStatusBarColor
import kotlinx.android.synthetic.main.activity_image_detail.*
import javax.inject.Inject

/*
** Created by Bapusaheb Patil {@link https://bapspatil.com}
*/

/**
 * View associated to image details
 */
class ImageDetailActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ImageDetailViewModel.Factory

    private lateinit var viewModel: ImageDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_detail)
        init()
        initViewModel()
    }

    /**
     * Init the View
     */
    private fun init() {
        initStatusAndNavBar()
    }

    /**
     * Init StatusBar and NavBar
     */
    private fun initStatusAndNavBar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        collapsingToolbarLayout.title = getString(R.string.milky_way)
        collapsingToolbarLayout.setExpandedTitleColor(ContextCompat.getColor(this, android.R.color.transparent))
        collapsingToolbarLayout.setCollapsedTitleTextColor(ContextCompat.getColor(this, android.R.color.white))
        setStatusBarColor(R.color.colorPrimary)
        setNavBarColor(R.color.colorPrimary)
    }

    /**
     * Init the ViewModel
     */
    private fun initViewModel() {
        val image = intent?.extras?.get(IMAGE_SELECTED) as NasaImage?
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ImageDetailViewModel::class.java)
        lifecycle.addObserver(viewModel)
        viewModel.setImage(image)
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
    private fun render(viewState: ImageDetailViewState) {
        when (viewState) {
            is ImageDetailViewState.RenderImageDetail -> renderImageData(viewState.image)
            is ImageDetailViewState.Error -> showError()
        }
    }


    /**
     * Render the image data
     */
    private fun renderImageData(image: NasaImage) {
        showContent()

        collapsingToolbarLayout.title = image.title
        nasaImageView.setImageWithGlide(image.image)
        titleTextView.text = image.title
        descriptionTextView.text = HtmlCompat.fromHtml(image.description, HtmlCompat.FROM_HTML_MODE_LEGACY)
        descriptionTextView.movementMethod = LinkMovementMethod.getInstance()
        centerAndDateTextView.text = HtmlCompat.fromHtml("<b>Center:</b> ${image.center}<br><b>Date Created:</b> ${image.date}", HtmlCompat.FROM_HTML_MODE_LEGACY)
    }

    /**
     * Show error if getting the image from the Intent fails
     */
    private fun showError() {
        hideContent()
        stateAnimationView.setAnimation(R.raw.error_loading)
        stateAnimationView.playAnimation()
    }

    /**
     * Handle options item selection
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this)
        }

        return super.onOptionsItemSelected(item)
    }

    /**
     * Show the content of the image data
     */
    private fun showContent() {
        contentContainer.visibility = View.VISIBLE
        stateAnimationView.visibility = View.GONE
    }

    /**
     * Hide the content of the image data
     */
    private fun hideContent() {
        contentContainer.visibility = View.GONE
        stateAnimationView.visibility = View.VISIBLE
    }

    companion object {

        @VisibleForTesting
        const val IMAGE_SELECTED = "image_selected"

        /**
         * Start the activity with the post associated to the view
         * @param context current `Context`
         * @param image `NasaImage` associated to the view
         */
        fun start(context: Context, image: NasaImage) {
            val intent = Intent(context, ImageDetailActivity::class.java)
            intent.putExtra(IMAGE_SELECTED, image)
            context.startActivity(intent)
        }
    }

}