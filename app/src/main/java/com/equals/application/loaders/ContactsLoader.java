package com.equals.application.loaders;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.equals.application.contacts.ContactsProvider;
import com.equals.application.contacts.SystemContact;

import java.util.Map;


public class ContactsLoader extends AsyncTaskLoader<Map<Long, SystemContact>> {

    private boolean e164format;

    public ContactsLoader(Context context, boolean e164format) {
        super(context);
        this.e164format = e164format;
    }

    @Override
    public Map<Long, SystemContact> loadInBackground() {
        return ContactsProvider.getInstance().getSystemContactMap(getContext(), e164format);
    }
}
