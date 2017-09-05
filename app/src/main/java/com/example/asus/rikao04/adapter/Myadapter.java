package com.example.asus.rikao04.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.asus.rikao04.R;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 创建时间  2017/9/5 8:32
 * 创建人    gaozhijie
 * 类描述
 */
public class Myadapter extends RecyclerView.Adapter<Myadapter.ViewHolder> {
    private List<String> mlist;
    private Context context;
    private Button buttonConut;

    // 用来控制CheckBox的选中状况
    private static HashMap<Integer,Boolean> isSelected;

    public Myadapter(Context context, List<String> mlist) {
        this.mlist = mlist;
        this.context = context;
        isSelected = new HashMap<Integer, Boolean>();
        // 初始化数据
        initDate();


    }

    private void initDate() {
        for(int i=0; i<mlist.size();i++) {
            getIsSelected().put(i,false);
        }
    }
    public static HashMap<Integer,Boolean> getIsSelected() {
        return isSelected;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.name.setText(mlist.get(position));
        holder.itemView.setTag(position);

        // 根据isSelected来设置checkbox的选中状况
        holder.checkbox.setChecked(getIsSelected().get(position));

        holder.checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                int pos = holder.getLayoutPosition();
                isSelected.put(pos, isChecked);

                int count = 0;
                for (int i = 0; i < isSelected.size(); i++) {

                    if(isSelected.get(i).booleanValue()){
                        count++;
                    }
                }

                findSelectBox.findBox(count);

            }
        });

    }
    public interface FindSelectBox{
        void findBox(int count);
    }
    private FindSelectBox findSelectBox;

    public void setFindSelectBox(FindSelectBox findSelectBox){
        this.findSelectBox = findSelectBox;
    }



    @Override
    public int getItemCount() {
        return mlist == null ? 0 : mlist.size();
    }
    public interface setonItemClick{
        void onItemclick(View view,int position);


    }
    setonItemClick setonItemClick;

    public void setSetonItemClick(Myadapter.setonItemClick setonItemClick) {
        this.setonItemClick = setonItemClick;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.checkbox)
        public CheckBox checkbox;
        @BindView(R.id.name)
        TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
