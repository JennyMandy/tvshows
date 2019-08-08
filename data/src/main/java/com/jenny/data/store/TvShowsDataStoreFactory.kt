package com.jenny.data.store

import javax.inject.Inject

class TvShowsDataStoreFactory @Inject
constructor(val tvShowsRemoteDataStore: TvShowsRemoteDataStore)