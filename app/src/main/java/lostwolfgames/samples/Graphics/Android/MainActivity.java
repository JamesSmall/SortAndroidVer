package lostwolfgames.samples.Graphics.Android;

import android.app.Activity;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

import lostwolfgames.samples.Graphics.Android.Views.SortView;
import lostwolfgames.samples.Graphics.RenderObject;
import lostwolfgames.samples.R;
import lostwolfgames.samples.SortAPI.Shuffler;
import lostwolfgames.samples.SortAPI.Sorts.BiDirectionalBubbleSort;
import lostwolfgames.samples.SortAPI.Sorts.BogoSort;
import lostwolfgames.samples.SortAPI.Sorts.BubbleSort;
import lostwolfgames.samples.SortAPI.Sorts.CountingSort;
import lostwolfgames.samples.SortAPI.Sorts.InsertionSort;
import lostwolfgames.samples.SortAPI.Sorts.SelectionSort;

public class MainActivity extends AppCompatActivity {
    public static final int DEFAULT_SIZE = 100;
    public static final int DEFAULT_WAIT_TIME = 100;
    private int size = DEFAULT_SIZE;
    private int time = DEFAULT_WAIT_TIME;
    private TextView inputSize;
    private TextView inputTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final SortView sort = (SortView) super.findViewById(R.id.SortView);

        GridLayout layout = (GridLayout) super.findViewById(R.id.buttons);
        layout.setRowCount(1);
        layout.setColumnCount(6);


        Button bubbleSortBTN = new Button(this);
        bubbleSortBTN.setText("Bubble Sort");
        bubbleSortBTN.setOnClickListener((e)->{
            sort.set(new BubbleSort(generateRenderList(getSize())),getWaitTime());
        });
        //bubbleSortBTN.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        layout.addView(bubbleSortBTN);

        Button BiBubbleSortBTN = new Button(this);
        BiBubbleSortBTN.setText("BI Bubble Sort");
        BiBubbleSortBTN.setOnClickListener((e)->{
            sort.set(new BiDirectionalBubbleSort(generateRenderList(getSize())),getWaitTime());
        });
        //BiBubbleSortBTN.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        layout.addView(BiBubbleSortBTN);

        Button InsertionSortBTN = new Button(this);
        InsertionSortBTN.setText("Insertion Sort");
        InsertionSortBTN.setOnClickListener((e)->{
            sort.set(new InsertionSort(generateRenderList(getSize())),getWaitTime());
        });
        //InsertionSortBTN.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        layout.addView(InsertionSortBTN);

        Button SelectionSortBTN = new Button(this);
        SelectionSortBTN.setText("Selection Sort");
        SelectionSortBTN.setOnClickListener((e)->{
            sort.set(new SelectionSort(generateRenderList(getSize())),getWaitTime());
        });
        //SelectionSortBTN.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        layout.addView(SelectionSortBTN);


        Button CountingSortBTN = new Button(this);
        CountingSortBTN.setText("Counting Sort");
        CountingSortBTN.setOnClickListener((e)->{
            sort.set(new CountingSort(generateRenderList(getSize())),getWaitTime());
        });
        //CountingSortBTN.setLayoutParams(new ViewGroup.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT));
        layout.addView(CountingSortBTN);

        Button bogoSortBTN = new Button(this);

        bogoSortBTN.setText("Bogo Sort");
        bogoSortBTN.setOnClickListener((e)->{
            sort.set(new BogoSort(generateRenderList(getSize())),getWaitTime());
        });
        //bogoSortBTN.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        layout.addView(bogoSortBTN);
        int width = Resources.getSystem().getDisplayMetrics().widthPixels;
        for(int i = 0; i< layout.getChildCount();i++){
            Button btn = (Button) layout.getChildAt(i);
            btn.setWidth(width/layout.getChildCount());
            btn.setPadding(0,0,0,0);
        }



        this.inputTime = super.findViewById(R.id.inputTime);
        this.inputSize = super.findViewById(R.id.inputSize);

        this.inputSize.setText(""+DEFAULT_SIZE);
        this.inputTime.setText(""+DEFAULT_WAIT_TIME);

        //View mainpanel = null;
        //int width = mainpanel.getWidth(), height = mainpanel.getHeight();
        //float x = mainpanel.getX(),y = mainpanel.getY();

       // SortView view = new SortView(this);
        //view.setMinimumHeight(width);view.setMinimumHeight(height);
        //view.setX(x);view.setY(y);

        //ViewGroup v = (ViewGroup) mainpanel.getParent();

        //v.removeView(mainpanel);
        //v.addView(view);

    }
    private int getSize(){
        String text = this.inputSize.getText().toString();
        try{
            int result = Integer.parseInt(text);
            if(5 <= result && result <= 500){
                inputSize.setError(null);
                return result;
            }
        }
        catch(Throwable t){

        }
        inputSize.setError("Invalid input, expected Number between 0 and 500 inclusive");
        inputTime.setText(""+DEFAULT_SIZE);
        return DEFAULT_SIZE;
    }
    private int getWaitTime(){
        String text = this.inputTime.getText().toString();
        try{
            int result = Integer.parseInt(text);
            if(5 <= result && result <= 500){
                inputTime.setError(null);
                return result;
            }
        }
        catch(Throwable t){

        }
        inputTime.setError("Invalid input, expected number between 5 and 500 inclusive");
        return DEFAULT_WAIT_TIME;
    }
    public static RenderObject[] generateRenderList(int size){
        RenderObject[] obj = new RenderObject[size];
        for(int i = 0; i < size;i++){
            obj[i] = new RenderObject();
            obj[i].setValue(i);
            obj[i].setCode(RenderObject.UNMARKED);
        }
        Shuffler.fullRandomShuffle(obj);
        return obj;
    }
}
