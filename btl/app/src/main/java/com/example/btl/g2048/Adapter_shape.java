package com.example.btl.g2048;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.btl.R;

import java.util.ArrayList;
import java.util.List;

public class Adapter_shape extends ArrayAdapter {
    private Context ct;
    private ArrayList<Integer> arr;
    public Adapter_shape(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        this.ct = context;
        this.arr =  new ArrayList<>(objects);
    }

    @NonNull
    @Override
    public void notifyDataSetChanged(){
        arr = DataGame.getDataGame().getArr();
        super.notifyDataSetChanged();
    }
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)  ct.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.shape,null);
        }
        if(arr.size()>0){
            Square square = (Square) convertView.findViewById(R.id.square);
            square.setSize(arr.get(position));
        }
        return convertView;
    }
}
