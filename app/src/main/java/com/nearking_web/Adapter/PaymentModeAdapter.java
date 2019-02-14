package com.nearking_web.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.nearking_web.R;
import com.nearking_web.extra.CommonConstant;
import com.nearking_web.model.PaymentModel;

import java.util.ArrayList;
import java.util.List;

public class PaymentModeAdapter extends RecyclerView.Adapter<PaymentModeAdapter.PaymentViewHolder> {
    private Context context;
    List<PaymentModel> paymentModels = new ArrayList<>();
    String id, title, mTitle;
    public int mSelectedItem = -1;

    public PaymentModeAdapter(Context context, List<PaymentModel> paymentModels) {
        this.context = context;
        this.paymentModels = paymentModels;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PaymentViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.payment_details, viewGroup, false);
        return new PaymentModeAdapter.PaymentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final PaymentViewHolder holder, final int position) {
        holder.radioButton.setChecked(position == mSelectedItem);

        id = paymentModels.get(position).getId();
        String title = paymentModels.get(position).getTitle();
        mTitle = paymentModels.get(position).getMethodTitle();
        holder.payText.setText(title);

    }

    @Override
    public int getItemCount() {
        return paymentModels.size();
    }

    public class PaymentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public RadioButton radioButton = null;
        public TextView payText;

        public PaymentViewHolder(@NonNull View itemView) {
            super(itemView);
            radioButton = (RadioButton) itemView.findViewById(R.id.radio1);
            payText = (TextView) itemView.findViewById(R.id.paytext);

            itemView.setOnClickListener(this);
            radioButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mSelectedItem = getAdapterPosition();
            CommonConstant.PAYMENTMODE=getAdapterPosition();
            notifyDataSetChanged();

        }
    }
}
