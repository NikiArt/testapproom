package ru.nikitaboiko.testapproom.ui

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.activity_main.*
import ru.nikitaboiko.testapproom.App
import ru.nikitaboiko.testapproom.R
import ru.nikitaboiko.testapproom.presenters.MainPresenter
import ru.nikitaboiko.testapproom.view.MainView


class MainActivity : MvpAppCompatActivity(), MainView {

    @InjectPresenter
    lateinit var mainPresenter: MainPresenter

    lateinit var search: SearchView
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: Adapter

    @ProvidePresenter
    fun providePresenter(): MainPresenter {
        val mainPresenter = MainPresenter()
        App.instance().getAppComponent().inject(mainPresenter)
        return mainPresenter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        //search = findViewById(R.id.menu_search)
        recyclerView = findViewById(R.id.content_list)

        /*search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                mainPresenter.getCarsList(newText as String)
                return false
            }
        })*/

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        search = menu.findItem(R.id.menu_search).actionView as SearchView
        search.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        search.setIconifiedByDefault(false) // Do not iconify the widget; expand it by default

        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                mainPresenter.getCarsList(newText as String)
                return false
            }
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_sort_model -> {
                mainPresenter.sortListBy("model")
                item.isChecked = true
            }
            R.id.menu_sort_submodel -> {
                mainPresenter.sortListBy("submodel")
                item.isChecked = true
            }
            R.id.menu_sort_number -> {
                mainPresenter.sortListBy("number")
                item.isChecked = true
            }
            R.id.menu_sort_age -> {
                mainPresenter.sortListBy("age")
                item.isChecked = true
            }
            R.id.menu_sort_color -> {
                mainPresenter.sortListBy("color")
                item.isChecked = true
            }
            else -> super.onOptionsItemSelected(item)
        }
        return true
    }

    override fun initialize() {
        recyclerView.setLayoutManager(LinearLayoutManager(this))
        adapter = Adapter(mainPresenter.getAdapterPresenter())
        recyclerView.setAdapter(adapter)
    }

    override fun updateList() {
        adapter.notifyDataSetChanged()
    }
}
