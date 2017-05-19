package com.example.lql.editmvp.adapter.main;

import android.content.Context;
import android.graphics.Paint;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lql.editmvp.R;
import com.example.lql.editmvp.bean.MainGetService;
import com.example.lql.editmvp.myhttp.MyUrl;
import com.example.lql.editmvp.utils.FinalData;
import com.example.lql.editmvp.utils.PublicStaticData;
import com.example.lql.editmvp.utils.RecyclerView.OnItemClickListener;

import java.util.ArrayList;

/**
 * 类描述：首页适配器
 * 作  者：李清林
 * 时  间：
 * 修改备注：
 */
public class MainServiceApapter extends RecyclerView.Adapter<MainServiceApapter.ViewHolder>{


    private Context mContext;

    public MainServiceApapter(Context mContext) {
        this.mContext = mContext;
    }

    private ArrayList<MainGetService.DataBean> mList=new ArrayList<>();

    public void setList(ArrayList<MainGetService.DataBean> mDataList){
        mList.clear();
        mList.addAll(mDataList);
        notifyDataSetChanged();
    }

    private OnItemClickListener mClick;

    public void setOnItemClickListener (OnItemClickListener mOnItemClickListener){
        mClick=mOnItemClickListener;
    }

    private View mHeaderView;
    public void setHeaderView(View headerView) {
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
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(mHeaderView != null && viewType == FinalData.TYPE_HEADER) return new MainServiceApapter.ViewHolder(mHeaderView);
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main_fragment, parent, false);
        return new MainServiceApapter.ViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if(getItemViewType(position) == FinalData.TYPE_HEADER) return;

        final int pos = getRealPosition(holder);
        if(holder instanceof MainServiceApapter.ViewHolder) {



            holder.item_main_name_tv.setText(mList.get(pos).getServiceName()+"");
//        Type1:检测查重 2：修改降重3：编辑速审
            if(mList.get(pos).getType()==1){
                holder.item_main_type_tv.setText("检测查重");
                //原价
                holder.item_main_priceold_tv.setText("￥"+ PublicStaticData.ddf.format(mList.get(pos).getOriginalCost()));
                holder.item_main_priceold_tv.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG); //中划线
                if(mList.get(pos).getServicePrice().contains("-")||mList.get(pos).getServicePrice().contains("~")){

                }else {
                    if (Double.parseDouble(mList.get(pos).getServicePrice()) == mList.get(pos).getOriginalCost() || mList.get(pos).getOriginalCost() == 0) {
                        holder.item_main_priceold_tv.setVisibility(View.GONE);
                    } else {
                        holder.item_main_priceold_tv.setVisibility(View.VISIBLE);
                    }
                }
            }else if(mList.get(pos).getType()==2){
                holder.item_main_type_tv.setText("修改降重");
                //原价
                holder.item_main_priceold_tv.setText("￥"+ PublicStaticData.ddf.format(mList.get(pos).getOriginalCost()));
                holder.item_main_priceold_tv.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG); //中划线

                if(mList.get(pos).getServicePrice().contains("-")||mList.get(pos).getServicePrice().contains("~")){

                }else {
                    if (mList.get(pos).getServicePrice().equals("" + mList.get(pos).getOriginalCost()) || mList.get(pos).getOriginalCost() == 0) {
                        holder.item_main_priceold_tv.setVisibility(View.GONE);
                    } else {
                        holder.item_main_priceold_tv.setVisibility(View.VISIBLE);
                    }
                }



            }else if(mList.get(pos).getType()==3){
                holder.item_main_type_tv.setText("编辑速审");
                holder.item_main_priceold_tv.setVisibility(View.INVISIBLE);
            }
            //现在的价格
            holder.item_main_price_tv.setText("￥"+mList.get(pos).getServicePrice());
            //月销量
            holder.item_main_number_tv.setText("月销量："+mList.get(pos).getCount());
            //设置图片
            Uri uri = Uri.parse(MyUrl.Pic+mList.get(pos).getPicture());
//        Uri uri = Uri.parse("https://www.baidu.com/img/bd_logo1.png");

            holder.item_main_img.setImageURI(uri);
        }
    }

    public int getRealPosition(RecyclerView.ViewHolder holder) {
        int position = holder.getLayoutPosition();
        return mHeaderView == null ? position : position - 1;
    }

    @Override
    public int getItemCount() {
        return mHeaderView==null?mList.size():mList.size()+1;
    }

    class ViewHolder extends  RecyclerView.ViewHolder{
        TextView item_main_name_tv;//名称
        TextView item_main_type_tv;//服务类型检测查重
        TextView item_main_price_tv;//现在的价格
        TextView item_main_number_tv;//月销量
        TextView item_main_priceold_tv;//原来的价格
        ImageView item_main_img;


        public ViewHolder(View itemView) {
            super(itemView);
            if(itemView == mHeaderView) return;
           item_main_name_tv= (TextView) itemView.findViewById(R.id.item_main_name_tv);
           item_main_type_tv= (TextView) itemView.findViewById(R.id.item_main_type_tv);
           item_main_price_tv= (TextView) itemView.findViewById(R.id.item_main_price_tv);
           item_main_priceold_tv= (TextView) itemView.findViewById(R.id.item_main_priceold_tv);

           item_main_number_tv= (TextView) itemView.findViewById(R.id.item_main_number_tv);
           item_main_img= (ImageView) itemView.findViewById(R.id.item_main_img);
        }
    }


}
