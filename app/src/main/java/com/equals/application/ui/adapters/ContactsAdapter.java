package com.equals.application.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.equals.application.R;
import com.equals.application.contacts.SystemContact;
import com.equals.application.ui.ContactListItem;

import java.util.LinkedList;
import java.util.List;


public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private List<SystemContact> contacts;
    private List<SystemContact> copy;

    static class ViewHolder extends RecyclerView.ViewHolder {
        ViewHolder(View itemView) {
            super(itemView);
        }

        private ContactListItem getItem() {
            return (ContactListItem) itemView;
        }
    }

    public ContactsAdapter(Context context, List<SystemContact> contacts) {
        this.inflater = LayoutInflater.from(context);
        this.contacts = contacts;
        this.copy = new LinkedList<>(contacts);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ContactListItem item = (ContactListItem) inflater.inflate(R.layout.contact_list_item,
                parent, false);
        return new ViewHolder(item);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.getItem().set(contacts.get(position));
    }

    @Override
    public int getItemCount() {
        return contacts != null ? contacts.size() : 0;
    }

    public void setFilter(String filter) {
        contacts.clear();
        if (filter.isEmpty()) {
            contacts.addAll(copy);
        } else {
            String lowCaseFilter = filter.toLowerCase();
            for (SystemContact contact : copy) {
                String name = contact.getName().toLowerCase();
                String number = contact.getNumber();
                if (name.contains(lowCaseFilter) || number.contains(lowCaseFilter)) {
                    contacts.add(contact);
                }
            }
        }
        notifyDataSetChanged();
    }
}
