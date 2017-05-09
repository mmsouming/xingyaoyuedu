package com.shinread.StarPlan.Parent.ui.activity.task;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.fancyfamily.primarylibrary.commentlibrary.framework.base.BaseFragmentActivity;
import com.fancyfamily.primarylibrary.commentlibrary.widget.TitleBar;
import com.shinyread.StarPlan.Parent.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import uk.co.senab.photoview.PhotoView;

/**
 * author:janecer on 2016/4/9 21:58
 * email:janecer@sina.cn
 */

public class TaskPicShowsActivity extends BaseFragmentActivity implements View.OnClickListener {

    private ViewPager vpPics;
    private TitleBar mTb ;
    private Button btnDelete ;

    private ArrayList<String> picPaths;//图片路径集合
   // private List<ImageView> picViews ;
    private int currentPosition  = 0 ;


    private String pathHeader  ;

    @Override
    protected String setTag() {
        return this.getClass().getSimpleName();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_task_picdelete);

        picPaths = getIntent().getStringArrayListExtra("selectPics") ;
        int position = getIntent().getIntExtra("currentIndex",0) ;
        int mode =getIntent().getIntExtra("mode", 0) ;//0表示待删除按钮，1表示简单查看
        int net = getIntent().getIntExtra("net",0) ;//0表示从本地加载，1表示从网络加载
      //  picViews = getItemViews() ;

        pathHeader = net == 0 ? "file://" :"" ;

        mTb = (TitleBar) findViewById(R.id.tb) ;
        mTb.setOnLeftNavClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishThis();
            }
        });
        btnDelete = (Button) findViewById(R.id.btn_delete);

        if(mode == 1) {
            btnDelete.setVisibility(View.GONE);
        } else {
            btnDelete.setOnClickListener(this);
        }

        vpPics = (ViewPager) findViewById(R.id.vp_pics) ;
        vpPics.setAdapter(pagerAdapter);
        pagerAdapter.notifyDataSetChanged();
        vpPics.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                currentPosition = position;
                mTb.setTitleText((currentPosition + 1) + "/" + picPaths.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        vpPics.setCurrentItem(position);
        mTb.setTitleText((currentPosition + 1) + "/" + picPaths.size());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_delete:
                picPaths.remove(currentPosition) ;

                if(picPaths.size() == 0) {
                    finishThis();
                    return ;
                }

                mTb.setTitleText((currentPosition + 1) + "/" + picPaths.size());
                pagerAdapter.notifyDataSetChanged();
                break ;
        }
    }

    private PagerAdapter pagerAdapter = new PagerAdapter() {
        @Override
        public int getCount() {
            return picPaths == null ? 0 : picPaths.size();
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

        @Override
        public void destroyItem(ViewGroup view, int position, Object object) {
            view.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup view, int position) {
            PhotoView photoView = new PhotoView(view.getContext());
            String path = picPaths.get(position);
            ImageLoader.getInstance().displayImage(pathHeader +path, photoView, options,
                    animateFirstListener);

            view.addView(photoView, ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);

            return photoView;
        }
    } ;

    @Override
    public void onBackPressed() {
        finishThis();
    }

    private void finishThis() {
        Intent intent = new Intent() ;
        intent.putStringArrayListExtra("selectPics", picPaths) ;
        setResult(RESULT_OK,intent);
        finish();
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
}
