package lostwolfgames.samples.Graphics.Android.Views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.GridLayout;

import lostwolfgames.samples.Graphics.Android.MainActivity;
import lostwolfgames.samples.Graphics.RenderObject;
import lostwolfgames.samples.SortAPI.BaseSortableObject;
import lostwolfgames.samples.SortAPI.Sorts.BubbleSort;

/**
 * Created by James on 11/27/2017.
 */

public class SortView extends View {
    private BaseSortableObject objects;
    private int waitTime = 0;

    public SortView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        set(new BubbleSort(MainActivity.generateRenderList(MainActivity.DEFAULT_SIZE)),MainActivity.DEFAULT_WAIT_TIME);
    }

    /*
    public SortView(Context context) {
        super(context);
        set(new BubbleSort(MainActivity.generateRenderList(40)));
    }*/
    public void set(BaseSortableObject objects, int wait){
        this.objects = objects;
        this.waitTime = wait;
    }


    public void onDraw(Canvas c){
        Paint black = new Paint();
        black.setARGB(255,0,0,0);
        Paint green = new Paint();
        green.setARGB(255,0,255,0);
        Paint red = new Paint();
        red.setARGB(255,255,0,0);
        Paint white = new Paint();
        white.setARGB(255,255,255,255);
        Paint yellow = new Paint();
        yellow.setARGB(255,255,255,0);

        int width = getWidth();
        int height = getHeight();
        int max = 0;
        objects.update();
        RenderObject[] list = this.objects.getRenderPoints();
        for(int i = 0; i < list.length;i++){
            max = Math.max(max,list[i].getValue());
        }
        Rect background = new Rect(0,0,getWidth(),getHeight());



        c.drawRect(background,black);
        for(int i = 0; i < list.length;i++){
            Rect r = new Rect();
            Paint select;
            switch(list[i].getCode()){
                case RenderObject.INDEXED:
                    select = yellow;
                    break;
                case RenderObject.SORTED:
                    select = green;
                    break;
                case RenderObject.TARGETED:
                    select = red;
                    break;
                default:
                    select = white;
                    break;

            }
            r.set(i*(width/(list.length)),(max-list[i].getValue())*height/(max+1),(i+1)*(width/(list.length)),height);
            c.drawRect(r,select);
        }
        try {
            Thread.sleep(waitTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.invalidate();
    }


}
