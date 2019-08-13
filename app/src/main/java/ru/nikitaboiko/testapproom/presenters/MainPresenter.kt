package ru.nikitaboiko.testapproom.presenters

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.nikitaboiko.testapproom.model.CarCache
import ru.nikitaboiko.testapproom.view.MainView
import javax.inject.Inject

@InjectViewState
class MainPresenter : MvpPresenter<MainView>() {

    @Inject
    lateinit var carCache: CarCache

    lateinit var sortValue: String

    private val adapterPresenter = AdapterPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.initialize()
        loadData()
    }

    private fun loadData() {
        carCache.getCars()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { result ->
                adapterPresenter.cars.clear()
                if (result.isEmpty()) {
                    carCache.addNewValues()
                        .subscribeOn(Schedulers.io())
                        .subscribe { addResult ->
                            adapterPresenter.cars.addAll(addResult)
                            viewState.initialize()
                        }
                } else {
                    adapterPresenter.cars.addAll(result)
                    viewState.initialize()
                }
            }
    }

    fun getCarsList(request: String = "") {
        carCache.getListWithRequest(request)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { result ->
                adapterPresenter.cars.clear()
                adapterPresenter.cars.addAll(result)
                sortListBy()
                viewState.updateList()
            }
    }

    fun getAdapterPresenter(): AdapterPresenter {
        return adapterPresenter
    }

    fun sortListBy(sortValue: String = "") {
        if (!sortValue.isEmpty()) this.sortValue = sortValue
        when (this.sortValue) {
            "model" -> adapterPresenter.cars.sortBy { it.model }
            "submodel" -> adapterPresenter.cars.sortBy { it.submodel }
            "number" -> adapterPresenter.cars.sortBy { it.carNumber }
            "age" -> adapterPresenter.cars.sortBy { it.age }
            "color" -> adapterPresenter.cars.sortBy { it.color }
        }
        viewState.updateList()
    }
}