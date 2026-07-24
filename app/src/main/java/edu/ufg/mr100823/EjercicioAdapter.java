package edu.ufg.mr100823;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class EjercicioAdapter extends RecyclerView.Adapter<EjercicioAdapter.ViewHolder> {

    private List<Ejercicio> ejercicios;
    private Context context;

    public EjercicioAdapter(List<Ejercicio> ejercicios, Context context) {
        this.ejercicios = ejercicios;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ejercicio, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Ejercicio ejercicio = ejercicios.get(position);
        holder.tvTitulo.setText(ejercicio.getTitulo());
        holder.tvDescripcion.setText(ejercicio.getDescripcion());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ejercicio.getActivityClass());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return ejercicios.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitulo, tvDescripcion;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitulo = itemView.findViewById(R.id.tvTitulo);
            tvDescripcion = itemView.findViewById(R.id.tvDescripcion);
        }
    }
}
