package com.example.living.userInterface.mainPage.featurePage.requestCustomerPage.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.living.R;
import com.example.living.data.remote.response.project.ItemResponseProject;
import com.example.living.userInterface.mainPage.featurePage.projectPage.DetailProjectPageActivity;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailRequestCustomerPageAdapter extends RecyclerView.Adapter<DetailRequestCustomerPageAdapter.ViewHolder> {
    private Context context;
    private List<ItemResponseProject> list;

    public DetailRequestCustomerPageAdapter(Context context, List<ItemResponseProject> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public DetailRequestCustomerPageAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.detail_request_customer_page_adapter, parent, false);
        DetailRequestCustomerPageAdapter.ViewHolder holder = new DetailRequestCustomerPageAdapter.ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(DetailRequestCustomerPageAdapter.ViewHolder holder, int position) {
        ItemResponseProject result = list.get(position);
        holder.tvIdentifierProject.setText(result.getIdentifierProject()); holder.tvIdentifierProject.setVisibility(View.GONE);
        holder.tvNameProject.setText(result.getNameProject());
        holder.tvTypeProject.setText(result.getTypeProject());
        holder.tvAccessProject.setText(result.getAccessProject()); holder.tvAccessProject.setVisibility(View.GONE);
        holder.tvStatusProject.setText(result.getStatusProject());
        holder.tvLocationProject.setText(result.getLocationProject());
        holder.tvPriceListProjectCash.setText(result.getPriceListProjectCash());
        holder.tvPriceListProjectCredit.setText(result.getPriceListProjectCredit());
        holder.tvPromo.setText(result.getPromo()); holder.tvPromo.setVisibility(View.GONE);
        holder.tvDescriptionProject.setText(result.getDescriptionProject()); holder.tvDescriptionProject.setVisibility(View.GONE);
        holder.tvBedroom.setText(result.getBedroom()); holder.tvBedroom.setVisibility(View.GONE);
        holder.tvBathroom.setText(result.getBathroom()); holder.tvBathroom.setVisibility(View.GONE);
        holder.tvCarport.setText(result.getCarport()); holder.tvCarport.setVisibility(View.GONE);
        holder.tvBuildingArea.setText(result.getBuildingArea()); holder.tvBuildingArea.setVisibility(View.GONE);
        holder.tvSurfaceArea.setText(result.getSurfaceArea()); holder.tvSurfaceArea.setVisibility(View.GONE);
        holder.tvFacility.setText(result.getFacility()); holder.tvFacility.setVisibility(View.GONE);
        holder.tvNameDeveloper.setText(result.getNameDeveloper()); holder.tvNameDeveloper.setVisibility(View.GONE);
        holder.tvContactDeveloper.setText(result.getContactDeveloper()); holder.tvContactDeveloper.setVisibility(View.GONE);
        holder.tvLoanBank.setText(result.getLoanBank()); holder.tvLoanBank.setVisibility(View.GONE);
        holder.tvHandover.setText(result.getHandover()); holder.tvHandover.setVisibility(View.GONE);

        // Location Project
        holder.pbLocationProject.setMax(100);
        holder.pbLocationProject.setProgress(20);

        int progressLocationProject = holder.pbLocationProject.getProgress();
        holder.tvResultLocationProject.setText(progressLocationProject + "% of 100%");

        // Price List Project Cash
        holder.pbPriceListProjectCash.setMax(100);
        holder.pbPriceListProjectCash.setProgress(40);

        int progressPriceListProjectCash = holder.pbPriceListProjectCash.getProgress();
        holder.tvResultPriceListProjectCash.setText(progressPriceListProjectCash + "% of 100%");

        // Price List Project Credit
        holder.pbPriceListProjectCredit.setMax(100);
        holder.pbPriceListProjectCredit.setProgress(60);

        int progressPriceListProjectCredit = holder.pbPriceListProjectCredit.getProgress();
        holder.tvResultPriceListProjectCredit.setText(progressPriceListProjectCredit + "% of 100%");

        // Status Project
        holder.pbStatusProject.setMax(100);
        holder.pbStatusProject.setProgress(100);

        int progressStatusProject = holder.pbStatusProject.getProgress();
        holder.tvResultStatusProject.setText(progressStatusProject + "% of 100%");
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.tvIdentifierProject) TextView tvIdentifierProject;
        @BindView(R.id.tvNameProject) TextView tvNameProject;
        @BindView(R.id.tvTypeProject) TextView tvTypeProject;
        @BindView(R.id.tvAccessProject) TextView tvAccessProject;
        @BindView(R.id.tvStatusProject) TextView tvStatusProject;
        @BindView(R.id.tvLocationProject) TextView tvLocationProject;
        @BindView(R.id.tvPriceListProjectCash) TextView tvPriceListProjectCash;
        @BindView(R.id.tvPriceListProjectCredit) TextView tvPriceListProjectCredit;
        @BindView(R.id.tvPromo) TextView tvPromo;
        @BindView(R.id.tvDescriptionProject) TextView tvDescriptionProject;
        @BindView(R.id.tvBedroom) TextView tvBedroom;
        @BindView(R.id.tvBathroom) TextView tvBathroom;
        @BindView(R.id.tvCarport) TextView tvCarport;
        @BindView(R.id.tvBuildingArea) TextView tvBuildingArea;
        @BindView(R.id.tvSurfaceArea) TextView tvSurfaceArea;
        @BindView(R.id.tvFacility) TextView tvFacility;
        @BindView(R.id.tvNameDeveloper) TextView tvNameDeveloper;
        @BindView(R.id.tvContactDeveloper) TextView tvContactDeveloper;
        @BindView(R.id.tvLoanBank) TextView tvLoanBank;
        @BindView(R.id.tvHandover) TextView tvHandover;

        // Location Project
        @BindView(R.id.pbLocationProject) ProgressBar pbLocationProject;
        @BindView(R.id.tvResultLocationProject) TextView tvResultLocationProject;

        // Price List Project Cash
        @BindView(R.id.pbPriceListProjectCash) ProgressBar pbPriceListProjectCash;
        @BindView(R.id.tvResultPriceListProjectCash) TextView tvResultPriceListProjectCash;

        // Price List Project Credit
        @BindView(R.id.pbPriceListProjectCredit) ProgressBar pbPriceListProjectCredit;
        @BindView(R.id.tvResultPriceListProjectCredit) TextView tvResultPriceListProjectCredit;

        // Status Project
        @BindView(R.id.pbStatusProject) ProgressBar pbStatusProject;
        @BindView(R.id.tvResultStatusProject) TextView tvResultStatusProject;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            String identifierProject = tvIdentifierProject.getText().toString();
            String nameProject = tvNameProject.getText().toString();
            String typeProject = tvTypeProject.getText().toString();
            String accessProject = tvAccessProject.getText().toString();
            String statusProject = tvStatusProject.getText().toString();
            String locationProject = tvLocationProject.getText().toString();
            String priceListProjectCash = tvPriceListProjectCash.getText().toString();
            String priceListProjectCredit = tvPriceListProjectCredit.getText().toString();
            String promo = tvPromo.getText().toString();
            String descriptionProject = tvDescriptionProject.getText().toString();
            String bedroom = tvBedroom.getText().toString();
            String bathroom = tvBathroom.getText().toString();
            String carport = tvCarport.getText().toString();
            String buildingArea = tvBuildingArea.getText().toString();
            String surfaceArea = tvSurfaceArea.getText().toString();
            String facility = tvFacility.getText().toString();
            String nameDeveloper = tvNameDeveloper.getText().toString();
            String contactDeveloper = tvContactDeveloper.getText().toString();
            String loanBank = tvLoanBank.getText().toString();
            String handover = tvHandover.getText().toString();

            Intent intent = new Intent(context, DetailProjectPageActivity.class);
            intent.putExtra("identifier_project", identifierProject);
            intent.putExtra("name_project", nameProject);
            intent.putExtra("type_project", typeProject);
            intent.putExtra("access_project", accessProject);
            intent.putExtra("status_project", statusProject);
            intent.putExtra("location_project", locationProject);
            intent.putExtra("price_list_project_cash", priceListProjectCash);
            intent.putExtra("price_list_project_credit", priceListProjectCredit);
            intent.putExtra("promo", promo);
            intent.putExtra("description_project", descriptionProject);
            intent.putExtra("bedroom", bedroom);
            intent.putExtra("bathroom", bathroom);
            intent.putExtra("carport", carport);
            intent.putExtra("building_area", buildingArea);
            intent.putExtra("surface_area", surfaceArea);
            intent.putExtra("facility", facility);
            intent.putExtra("name_developer", nameDeveloper);
            intent.putExtra("contact_developer", contactDeveloper);
            intent.putExtra("loan_bank", loanBank);
            intent.putExtra("handover", handover);
            context.startActivity(intent);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
