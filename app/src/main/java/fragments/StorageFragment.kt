package com.example.implementingmenusandwebview.fragments

import android.net.Uri
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.example.implementingmenusandwebview.R

class StorageFragment :
    Fragment(R.layout.fragment_storage) {

    private lateinit var image1: ImageView
    private lateinit var image2: ImageView
    private lateinit var image3: ImageView

    private val imagePicker =
        registerForActivityResult(
            ActivityResultContracts.GetMultipleContents()
        ) { images: List<Uri> ->

            if (images.isNotEmpty()) {
                image1.setImageURI(images[0])
            }

            if (images.size > 1) {
                image2.setImageURI(images[1])
            }

            if (images.size > 2) {
                image3.setImageURI(images[2])
            }
        }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {

        super.onViewCreated(view, savedInstanceState)

        image1 =
            view.findViewById(R.id.storageImage1)

        image2 =
            view.findViewById(R.id.storageImage2)

        image3 =
            view.findViewById(R.id.storageImage3)

        setupImageSelectionAndContext(image1)
        setupImageSelectionAndContext(image2)
        setupImageSelectionAndContext(image3)

        val button =
            view.findViewById<Button>(
                R.id.selectStorage
            )

        button.setOnClickListener {
            launchPicker()
        }
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

    fun launchPicker() {
        imagePicker.launch("image/*")
    }

    fun clearImages() {
        image1.setImageURI(null)
        image2.setImageURI(null)
        image3.setImageURI(null)
        image1.setImageResource(0)
        image2.setImageResource(0)
        image3.setImageResource(0)
    }
}
