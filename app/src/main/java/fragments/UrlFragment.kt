package com.example.implementingmenusandwebview.fragments

import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import coil3.load
import com.example.implementingmenusandwebview.R

class UrlFragment :
    Fragment(R.layout.fragment_url) {

    private lateinit var image1: ImageView
    private lateinit var image2: ImageView
    private lateinit var image3: ImageView

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {

        super.onViewCreated(view, savedInstanceState)

        image1 =
            view.findViewById<ImageView>(
                R.id.urlImage1
            )

        image2 =
            view.findViewById<ImageView>(
                R.id.urlImage2
            )

        image3 =
            view.findViewById<ImageView>(
                R.id.urlImage3
            )

        setupImageSelectionAndContext(image1)
        setupImageSelectionAndContext(image2)
        setupImageSelectionAndContext(image3)

        image1.load(
            "https://picsum.photos/400/300?random=1"
        )

        image2.load(
            "https://picsum.photos/400/300?random=2"
        )

        image3.load(
            "https://picsum.photos/400/300?random=3"
        )
    }

    private fun setupImageSelectionAndContext(imageView: ImageView) {
        imageView.setOnClickListener {
            imageView.isSelected = !imageView.isSelected
            imageView.alpha = if (imageView.isSelected) 0.5f else 1.0f
            val status = if (imageView.isSelected) "Selected" else "Deselected"
            Toast.makeText(requireContext(), "Photo $status", Toast.LENGTH_SHORT).show()
        }
        registerForContextMenu(imageView)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu,
        v: View,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        requireActivity().menuInflater.inflate(R.menu.context_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.context_edit -> {
                Toast.makeText(requireContext(), "Edit photo selected", Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.context_share -> {
                Toast.makeText(requireContext(), "Share photo selected", Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.context_delete -> {
                Toast.makeText(requireContext(), "Delete photo selected", Toast.LENGTH_SHORT).show()
                return true
            }
        }
        return super.onContextItemSelected(item)
    }
}
