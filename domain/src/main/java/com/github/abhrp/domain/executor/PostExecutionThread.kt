package com.github.abhrp.domain.executor

import io.reactivex.Scheduler

interface PostExecutionThread {
    var scheduler: Scheduler
}