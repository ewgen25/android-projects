package com.example.a4tassk;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CarsAdapter extends RecyclerView.Adapter<CarsAdapter.ViewHolder> {
    private final OnCarClickListener onClickListener;
    private final List<Car> mCars; //список

    //интерфейс слушателя событий
    interface OnCarClickListener {
        void onCarClick(Car car, int position);
    }
    //конструктор
    public CarsAdapter(List<Car> cars, OnCarClickListener onClickListener) {
        mCars = cars;
        this.onClickListener = onClickListener;
    }

    //создание ячейки
    public CarsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View carView = inflater.inflate(R.layout.recyclerview_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(carView);
        return viewHolder;
    }


    //привязка данных к ячейке
    public void onBindViewHolder(CarsAdapter.ViewHolder holder, int position) {
        //получение объекта, для которого создается разметка
        Car car = mCars.get(position);

        TextView carName = holder.carName;
        TextView carClass = holder.carClass;
        TextView carColor = holder.carColor;
        TextView carCost = holder.carCost;
        TextView carRating = holder.carRating;
        ImageView carImg = holder.carImg;
        //наполнение из полученного по позиции объекта car
        carName.setText(car.getCarName());
        carClass.setText(car.getCarClass());
        carColor.setText(car.getCarColor());
        carCost.setText(""+car.getCarCost());
        carRating.setText(""+car.getCarRating());
        carImg.setImageResource(car.getCarImg());
        //обработка нажатия
        holder.next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //вызов метода слушателя, передавая ему данные
                onClickListener.onCarClick(car, holder.getAdapterPosition());

            }
        });
    }
    //количество элементов в списке
    public int getItemCount() {
        return mCars.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView carName;
        public TextView carClass;
        public TextView carCost;
        public TextView carColor;
        public TextView carRating;
        public ImageView carImg;
        public TextView next;



        public ViewHolder(View itemView) {
            super(itemView);
            carClass = itemView.findViewById(R.id.carClass);
            carColor = itemView.findViewById(R.id.carColor);
            carCost = itemView.findViewById(R.id.carCost);
            carName = itemView.findViewById(R.id.carName);
            carRating = itemView.findViewById(R.id.carRating);
            carImg = itemView.findViewById(R.id.carImg);
            next = itemView.findViewById(R.id.next);
        }
    }
}
