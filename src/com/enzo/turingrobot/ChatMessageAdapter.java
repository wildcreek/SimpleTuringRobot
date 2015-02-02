package com.enzo.turingrobot;

import java.text.SimpleDateFormat;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.enzo.turingrobot.Bean.ChatMessage;
import com.enzo.turingrobot.Bean.ChatMessage.Type;

 

public class ChatMessageAdapter extends BaseAdapter {
	private LayoutInflater inflater;
	private List<ChatMessage> mDatas;
	public ChatMessageAdapter(Context context,List<ChatMessage> mDatas) {
		inflater=LayoutInflater.from(context);
		 this.mDatas=mDatas;
	}

	@Override
	public int getCount() {
		return mDatas.size();
	}

	@Override
	public Object getItem(int position) {
		return mDatas.get(position) ;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	
	@Override
	public int getItemViewType(int position) {
		ChatMessage  chatMessage = mDatas.get(position);
		if(chatMessage.getType()==Type.INCOMING){
			return 0;
		}
		return 1;
	}

	@Override
	public int getViewTypeCount() {
		 
		return 2;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ChatMessage  chatMessage = mDatas.get(position);
		ViewHolder viewHolder =null;
		if(convertView==null){
			if(getItemViewType(position)==0){
				convertView = inflater.inflate(R.layout.item_from_msg, parent,
						false);
				viewHolder = new ViewHolder();
				viewHolder.mDate = (TextView) convertView
						.findViewById(R.id.id_from_msg_date);
				viewHolder.mMsg = (TextView) convertView
						.findViewById(R.id.id_from_text);
			}else{
				convertView = inflater.inflate(R.layout.item_to_msg, parent,
						false);
				viewHolder = new ViewHolder();
				viewHolder.mDate = (TextView) convertView
						.findViewById(R.id.id_to_msg_date);
				viewHolder.mMsg = (TextView) convertView
						.findViewById(R.id.id_to_text);
			}
			convertView.setTag(viewHolder);
		}else{
			viewHolder =(ViewHolder) convertView.getTag();
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyyƒÍMM‘¬dd»’  hh:mm");
		String date = format.format(chatMessage.getDate());
		viewHolder.mDate.setText(date);
		viewHolder.mMsg.setText(chatMessage.getMsg());
		return convertView;
	}
	 private final class ViewHolder{
		 TextView mDate;
		 TextView mMsg;
		 
	 }
	 

}

