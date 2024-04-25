package com.example.contact;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {
    private ArrayList<Contact> contactList;
    private ArrayList<Contact> contactListCopy;

    public ContactAdapter(ArrayList<Contact> contactList) {
        this.contactList = contactList;
        this.contactListCopy = contactList;
    }

    @NonNull
    @Override
    public ContactAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contact_row_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactAdapter.ViewHolder holder, int position) {
        holder.tvName.setText(contactList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName;
        public CardView layoutItem;
        public ViewHolder(@NonNull View view) {
            super(view);
            tvName = (TextView) view.findViewById(R.id.tv_name);
            layoutItem = (CardView) view.findViewById(R.id.layout_item);
        }
    }
}
