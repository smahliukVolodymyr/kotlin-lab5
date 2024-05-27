package com.example.lab5.ui

import ButtonAdapter
import ButtonItem
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lab5.data.api.Item
import com.example.lab5.R

class MainActivity : AppCompatActivity(), MainContract.View {
    private lateinit var presenter: MainContract.Presenter
    private lateinit var resultsRecyclerView: RecyclerView
    private lateinit var resultsAdapter: ItemAdaptor
    private lateinit var dataList: List<Item>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val recyclerView: RecyclerView = findViewById(R.id.horizontal_recycler_view)
        val buttonList = listOf(
            ButtonItem("Lorem", Color.GRAY, Color.WHITE),
            ButtonItem("Lorem Ipsum", Color.LTGRAY, Color.DKGRAY),
            ButtonItem("Lorem", Color.LTGRAY, Color.DKGRAY),
            ButtonItem("Lorem Ipsum", Color.LTGRAY, Color.DKGRAY)
        )

        val adapter = ButtonAdapter(buttonList)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            this.adapter = adapter
        }

        resultsRecyclerView = findViewById(R.id.results_recycler_view)
        presenter = MainPresenter(this)
        presenter.getLocalItems()

        val searchView = findViewById<SearchView>(R.id.secondSearchView)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(newText: String): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String): Boolean {
                val filtered = filterItems(dataList, newText)
                resultsAdapter.setItems(filtered)
                return true
            }
        })
    }
    override fun setupSearchView(resultsAdapter: ItemAdaptor, dataList: List<Item>) {
        this.resultsAdapter = resultsAdapter
        this.dataList = dataList
        resultsRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
        }
        resultsRecyclerView.adapter = resultsAdapter
    }
    fun filterItems(items: List<Item>, query: String): List<Item> {
        return items.filter { it.title.contains(query, ignoreCase = true) }
    }

    override fun displayError() {
        Log.d("API", "error loading data")
        Toast.makeText(
            this, R.string.failed_load_data,
            Toast.LENGTH_LONG).show()
    }
}