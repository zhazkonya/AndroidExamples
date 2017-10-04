package com.example.zhaziraoskenbayeva.fragmentexample;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.beardedhen.androidbootstrap.BootstrapCircleThumbnail;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AnimalDetailsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AnimalDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AnimalDetailsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public AnimalDetailsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AnimalDetailsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AnimalDetailsFragment newInstance(String param1, String param2) {
        AnimalDetailsFragment fragment = new AnimalDetailsFragment();
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

    View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_animal_details, container, false);
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
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void updateView(String animalName){
        TextView animalNameTxtView = (TextView) v.findViewById(R.id.aNameTxtView);
        animalNameTxtView.setText(animalName);

        YoYo.with(Techniques.Tada)
                .duration(700)
                .repeat(5)
                .playOn(animalNameTxtView);

        BootstrapCircleThumbnail imgView =
                (BootstrapCircleThumbnail) v.findViewById(R.id.animalImgView);


        YoYo.with(Techniques.FadeIn)
                .duration(700)
                .repeat(1)
                .playOn(imgView);

        if(animalName.trim().equals("Cat")){
            Picasso.
                with(getContext())
                .load(
                        "https://www.petfinder.com/wp-content/uploads/2012/11/91615172-find-a-lump-on-cats-skin-632x475.jpg")
                    .into(imgView);
        }else if(animalName.trim().equals("Dog")){
            Picasso.
                    with(getContext())
                    .load(
                            "http://germanshepherdcountry.com/wp-content/uploads/2016/11/2-German-Shepherds-GSC-600x400.jpg")
                    .into(imgView);
        }
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
