package com.alpaca.library_base.utils;

/**
 * @文件名: PayUtils
 * @功能描述:
 * @Date : 2019/12/19
 * @email:
 * @修改记录:
 */
//public class PayUtils {
//    public static void popPayWindow(BaseMVPActivity activity, View view, String msg, OnResultCallBack inputCallBack) {
//        if (Constants.User.USER_PWD) {
//            Bundle bundle = new Bundle();
//            bundle.putString(PayFragment.EXTRA_CONTENT, msg);
//
//            PayFragment fragment = new PayFragment();
//            fragment.setArguments(bundle);
//            fragment.setPaySuccessCallBack(new PayPwdView.InputCallBack() {
//                @Override
//                public void onInputFinish(String result) {
//                    if (inputCallBack != null) {
//                        inputCallBack.inputFinsih(result);
//                    }
//                    fragment.dismiss();
//                }
//            });
//            fragment.show(activity.getSupportFragmentManager(), "Pay");
//        } else {
//            ShowPopWindowUtils.showNoPsd(activity, view);
//        }
//    }
//
//    public interface OnResultCallBack {
//        void inputFinsih(String result);
//    }
//
//    public static void popPayWindow(BaseMVPFragment activity, String msg, PayPwdView.InputCallBack inputCallBack) {
//        Bundle bundle = new Bundle();
//        bundle.putString(PayFragment.EXTRA_CONTENT, msg);
//
//        PayFragment fragment = new PayFragment();
//        fragment.setArguments(bundle);
//        fragment.setPaySuccessCallBack(inputCallBack);
//        fragment.show(activity.getChildFragmentManager(), "Pay");
//    }
//}
