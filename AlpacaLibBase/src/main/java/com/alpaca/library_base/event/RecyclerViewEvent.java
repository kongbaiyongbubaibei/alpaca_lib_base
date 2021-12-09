package com.alpaca.library_base.event;

import com.dreamliner.rvhelper.OptimumRecyclerView;

public class RecyclerViewEvent {
    private OptimumRecyclerView recyclerView;

    public RecyclerViewEvent(OptimumRecyclerView myRecyclerView) {
        this.recyclerView = myRecyclerView;

    }


    public OptimumRecyclerView getRecyclerView() {
        return recyclerView;
    }

    public void setRecyclerView(OptimumRecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }
}
