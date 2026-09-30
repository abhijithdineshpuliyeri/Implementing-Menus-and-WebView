package com.example.implementingmenusandwebview

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.implementingmenusandwebview.fragments.DrawableFragment
import com.example.implementingmenusandwebview.fragments.StorageFragment
import com.example.implementingmenusandwebview.fragments.UrlFragment
import com.example.implementingmenusandwebview.fragments.WebViewFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Toolbar
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Load the three image fragments
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.drawableContainer, DrawableFragment())
                .replace(R.id.storageContainer, StorageFragment())
                .replace(R.id.urlContainer, UrlFragment())
                .commit()
        }
    }

    // ---------------- OPTIONS MENU ----------------

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.select_all -> {
                Toast.makeText(this, "All Images Selected", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.action_share -> {
                shareSelectedContent()
                true
            }
            R.id.action_delete -> {
                deleteSelectedContent()
                true
            }
            R.id.action_webview -> {
                openWebView()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun shareSelectedContent() {
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "Check out these selected photos from the Image Application!")
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }

    private fun deleteSelectedContent() {
        Toast.makeText(this, "Selected photos deleted", Toast.LENGTH_SHORT).show()
    }

    private fun openWebView() {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.mainContainer,
                WebViewFragment()
            )
            .addToBackStack(null)
            .commit()
    }
}
