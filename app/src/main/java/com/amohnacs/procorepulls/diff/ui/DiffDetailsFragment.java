package com.amohnacs.procorepulls.diff.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amohnacs.common.mvp.MvpFragment;
import com.amohnacs.model.DiffPage;
import com.amohnacs.model.PullRequest;
import com.amohnacs.procorepulls.R;
import com.amohnacs.procorepulls.diff.Contract;
import com.amohnacs.procorepulls.diff.DiffDetailPresenter;
import com.amohnacs.procorepulls.diff.DiffRowRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class DiffDetailsFragment extends MvpFragment<DiffDetailPresenter, Contract.View> implements Contract.View {

    public static final String DIFF_URL = "diff_url";
    private String diffUrl;

    private OnListFragmentInteractionListener mListener;
    private DiffRowRecyclerViewAdapter adapter;
    private ArrayList<DiffPage> diffItems;

    private DiffDetailPresenter presenter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public DiffDetailsFragment() {
    }

    public static DiffDetailsFragment newInstance(String gitDiffUrl) {
        DiffDetailsFragment fragment = new DiffDetailsFragment();

        Bundle args = new Bundle();
        args.putString(DIFF_URL, gitDiffUrl);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            diffUrl = getArguments().getString(DIFF_URL);
        }

        presenter = DiffDetailPresenter.getInstance(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_diffrow, container, false);
        setRecyclerView(view);
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public DiffDetailPresenter getPresenter() {
        return presenter;
    }

    @Override
    public Contract.View getMvpView() {
        return this;
    }

    @Override
    public void updateList(List<DiffPage> items) {
        if (diffItems.size() > 0) {
            diffItems.clear();
        }
        diffItems.addAll(items);

        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void displayError(String message) {

    }

    public void setRecyclerView(View view) {
        // Set the adapter
        if (view instanceof RecyclerView) {
            diffItems = new ArrayList<>();

            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            adapter = new DiffRowRecyclerViewAdapter(diffItems, mListener);
            // TODO: 4/18/18  recyclerView.setAdapter();
            presenter.getDiffs(diffUrl);
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(PullRequest item);
    }
}
