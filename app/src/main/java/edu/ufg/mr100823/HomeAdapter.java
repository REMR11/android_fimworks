package edu.ufg.mr100823;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_SECTION = 0;
    private static final int TYPE_EJERCICIO = 1;

    private List<Seccion> secciones;
    private Context context;
    private List<Object> itemsFlattened;

    public HomeAdapter(List<Seccion> secciones, Context context) {
        this.secciones = secciones;
        this.context = context;
        flattenItems();
    }

    private void flattenItems() {
        itemsFlattened = new ArrayList<>();
        for (Seccion seccion : secciones) {
            itemsFlattened.add(seccion);
            if (seccion.isExpandida()) {
                itemsFlattened.addAll(seccion.getEjercicios());
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (itemsFlattened.get(position) instanceof Seccion) {
            return TYPE_SECTION;
        } else {
            return TYPE_EJERCICIO;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_SECTION) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_seccion, parent, false);
            return new SectionViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ejercicio, parent, false);
            return new EjercicioViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Object item = itemsFlattened.get(position);
        if (holder instanceof SectionViewHolder) {
            Seccion seccion = (Seccion) item;
            SectionViewHolder sectionHolder = (SectionViewHolder) holder;
            sectionHolder.tvSeccionTitulo.setText(seccion.getTitulo());
            sectionHolder.ivFlecha.setImageResource(seccion.isExpandida() ? android.R.drawable.arrow_up_float : android.R.drawable.arrow_down_float);
            
            sectionHolder.itemView.setOnClickListener(v -> {
                seccion.setExpandida(!seccion.isExpandida());
                flattenItems();
                notifyDataSetChanged();
            });
        } else if (holder instanceof EjercicioViewHolder) {
            Ejercicio ejercicio = (Ejercicio) item;
            EjercicioViewHolder ejercicioHolder = (EjercicioViewHolder) holder;
            ejercicioHolder.tvTitulo.setText(ejercicio.getTitulo());
            ejercicioHolder.tvDescripcion.setText(ejercicio.getDescripcion());
            
            ejercicioHolder.itemView.setOnClickListener(v -> {
                Intent intent = new Intent(context, ejercicio.getActivityClass());
                context.startActivity(intent);
            });
        }
    }

    @Override
    public int getItemCount() {
        return itemsFlattened.size();
    }

    public static class SectionViewHolder extends RecyclerView.ViewHolder {
        TextView tvSeccionTitulo;
        ImageView ivFlecha;

        public SectionViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSeccionTitulo = itemView.findViewById(R.id.tvSeccionTitulo);
            ivFlecha = itemView.findViewById(R.id.ivFlecha);
        }
    }

    public static class EjercicioViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitulo, tvDescripcion;

        public EjercicioViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitulo = itemView.findViewById(R.id.tvTitulo);
            tvDescripcion = itemView.findViewById(R.id.tvDescripcion);
        }
    }
}
