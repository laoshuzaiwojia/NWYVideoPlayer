package com.ningwuyue.sdk.nwyvideoplayersdk.util.common;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;

/**
 * Created by ${武跃} on 2017/9/15.
 * <p>
 * {一句话描述功能:----}
 */

public class AnimatorUtils {

    private static long ANIMA_TIME = 2000;

    /**
     * 放大
     *
     * @param iv
     */
    public static void startCcaleAnimator(View iv) {
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(iv, "scaleX", 1, 0.8f, 1);
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(iv, "scaleY", 1, 0.8f, 1);
        animator2.setDuration(300);
        animator2.setInterpolator(new AccelerateInterpolator());
        // animator2.setRepeatMode(ValueAnimator.RESTART);
        //animator2.setRepeatCount(1);
        animator2.start();
        animator1.setDuration(300);
        animator1.setInterpolator(new AccelerateInterpolator());
        // animator1.setRepeatMode(ValueAnimator.RESTART);
        // animator1.setRepeatCount(1);
        animator1.start();
    }

    /**
     * 放大
     *
     * @param iv
     */
    public static void startCcaleAnimator2(View iv) {
        try {
            ObjectAnimator animator2 = ObjectAnimator.ofFloat(iv, "scaleX", 1, 0.5f, 1);
            ObjectAnimator animator1 = ObjectAnimator.ofFloat(iv, "scaleY", 1, 0.5f, 1);
            animator2.setDuration(200);
            animator2.setInterpolator(new AccelerateInterpolator());
            // animator2.setRepeatMode(ValueAnimator.RESTART);
            //animator2.setRepeatCount(1);
            animator2.start();
            animator1.setDuration(200);
            animator1.setInterpolator(new AccelerateInterpolator());
            // animator1.setRepeatMode(ValueAnimator.RESTART);
            // animator1.setRepeatCount(1);
            animator1.start();
        } catch (Exception e) {
            e.getMessage();
        }
    }

    /**
     * 绕X轴旋转
     *
     * @param iv
     */
    public static void startRotationXAnimator(View iv) {
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(iv, "rotationX", 0, 180, 360);
        animator2.setDuration(2000);
        animator2.setInterpolator(new AccelerateInterpolator());
        animator2.setRepeatMode(ValueAnimator.RESTART);
        animator2.setRepeatCount(-1);
        animator2.start();
    }

