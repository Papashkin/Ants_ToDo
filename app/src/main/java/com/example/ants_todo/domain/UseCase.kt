package com.example.ants_todo.domain



abstract class UseCase<in C, T> {

//    private var mSubscription: Disposable = Disposables.empty()
//
//    protected abstract fun buildObservable(criteria: C): Observable<T>
//
//    fun execute(useCaseSubscriber: FunctionalSubscriber<T>, criteria: C) {
//        mSubscription = buildObservable(criteria)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribeWith(useCaseSubscriber)
//    }
//
//    fun execute(criteria: C): Observable<T> = buildObservable(criteria)
//
//    fun unsubscribe() {
//        if (!mSubscription.isDisposed) {
//            mSubscription.dispose()
//        }
//    }
}