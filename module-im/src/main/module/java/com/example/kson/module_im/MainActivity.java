package com.example.kson.module_im;

import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.kson.lib_core.base.BaseActivity;
import com.example.kson.lib_net.network.http.HttpRequestPresenter;
import com.example.kson.lib_net.utils.publickeytool.RsaCoder;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.widget.EaseTitleBar;

import static com.hyphenate.easeui.EaseConstant.CHATTYPE_GROUP;


public class MainActivity extends BaseActivity {

    private EaseTitleBar easeTitleBar;
    private TextView sendTv;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        easeTitleBar = findViewById(R.id.title_bar);
        sendTv = findViewById(R.id.send);
        easeTitleBar.setTitle("聊天列表");
        sendTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = "18612991523";
                String password = "111111";
                EMClient.getInstance().login(username, password, new EMCallBack() {
                    @Override
                    public void onSuccess() {
                        //创建一条文本消息，content为消息文字内容，toChatUsername为对方用户或者群聊的id，后文皆是如此
                        EMMessage message = EMMessage.createTxtSendMessage("hhhhhh", "18612991023");
                        EMClient.getInstance().chatManager().sendMessage(message);
                    }

                    @Override
                    public void onError(int i, String s) {

                    }

                    @Override
                    public void onProgress(int i, String s) {

                    }
                });
            }
        });

    }

    @Override
    protected int bindLayoutId() {
        return R.layout.activity_main;
    }


    /**
     *
     * @param view
     */
    public void testHome(View view){
        ARouter.getInstance().build("/com/home2").navigation();
    }
}
