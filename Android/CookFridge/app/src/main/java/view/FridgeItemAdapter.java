package view;

import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bertrandyvernault.cookfridge.R;

import java.util.List;

import model.ItemRowFridge;

public class FridgeItemAdapter extends BaseAdapter {

    //Fields
    private Context mContext;
    private List<ItemRowFridge> mItemRowFridges;
    private LayoutInflater mInflater;

    //constructor
    public FridgeItemAdapter(Context context, List<ItemRowFridge> itemRowFridges)
    {
        this.mContext = context;
        this.mItemRowFridges = itemRowFridges;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mItemRowFridges.size();
    }

    @Override
    public ItemRowFridge getItem(int position) {
        return mItemRowFridges.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        view = mInflater.inflate(R.layout.adapter_item, null);

        // get information about item
        ItemRowFridge currentItem = getItem(position);
        String itemName = currentItem.getName();
        int itemAmount = currentItem.getAmount();

        TextView itemNameView = view.findViewById(R.id.item_name);
        itemNameView.setText(itemName);

        TextView itemAmountView = view.findViewById(R.id.item_amount);
        itemAmountView.setText(String.valueOf(itemAmount));


        return view;
    }
}
