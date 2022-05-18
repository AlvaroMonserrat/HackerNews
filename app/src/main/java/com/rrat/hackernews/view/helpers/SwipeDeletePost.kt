package com.rrat.hackernews.view.helpers

import android.graphics.Canvas
import android.util.TypedValue
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.rrat.hackernews.R
import com.rrat.hackernews.view.adapters.HackerNewsAdapter
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator


class SwipeDeletePost( var adapter: HackerNewsAdapter) : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT)
{


    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val position = viewHolder.bindingAdapterPosition
        adapter.deleteItem(position)
    }

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
            .addBackgroundColor(ContextCompat.getColor(recyclerView.context, R.color.delete))
            .addActionIcon(R.drawable.ic_baseline_delete_24)
            .setIconHorizontalMargin(TypedValue.COMPLEX_UNIT_DIP, 32)
            .addSwipeLeftLabel(recyclerView.context.getString(R.string.delete))
            .setSwipeLeftLabelColor(ContextCompat.getColor(recyclerView.context, R.color.white))
            //.setSwipeLeftLabelTextSize(TypedValue.COMPLEX_UNIT_SP, 24F)
            .create()
            .decorate()

        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
    }

}