package com.fancyfamily.primarylibrary.commentlibrary.ui.book;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import com.fancyfamily.primarylibrary.R;
import com.fancyfamily.primarylibrary.commentlibrary.framework.base.BaseFragmentActivity;
import com.fancyfamily.primarylibrary.commentlibrary.ui.book.fragment.SimpleScannerFragment;

public class BookScanActivity extends BaseFragmentActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_scan);

        FragmentManager fragmentManager = getSupportFragmentManager();
        SimpleScannerFragment fragment = (SimpleScannerFragment) fragmentManager.findFragmentById(R.id.scanner_fragment);

        fragment.setListener(new SimpleScannerFragment.OnScanResultListener() {
            @Override
            public void onScanResult(String content, String format) {
                Intent i = new Intent();
                i.setClass(BookScanActivity.this, SearchBookActivity.class);
                i.putExtra(SearchBookActivity.EXTRA_CODE, content);
                startActivity(i);
                finish();
            }
        });
    }
}
