package com.example.yandexlavka;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder> {

    private final List<Food> mFoods; //список
    private final OnFoodClickListener onClickListener;



    //конструктор
    public FoodAdapter(List<Food> foods, OnFoodClickListener onClickListener) {
        mFoods = foods;
        this.onClickListener = onClickListener;
    }

    public FoodAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View foodView = inflater.inflate(R.layout.recycler, parent, false);
        ViewHolder viewHolder = new ViewHolder(foodView);
        return viewHolder;
    }

    public void onBindViewHolder(FoodAdapter.ViewHolder holder, int position) {
        //получение объекта, для которого создается разметка
        Food food = mFoods.get(position);

        TextView txtName = holder.txtName;
        TextView txtPrice = holder.txtPrice;
        ImageView img = holder.img;
        //наполняем из полученного по позиции объекта country
        txtName.setText(food.getName());
        txtPrice.setText(" " + food.getPrice());
        img.setImageResource(food.getImage());
        //обработка нажатия
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //вызов метода слушателя, передавая данные
                onClickListener.onFoodClick(food, holder.getAdapterPosition());
            }
        });
    }

    public int getItemCount() {
        return mFoods.size();
    }

    //интерфейс слушателя события нажатия
    interface OnFoodClickListener {
        //метод, который будет получать выбранный объект и его позицию
        void onFoodClick(Food food, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txtName;
        public TextView txtPrice;
        public ImageView img;

        public ViewHolder(View itemView) {

            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtPrice = itemView.findViewById(R.id.txtPrice);
            img = itemView.findViewById(R.id.img);
        }
    }

}




