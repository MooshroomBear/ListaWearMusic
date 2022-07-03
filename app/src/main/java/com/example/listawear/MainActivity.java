package com.example.listawear;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.wear.widget.WearableLinearLayoutManager;
import androidx.wear.widget.WearableRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    private List<ItemsList> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WearableRecyclerView recyclerView = (WearableRecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setEdgeItemsCenteringEnabled(true);

        WearableLinearLayoutManager layoutManager = new WearableLinearLayoutManager(this);
        layoutManager.setOrientation(WearableLinearLayoutManager.VERTICAL);
        layoutManager.setLayoutCallback(new CustomScrollingLayoutCallback());

        recyclerView.setLayoutManager(layoutManager);

        items.add(new ItemsList(R.mipmap.audifonos, "Ingeniería en audio", "Curso de Ingeniería en audio"));
        items.add(new ItemsList(R.mipmap.curso_de_dj, "DJ", "Curso de DJ"));
        items.add(new ItemsList(R.mipmap.guitarra, "Guitarra", "Curso de Guitarra"));
        items.add(new ItemsList(R.mipmap.nota, "Música y canto", "Curso de Música y canto"));
        items.add(new ItemsList(R.mipmap.saxo, "Saxo", "Curso de Saxo"));

        ListAdapter listAdapter = new ListAdapter(items, new ListAdapter.AdapterCallback() {
            @Override
            public void onItemClicked(View v, int itemPosition) {
                Intent intent = new Intent(getApplicationContext(), CardActivity.class);
                intent.putExtra("titulo", items.get(itemPosition).getNameItem());
                intent.putExtra("descripcion", items.get(itemPosition).getDescriptionItem());
                startActivity(intent);
            }
        });

        recyclerView.setAdapter(listAdapter);

    }
}

class CustomScrollingLayoutCallback extends WearableLinearLayoutManager.LayoutCallback {
    /** How much should we scale the icon at most. */
    private static final float MAX_ICON_PROGRESS = 0.65f;

    private float progressToCenter;

    @Override
    public void onLayoutFinished(View child, RecyclerView parent) {

        float centerOffset = ((float) child.getHeight() / 2.0f) / (float) parent.getHeight();
        float yRelativeToCenterOffset = (child.getY() / parent.getHeight()) + centerOffset;

        progressToCenter = Math.abs(0.5f - yRelativeToCenterOffset);

        progressToCenter = Math.min(progressToCenter, MAX_ICON_PROGRESS);

        child.setScaleX(1 - progressToCenter);
        child.setScaleY(1 - progressToCenter);
    }
}