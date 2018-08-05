package com.developer.dinhduy.jsontutorial;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.hoder> {
    List<Product> list;
    Context context;

    public ProductAdapter(List<Product> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ProductAdapter.hoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.custom,parent,false);
        return new hoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.hoder holder, int position) {
        Product product=list.get(position);
        holder.TxtName.setText(product.getName());
        holder.TxtPrice.setText(product.getPrice());
        Picasso.get().load(product.getPath()).into(holder.Imageview);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class hoder extends RecyclerView.ViewHolder{
     private TextView TxtName,TxtPrice;
     private ImageView Imageview;
        public hoder(View itemView) {
            super(itemView);
            TxtName=(TextView) itemView.findViewById(R.id.id_view_Name);
            TxtPrice=(TextView) itemView.findViewById(R.id.id_view_Price);
            Imageview=(ImageView) itemView.findViewById(R.id.id_viewPicture);
        }
    }
}