    /**
     * 绕Y轴旋转
     *
     * @param iv
     */
    public static void startRotationYAnimator(View iv, long duration, int count) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(iv, "rotationY", 0, 180, 360);
        animator.setDuration(duration);
        animator.setInterpolator(new AccelerateInterpolator());
        animator.setRepeatMode(ValueAnimator.RESTART);
        //animator.setRepeatCount(-1);
        animator.setRepeatCount(count);
        animator.start();
    }


    public static void rotationAnimation(final View view) {
        final RotateAnimation rotateAnimation = new RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0f);
        rotateAnimation.setDuration(3000);
        rotateAnimation.setInterpolator(new AccelerateInterpolator());
        rotateAnimation.setFillAfter(false);
        view.setAnimation(rotateAnimation);

        rotateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        rotateAnimation.start();
    }

    /**
     * 缩放动画 与 平移动画
     *
     * @param view
     * @param fromScale
     * @param toScale
     * @param fromX
     * @param toX
     * @param fromY
     * @param toY
     * @param listener
     */
    public static void startZoomAnim(View view,
                                     // 缩放
                                     float fromScale, float toScale,
                                     // 平移
                                     float fromX, float toX, float fromY, float toY,
                                     // 动画
                                     Animator.AnimatorListener listener
    ) {

        if (view == null) {
            return;
        }
        if (view.getAnimation() != null) {
            view.getAnimation().cancel();
            view.clearAnimation();
            return;
        }

        AnimatorSet localAnimatorSet = new AnimatorSet();
        // ############## scaleX ###############
        float[] scaleX = new float[2];
        scaleX[0] = fromScale;
        scaleX[1] = toScale;
        ObjectAnimator scaleXAnima = ObjectAnimator.ofFloat(view, "scaleX", scaleX);
        scaleXAnima.setDuration(ANIMA_TIME);
        scaleXAnima.setInterpolator(new DecelerateInterpolator());
        // ############## scaleY ###############
        float[] scaleY = new float[2];
        scaleY[0] = fromScale;
        scaleY[1] = toScale;
        ObjectAnimator scaleYAnima = ObjectAnimator.ofFloat(view, "scaleY", scaleY);
        scaleYAnima.setDuration(ANIMA_TIME);
        scaleYAnima.setInterpolator(new DecelerateInterpolator());
        // #############translationX ##########
        float[] translationX = new float[2];
        translationX[0] = fromX;
        translationX[1] = toX;
        ObjectAnimator translationXAnima = ObjectAnimator.ofFloat(view, "translationX", translationX);
        translationXAnima.setDuration(ANIMA_TIME);
        translationXAnima.setInterpolator(new DecelerateInterpolator());
        // ###############translationY##################
        float[] translationY = new float[2];
        translationY[0] = fromY;
        translationY[1] = toY;
        ObjectAnimator translationYAnima = ObjectAnimator.ofFloat(view, "translationY", translationY);
        translationYAnima.setDuration(ANIMA_TIME);
        translationYAnima.setInterpolator(new DecelerateInterpolator());

        //
        Animator[] arrayOfAnimator = new Animator[4];
        arrayOfAnimator[0] = scaleXAnima;
        arrayOfAnimator[1] = scaleYAnima;
        arrayOfAnimator[2] = translationXAnima;
        arrayOfAnimator[3] = translationYAnima;
        localAnimatorSet.playTogether(arrayOfAnimator);
        if (listener != null) {
            localAnimatorSet.addListener(listener);
        }
        //
        localAnimatorSet.start();
    }

    /**
     * 开启Alpha 动画
     *
     * @param view
     * @param fromAlpha
     * @param toAlpha
     */
    public static void startAlphaAnima(View view, float fromAlpha, float toAlpha) {
        if (view == null) {
            return;
        }
        if (view.getAnimation() != null) {
            view.getAnimation().cancel();
            view.clearAnimation();
            return;
        }
        //-------Alpaha--------
        AnimatorSet set = new AnimatorSet();
        set.play(ObjectAnimator.ofFloat(view, "alpha", fromAlpha, toAlpha));
        set.setDuration(ANIMA_TIME);
        set.setInterpolator(new DecelerateInterpolator());
        set.start();

    }

    /**
     * 清理目标View的动画
     *
     * @param paramView
     */
    public static void clearAnimation(View paramView) {
        if (paramView == null)
            return;

        if (paramView.getAnimation() == null) {
            return;
        }

        paramView.getAnimation().cancel();
        paramView.clearAnimation();
    }

    /**
     * 旋转动画
     *
     * @param paramView
     * @param paramInt  时间
     */
    public static void startRotateAnimation(View paramView, int paramInt) {
        float[] arrayOfFloat = new float[2];
        arrayOfFloat[0] = 0.0F;
        arrayOfFloat[1] = 360.0F;
        ObjectAnimator localObjectAnimator = ObjectAnimator.ofFloat(paramView,
                "rotation", arrayOfFloat);
        localObjectAnimator.setDuration(paramInt);
        localObjectAnimator.setInterpolator(null);
        localObjectAnimator.setRepeatCount(-1);
        localObjectAnimator.start();
    }

    /**
     * 放大动画
     *
     * @param paramView             目标View
     * @param paramFloat            放大的比例
     * @param paramAnimatorListener
     * @return
     */
    public static AnimatorSet startScaleToBigAnimation(View paramView,
                                                       float paramFloat, Animator.AnimatorListener paramAnimatorListener) {
        if (paramView.getAnimation() != null)
            paramView.getAnimation().cancel();
        paramView.clearAnimation();
        AnimatorSet localAnimatorSet = new AnimatorSet();
        float[] arrayOfFloat1 = new float[2];
        arrayOfFloat1[0] = 1.0F;
        arrayOfFloat1[1] = paramFloat;
        ObjectAnimator localObjectAnimator1 = ObjectAnimator.ofFloat(paramView,
                "scaleY", arrayOfFloat1);
        localObjectAnimator1.setDuration(240L);
        localObjectAnimator1.setInterpolator(new DecelerateInterpolator());
        float[] arrayOfFloat2 = new float[2];
        arrayOfFloat2[0] = 1.0F;
        arrayOfFloat2[1] = paramFloat;
        ObjectAnimator localObjectAnimator2 = ObjectAnimator.ofFloat(paramView,
                "scaleX", arrayOfFloat2);
        localObjectAnimator2.setDuration(240L);
        localObjectAnimator2.setInterpolator(new DecelerateInterpolator());
        Animator[] arrayOfAnimator = new Animator[2];
        arrayOfAnimator[0] = localObjectAnimator1;
        arrayOfAnimator[1] = localObjectAnimator2;
        localAnimatorSet.playTogether(arrayOfAnimator);
        if (paramAnimatorListener != null)
            localAnimatorSet.addListener(paramAnimatorListener);
        localAnimatorSet.start();
        return localAnimatorSet;
    }

    /**
     * 缩小动画
     *
     * @param paramView
     * @param paramFloat            缩小的比例
     * @param paramAnimatorListener
     * @return
     */
    public static AnimatorSet startScaleToSmallAnimation(View paramView,
                                                         float paramFloat, Animator.AnimatorListener paramAnimatorListener) {
        if (paramView.getAnimation() != null)
            paramView.getAnimation().cancel();
        paramView.clearAnimation();
        AnimatorSet localAnimatorSet = new AnimatorSet();
        float[] arrayOfFloat1 = new float[2];
        arrayOfFloat1[0] = paramFloat;
        arrayOfFloat1[1] = 1.0F;
        ObjectAnimator localObjectAnimator1 = ObjectAnimator.ofFloat(paramView,
                "scaleY", arrayOfFloat1);
        localObjectAnimator1.setDuration(140L);
        localObjectAnimator1.setInterpolator(new DecelerateInterpolator());
        float[] arrayOfFloat2 = new float[2];
        arrayOfFloat2[0] = paramFloat;
        arrayOfFloat2[1] = 1.0F;
        ObjectAnimator localObjectAnimator2 = ObjectAnimator.ofFloat(paramView,
                "scaleX", arrayOfFloat2);
        localObjectAnimator2.setDuration(140L);
        localObjectAnimator2.setInterpolator(new DecelerateInterpolator());
        Animator[] arrayOfAnimator = new Animator[2];
        arrayOfAnimator[0] = localObjectAnimator1;
        arrayOfAnimator[1] = localObjectAnimator2;
        localAnimatorSet.playTogether(arrayOfAnimator);
        localAnimatorSet.start();
        return localAnimatorSet;
    }

    /**
     * 从上往下移动的位移动画
     *
     * @param paramView
     * @return
     */
    public static AnimatorSet startTranlateDownAnimation(View paramView) {
        if (paramView.getAnimation() != null)
            paramView.getAnimation().cancel();
        paramView.clearAnimation();
        AnimatorSet localAnimatorSet = new AnimatorSet();
        float[] arrayOfFloat = new float[2];
        arrayOfFloat[0] = -80.0F;
        arrayOfFloat[1] = 1.0F;
        ObjectAnimator localObjectAnimator = ObjectAnimator.ofFloat(paramView,
                "translationY", arrayOfFloat);
        localObjectAnimator.setDuration(240L);
        localObjectAnimator.setInterpolator(new DecelerateInterpolator());
        localAnimatorSet.play(localObjectAnimator);
        localAnimatorSet.start();
        return localAnimatorSet;
    }

    /**
     * 从下网上移动的位移动画
     *
     * @param paramView
     * @return
     */
    public static AnimatorSet startTranlateUpAnimation(View paramView) {
        if (paramView.getAnimation() != null)
            paramView.getAnimation().cancel();
        paramView.clearAnimation();
        AnimatorSet localAnimatorSet = new AnimatorSet();
        float[] arrayOfFloat = new float[2];
        arrayOfFloat[0] = 1.0F;
        arrayOfFloat[1] = -80.0F;
        ObjectAnimator localObjectAnimator = ObjectAnimator.ofFloat(paramView,
                "translationY", arrayOfFloat);
        localObjectAnimator.setDuration(240L);
        localObjectAnimator.setInterpolator(new DecelerateInterpolator());
        localAnimatorSet.play(localObjectAnimator);
        localAnimatorSet.start();
        return localAnimatorSet;
    }
    /**
     * 抖动动画
     * @param view
     * @param shakeFactor
     * @return
     */
    public static ObjectAnimator startShakeAnimator(View view, float shakeFactor) {
        PropertyValuesHolder pvhScaleX = PropertyValuesHolder.ofKeyframe(View.SCALE_X,
                Keyframe.ofFloat(0f, 1f),
                Keyframe.ofFloat(.1f, .9f),
                Keyframe.ofFloat(.2f, .9f),
                Keyframe.ofFloat(.3f, 1.1f),
                Keyframe.ofFloat(.4f, 1.1f),
                Keyframe.ofFloat(.5f, 1.1f),
                Keyframe.ofFloat(.6f, 1.1f),
                Keyframe.ofFloat(.7f, 1.1f),
                Keyframe.ofFloat(.8f, 1.1f),
                Keyframe.ofFloat(.9f, 1.1f),
                Keyframe.ofFloat(1f, 1f)
        );

        PropertyValuesHolder pvhScaleY = PropertyValuesHolder.ofKeyframe(View.SCALE_Y,
                Keyframe.ofFloat(0f, 1f),
                Keyframe.ofFloat(.1f, .9f),
                Keyframe.ofFloat(.2f, .9f),
                Keyframe.ofFloat(.3f, 1.1f),
                Keyframe.ofFloat(.4f, 1.1f),
                Keyframe.ofFloat(.5f, 1.1f),
                Keyframe.ofFloat(.6f, 1.1f),
                Keyframe.ofFloat(.7f, 1.1f),
                Keyframe.ofFloat(.8f, 1.1f),
                Keyframe.ofFloat(.9f, 1.1f),
                Keyframe.ofFloat(1f, 1f)
        );

        PropertyValuesHolder pvhRotate = PropertyValuesHolder.ofKeyframe(View.ROTATION,
                Keyframe.ofFloat(0f, 0f),
                Keyframe.ofFloat(.1f, -3f * shakeFactor),
                Keyframe.ofFloat(.2f, -3f * shakeFactor),
                Keyframe.ofFloat(.3f, 3f * shakeFactor),
                Keyframe.ofFloat(.4f, -3f * shakeFactor),
                Keyframe.ofFloat(.5f, 3f * shakeFactor),
                Keyframe.ofFloat(.6f, -3f * shakeFactor),
                Keyframe.ofFloat(.7f, 3f * shakeFactor),
                Keyframe.ofFloat(.8f, -3f * shakeFactor),
                Keyframe.ofFloat(.9f, 3f * shakeFactor),
                Keyframe.ofFloat(1f, 0)
        );
        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(view, pvhScaleX, pvhScaleY, pvhRotate);
        objectAnimator.setDuration(1200);
        return objectAnimator;
    }
}
