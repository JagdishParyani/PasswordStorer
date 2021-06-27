package com.example.passwordstorer.common.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData

fun <T1, T2> combineTuple(f1: LiveData<T1>, f2: LiveData<T2>): LiveData<Pair<T1?, T2?>> = MediatorLiveData<Pair<T1?, T2?>>().also { mediator ->
    mediator.value = Pair(f1.value, f2.value)

    mediator.addSource(f1) { t1: T1? ->
        val (_, t2) = mediator.value!!
        mediator.value = Pair(t1, t2)
    }

    mediator.addSource(f2) { t2: T2? ->
        val (t1, _) = mediator.value!!
        mediator.value = Pair(t1, t2)
    }
}

fun <T1 : Any, T2 : Any> combineTupleNonNull(
    f1: LiveData<T1>,
    f2: LiveData<T2>
): LiveData<Pair<T1, T2>> = MediatorLiveData<Pair<T1, T2>>().also { mediator ->
    mediator.value = Pair(checkNotNull(f1.value) { "Value of First Livedata was null" },
        checkNotNull(f2.value) { "Value of Second Livedata was null" })

    mediator.addSource(f1) { t1: T1 ->
        val (_, t2) = mediator.value!!
        mediator.value = Pair(t1, t2)
    }

    mediator.addSource(f2) { t2: T2 ->
        val (t1, _) = mediator.value!!
        mediator.value = Pair(t1, t2)
    }
}

fun <T1 : Any, T2 : Any, T3 : Any> combineTupleNonNull(
    f1: LiveData<T1>,
    f2: LiveData<T2>,
    f3: LiveData<T3>
): LiveData<Triple<T1, T2, T3>> = MediatorLiveData<Triple<T1, T2, T3>>().also { mediator ->
    mediator.value = Triple(checkNotNull(f1.value) { "Value of f1 was null" },
        checkNotNull(f2.value) { "Value of f2 was null" },
        checkNotNull(f3.value) { "Value of f3 was null" })

    mediator.addSource(f1) { t1: T1 ->
        val (_, t2, t3) = mediator.value!!
        mediator.value = Triple(t1, t2, t3)
    }

    mediator.addSource(f2) { t2: T2 ->
        val (t1, _, t3) = mediator.value!!
        mediator.value = Triple(t1, t2, t3)
    }

    mediator.addSource(f3) { t3: T3 ->
        val (t1, t2, _) = mediator.value!!
        mediator.value = Triple(t1, t2, t3)
    }
}