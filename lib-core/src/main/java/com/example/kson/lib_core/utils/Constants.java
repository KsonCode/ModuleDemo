package com.example.kson.lib_core.utils;

/**
 * Created by Administrator on 2017/11/27 0027.
 */

public class Constants {
    public static final int LANGUAGETYPE = 3;
    public static final int LANGUAGETYPE_EN = 0;
    public static final int LANGUAGETYPE_SIMOLIFIED = 1;
    public static final int LANGUAGETYPE_TRADITION = 2;

    public static final String AUTOMATIC_LOCK_KEY = "automatic_lock_key";
    public static final int LOCK_30S = 0;
    public static final int LOCK_1M = 1;
    public static final int LOCK_2M = 2;
    public static final int LOCK_5M = 3;
    public static final int LOCK_10M = 4;
    public static final int LOCK_DONTUSE = 5;
    public static final int LOCK_DEFAULT = LOCK_DONTUSE;

    public static final String NICK_NAME = "nick_name";
    public static final String AVATAR = "avatar";
    public static final String TELEPHONE = "telephone";
    public static final String NATION_NAME = "nation_name";
    public static final String EMAIL = "email";
    public static final String MOBILE = "mobile";
    public static final String SEX = "sex";
    public static final String BIRTHDAY = "birthday";
    public static final String TOKEN = "token";
    public static final String IDDCODE = "iddCode";
    public static final String LOCK_PASSWORD = "lock_password";
    public static final String LANGUAGE_JSON = "language_json";
    public static final String ISSETPAYPW = "is_set_pay_pw";
    public static final String logonMode = "logonMode";// 1:手机   2：邮箱

    //判断机器人是否消失
    public static final String XIAOWENG = "xiao_weng";
    public static final String XIAOWENG_LONG = "xiao_weng_long";
    public static final String XIAOWENG_IMAGE = "xiao_weng_image";
    //手机号码的规则
    public static final String MOBILERULE = "mobile_rule";

    public static final String NET_ERROR = "服务器繁忙";

    //交易明细-充值
    public static final int TRADE_DETAIL_TOP_UP = 1;
    //交易明细-提现
    public static final int TRADE_DETAIL_WITHDRAW = 2;
    //交易明细-转账
    public static final int TRADE_DETAIL_TRANSFER = 3;
    //交易明细-兑换
    public static final int TRADE_DETAIL_EXCHANGE = 4;
    //交易明细-C2C
    public static final int TRADE_DETAIL_C2C = 5;
    //手势密码输入错误次数的变量
    public static final String  INPUTERRORTIME = "inputErrorTime";
    //根据设定时间输入错误次数的变量
    public static final String  ERRORTIME = "errorTime";
    //杀死进程再次进入的时候，输入手势密码的错误次数
    public static final String  INPUTFIRSTERRORTIME = "inputFirstErrorTime";


    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "beifu_wallet.db";

    // +86手机号码的游戏规则
    public static final String CHINESE_MOBILE_RULE = "^1([38][0-9]|4[579]|5[0-3,5-9]|6[6]|7[0135678]|9[89])\\d{8}$";

    // public static final String CHINESE_MOBILE_RULE = "^((16[0-9])|(17[0-9])|(19[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$";

    public static String CT ="";

