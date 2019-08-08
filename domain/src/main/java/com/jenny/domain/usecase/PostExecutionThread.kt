package com.jenny.domain.usecase

import io.reactivex.Scheduler

interface PostExecutionThread {
    val scheduler: Scheduler?
}