package com.example.banner;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.banner.entity.BanneModel;
import com.example.banner.entity.DateBox;
import com.example.banner.transformer.GallyPageTransformer;

import cn.ymex.kits.Device;
import cn.ymex.kits.Finder;
import cn.ymex.widget.banner.Banner;
import cn.ymex.widget.banner.callback.BindViewCallBack;
import cn.ymex.widget.banner.callback.CreateViewCaller;
import cn.ymex.widget.banner.callback.OnClickBannerListener;

public class GalleryActivity extends AppCompatActivity {


    Banner banner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        banner = Finder.find(this, R.id.banner);
        marginSide(banner);
        banner.createView(CreateViewCaller.build())
                .setPageTransformer(new GallyPageTransformer())
                .bindView(new BindViewCallBack<FrameLayout, BanneModel>() {

                    @Override
                    public void bindView(FrameLayout view, BanneModel data, int position) {//图片处理
                        //使用glide 加载图片到 view组件，data 是你的数据 。
                        Glide.with(view.getContext()).load(data.getUrl()).into(CreateViewCaller.findImageView(view));
                    }

                }).setOnClickBannerListener(new OnClickBannerListener<AppCompatImageView, BanneModel>() {

            @Override
            public void onClickBanner(AppCompatImageView view, BanneModel data, int position) {//点击事件
                Toast.makeText(view.getContext(), "click position ：" + position + "\n标题：" + data.getTitle(), Toast.LENGTH_SHORT).show();
            }

        }).execute(DateBox.banneModels());//填充数据
    }

    public static void marginSide(Banner banner) {
        final int paddtb = Device.dip2px(64);
        final int paddlr = Device.dip2px(8);
        banner.getPageView().setPadding(paddtb, paddlr, paddtb, paddlr);
        banner.getPageView().setPageMargin(Device.dip2px(32));
        banner.getPageView().setClipToPadding(false);
    }

}
