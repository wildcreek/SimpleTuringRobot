package com.enzo.turingrobot;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.enzo.turingrobot.Bean.ChatMessage;
import com.enzo.turingrobot.Bean.ChatMessage.Type;
import com.enzo.turingrobot.utils.HttpUtils;

public class MainActivity extends Activity{
	
	private ListView mMsg;
	private ChatMessageAdapter mAdapter;
	private List<ChatMessage> mDatas;
	private EditText inputMsg;
	private Button sendButton;
	private Handler mHandler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			
			ChatMessage fromMessage = (ChatMessage) msg.obj;
			mDatas.add(fromMessage);
			mAdapter.notifyDataSetChanged();
		};
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		initView();
		initDatas();
		//初始化事件
		initListener();
	}
	private void initListener() {
		sendButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				final String toMsg = inputMsg.getText().toString();
				if(TextUtils.isEmpty(toMsg)){
					Toast.makeText(MainActivity.this, "消息不能为空", 0).show();
					return;
				}
				ChatMessage toMessage = new ChatMessage();
				toMessage.setDate(new Date());
				toMessage.setMsg(toMsg);
				toMessage.setType(Type.OUTCOMING);
				mDatas.add(toMessage);
				mAdapter.notifyDataSetChanged();
				
				inputMsg.setText("");
				
				new Thread(){
					 public void run(){
						ChatMessage fromMessage = HttpUtils.sendMessage(toMsg);
						Message msg = Message.obtain();
						msg.obj = fromMessage;
						mHandler.sendMessage(msg);
					 }
				}.start();
			
				
			}
		});
	}
	private void initDatas() {
		mDatas = new ArrayList<ChatMessage>();
		mDatas.add(new ChatMessage("Hola！I'm Fairy，at your service.", Type.INCOMING, new Date()));
		mAdapter = new ChatMessageAdapter(this, mDatas);
		mMsg.setAdapter(mAdapter);
	}
	private void initView() {
		mMsg = (ListView) findViewById(R.id.id_listview_msgs);
		inputMsg=(EditText) findViewById(R.id.id_input_msg);
		sendButton=(Button) findViewById(R.id.id_send_msg);
	}
	
}
