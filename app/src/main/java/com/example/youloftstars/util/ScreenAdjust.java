package com.example.youloftstars.util;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;

import androidx.annotation.NonNull;

public final class ScreenAdjust {
    public static final int MATCH_BASE_WIDTH = 0;
    public static final int MATCH_BASE_HEIGHT = 1;

    public static final int MATCH_UNIT_DP = 0;
    public static final int MATCH_UNIT_PT = 1;
    //适配信息
    private static MatchInfo sMatchInfo;
    //Activity的生命周期检测
    private static Application.ActivityLifecycleCallbacks mActivityLifecycleCallbacks;

    private ScreenAdjust(){
        //疑惑
        throw new UnsupportedOperationException("u can't instantiate me...");
    }
    //初始化
    public static void setup(@NonNull final Application application){
        DisplayMetrics displayMetrics = application.getResources().getDisplayMetrics();
        if(sMatchInfo == null){
            //记录系统的原始值
            sMatchInfo = new MatchInfo();
            /**
             * 屏幕宽度
             * 屏幕长度
             * 密度比值
             * 屏幕密度
             * 文字缩放计算
             * 水平方向的真实密度
             */
            sMatchInfo.setScreenWidth(displayMetrics.widthPixels);
            sMatchInfo.setScreenHeight(displayMetrics.heightPixels);
            sMatchInfo.setAppDensity(displayMetrics.density);
            sMatchInfo.setAppDensityDpi(displayMetrics.densityDpi);
            sMatchInfo.setAppScaledDensity(displayMetrics.scaledDensity);
            sMatchInfo.setAppXdpi(displayMetrics.xdpi);
        }
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH){
            //添加文字变换的监听
            application.registerComponentCallbacks(new ComponentCallbacks() {
                @Override
                public void onConfigurationChanged(Configuration newConfig) {
                    //字体改变后 将appScaledDenstiy重新幅值
                    if(newConfig != null && newConfig.fontScale > 0){
                        sMatchInfo.setAppScaledDensity(application.getResources()
                                .getDisplayMetrics().scaledDensity);
                    }
                }
                @Override
                public void onLowMemory() {
                }
            });
        }
    }

    /**
     * 在application 中全局激活适配
     */

    public static void register(final Application application, final float designSize,
                                final int matchBase, final int matchUnit){
        if(mActivityLifecycleCallbacks == null){
            mActivityLifecycleCallbacks = new Application.ActivityLifecycleCallbacks() {
                @Override
                public void onActivityCreated(Activity activity, Bundle bundle) {
                    if (activity != null){
                        match(activity,designSize,matchBase,matchUnit);
                    }
                }

                @Override
                public void onActivityStarted(Activity activity) {

                }

                @Override
                public void onActivityResumed(Activity activity) {

                }

                @Override
                public void onActivityPaused(Activity activity) {

                }

                @Override
                public void onActivityStopped(Activity activity) {

                }

                @Override
                public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

                }

                @Override
                public void onActivityDestroyed(Activity activity) {

                }
            };
            application.registerActivityLifecycleCallbacks(mActivityLifecycleCallbacks);
        }
    }

    /**
     * 全局取消所有的适配
     */

    public static void unregister(@NonNull final Application application, @NonNull int... matchUnit){
        if(mActivityLifecycleCallbacks != null){
            application.unregisterActivityLifecycleCallbacks(mActivityLifecycleCallbacks);
            mActivityLifecycleCallbacks = null;
        }
    }

    /**
     * 屏幕适配(setContentView之前执行
     *
     * @param context
     * @param designSize
     */
    public static void match(@NonNull final Context context, final float designSize){
        match(context,designSize,MATCH_BASE_WIDTH,MATCH_UNIT_DP);
    }

    /**
     * 屏幕适配(setContentView之前执行
     * @param context
     * @param designSize
     */
    public static void match(@NonNull final Context context, final float designSize, int matchBase){
        match(context,designSize,matchBase,MATCH_UNIT_DP);
    }
    /**
     * 屏幕适配(setContentView之前执行
     * @param context
     * @param designSize 设计图的尺寸
     * @param matchBase 使用的适配单位
     */

    public static void match(@NonNull final Context context, final float designSize, int matchBase, int matchUnit){
        if(designSize == 0){
            throw  new UnsupportedOperationException("the designsize cannot be equal to 0");
        }
        if(matchUnit == MATCH_UNIT_DP){
            matchByDP(context,designSize,matchBase);
        }
        if (matchUnit == MATCH_UNIT_PT){
            matchByPT(context,designSize,matchBase);
        }
    }

    /**
     * 重置适配信息 取消适配
     */
    public static void cancelMatch(@NonNull final Context context){
        cancelMatch(context,MATCH_UNIT_DP);
        cancelMatch(context,MATCH_UNIT_PT);
    }
    public static void cancelMatch(@NonNull final Context context, int matchUnit){
        if(sMatchInfo != null){
            final DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            if(matchUnit == MATCH_UNIT_DP){
                if(displayMetrics.density != sMatchInfo.getAppDensity());
                displayMetrics.density = sMatchInfo.getAppDensity();
            }
            if(displayMetrics.densityDpi != sMatchInfo.getAppDensityDpi()){
                displayMetrics.densityDpi = (int)sMatchInfo.getAppDensityDpi();
            }
            if(displayMetrics.scaledDensity != sMatchInfo.getAppScaledDensity()){
                displayMetrics.scaledDensity = sMatchInfo.getAppScaledDensity();
            }else if(matchUnit == MATCH_UNIT_PT){
                if(displayMetrics.xdpi != sMatchInfo.getAppXdpi()){
                    displayMetrics.xdpi = sMatchInfo.getAppXdpi();
                }
            }
        }
    }

    public static MatchInfo getsMatchInfo(){
        return sMatchInfo;
    }

    /**
     * 使用dp作为适配单位
     * @param context 上下文路径
     * @param designSize 设计图的宽/高 单位 dp
     * @param base 适配基准
     */
    private static void matchByDP(@NonNull final Context context, final float designSize, int base){
        final float targetDensity;
        if(base == MATCH_BASE_WIDTH){
            targetDensity = sMatchInfo.getScreenWidth() * 1f / designSize;
        }else if(base == MATCH_BASE_HEIGHT){
            targetDensity = sMatchInfo.getScreenHeight() * 1f /designSize;
        }else{
            targetDensity = sMatchInfo.getScreenWidth() * 1f /designSize;
        }
        final int targetScaleDensityDpi = (int)(targetDensity * 160);
        final float targetScaledDensity = targetDensity * (sMatchInfo.getAppScaledDensity() / sMatchInfo.getAppDensity());
        final DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        displayMetrics.density = targetDensity;
        displayMetrics.densityDpi = targetScaleDensityDpi;
        displayMetrics.scaledDensity = targetScaledDensity;
    }

    private static void matchByPT(@NonNull final Context context, final float designSize, int base){
        final float targetXdpi;
        if(base == MATCH_BASE_WIDTH){
            targetXdpi = sMatchInfo.getScreenWidth() * 72f / designSize;
        }else if(base == MATCH_BASE_HEIGHT){
            targetXdpi = sMatchInfo.getScreenHeight() * 72f /designSize;
        }else{
            targetXdpi = sMatchInfo.getScreenWidth() * 72f /designSize;
        }
        final DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        displayMetrics.xdpi = targetXdpi;
    }
    /**
     * 适配信息
     * screenWidth 屏幕宽度
     * screenHeight 屏幕高度
     * appDensity ?
     * appDensityDpi
     * appScaledDensity
     * appXdpi
     */
    public static class MatchInfo {
        private int screenWidth;
        private int screenHeight;
        private float appDensity;
        private float appDensityDpi;
        private float appScaledDensity;
        private float appXdpi;

        public int getScreenWidth() {
            return screenWidth;
        }

        public void setScreenWidth(int screenWidth) {
            this.screenWidth = screenWidth;
        }

        public int getScreenHeight() {
            return screenHeight;
        }

        public void setScreenHeight(int screenHeight) {
            this.screenHeight = screenHeight;
        }

        public float getAppDensity() {
            return appDensity;
        }

        public void setAppDensity(float appDensity) {
            this.appDensity = appDensity;
        }

        public float getAppDensityDpi() {
            return appDensityDpi;
        }

        public void setAppDensityDpi(float appDensityDpi) {
            this.appDensityDpi = appDensityDpi;
        }

        public float getAppScaledDensity() {
            return appScaledDensity;
        }

        public void setAppScaledDensity(float appScaledDensity) {
            this.appScaledDensity = appScaledDensity;
        }

        public float getAppXdpi() {
            return appXdpi;
        }

        public void setAppXdpi(float appXdpi) {
            this.appXdpi = appXdpi;
        }
    }
}
