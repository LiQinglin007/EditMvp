package com.example.lql.editmvp.adapter.studio;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lql.editmvp.R;
import com.example.lql.editmvp.bean.StudioListBean;
import com.example.lql.editmvp.myhttp.MyUrl;
import com.example.lql.editmvp.utils.Glide.GlidePicUtils;
import com.example.lql.editmvp.utils.RecyclerView.OnItemClickListener;

import java.util.ArrayList;


/**
 * 类描述： 工作室适配器
 * 作  者：李清林
 * 时  间：
 * 修改备注：
 */
public class StudioAdapter extends  RecyclerView.Adapter<StudioAdapter.MyViewHolder>{

    private ArrayList<StudioListBean.DataBean> mList = new ArrayList<>();
    private Context mContext;

    public StudioAdapter( Context context) {
        mContext = context;
    }
    private OnItemClickListener mOnItemClickLitener;


    public void setOnItemClickLitener(OnItemClickListener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public void  setList(ArrayList<StudioListBean.DataBean> list){
        mList.clear();
        mList.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public StudioAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        StudioAdapter.MyViewHolder myViewHolder=new StudioAdapter.MyViewHolder(LayoutInflater.from(mContext).
                inflate(R.layout.item_collection_studio,parent,false));
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final StudioAdapter.MyViewHolder holder, int position) {
        holder.item_collection_studio_cancel_tv.setVisibility(View.GONE);//取消收藏按钮隐藏掉


        holder.item_collection_studio_name_tv.setText(mList.get(position).getWorkName());
        holder.item_collection_studio_tv1.setText("诚信保证金："+mList.get(position).getDeposit());
        holder.item_collection_studio_tv2.setText("月销量："+mList.get(position).getCount());
        holder.item_collection_studio_tv3.setText("信誉值："+mList.get(position).getCredit());
        GlidePicUtils.NormalPic(mContext,MyUrl.Pic+mList.get(position).getHeadImg(),holder.item_collection_studio_img);

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
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView item_collection_studio_name_tv;//工作室名称
        TextView item_collection_studio_tv1;//诚信保证金
        TextView item_collection_studio_tv2;//月销量
        TextView item_collection_studio_tv3;//信誉值
        TextView item_collection_studio_cancel_tv;//取消收藏（可点击）
        ImageView item_collection_studio_img;//工作室头像



        public MyViewHolder(View itemView) {
            super(itemView);
            item_collection_studio_name_tv= (TextView) itemView.findViewById(R.id.item_collection_studio_name_tv);
            item_collection_studio_tv1= (TextView) itemView.findViewById(R.id.item_collection_studio_tv1);
            item_collection_studio_tv2= (TextView) itemView.findViewById(R.id.item_collection_studio_tv2);
            item_collection_studio_tv3= (TextView) itemView.findViewById(R.id.item_collection_studio_tv3);
            item_collection_studio_cancel_tv= (TextView) itemView.findViewById(R.id.item_collection_studio_cancel_tv);


            item_collection_studio_img= (ImageView) itemView.findViewById(R.id.item_collection_studio_img);

        }
    }
}