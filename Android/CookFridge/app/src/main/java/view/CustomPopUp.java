package view;

import android.app.Activity;
import android.app.Dialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.bertrandyvernault.cookfridge.R;

public class CustomPopUp extends Dialog {

    //fields
    private String title;
    private String textName;
    private String textAmount;
    private Button doneButton, cancelButton;
    private TextView titleView, nameView, amountView;
    private EditText nameViewEdit, amountViewEdit;

    //Constructor
    public CustomPopUp(Activity activity){
        super(activity, R.style.Theme_AppCompat_DayNight);
        setContentView(R.layout.my_popup_add);
        this.title = "New Article";
        this.textName = " Enter the Name of the new article : ";
        this.textAmount = " Entre the amount : ";
        this.doneButton = findViewById(R.id.done_button);
        this.cancelButton = findViewById(R.id.cancel_button);
        this.titleView = findViewById(R.id.title);
        this.nameView = findViewById(R.id.text_name);
        this.amountView = findViewById(R.id.text_amount);
        this.nameViewEdit = findViewById(R.id.edit_text_name);
        this.amountViewEdit = findViewById(R.id.edit_text_amount);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTextName(String subTitle) {
        this.textName = textName;
    }

    public void setTextAmount(String textAmount) {
        this.textAmount = textAmount;
    }

    public Button getDoneButton() {
        return doneButton;
    }

    public Button getCancelButton() {
        return cancelButton;
    }

    public EditText getNameViewEdit() {
        return nameViewEdit;
    }

    public EditText getAmountViewEdit() {
        return amountViewEdit;
    }

    public void build(){
        show();
        titleView.setText(title);
        nameView.setText(textName);
        amountView.setText(textAmount);
    }
}
