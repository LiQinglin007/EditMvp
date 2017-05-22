package com.example.lql.editmvp.adapter.main;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lql.editmvp.R;
import com.example.lql.editmvp.bean.NoticeBean;
import com.example.lql.editmvp.utils.RecyclerView.OnItemClickListener;

import java.util.ArrayList;

/**
 * 类描述：公告适配器
 * 作  者：李清林
 * 时  间：
 * 修改备注：
 */

public class NoticeAdapter extends  RecyclerView.Adapter<NoticeAdapter.MyViewHolder>{

    private ArrayList<NoticeBean.DataBean> mList = new ArrayList<>();
    private Context mContext;
    private OnItemClickListener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickListener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }


    public NoticeAdapter(Context context) {
        mContext = context;
    }


    public void setList(ArrayList<NoticeBean.DataBean> list){
        mList.clear();
        mList.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public NoticeAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NoticeAdapter.MyViewHolder(LayoutInflater.
                from(mContext).inflate(R.layout.item_notice,parent,false));
    }

    @Override
    public void onBindViewHolder(final NoticeAdapter.MyViewHolder holder, int position) {
        //点击事件
        if(mOnItemClickLitener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(holder.itemView, pos);
                }
            });
        }

        holder.item_notice_title.setText(mList.get(position).getNotice()+"");
        holder.item_notice_content.setText(mList.get(position).getContent()+"");
        holder.item_notice_time.setText(mList.get(position).getCreateTime()+"");
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView item_notice_title;//名称
        TextView item_notice_content;//内容
        TextView item_notice_time;//时间


        public MyViewHolder(View itemView) {

            super(itemView);
            item_notice_title= (TextView) itemView.findViewById(R.id.item_notice_title);
            item_notice_content= (TextView) itemView.findViewById(R.id.item_notice_content);
            item_notice_time= (TextView) itemView.findViewById(R.id.item_notice_time);
        }
    }
}