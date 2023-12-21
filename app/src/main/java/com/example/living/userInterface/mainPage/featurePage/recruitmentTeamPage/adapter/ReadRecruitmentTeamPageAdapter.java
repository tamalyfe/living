package com.example.living.userInterface.mainPage.featurePage.recruitmentTeamPage.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.living.R;
import com.example.living.data.remote.response.recruitmentCustomer.ItemResponseRecruitmentTeam;
import com.example.living.userInterface.mainPage.featurePage.recruitmentTeamPage.DetailRecruitmentTeamPageActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReadRecruitmentTeamPageAdapter extends RecyclerView.Adapter<ReadRecruitmentTeamPageAdapter.ViewHolder> {
    private Context context;
    private List<ItemResponseRecruitmentTeam> list;

    public ReadRecruitmentTeamPageAdapter(Context context, List<ItemResponseRecruitmentTeam> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.read_recruitment_team_page_adapter, parent, false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ItemResponseRecruitmentTeam result = list.get(position);
        holder.tvIdentifierRecruitmentTeam.setText(result.getIdentifierRecruitmentTeam()); holder.tvIdentifierRecruitmentTeam.setVisibility(View.GONE);
        holder.tvNameTeam.setText(result.getNameTeam());
        holder.tvPostTeam.setText(result.getPostTeam());
        holder.tvDomicileTeam.setText(result.getDomicileTeam()); holder.tvDomicileTeam.setVisibility(View.GONE);
        holder.tvJobDescription.setText(result.getJobDescription());
        holder.tvExperienceRecruitmentTeam.setText(result.getExperience()); holder.tvExperienceRecruitmentTeam.setVisibility(View.GONE);
        holder.tvCertificate.setText(result.getCertificate()); holder.tvCertificate.setVisibility(View.GONE);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.tvIdentifierRecruitmentTeam) TextView tvIdentifierRecruitmentTeam;
        @BindView(R.id.tvNameTeam) TextView tvNameTeam;
        @BindView(R.id.tvPostTeam) TextView tvPostTeam;
        @BindView(R.id.tvDomicileTeam) TextView tvDomicileTeam;
        @BindView(R.id.tvJobDescription) TextView tvJobDescription;
        @BindView(R.id.tvExperienceRecruitmentTeam) TextView tvExperienceRecruitmentTeam;
        @BindView(R.id.tvCertificate) TextView tvCertificate;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            String identifierRecruitmentTeam = tvIdentifierRecruitmentTeam.getText().toString();
            String nameTeam = tvNameTeam.getText().toString();
            String postTeam = tvPostTeam.getText().toString();
            String domicileTeam = tvDomicileTeam.getText().toString();
            String jobDescription = tvJobDescription.getText().toString();
            String experienceRecruitmentTeam = tvExperienceRecruitmentTeam.getText().toString();
            String certificate = tvCertificate.getText().toString();


            Intent intent = new Intent(context, DetailRecruitmentTeamPageActivity.class);
            intent.putExtra("identifier_recruitment_team", identifierRecruitmentTeam);
            intent.putExtra("name_team", nameTeam);
            intent.putExtra("post_team", postTeam);
            intent.putExtra("domicile_team", domicileTeam);
            intent.putExtra("job_description", jobDescription);
            intent.putExtra("experience_recruitment_team", experienceRecruitmentTeam);
            intent.putExtra("certificate", certificate);
            context.startActivity(intent);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
