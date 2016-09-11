package com.example.listinscrolldemo;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.listinscrolldemo.CustomcHorizontalScrollView.SendCurrentX;

public class MainActivity extends Activity {

	private XListView xList;
	private ArrayList<Data> lisDatas = new ArrayList<Data>();
	private CustomcHorizontalScrollView titScr;
	private ArrayList<View> views = new ArrayList<View>();
	private MyAdapter adapter;
	private int isUp = 0;// 0:默认，1：增加，2：减少
	private ImageView img;
	private View left;
	private View right;
	private int currentX, t;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		xList = (XListView) findViewById(R.id.xListView1);
		xList.setPullLoadEnable(false);
		xList.setPullRefreshEnable(false);

		final Data data = new Data("新浪", "36.75", "-1.00%", "-0.37", "89.58",
				"--");
		final Data data1 = new Data("纳斯达克", "5008.10", "+0.90%", "+44.57",
				"79.32", "152");
		final Data data2 = new Data("道琼斯", "18288", "-5.23%", "-65.51",
				"89.58", "85");
		final Data data3 = new Data("恒生指数", "24733.680", "-0.85%", "-11.27",
				"21.54", "232");
		final Data data4 = new Data("深证成指", "11526.22", "+2.31%", "+52.26",
				"59.24", "422");

		lisDatas.add(data);
		lisDatas.add(data1);
		lisDatas.add(data2);
		lisDatas.add(data3);
		lisDatas.add(data4);

		adapter = new MyAdapter();

		titScr = (CustomcHorizontalScrollView) findViewById(R.id.horizontalScrollView1);
		views.add(titScr);
		xList.setAdapter(adapter);

		img = (ImageView) findViewById(R.id.img);
		left = findViewById(R.id.left);
		right = findViewById(R.id.right);

		findViewById(R.id.textView4).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				lisDatas.clear();
				isUp++;
				if (isUp == 3) {
					isUp = 0;
				}
				switch (isUp) {
				case 0:
					img.setVisibility(View.GONE);
					lisDatas.add(data);
					lisDatas.add(data1);
					lisDatas.add(data2);
					lisDatas.add(data3);
					lisDatas.add(data4);
					adapter.notifyDataSetChanged();
					break;
				case 1:
					img.setVisibility(View.VISIBLE);
					img.setBackgroundResource(R.drawable.arrowup03);
					lisDatas.add(data4);
					lisDatas.add(data1);
					lisDatas.add(data3);
					lisDatas.add(data);
					lisDatas.add(data2);
					adapter.notifyDataSetChanged();
					break;
				case 2:
					img.setVisibility(View.VISIBLE);
					img.setBackgroundResource(R.drawable.arrdown03);
					lisDatas.add(data2);
					lisDatas.add(data);
					lisDatas.add(data3);
					lisDatas.add(data1);
					lisDatas.add(data4);
					adapter.notifyDataSetChanged();
					break;
				default:
					break;
				}

			}
		});
	}

	class MyAdapter extends BaseAdapter {

		public MyAdapter() {
			super();
			// TODO Auto-generated constructor stub

		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return lisDatas.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		ViewHolder holder;

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			if (convertView == null) {
				convertView = getLayoutInflater().inflate(R.layout.list_item,
						null);
				holder = new ViewHolder();
				holder.name = (TextView) convertView.findViewById(R.id.name);
				holder.down = (TextView) convertView
						.findViewById(R.id.textView3);
				holder.up = (TextView) convertView.findViewById(R.id.textView4);
				holder.nowPrice = (TextView) convertView
						.findViewById(R.id.textView5);
				holder.num = (TextView) convertView
						.findViewById(R.id.textView6);
				holder.detail = (TextView) convertView
						.findViewById(R.id.textView7);
				holder.scrollView = (CustomcHorizontalScrollView) convertView
						.findViewById(R.id.horizontalScrollView1);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			if (views.size() < lisDatas.size() + 1) {
				views.add(holder.scrollView);
			}
			Data data = lisDatas.get(position);
			holder.name.setText(data.getName());
			holder.down.setText(data.getDown());
			holder.up.setText(data.getUp());
			holder.nowPrice.setText(data.getNowPrice());
			holder.num.setText(data.getNum());
			holder.detail.setText(data.getDetail());

			holder.scrollView.setScrollView(views, position,
					new SendCurrentX() {

						@Override
						public void currentX(int currentX, int t) {
							// TODO Auto-generated method stub
							MainActivity.this.currentX = currentX;
							MainActivity.this.t = t;
							if (currentX == 0) {
								right.setVisibility(View.VISIBLE);
								left.setVisibility(View.GONE);
							} else if (currentX == 480) {
								right.setVisibility(View.GONE);
								left.setVisibility(View.VISIBLE);
							} else {
								right.setVisibility(View.VISIBLE);
								left.setVisibility(View.VISIBLE);
							}
						}
					});
			holder.scrollView.setOnTouchListener(new OnTouchListener() {

				@Override
				public boolean onTouch(View v, MotionEvent event) {
					// TODO Auto-generated method stub
					switch (event.getAction()) {
					case MotionEvent.ACTION_UP:
						holder.scrollView.setXScroll(currentX, t);
						break;

					default:
						break;
					}
					return false;
				}
			});

			return convertView;
		}

		class ViewHolder {
			TextView name;
			TextView nowPrice;
			TextView down;
			TextView up;
			TextView num;
			TextView detail;
			CustomcHorizontalScrollView scrollView;
		}
	}
}
