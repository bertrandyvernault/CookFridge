package view;

import android.content.ClipData;
import android.content.Context;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bertrandyvernault.cookfridge.R;

import java.util.ArrayList;
import java.util.List;

import model.Product;

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

        view = mInflater.inflate(R.layout.adapter_item, null);

        // get information about item
        Product currentItem = getItem(position);
        String itemName = currentItem.getName();
        String itemDate = currentItem.getDate();
        int itemAmount = currentItem.getAmount();

        TextView itemNameView = view.findViewById(R.id.item_name);
        itemNameView.setText(itemName);

        TextView itemAmountView = view.findViewById(R.id.item_amount);
        itemAmountView.setText(String.valueOf(itemAmount));

        TextView itemDateView = view.findViewById(R.id.item_date);
        itemDateView.setText(String.valueOf("DATE : "+itemDate));

        ImageView itemImageView = view.findViewById(R.id.item_icon);
        int resId = mContext.getResources().getIdentifier(itemName.toLowerCase(),  "drawable", mContext.getPackageName());
        itemImageView.setImageResource(resId);


        return view;
    }
}
