package com.jenny.tvshows

import android.widget.AbsListView

public abstract class EndlessScrollListener() : AbsListView.OnScrollListener {
    private var visibleThreshold = 5;
    private var currentPage = 0;
    private var previousTotalItemCount = 0;
    private var loading = true;
    private var startingPageIndex = 0;

    constructor(visibleThreshold: Int) : this() {
        this.visibleThreshold = visibleThreshold
    }

    constructor(visibleThreshold: Int, startPage: Int) : this() {
        this.visibleThreshold = visibleThreshold
        this.startingPageIndex = startPage
        this.currentPage = startPage
    }

    public override fun onScroll(view: AbsListView, firstVisibleItem: Int, visibleItemCount: Int, totalItemCount: Int) {
        if (totalItemCount < previousTotalItemCount) {
            this.currentPage = this.startingPageIndex;
            this.previousTotalItemCount = totalItemCount;
            if (totalItemCount == 0) {
                this.loading = true; }
        }
        if (loading && (totalItemCount > previousTotalItemCount)) {
            loading = false;
            previousTotalItemCount = totalItemCount;
            currentPage++;
        }
        if (!loading && (firstVisibleItem + visibleItemCount + visibleThreshold) >= totalItemCount) {
            loading = onLoadMore(currentPage + 1, totalItemCount);
        }
    }

    public abstract fun onLoadMore(page: Int, totalItemsCount: Int): Boolean

    override public fun onScrollStateChanged(view: AbsListView, scrollState: Int) {}
}