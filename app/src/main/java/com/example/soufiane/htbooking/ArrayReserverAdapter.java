package com.example.soufiane.htbooking;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Soufiane on 13/11/2018.
 */

public class ArrayReserverAdapter extends ArrayAdapter<Voiture> {
    private ArrayList<Voiture> Voitures;
    private LayoutInflater inflater;

    public ArrayReserverAdapter(Context context, int textViewResourceId, ArrayList<Voiture> Voitures){
        super(context,R.layout.fragment_reserver,Voitures);
        this.inflater= LayoutInflater.from(context);
        this.Voitures=Voitures;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder Holder;
        if(convertView==null){
            BDD db = new BDD(getContext());
            db.getWritableDatabase();
            Holder=new ViewHolder();
            convertView=inflater.inflate(R.layout.itemreserver,null);
            Holder.Type=(TextView)convertView.findViewById(R.id.textView4);
            Holder.Matricule=(TextView)convertView.findViewById(R.id.textView5);
            Holder.disponible=(Button) convertView.findViewById(R.id.button3);
            convertView.setTag(Holder);
            Voiture voiture=db.getCarsWithIdOnly(String.valueOf(Voitures.get(position).getId_voiture()));
            Type_voiture tv=db.getTypeWithIdOnly(String.valueOf(Voitures.get(position).getId_type()));
            Holder.Type.setText(tv.getLibele());
            Holder.Matricule.setText(Voitures.get(position).getMatricule());
            if(Voitures.get(position).getDisponible()==0){
                Holder.disponible.setBackgroundColor(Color.RED);
            }
            else if(Voitures.get(position).getDisponible()==1){
                Holder.disponible.setBackgroundColor(Color.GREEN);
            }
        }else{
            Holder=(ViewHolder) convertView.getTag();
        }

        return convertView;
    }
    public class ViewHolder{
        public TextView Type;
        public TextView Matricule;
        public Button disponible;

    }
}
