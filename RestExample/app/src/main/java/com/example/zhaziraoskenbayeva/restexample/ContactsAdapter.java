package com.example.zhaziraoskenbayeva.restexample;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by zhaziraoskenbayeva on 04/10/17.
 */

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ContactViewHoler> {

    List<Contact> conList ;

    public ContactsAdapter(List<Contact> cList) {
        conList = cList;
    }

    @Override
    public ContactViewHoler onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contacts_card, parent, false);
        ContactViewHoler cvh = new ContactViewHoler(v);
        return cvh;
    }

    @Override
    public void onBindViewHolder(ContactViewHoler holder, int position) {
        holder.c_nameView.setText(conList.get(position).getC_name());
        holder.c_phoneView.setText(conList.get(position).getC_mobile());
        holder.c_addressView.setText(conList.get(position).getC_address());
        holder.c_emailView.setText(conList.get(position).getC_email());
    }

    @Override
    public int getItemCount() {
        return conList.size();
    }

    public static class ContactViewHoler extends RecyclerView.ViewHolder{
        TextView c_nameView;
        TextView c_emailView;
        TextView c_addressView;
        TextView c_phoneView;

        public ContactViewHoler(View itemView) {
            super(itemView);
            c_nameView = (TextView) itemView.findViewById(R.id.person_name);
            c_emailView = (TextView) itemView.findViewById(R.id.person_email);
            c_addressView = (TextView) itemView.findViewById(R.id.person_address);
            c_phoneView = (TextView) itemView.findViewById(R.id.person_phone);
        }
    }
}
