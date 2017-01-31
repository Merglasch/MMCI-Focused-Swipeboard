package mmci.mmcifocusedswipeboard;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import static android.support.wearable.activity.ConfirmationActivity.EXTRA_MESSAGE;

public class MainActivity extends Activity implements View.OnTouchListener {

    private TextView mTextView;
    private static EditText text;
    private View testView;
    private View focusedTextView;

    private float x1,x2,y1,y2;
    static final int MIN_DISTANCE = 30;
    private int charBlock;
    private int swipeDirection;
    private ViewFlipper viewFlipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                mTextView = (TextView) stub.findViewById(R.id.text);
            }
        });
        setContentView(R.layout.rect_activity_main);

        text = (EditText) findViewById(R.id.editText);
        viewFlipper = (ViewFlipper)findViewById(R.id.daViewFlipper);
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO Wenn auf das Textfeld geklickt wird, soll die View/der Fokus geändert werden
                Log.d("Debug", "onClick performed");
                if(viewFlipper.getDisplayedChild()==0)
                    viewFlipper.setDisplayedChild(1);
           }
        });
        focusedTextView = findViewById(R.id.FSBFocusedTextView);
        focusedTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO Wenn auf das Textfeld geklickt wird, soll die View/der Fokus geändert werden
                Log.d("Debug", "onClick performed");
                if(viewFlipper.getDisplayedChild()==1)
                    viewFlipper.setDisplayedChild(0);
            }
        });
        testView = findViewById(R.id.FSBFocusedBoxesView);
        testView.setOnTouchListener(this);

    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {



        switch(event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                x1 = event.getX();
                y1 = event.getY();
                Log.d("Debug", "Action_down x1:" + x1 + " y1: " + y1);
                break;
            case MotionEvent.ACTION_UP:
                x2 = event.getX();
                y2 = event.getY();
                Log.d("Debug", "Action_up x2:" + x2 + " y2: " + y2);
                float deltaX = x2 - x1;
                float deltaY = y2 - y1;


                float absDeltaX = Math.abs(deltaX);
                float absDeltaY = Math.abs(deltaY);

                Log.d("Debug", "DeltaX: " + absDeltaX + " DeltaY: " + absDeltaY);

                //horizontal swipe
                if (absDeltaX > MIN_DISTANCE && absDeltaY < MIN_DISTANCE){
                    if (x1 < x2){
                        //left to right swipe
                        swipeDirection = 5;
                    } else {
                        //right to left swipe
                        swipeDirection = 1;
                    }
                }

                //vertical swipe
                if (absDeltaX < MIN_DISTANCE && absDeltaY > MIN_DISTANCE){
                    if (y1 < y2){
                        //top to bottom swipe
                        swipeDirection = 7;
                    } else {
                        //bottom to top swipe
                        swipeDirection = 3;
                    }
                }

                //corner swipe
                if (absDeltaX > MIN_DISTANCE && absDeltaY > MIN_DISTANCE){
                    if (x1 > x2 && y1 < y2) {
                        //top right to bottom left swipe
                        swipeDirection = 8;
                    }
                    if (x1 > x2 && y1 > y2) {
                        //bottom right to top left swipe
                        swipeDirection = 2;
                    }
                    if (x1 < x2 && y1 < y2) {
                        //top left to bottom right swipe
                        swipeDirection = 6;
                    }
                    if (x1 < x2 && y1 > y2) {
                        //bottom left to top right swipe
                        swipeDirection = 4;
                    }
                }

                // no swipe
                if (absDeltaX < MIN_DISTANCE && absDeltaY < MIN_DISTANCE){
                    swipeDirection = 0;
                }

                Log.d("Debug", "swipe direction: " + swipeDirection);
                break;
        }
        return true;
    }
}
