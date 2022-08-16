package mobiler.abbosbek.ecommerceapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import mobiler.abbosbek.ecommerceapp.R;
import mobiler.abbosbek.ecommerceapp.models.AddressModel;

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.ViewHolder> {

    List<AddressModel> list;
    Context context;
    SelectedAddress selectedAddress;

    private RadioButton selectedRadioButton;

    public AddressAdapter(Context context,List<AddressModel> list,SelectedAddress selectedAddress){
        this.context = context;
        this.list = list;
        this.selectedAddress = selectedAddress;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.address_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.address.setText(list.get(position).getUserAddress());
        holder.radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (AddressModel address : list){
                    address.setSelected(false);
                }
                list.get(position).setSelected(true);

                if (selectedRadioButton !=null){
                    selectedRadioButton.setChecked(false);
                }
                selectedRadioButton = (RadioButton) view;
                selectedRadioButton.setChecked(true);
                selectedAddress.setAddress(list.get(position).getUserAddress());

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView address;
        RadioButton radioButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            address = itemView.findViewById(R.id.address_add);
            radioButton = itemView.findViewById(R.id.select_address);
        }
    }

    public interface SelectedAddress{
        void setAddress(String address);
    }
}
