package ru.nikitaboiko.testapproom.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
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
        search = findViewById(R.id.content_search_view)
        recyclerView = findViewById(R.id.content_list)

        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                mainPresenter.getCarsList(newText as String)
                return false
            }
        })

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
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
