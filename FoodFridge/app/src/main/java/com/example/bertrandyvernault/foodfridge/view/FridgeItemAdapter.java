package com.example.bertrandyvernault.foodfridge.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bertrandyvernault.foodfridge.R;
import com.example.bertrandyvernault.foodfridge.controller.FridgeFragment;
import com.example.bertrandyvernault.foodfridge.model.Product;

import java.util.ArrayList;
import java.util.List;

public class FridgeItemAdapter extends BaseAdapter {

    //Fields
    private Context mContext;
    private List<Product> mProduct;
    private LayoutInflater mInflater;

    //constructor
    public FridgeItemAdapter(Context context, ArrayList<Product> itemRowFridges)
    {
        this.mContext = context;
        this.mProduct = itemRowFridges;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mProduct.size();
    }

    @Override
    public Product getItem(int position) {
        return mProduct.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        // get information about item
        Product currentItem = getItem(position);

        if (view == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            view = mInflater.inflate(R.layout.adapter_item, null);
        }

        String itemName = currentItem.getName();
        String itemDate = currentItem.getDate();
        int itemAmount = currentItem.getAmount();

        TextView itemNameView = view.findViewById(R.id.item_name);
        itemNameView.setText(itemName);

        TextView itemAmountView = view.findViewById(R.id.item_amount);
        itemAmountView.setText(String.valueOf(itemAmount));

        TextView itemDateView = view.findViewById(R.id.item_date);
        itemDateView.setText(String.valueOf(itemDate));

        ImageView itemImageView = view.findViewById(R.id.item_icon);
        int resId = mContext.getResources().getIdentifier(itemName.toLowerCase(),  "drawable", mContext.getPackageName());
        itemImageView.setImageResource(resId);


        return view;
    }

    public void clearData(){
        mProduct.clear();
        notifyDataSetChanged();
    }
}
