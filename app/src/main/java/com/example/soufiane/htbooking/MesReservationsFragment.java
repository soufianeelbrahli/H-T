package com.example.soufiane.htbooking;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MesReservationsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MesReservationsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MesReservationsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public MesReservationsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MesReservationsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MesReservationsFragment newInstance(String param1, String param2) {
        MesReservationsFragment fragment = new MesReservationsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final BDD db=new BDD(getActivity());
        db.getWritableDatabase();
        ArrayList<Reservation> Reservation=null;
        Reservation=db.getAllReservations();
        ListAdapter adapter = new ArrayMesReservationsAdapter(getContext(), R.layout.itemmesreservations, Reservation);
        View v = inflater.inflate(R.layout.fragment_mes_reservations, container, false);
        final ListView l=v.findViewById(R.id.list);
        l.setAdapter(adapter);
        l.setLongClickable(true);
        l.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view,final int pos, long id) {
                AlertDialog.Builder builder =new AlertDialog.Builder(getActivity());
                builder.setMessage("Etes-vous sur de vouloir annuler cette réservation ?").setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Reservation reservation=(Reservation)l.getAdapter().getItem(pos);
                        System.out.println(reservation.getId_voiture()+ "A7A7A7A7A7A7A7A7");
                        int id_adherent=getArguments().getInt("id_adherent");
                        Voiture voiture=db.getCarsWithIdOnly(String.valueOf(reservation.getId_voiture()));
                        db.supprimerReservationById(reservation.getId_reservation());
                        db.UpdateVoiture(voiture,1);
                        Toast.makeText(getContext(),"Réservation annulée",Toast.LENGTH_LONG).show();
                    }
                }).setNegativeButton("Annuler",null);
                AlertDialog alert=builder.create();
                alert.show();
                return true;
            }
        });
        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
