package com.example.lql.editmvp.adapter.studio;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lql.editmvp.R;
import com.example.lql.editmvp.bean.StudioDetailsBean;
import com.example.lql.editmvp.myhttp.MyUrl;
import com.example.lql.editmvp.utils.FinalData;
import com.example.lql.editmvp.utils.Glide.GlidePicUtils;
import com.example.lql.editmvp.utils.PublicStaticData;
import com.example.lql.editmvp.utils.RecyclerView.OnItemClickListener;

import java.util.ArrayList;

/**
 * 类描述：工作室详情
 * 作  者：李清林
 * 时  间：
 * 修改备注：
 */
public class StudioDetailsAdapter extends RecyclerView.Adapter<StudioDetailsAdapter.MyViewHolder> {

    private ArrayList<StudioDetailsBean.DataBean.ServiceBean> mList = new ArrayList<>();
    private Context mContext;

    public StudioDetailsAdapter( Context context) {
        mContext = context;
    }

    public void setList(ArrayList<StudioDetailsBean.DataBean.ServiceBean> list){
        mList.clear();
        mList.addAll(list);
        notifyDataSetChanged();
    }

    private OnItemClickListener mOnItemClickListener;
    public void setOnItemClickListener (OnItemClickListener onItemClickListener){
        mOnItemClickListener=onItemClickListener;
    }


    private View mHeaderView;
    public void setHeadView(View headerView) {
        mHeaderView = headerView;
        notifyItemInserted(0);
    }

    @Override
    public int getItemViewType(int position) {
        if(mHeaderView == null) return FinalData.TYPE_NORMAL;
        if(position == 0) return FinalData.TYPE_HEADER;
        return FinalData.TYPE_NORMAL;
    }



    @Override
    public StudioDetailsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(mHeaderView != null && viewType == FinalData.TYPE_HEADER) return new StudioDetailsAdapter.MyViewHolder(mHeaderView);
        View layout = LayoutInflater.from(mContext).inflate(R.layout.item_main_fragment, parent, false);
        return new StudioDetailsAdapter.MyViewHolder(layout);
    }



    @Override
    public void onBindViewHolder(final StudioDetailsAdapter.MyViewHolder holder, final int position) {
        if(getItemViewType(position) == FinalData.TYPE_HEADER) return;

        final int pos = getRealPosition(holder);
        if(holder instanceof StudioDetailsAdapter.MyViewHolder) {

            if(null!=mOnItemClickListener){
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mOnItemClickListener.onItemClick( holder.itemView,
                                holder.getLayoutPosition());
                    }
                });
            }

            holder.item_studio_priceold_tv.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            // 给 ImageView 设置一个 tag
            holder.item_studio_img.setTag(mList.get(pos).getPicture());
            // 通过 tag 来防止图片错位
            if (holder.item_studio_img.getTag() != null && holder.item_studio_img.getTag().equals(mList.get(pos).getPicture())) {
                GlidePicUtils.NormalPic(mContext, MyUrl.Pic + mList.get(pos).getPicture(),holder.item_studio_img);
            }

            holder.item_studio_name_tv.setText(mList.get(pos).getServiceName());

//        "Type": 1								1：检测查重2：修改降重3：编辑速审


            int type = mList.get(pos).getType();

            if (type == 1) {
                holder.item_studio_type_tv.setText("检测查重");
                if(mList.get(pos).getServicePrice().contains("-")||mList.get(pos).getServicePrice().contains("~")){

                }else{
                    if(Double.parseDouble(mList.get(pos).getServicePrice())==mList.get(pos).getOriginalCost()||mList.get(pos).getOriginalCost()==0){
                        holder.item_studio_priceold_tv.setVisibility(View.GONE);
                    }else{
                        holder.item_studio_priceold_tv.setVisibility(View.VISIBLE);
                    }
                }
            } else if (type == 2) {
                holder.item_studio_type_tv.setText("修改降重");
                if(mList.get(pos).getServicePrice().contains("-")||mList.get(pos).getServicePrice().contains("~")){

                }else {
                    if (Double.parseDouble(mList.get(pos).getServicePrice()) == mList.get(pos).getOriginalCost() || mList.get(pos).getOriginalCost() == 0) {
                        holder.item_studio_priceold_tv.setVisibility(View.GONE);
                    } else {
                        holder.item_studio_priceold_tv.setVisibility(View.VISIBLE);
                    }
                }
            } else {
                holder.item_studio_type_tv.setText("编辑速审");
                holder.item_studio_priceold_tv.setVisibility(View.GONE);
            }
            holder.item_studio_price_tv.setText("￥"+mList.get(pos).getServicePrice() + "");

            holder.item_studio_number_tv.setText("月销量:" + mList.get(pos).getCount());
            holder.item_studio_priceold_tv.setText("￥"+ PublicStaticData.ddf.format(mList.get(pos).getOriginalCost()));

        }
    }

    @Override
    public int getItemCount() {
        return mHeaderView==null?mList.size():mList.size()+1;
    }

    public int getRealPosition(RecyclerView.ViewHolder holder) {
        int position = holder.getLayoutPosition();
        return mHeaderView == null ? position : position - 1;
    }


    class MyViewHolder extends  RecyclerView.ViewHolder{
        TextView item_studio_name_tv;//名称
        TextView item_studio_type_tv;//服务类型检测查重
        TextView item_studio_price_tv;//现在的价格
        TextView item_studio_priceold_tv;//原来的价格
        TextView item_studio_number_tv;//月销量

        ImageView item_studio_img;

        public MyViewHolder(View itemView) {
            super(itemView);
            if(itemView == mHeaderView) return;
           item_studio_name_tv = (TextView) itemView.findViewById(R.id.item_studio_name_tv);
           item_studio_type_tv = (TextView) itemView.findViewById(R.id.item_studio_type_tv);
           item_studio_price_tv = (TextView) itemView.findViewById(R.id.item_studio_price_tv);
           item_studio_priceold_tv = (TextView) itemView.findViewById(R.id.item_studio_priceold_tv);
           item_studio_number_tv = (TextView) itemView.findViewById(R.id.item_studio_number_tv);
           item_studio_img = (ImageView) itemView.findViewById(R.id.item_studio_img);
        }
    }
}
