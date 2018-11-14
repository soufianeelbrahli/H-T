package com.example.soufiane.htbooking;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Soufiane on 13/11/2018.
 */

public class ArrayMesReservationsAdapter extends ArrayAdapter<Reservation> {
    private ArrayList<Reservation> Reservations;
    private LayoutInflater inflater;

    public ArrayMesReservationsAdapter(Context context, int textViewResourceId, ArrayList<Reservation> Reservations){
        super(context,R.layout.fragment_reserver,Reservations);
        this.inflater= LayoutInflater.from(context);
        this.Reservations=Reservations;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder Holder;
        if(convertView==null){
            BDD db = new BDD(getContext());
            db.getWritableDatabase();
            Holder=new ViewHolder();
            convertView=inflater.inflate(R.layout.itemmesreservations,null);
            Holder.Type=(TextView)convertView.findViewById(R.id.textView4);
            Holder.Matricule=(TextView)convertView.findViewById(R.id.textView5);
            convertView.setTag(Holder);
            Reservation resa=db.getReservationWithIdOnly(String.valueOf(Reservations.get(position).getId_adherent()));
            Voiture voiture=db.getCarsWithIdOnly(String.valueOf(resa.getId_voiture()));
            Type_voiture tv=db.getTypeWithIdOnly(String.valueOf(voiture.getId_type()));
            Holder.Type.setText(tv.getLibele());
            Holder.Matricule.setText(voiture.getMatricule());
        }else{
            Holder=(ViewHolder) convertView.getTag();
        }

        return convertView;
    }
    public class ViewHolder{
        public TextView Type;
        public TextView Matricule;

    }
}
