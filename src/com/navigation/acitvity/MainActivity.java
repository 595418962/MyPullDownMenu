package com.navigation.acitvity;

import com.navigation.constant.ConstantCategoryMenu;
import com.navigation.control.PulldownMenuView;
import com.navigation.control.PulldownMenuView.OnMenuItemClickListener;
import com.navigation.utility.DeviceUtility;
import com.navigation.utility.MenuUtility;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Androidʵ����������ѡ��˵�Ч��
 * @Description: Androidʵ����������ѡ��˵�Ч��

 * @File: MainActivity.java

 * @Package com.navigation.acitvity

 * @Author Hanyonglu

 * @Date 2012-7-28 ����06:08:27

 * @Version V1.0
 */
public class MainActivity extends Activity {
	// ����ͷ��LinearLayout
	private LinearLayout linearLayoutTopic = null;
	// ���沼��
	private RelativeLayout layoutHeader = null;
	private LinearLayout layoutBottom = null;
	private FrameLayout layoutBody = null;
	// PulldownMenuView����������
	private MenuUtility menuUtility = null;
	// PulldownMenuView����
	private PulldownMenuView pullDownMenu = null;
	private TextView tvTopic = null;
	private ImageView ivTopic = null;
	
	private int height = 0;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        // ��ʼ��
        initViews();
    }
    
    /**
     * ��ʼ��
     */
    protected void initViews(){    
    	ivTopic = (ImageView) findViewById(R.id.imageViewTopic);
    	tvTopic = (TextView) findViewById(R.id.textViewTopic);
    	
    	linearLayoutTopic = (LinearLayout)findViewById(R.id.linearLayoutTopic);
    	linearLayoutTopic.setOnClickListener(TopicOnClickListener);
    	layoutHeader = (RelativeLayout) findViewById(R.id.layout_top_header);
    	layoutBottom = (LinearLayout) findViewById(R.id.layout_bottom);
    	layoutBody = (FrameLayout) findViewById(R.id.layout_body);
    	
    	height = DeviceUtility.getScreenSize(this)[1] - 
    			 layoutHeader.getLayoutParams().height - 
    		  	 layoutBottom.getLayoutParams().height -
    		  	 DeviceUtility.getStatusBarHeight(this);
    	
    	menuUtility = new MenuUtility(
    			MainActivity.this, 
    			ConstantCategoryMenu.newsImageRes,
    			ConstantCategoryMenu.newsMenuTexts,
    			height,layoutHeader);
    }
    
    /**
     * ��ʾPulldownMenuView
     */
    protected void showPulldownMenu(){
    	pullDownMenu = menuUtility.getPulldownMenuView((String)tvTopic.getText());
    	ivTopic.setImageResource(R.drawable.ic_menu_trangle_up);
    }
    
    /**
     * ����PulldownMenuView
     */
    protected void hidePulldownMenu(){
    	pullDownMenu.releasePopupMenuView();
    	ivTopic.setImageResource(R.drawable.ic_menu_trangle_down);
    }
    
    // ��������ͷ���¼�������
    private OnClickListener TopicOnClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			// ��ʼ��ʾ�����˵�
			showPulldownMenu();
			
			// TODO Auto-generated method stub
			pullDownMenu.setOnMenuItemClickListener(new OnMenuItemClickListener() {
				@Override
				public void onMenuItemClick(AdapterView<?> parent, View view, int position) {
					// TODO Auto-generated method stub
					tvTopic.setText(ConstantCategoryMenu.newsMenuTexts[position]);
					layoutBody.setBackgroundResource(ConstantCategoryMenu.newsBodyRes[position]);
				}
				
				@Override
				public void hideMenu() {
					// TODO Auto-generated method stub
					hidePulldownMenu();
				}
			});
			
			pullDownMenu.show();
		}
	};
}