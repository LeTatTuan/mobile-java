package com.example.contactappv2.viewModel;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contactappv2.R;
import com.example.contactappv2.databinding.ContactRowItemBinding;
import com.example.contactappv2.model.Contact;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> implements Filterable {
    private ArrayList<Contact> contacts;
    private ArrayList<Contact> contactsCopy;

    public ContactAdapter(ArrayList<Contact> contacts) {
        this.contacts = contacts;
        this.contactsCopy = contacts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ContactRowItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.contact_row_item, parent, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactAdapter.ViewHolder holder, int position) {
        Contact contact = contacts.get(position);
        holder.binding.setContact(contact);
        Log.d("DEBUG", contact.getId() + ": " + contact.getAvatar());
        if(!contact.getAvatar().isEmpty()) {
            Picasso.get()
                    .load(contact.getAvatar())
                    .resize(50, 50)
                    .centerCrop()
                    .into(holder.binding.ivAvatar);
        }

    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String input = constraint.toString().trim().toLowerCase();
                List<Contact> filteredContact = new ArrayList<Contact>();
                if (input.isEmpty()) {
                    filteredContact = contactsCopy;
                } else {
                    for (Contact contact : contactsCopy) {
                        if (contact.getName().toLowerCase().contains(input)) {
                            filteredContact.add(contact);
                        }
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredContact;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                contacts = new ArrayList<Contact>();
                if (results != null && results.values != null) {
                    contacts.addAll((List<Contact>) results.values);
                }
                notifyDataSetChanged();
            }
        };
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ContactRowItemBinding binding;

        public ViewHolder(ContactRowItemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.binding = itemBinding;
        }
    }
}
