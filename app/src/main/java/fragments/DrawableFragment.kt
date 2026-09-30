package com.example.implementingmenusandwebview.fragments

import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.implementingmenusandwebview.R

class DrawableFragment :
    Fragment(R.layout.fragment_drawable) {

    private lateinit var image1: ImageView
    private lateinit var image2: ImageView
    private lateinit var image3: ImageView

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        image1 = view.findViewById(R.id.drawableImage1)
        image2 = view.findViewById(R.id.drawableImage2)
        image3 = view.findViewById(R.id.drawableImage3)

        image1.setImageResource(R.drawable.image1)
        image2.setImageResource(R.drawable.image2)
        image3.setImageResource(R.drawable.image3)

        setupImageSelectionAndContext(image1)
        setupImageSelectionAndContext(image2)
        setupImageSelectionAndContext(image3)
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
