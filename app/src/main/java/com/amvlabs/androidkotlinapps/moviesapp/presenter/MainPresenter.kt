package com.amvlabs.androidkotlinapps.moviesapp.presenter

import android.util.Log
import android.widget.Toast
import com.amvlabs.androidkotlinapps.moviesapp.data.MoviesApi
import com.amvlabs.androidkotlinapps.moviesapp.model.Search
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.ObservableSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import javax.inject.Inject

class MainPresenter : BasePresenter<MainPresenter.View>() {

    private val TAG = MainPresenter::class.java.simpleName

    private val api = MoviesApi.create()
    private val subscriptions = CompositeDisposable()

    fun getMovieList(name: String) {
        val subscriber = api.getMovies(name)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({  it ->
                if (it != null) {
                    view?.notifyDataChange(it.Search)
                } else {
                    view?.showError()
                }
//    }
                }, { throwable ->
                view?.showError()
            })

                subscriptions.add(subscriber)
            }

//  },{

                    override fun destroy() {
                subscriptions.clear()
            }

                interface View {
            fun notifyDataChange(movieList: List<Search>)
            fun showError()
        }

    }


