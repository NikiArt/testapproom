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
                        }
                } else {
                    adapterPresenter.cars.addAll(result)
                }
                viewState.initialize()
            }
    }

    fun getCarsList(request: String) {
        if (request.isEmpty()) {
            loadData()
        } else {
            carCache.getListWithRequest(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { result ->
                    adapterPresenter.cars.clear()
                    adapterPresenter.cars.addAll(result)
                    viewState.initialize()
                }
        }

    }

    fun getAdapterPresenter(): AdapterPresenter {
        return adapterPresenter
    }
}