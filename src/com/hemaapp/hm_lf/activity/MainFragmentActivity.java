package com.hemaapp.hm_lf.activity;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.hemaapp.hm_FrameWork.HemaNetTask;
import com.hemaapp.hm_FrameWork.result.HemaBaseResult;
import com.hemaapp.hm_lf.LfFragmentActivity;
import com.hemaapp.hm_lf.R;
import com.hemaapp.hm_lf.dialog.MyTwoButtonDialog;
import com.hemaapp.hm_lf.dialog.MyTwoButtonDialog.OnButtonListener;
import com.hemaapp.hm_lf.fragment.MainFragment;
/**
 * 主页的四个Fragment
 * @author Wen
 * @author HuFanglin
 *
 */
public class MainFragmentActivity extends LfFragmentActivity implements OnClickListener{
	
	private View layoutHead, layoutAsk, layoutDiscovery, layoutMe, layoutTemp;
	private ImageView imageTop, imageAsk, imageDiscovery, imageMe, imageTemp;
	private TextView txtTop, txtAsk, txtDiscovery, txtMe, txtTemp;
	private TextView txtBefore, txtTitle, txtNoticeCount;
	private View layoutImageRight, layoutImageRedCircle;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_mainpage);
		super.onCreate(savedInstanceState);
		ChangeFragment(MainFragment.class);//默认为第一个Tab页
		imageTop.setImageResource(R.drawable.icon_top_select);
		imageTemp = imageTop;
		txtTop.setTextColor(getResources().getColor(R.color.blue_main));
		txtTemp = txtTop;
		txtTitle.setText("来访");
	}

	@Override
	protected void callBeforeDataBack(HemaNetTask netTask) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void callAfterDataBack(HemaNetTask netTask) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void callBackForServerSuccess(HemaNetTask netTask,
			HemaBaseResult baseResult) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void callBackForServerFailed(HemaNetTask netTask,
			HemaBaseResult baseResult) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void callBackForGetDataFailed(HemaNetTask netTask, int failedType) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void findView() {
		layoutHead = findViewById(R.id.layoutHead);
		layoutAsk = findViewById(R.id.layoutAsk);
		layoutDiscovery = findViewById(R.id.layoutDiscovery);
		layoutMe = findViewById(R.id.layoutMe);
		imageTop = (ImageView)findViewById(R.id.imageTop);
		imageTop.setTag(R.id.Tab_None, R.drawable.icon_top_none);
		imageTop.setTag(R.id.Tab_Select, R.drawable.icon_top_select);
		imageAsk = (ImageView)findViewById(R.id.imageAsk);
		imageAsk.setTag(R.id.Tab_None, R.drawable.icon_ask_none);
		imageAsk.setTag(R.id.Tab_Select, R.drawable.icon_ask_select);
		imageDiscovery = (ImageView)findViewById(R.id.imageDiscovery);
		imageDiscovery.setTag(R.id.Tab_None, R.drawable.icon_discovery_none);
		imageDiscovery.setTag(R.id.Tab_Select, R.drawable.icon_discovery_select);
		imageMe = (ImageView)findViewById(R.id.imageMe);
		imageMe.setTag(R.id.Tab_None, R.drawable.icon_me_none);
		imageMe.setTag(R.id.Tab_Select, R.drawable.icon_me_select);
		txtTop = (TextView)findViewById(R.id.txtTop);
		txtAsk = (TextView)findViewById(R.id.txtAsk);
		txtDiscovery = (TextView)findViewById(R.id.txtDiscovery);
		txtMe = (TextView)findViewById(R.id.txtMe);
		txtBefore = (TextView)findViewById(R.id.txtBefore);
		txtTitle = (TextView)findViewById(R.id.txtTitle);
		txtNoticeCount = (TextView)findViewById(R.id.txtNoticeCount);
		layoutImageRight = findViewById(R.id.layoutImageRight);
		layoutImageRedCircle = findViewById(R.id.layoutImageRedCircle);
	}

	@Override
	protected void getExras() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void setListener() {
		layoutHead.setOnClickListener(this);
		layoutAsk.setOnClickListener(this);
		layoutDiscovery.setOnClickListener(this);
		layoutMe.setOnClickListener(this);
		txtBefore.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.layoutHead:
			ChangeIconAndColor(layoutHead, imageTop, txtTop);
			ChangeFragment(MainFragment.class);
			txtTitle.setText(R.string.laifang);
			txtBefore.setVisibility(View.VISIBLE);
			layoutImageRight.setVisibility(View.VISIBLE);
			break;
		case R.id.layoutAsk:
			ChangeIconAndColor(layoutAsk, imageAsk, txtAsk);
			ChangeFragment(MainFragment.class);
			txtTitle.setText(R.string.stringAsk);
			txtBefore.setVisibility(View.INVISIBLE);
			layoutImageRight.setVisibility(View.INVISIBLE);
			
			break;
		case R.id.layoutDiscovery:
			ChangeIconAndColor(layoutDiscovery, imageDiscovery, txtDiscovery);
			ChangeFragment(MainFragment.class);
			txtTitle.setText(R.string.stringDiscovery);
			txtBefore.setVisibility(View.INVISIBLE);
			layoutImageRight.setVisibility(View.INVISIBLE);
			
			break;
		case R.id.layoutMe://点击我的
			ChangeIconAndColor(layoutMe, imageMe, txtMe);
			//李坚请在这里换成自己的Fragment即可
			ChangeFragment(MainFragment.class);
			txtTitle.setText(R.string.usercenter);
			txtBefore.setVisibility(View.INVISIBLE);
			layoutImageRight.setVisibility(View.INVISIBLE);
			
			break;
		case R.id.txtBefore:
			Intent intent = new Intent(MainFragmentActivity.this, LoginActivity.class);
			startActivity(intent);
			overridePendingTransition(R.anim.right_in, R.anim.none);
			break;
		}
	}
	/**
	 * 修改图标
	 * @param layoutView
	 * @param imageView
	 * @param textView
	 */
	private void ChangeIconAndColor(View layoutView, ImageView imageView, TextView textView)
	{
		imageTemp.setImageResource((int)imageTemp.getTag(R.id.Tab_None));
		imageView.setImageResource((int)imageView.getTag(R.id.Tab_Select));
		imageTemp = imageView;
		
		txtTemp.setTextColor(getResources().getColor(R.color.grey_text));
		textView.setTextColor(getResources().getColor(R.color.blue_main));
		txtTemp = textView;
		
		layoutTemp = layoutView;
	}
	
	/**
	 * 切换Fragment
	 * @param c
	 */
	private void ChangeFragment(Class<? extends Fragment> c) {
		FragmentManager manager = getSupportFragmentManager();
		String tag = c.getName();
		FragmentTransaction transaction = manager.beginTransaction();
		Fragment fragment = manager.findFragmentByTag(tag);

		if (fragment == null) {
			try {
				fragment = c.newInstance();
				// 替换时保留Fragment,以便复用
				transaction.add(R.id.frameLayout, fragment, tag);
			} catch (Exception e) {
			}
		} else {
		}

		// 遍历存在的Fragment,隐藏其他Fragment
		List<Fragment> fragments = manager.getFragments();
		if (fragments != null)
			for (Fragment fm : fragments)
				if (!fm.equals(fragment))
					transaction.hide(fm);
		transaction.show(fragment);
		transaction.commit();
	}
	
	@Override
	protected boolean onKeyBack() {
		MyTwoButtonDialog quitDialog = new MyTwoButtonDialog(mContext);
		quitDialog.setText("确定要退出吗？");
		quitDialog.setCancelable(true);
		quitDialog.setButtonListener(new OnButtonListener() {
			
			@Override
			public void onRightButtonClick(MyTwoButtonDialog dialog) {
				dialog.cancel();
				finish();
			}
			
			@Override
			public void onLeftButtonClick(MyTwoButtonDialog dialog) {
				dialog.cancel();
			}
		});
		quitDialog.show();
		return false;
	}

}
