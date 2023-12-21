package com.example.living.userInterface.mainPage.featurePage.requestCustomerPage.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.living.R;
import com.example.living.data.remote.response.requestCustomer.ItemResponseRequestCustomer;
import com.example.living.userInterface.mainPage.featurePage.requestCustomerPage.DetailRequestCustomerPageActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReadRequestCustomerPageAdapter extends RecyclerView.Adapter<ReadRequestCustomerPageAdapter.ViewHolder> {
    private Context context;
    private List<ItemResponseRequestCustomer> list;

    public ReadRequestCustomerPageAdapter(Context context, List<ItemResponseRequestCustomer> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.read_request_customer_page_adapter, parent, false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ItemResponseRequestCustomer result = list.get(position);
        holder.tvIdentifierRequestCustomer.setText(result.getIdentifierRequestCustomer()); holder.tvIdentifierRequestCustomer.setVisibility(View.GONE);
        holder.tvNameCustomer.setText(result.getNameCustomer());
        holder.tvContactCustomer.setText(result.getContactCustomer());
        holder.tvDomicileCustomer.setText(result.getDomicileCustomer());
        holder.tvDescriptionRequestCustomer.setText(result.getDescriptionRequestCustomer());
        holder.tvLocationProject.setText(result.getLocationProject()); holder.tvLocationProject.setVisibility(View.GONE);
        holder.tvPriceListProjectCash.setText(result.getPriceListProjectCash()); holder.tvPriceListProjectCash.setVisibility(View.GONE);
        holder.tvPriceListProjectCredit.setText(result.getPriceListProjectCredit()); holder.tvPriceListProjectCredit.setVisibility(View.GONE);
        holder.tvStatusProject.setText(result.getStatusProject()); holder.tvStatusProject.setVisibility(View.GONE);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.tvIdentifierRequestCustomer) TextView tvIdentifierRequestCustomer;
        @BindView(R.id.tvNameCustomer) TextView tvNameCustomer;
        @BindView(R.id.tvContactCustomer) TextView tvContactCustomer;
        @BindView(R.id.tvDomicileCustomer) TextView tvDomicileCustomer;
        @BindView(R.id.tvDescriptionRequestCustomer) TextView tvDescriptionRequestCustomer;
        @BindView(R.id.tvLocationProject) TextView tvLocationProject;
        @BindView(R.id.tvPriceListProjectCash) TextView tvPriceListProjectCash;
        @BindView(R.id.tvPriceListProjectCredit) TextView tvPriceListProjectCredit;
        @BindView(R.id.tvStatusProject) TextView tvStatusProject;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            String identifierRequestCustomer = tvIdentifierRequestCustomer.getText().toString();
            String nameCustomer = tvNameCustomer.getText().toString();
            String contactCustomer = tvContactCustomer.getText().toString();
            String domicileCustomer = tvDomicileCustomer.getText().toString();
            String descriptionRequestCustomer = tvDescriptionRequestCustomer.getText().toString();
            String locationProject = tvLocationProject.getText().toString();
            String priceListProjectCash = tvPriceListProjectCash.getText().toString();
            String priceListProjectCredit = tvPriceListProjectCredit.getText().toString();
            String statusProject = tvStatusProject.getText().toString();

            Intent intent = new Intent(context, DetailRequestCustomerPageActivity.class);
            intent.putExtra("identifier_request_customer", identifierRequestCustomer);
            intent.putExtra("name_customer", nameCustomer);
            intent.putExtra("contact_customer", contactCustomer);
            intent.putExtra("domicile_customer", domicileCustomer);
            intent.putExtra("description_request_customer", descriptionRequestCustomer);
            intent.putExtra("location_project", locationProject);
            intent.putExtra("price_list_project_cash", priceListProjectCash);
            intent.putExtra("price_list_project_credit", priceListProjectCredit);
            intent.putExtra("status_project", statusProject);
            context.startActivity(intent);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
