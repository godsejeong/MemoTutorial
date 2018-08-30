package com.example.sunrin.memo;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter extends BaseAdapter {
    // Adapter에 추가된 데이터를 저장하기 위한 ArrayList
    ArrayList<Data> items;


    // ListViewAdapter의 생성자
    public Adapter(ArrayList<Data> items){
        this.items = items;
    }

    // Adapter에 사용되는 데이터의 개수를 리턴. : 필수 구현
    @Override
    @Nullable
    public int getCount() {
        return items.size();
    }

    // 지정한 위치(position)에 있는 데이터 리턴 : 필수 구현@Override
    public Object getItem(int position) {
        return items.get(position);
    }

    // 지정한 위치(position)에 있는 데이터와 관계된 아이템(row)의 ID를 리턴. : 필수 구현
    @Override
    public long getItemId(int position) {
        return position;
    }

    // position에 위치한 데이터를 화면에 출력하는데 사용될 View를 리턴. : 필수 구현
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // "listview_item" Layout을 inflate하여 convertView 참조 획득.
        if(convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.item, parent, false);
        }
        //화면에 표시될 아이템의 포지션에 맞는 값을 가져옴
        Data data = items.get(position);

        // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
        TextView textView = convertView.findViewById(R.id.item_text);

        // 아이템 내 각 위젯에 데이터 반영
        textView.setText(data.getTitle());


        return convertView;
    }
}
