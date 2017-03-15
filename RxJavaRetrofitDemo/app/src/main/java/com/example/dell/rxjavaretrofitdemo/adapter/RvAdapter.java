package com.example.dell.rxjavaretrofitdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dell.rxjavaretrofitdemo.R;
import com.example.dell.rxjavaretrofitdemo.model.entity.TextEntity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 项目:RxJavaRetrofitDemo
 * 作者:Tony
 * 日期:2017年03月15
 * 时间:17:34
 */

public class RvAdapter extends RecyclerView.Adapter<RvAdapter.ViewHolder> {
    public final static String URL_USER_ICON="http://pic.qiushibaike.com/system/avtnew/%d/%d/thumb/%s";
    private Context context;
    private List<TextEntity.ItemsBean> datas;

    public RvAdapter(Context context) {
        this.context = context;
        datas = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_text, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if(datas.get(position).getUser()!=null && datas.get(position).getUser().getLogin()!=null)
        {
            holder.login.setText(datas.get(position).getUser().getLogin());
        }
        if(datas.get(position).getContent()!=null)
        {
            holder.content.setText(datas.get(position).getContent());
        }
        if(datas.get(position).getUser()!=null && datas.get(position).getUser().getIcon()!=null)
        {
            String icon = String.format(URL_USER_ICON,datas.get(position).getUser().getId()/10000,datas.get(position).getUser().getId(),datas.get(position).getUser().getIcon());
            Glide.with(context).load(icon).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(holder.icon);
        }
    }


    public void addAll(List<TextEntity.ItemsBean> dd)
    {
        datas.addAll(dd);
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return datas.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.icon)
        ImageView icon;
        @BindView(R.id.login)
        TextView login;
        @BindView(R.id.content)
        TextView content;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
