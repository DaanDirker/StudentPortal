package com.example.daan.studentportal;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class PortalObjectAdapter extends RecyclerView.Adapter<PortalObjectAdapter.ViewHolder> {

    public List<PortalObject> portalObjectsList;
    private PortalClicketListener mPortalClickListener;

    public interface PortalClicketListener {
        void PortalOnClick(int position);
    }

    public PortalObjectAdapter(List<PortalObject> portalObjectsList, PortalClicketListener mPortalClickListener) {
        this.portalObjectsList = portalObjectsList;
        this.mPortalClickListener = mPortalClickListener;
    }

    @Override
    public PortalObjectAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_layout, parent, false);
        return new PortalObjectAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PortalObjectAdapter.ViewHolder holder, int position) {
        // Gets a single item in the list from its position
        final PortalObject portalObject = portalObjectsList.get(position);
        // The holder argument is used to reference the views inside the viewHolder
        // Populate the views with the data from the list
        holder.titleText.setText(portalObject.getTitle());
    }

    @Override
    public int getItemCount() {
        return portalObjectsList.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView titleText;

        public ViewHolder(View itemView) {
            super(itemView);
            this.titleText = itemView.findViewById(R.id.titleTextView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            mPortalClickListener.PortalOnClick(clickedPosition);
        }
    }
}
