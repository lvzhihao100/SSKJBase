package com.sskj.k3dlib.util;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;

import com.sskj.common.adapter.ItemClickSupport;
import com.sskj.common.adapter.slimadapter.IViewInjector;
import com.sskj.common.adapter.slimadapter.SlimAdapter;
import com.sskj.common.adapter.slimadapter.SlimInjector;
import com.sskj.common.box.decoration.DividerLineItemDecoration;
import com.sskj.k3dlib.BottomSheetSelectorCustom;
import com.sskj.k3dlib.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author吕志豪 .
 * @date 17-12-26  上午10:57.
 * Github :https://github.com/lvzhihao100
 * E-Mail：1030753080@qq.com
 * 简书 :http://www.jianshu.com/u/6e525b929aac
 */

public class BottomSheetUtil {
    public static BottomSheetDialog getBottomSheet(Activity activity, String title, ItemClickSupport.OnItemClickListener itemClick, String... items) {
        ArrayList<ItemBean> ItemBeans = new ArrayList<>();
        for (String item : items) {
            ItemBeans.add(new ItemBean(item));
        }
        return getBottomSheet(activity, title, itemClick, ItemBeans);
    }

    public static BottomSheetDialog getBottomSheet(Activity activity, String title, ItemClickSupport.OnItemClickListener itemClick, ArrayList<ItemBean> ItemBeans) {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(activity);
        BottomSheetSelectorCustom inflate = DataBindingUtil.inflate(activity.getLayoutInflater(), R.layout.k3dlib_bottom_sheet_selector, null, false);
        if (TextUtils.isEmpty(title)) {
            inflate.tvTitle.setVisibility(View.GONE);
        } else {
            inflate.tvTitle.setVisibility(View.VISIBLE);
            inflate.tvTitle.setText(title);
        }
        inflate.tvCancel.setOnClickListener(v -> {
            bottomSheetDialog.dismiss();
        });
        inflate.recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        inflate.recyclerView.addItemDecoration(new DividerLineItemDecoration(activity).setLastDraw(false));
        SlimAdapter.create().register(R.layout.k3dlib_recy_item_text, new SlimInjector<ItemBean>() {
            @Override
            public void onInject(ItemBean data, IViewInjector injector, List<Object> payloads) {
                injector.text(R.id.tv_content, data.content)
                        .textColor(R.id.tv_content, data.color);
            }
        }).attachTo(inflate.recyclerView).updateData(ItemBeans);
        ItemClickSupport.addTo(inflate.recyclerView)
                .setOnItemClickListener(itemClick);
        bottomSheetDialog.setContentView(inflate.getRoot());
        bottomSheetDialog.setCancelable(true);
        bottomSheetDialog.setCanceledOnTouchOutside(true);
        return bottomSheetDialog;
    }

   public static class ItemBean {
        public String content;
        public int color = 0xff000000;
        public boolean isRedDot = false;

        public ItemBean(String content) {
            this.content = content;
        }

       public ItemBean setColor(int color) {
           this.color = color;
           return this;
       }
   }

    public interface ItemClick {
        void click(int pos);
    }

}
