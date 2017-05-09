package com.fancyfamily.primarylibrary.commentlibrary.ui.comment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.ImageView;

import com.fancyfamily.primarylibrary.R;
import com.fancyfamily.primarylibrary.commentlibrary.framework.base.BaseActivity;
import com.fancyfamily.primarylibrary.commentlibrary.util.NavBarManager;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import uk.co.senab.photoview.PhotoView;


public class PicBrowserActivity extends BaseActivity
        implements OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);// 去掉信息栏

        setContentView(R.layout.activity_pic_browser);
        initRes();


    }


    private ArrayList<ImageInfo> ImgList = new ArrayList<ImageInfo>();


    NavBarManager manager;
    protected void initRes() {

        manager = new NavBarManager(this);



        viewPager = (ViewPager) findViewById(R.id.guidePages);


        manager.setRight("删除");
        manager.btn_right.setOnClickListener(this);
        manager.btn_back.setOnClickListener(this);
        index = getIntent().getIntExtra("index", 0);
        type = getIntent().getIntExtra("type", 0);
        isEdit = getIntent().getBooleanExtra("isEdit", true);
        ImgList = (ArrayList<ImageInfo>) getIntent()
                .getSerializableExtra("data");
        cindex = index;
        if (isEdit) {
            manager.btn_right.setVisibility(View.VISIBLE);
        } else {
            manager.btn_right.setVisibility(View.GONE);
        }
        initPage(ImgList);
    }

    private int cindex = 0;
    private int index = 0;
    private int type = 0;
    private boolean isEdit = true;



    private ViewPager viewPager;
    private SamplePagerAdapter pagerAdapter;

    private void initPage(ArrayList<ImageInfo> pages) {

        manager.txt_title.setText((index + 1) + "/" + ImgList.size() + "  ");
        pagerAdapter = new SamplePagerAdapter();
        viewPager.setAdapter(pagerAdapter);
        viewPager.setOnPageChangeListener(new GuidePageChangeListener());

        viewPager.setCurrentItem(index);
    }

    private DisplayImageOptions options;
    private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();

    private class AnimateFirstDisplayListener
            extends SimpleImageLoadingListener {

        final List<String> displayedImages = Collections
                .synchronizedList(new LinkedList<String>());

        @Override
        public void onLoadingComplete(String imageUri, View view,
                                      Bitmap loadedImage) {
            if (loadedImage != null) {
                ImageView imageView = (ImageView) view;
                imageView.setImageBitmap(loadedImage);
                boolean firstDisplay = !displayedImages.contains(imageUri);
                if (firstDisplay) {
                    // FadeInBitmapDisplayer.animate(imageView, 500);
                    displayedImages.add(imageUri);
                }
            }
        }
    }

    class SamplePagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return ImgList.size();
        }

        @Override
        public View instantiateItem(ViewGroup container, int position) {
            PhotoView photoView = new PhotoView(container.getContext());
            String path = ImgList.get(position).url;
            ImageLoader.getInstance().displayImage(path, photoView, options,
                    animateFirstListener);
            // Now just add PhotoView to ViewPager and return it

            container.addView(photoView, LayoutParams.MATCH_PARENT,
                    LayoutParams.MATCH_PARENT);

            return photoView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position,
                                Object object) {
            container.removeView((View) object);
        }

        @Override
        public int getItemPosition(Object object) {
            // TODO Auto-generated method stub
            return POSITION_NONE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

    }

    class GuidePageChangeListener implements OnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int arg0) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onPageSelected(int arg0) {
            cindex = arg0;
            manager.txt_title.setText((arg0 + 1) + "/" + ImgList.size() + "  ");

        }

    }


    private void back(){
        Intent data = new Intent();
        data.putExtra("ImageInfos", (Serializable) ImgList);
        setResult(RESULT_OK, data);
        finish();
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.btn_right){
            ImgList.remove(cindex);
            manager.txt_title.setText((cindex + 1) + "/" + ImgList.size() + "  ");
            pagerAdapter.notifyDataSetChanged();
            if (ImgList.size() == 0) {
                back();
            }
        }else if(v.getId() == R.id.btn_back){
            back();
        }

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            back();
        }
        return super.onKeyDown(keyCode, event);
    }
}