    //全局变脸环信ID
    public  static long HXID = 0;
    // 环信退出登录发出的广播消息
    public static final String HX_DISCONNNECTED = "hx_disconnnected";
    // 好友请求的类型，用于Intent传递数据操作
    public static final String FRIENDS_APPLY_TYPE = "friends_apply_type";
    // 从好友申请记录跳转到好友详情页面中，需要携带的参数(FriendsApplyBean对象)
    public static final String FRIENDS_APPLY_PARAMETER = "friends_apply_parameter";
    // 从好友信息界面跳转到备注页面，需要携带好友的环信编号
    public static final String FRIENDS_HXID = "friends_hxid";
    // 从备注页面界面回退到好友信息页面，需要携带好友的备注信息返回
    public static final String FRIENDS_NOTE = "friends_note";
    // 从好友信息界面跳转到备注页面，需要携带当前是修改备注还是设置备注
    public static final String FRIENDS_NOTE_TYPE = "friends_note_type";
    // 是否需要回传数据
    public static final String NEED_ACTIVITY_RESULT = "need_activity_result";
    // 回传的数据对象
    public static final String ACTIVITY_RESULT = "activity_result";
    // 更新备注成功之后发送的广播消息对象
    public static final String UPDATE_REMARK_SUCCESS = "update_remark_success";
    // 更新备注成功之后发送的中携带的好友用户名
    public static final String FRIENDS_USER_NAME = "friends_user_name";
    // 添加好友成功之后发送的广播消息对象
    public static final String ADD_NEW_FRIEND_SUCCESS = "add_new_friend_success";
    // 删除好友成功之后发送的广播消息对象
    public static final String DELETE_FRIEND_SUCCESS = "delete_friend_success";
    // 退出/解散群聊成功之后发送的广播消息对象
    public static final String EXIT_GROUP_SUCCESS = "exit_group_success";
    // 被踢出群聊成功之后发送的广播消息对象
    public static final String BEEN_TAKE_OUT_GROUP_SUCCESS = "been_take_out_group_success";

    // 从群消息页面跳转到好友信息页面需要携带的额外参数
    public static final String IS_GROUP_CHAT = "is_group_chat";

    // 创建群的时候需要传递的好友列表参数
    public static final String FRIENDS_LIST = "friends_list";
    // 跳转到设置群名称的时候需要传递的参数(判断当前是否是创建群还是仅仅是修改群名称，创建群为false，修改群名称为true)
    public static final String MODIFY_GROUP_NAME = "modify_group_name";
    // 群名称
    public static final String GROUP_NAME = "group_name";
    // 群成员数量
    public static final String GROUP_MEMBER_COUNT = "group_member_count";
    // 群昵称
    public static final String GROUP_NICK_NAME = "group_nick_name";

    // 群组在环信服务器的编号
    public static final String GROUP_ID = "group_id";
    // 群组在本地服务器的编号
    public static final String GROUP_CHAT_ID = "group_chat_id";
    // 是否是群主
    public static final String IS_GROUP_OWNER = "is_group_owner";
    // 某个群的设置信息
    public static final String GROUP_SETTINGS_INFO = "group_settings_info";
    // 群组公告信息
    public static final String GROUP_ANNOUNCE = "group_announce";
    // 创建群成功需要发送的广播消息对象
    public static final String CREATE_GROUP_SUCCESS = "create_group_success";
    // 添加好友进群成功需要发送的广播消息对象
    public static final String ADD_FRIENDS_2_GROUP_SUCCESS = "add_friends_2_group_success";
    // 移除群成员成功需要发送的广播消息对象
    public static final String REMOVE_GROUP_MEMBER_SUCCESS = "remove_group_member_success";
    // 更新群公告成功之后发送的广播消息对象
    public static final String UPDATE_GROUP_ANNOUNCE_SUCCESS = "update_group_announce_success";
    // 更新群昵称成功之后发送的广播消息对象
    public static final String UPDATE_GROUP_NICK_NAME_SUCCESS = "update_group_nick_name_success";
    // 修改变更的群成员数量
    public static final String CHANGED_GROUP_MEMBER_COUNT = "changed_group_member_count";
    // 修改群名称成功，发送的广播消息对象
    public static final String MODIFY_GROUP_NAME_SUCCESS = "modify_group_name_success";
    // 更换群主成功，发送的广播消息对象
    public static final String CHANGE_GROUP_OWNER_SUCCESS = "change_group_owner_success";
    // 跳转到邀请好友页面时是否是邀请好友操作
    public static final String INVITE_FRIENDS_2_GROUP = "invite_friends_2_group";
    // 跳转到邀请好友页面时是否是删除群成员
    public static final String IS_REMOVE_GROUP_MEMBER = "is_remove_group_member";
    // 跳转到邀请好友页面时是否是更换群主
    public static final String IS_CHANGE_GROUP_OWNER = "is_change_group_owner";
    // 跳转到邀请好友页面时是否要展示底部栏
    public static final String IS_NEED_SHOW_BOTTOM_BAR = "is_need_show_bottom_bar";

    //是否需要登录
    public static final int LOGIN_NEEDED = 1;

}
