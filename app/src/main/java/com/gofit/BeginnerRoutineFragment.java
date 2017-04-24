package com.gofit;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.lang.reflect.Array;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BeginnerRoutineFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BeginnerRoutineFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BeginnerRoutineFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    ArrayList list  = new ArrayList();
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public BeginnerRoutineFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BeginnerRoutineFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BeginnerRoutineFragment newInstance(String param1, String param2) {
        BeginnerRoutineFragment fragment = new BeginnerRoutineFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    public static BeginnerRoutineFragment newInstance(ArrayList list) {
        BeginnerRoutineFragment fragment = new BeginnerRoutineFragment();
        Bundle args = new Bundle();
        args.putSerializable("list",list);
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
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_beginner_routine, container, false);
        final ArrayList list = (ArrayList) getArguments().getSerializable("list");

        RecyclerView beginnerRecycler = (RecyclerView) rootView.findViewById(R.id.beginnerlist);
        MyAdapter adapter = new MyAdapter(list,getContext());

        LinearLayoutManager layout= new LinearLayoutManager(getContext());
        beginnerRecycler.setLayoutManager(layout);
        beginnerRecycler.setAdapter(adapter);
        MyAdapter.OnItemClickListener listener = new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                mListener.onFragmentInteraction(v,position);

            }

        };
        Button begin = (Button) rootView.findViewById(R.id.button2);
        begin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.beginWorkout(list);
            }
        });
        adapter.setOnItemClickListener(listener,getActivity());
        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
           // mListener.onFragmentInteraction(uri);
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
        void onFragmentInteraction(View v, int position);
        void beginWorkout(ArrayList list);
    }
}
