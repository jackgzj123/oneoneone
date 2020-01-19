package com.lenovo.smarttraffic.bean;

import android.graphics.Matrix;
import android.graphics.PointF;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class ImgOnTouch implements View.OnTouchListener {
    private ImageView img;

    private int code;
    private static final  int MOVE=1;
    private static final  int SCALE=2;

    private Matrix matrix=new Matrix();
    private Matrix currentMatrix=new Matrix();
    private PointF startPointF=new PointF();
    private PointF mid;
    private float length;

    public ImgOnTouch(ImageView img) {
        this.img = img;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction() & MotionEvent.ACTION_MASK){
            case MotionEvent.ACTION_DOWN:
                code=MOVE;
                currentMatrix.set(img.getImageMatrix());
                startPointF.set(motionEvent.getX(),motionEvent.getY());
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                code=SCALE;
                length = getLength(motionEvent);
                if(length >10f){
                    mid = getMid(motionEvent);
                    currentMatrix.set(img.getImageMatrix());
                }
                break;

            case MotionEvent.ACTION_MOVE:
                img.setScaleType(ImageView.ScaleType.MATRIX);
                if(code==MOVE){
                    float x = motionEvent.getX() - startPointF.x;
                    float y = motionEvent.getY() - startPointF.y;
                    matrix.set(currentMatrix);
                    matrix.postTranslate(x,y);
                }else if(code ==SCALE){
                    float newLength = getLength(motionEvent);
                    if(newLength>10f){
                        float v = newLength / length;
                        matrix.set(currentMatrix);
                        matrix.postScale(v,v,mid.x,mid.y);
                    }
                }
                break;

                case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                code=0;
                break;
        }
        img.setImageMatrix(matrix);
        return  true;
    }

    private PointF getMid(MotionEvent event) {
        float dx = (event.getX(1) + event.getX(0))/2f;
        float dy = (event.getY(1) + event.getY(0))/2f;
        return new PointF(dx,dy);
    }

    private float getLength(MotionEvent event) {
        float dx = event.getX(1) - event.getX(0);
        float dy = event.getY(1) - event.getY(0);
        return (float) Math.hypot(dx,dy);
    }
}
