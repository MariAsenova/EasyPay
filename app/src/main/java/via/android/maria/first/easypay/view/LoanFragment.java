package via.android.maria.first.easypay.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import via.android.maria.first.easypay.R;
import via.android.maria.first.easypay.model.Loan;
import via.android.maria.first.easypay.view.adapter.LoanAdapter;
import via.android.maria.first.easypay.viewmodel.LoanViewModel;

public class LoanFragment extends Fragment {
    RecyclerView recyclerViewLoans;
    LoanAdapter loanAdapter;
    LoanViewModel loanViewModel;

    public LoanFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_loan, container, false);
        loanViewModel = new ViewModelProvider(this).get(LoanViewModel.class);
        loanViewModel.init();
        initRecyclerView();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loanViewModel.getLoans().observe(getViewLifecycleOwner(), new Observer<List<Loan>>() {
            @Override
            public void onChanged(List<Loan> loans) {
                loanAdapter.setLoanList(loans);
            }
        });
    }

    private void initRecyclerView() {
        loanAdapter = new LoanAdapter();
        recyclerViewLoans.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewLoans.setAdapter(loanAdapter);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}