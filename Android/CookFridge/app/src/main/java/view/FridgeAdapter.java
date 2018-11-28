package view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import model.ItemRowFridge;

public class FridgeAdapter extends ArrayAdapter<ItemRowFridge> {

    //scoreList is the model list to show
    public FridgeAdapter(Context context, List<ItemRowFridge> scoreList) {
        super(context, 0, scoreList);
    }
}

