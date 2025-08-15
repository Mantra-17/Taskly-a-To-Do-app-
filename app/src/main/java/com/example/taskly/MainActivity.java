package com.example.taskly;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.graphics.Paint;

import com.example.taskly.R;

public class MainActivity extends AppCompatActivity {

    private Button add;
    private AlertDialog dialog;
    private LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        add = findViewById(R.id.add);
        layout = findViewById(R.id.container);

        buildDialog();

        // Show dialog when "Add" button is pressed
        add.setOnClickListener(v -> dialog.show());
    }

    // Build input dialog for new task
    private void buildDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.dialog, null);

        final EditText name = view.findViewById(R.id.nameEdit);

        builder.setView(view);
        builder.setTitle("Enter your Task")
                .setPositiveButton("SAVE", (dialog, which) -> {
                    String taskName = name.getText().toString().trim();
                    if (!taskName.isEmpty()) {
                        addCard(taskName);
                    }
                })
                .setNegativeButton("Cancel", (dialog, which) -> {
                    // do nothing
                });

        dialog = builder.create();
    }

    // Add a new task card
    private void addCard(String taskName) {
        // Inflate card.xml with parent = layout, but don’t attach immediately
        final View view = getLayoutInflater().inflate(R.layout.card, layout, false);

        TextView nameView = view.findViewById(R.id.name);
        Button delete = view.findViewById(R.id.delete);
        CheckBox checkBox = view.findViewById(R.id.checkTask);

        nameView.setText(taskName);

        // Delete card on button click
        delete.setOnClickListener(v -> layout.removeView(view));

        // Strike-through when checkbox is checked
        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                nameView.setPaintFlags(nameView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            } else {
                nameView.setPaintFlags(nameView.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
            }
        });

        // Finally add the card to container
        layout.addView(view);
    }
}
