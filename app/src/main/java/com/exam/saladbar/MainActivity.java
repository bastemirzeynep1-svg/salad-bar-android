package com.exam.saladbar;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MainActivity extends Activity {
    String[] ingredients = {"🥬", "🍅", "🥒", "🥕", "🧀"};
    ArrayList<String> currentOrder = new ArrayList<>();
    ArrayList<String> currentPlate = new ArrayList<>();
    int score = 0;
    TextView orderText, plateText, scoreText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        orderText = findViewById(R.id.orderText);
        plateText = findViewById(R.id.plateText);
        scoreText = findViewById(R.id.scoreText);

        findViewById(R.id.btn1).setOnClickListener(v -> addIngredient("🥬"));
        findViewById(R.id.btn2).setOnClickListener(v -> addIngredient("🍅"));
        findViewById(R.id.btn3).setOnClickListener(v -> addIngredient("🥒"));
        findViewById(R.id.btn4).setOnClickListener(v -> addIngredient("🥕"));
        findViewById(R.id.btn5).setOnClickListener(v -> addIngredient("🧀"));
        
        findViewById(R.id.btnClear).setOnClickListener(v -> {
            currentPlate.clear();
            updatePlateText();
        });

        findViewById(R.id.btnServe).setOnClickListener(v -> {
            ArrayList<String> sortedPlate = new ArrayList<>(currentPlate);
            ArrayList<String> sortedOrder = new ArrayList<>(currentOrder);
            Collections.sort(sortedPlate);
            Collections.sort(sortedOrder);

            if (sortedPlate.equals(sortedOrder) && !sortedPlate.isEmpty()) {
                score += 10;
                scoreText.setText("Score: " + score);
                Toast.makeText(this, "Perfect!", Toast.LENGTH_SHORT).show();
                generateOrder();
            } else {
                Toast.makeText(this, "Wrong order!", Toast.LENGTH_SHORT).show();
                currentPlate.clear();
                updatePlateText();
            }
        });

        generateOrder();
    }

    private void addIngredient(String item) {
        if (currentPlate.size() < 6) {
            currentPlate.add(item);
            updatePlateText();
        }
    }

    private void updatePlateText() {
        plateText.setText(String.join(" ", currentPlate));
    }

    private void generateOrder() {
        currentPlate.clear();
        updatePlateText();
        currentOrder.clear();
        Random r = new Random();
        int size = r.nextInt(3) + 2;
        for(int i = 0; i < size; i++) {
            currentOrder.add(ingredients[r.nextInt(ingredients.length)]);
        }
        orderText.setText(String.join(" ", currentOrder));
    }
}
