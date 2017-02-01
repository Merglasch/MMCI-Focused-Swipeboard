package mmci.mmcifocusedswipeboard;

import android.app.Activity;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class MainActivity extends Activity implements View.OnTouchListener {

    private TextView mTextView;
    private static EditText text;
    private View testView;
    private View focusedTextView;

    private float x1,x2,y1,y2;
    static final int MIN_DISTANCE = 50;
    private int charBlock;
    private int swipeDirection8;
    private int swipeDirection4=0;
    private int swipeDirection3=0;
    private ViewFlipper viewFlipper;

    private String[] characters = {
            "ASDF",
            "QWE",
            "RTZU",
            "IOP",
            "HJKL",
            "M,. ",
            "VGBN",
            "YXC",
            };


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
        //testView = findViewById(R.id.FSBFocusedBoxesView);
        //testView.setOnTouchListener(this);
        for(int i = 0;i<viewFlipper.getChildCount();i++){
            if(i!=1){
                viewFlipper.getChildAt(i).setOnTouchListener(this);
            }
        }

    }

    private void showCharacters(){
        Log.d("Debug", "displayedChild: " + viewFlipper.getDisplayedChild());
        int charposition = 0;
        switch(viewFlipper.getDisplayedChild()){
            case 0:
            case 1:
                viewFlipper.setDisplayedChild(swipeDirection8 +1);
                //text.getBackground().setAlpha(128);
                text.setVisibility(View.INVISIBLE);
                break;
            case 2:
            case 4:
            case 6:
            case 7:
            case 8:

                switch (swipeDirection8) {
                    case 1:
                    case 8:
                        charposition = 0;
                        break;
                    case 2:
                        charposition = 1;
                        break;
                    case 4:
                        charposition = 2;
                        break;
                    case 5:
                    case 6:
                        charposition = 3;
                        break;
                    case 3:
                        //TODO swipe nach oben bewirk nicht eventuell Ausgabe dass swipe nicht eindeutig war?
                        break;
                    default:
                        //TODO Add back button here
                        break;
                }
                text.setText(text.getText()+""+characters[viewFlipper.getDisplayedChild()-2].charAt(charposition));
                //Log.d("Debug", "displayedCharacter: " + characters[viewFlipper.getDisplayedChild()-2].charAt(swipeDirection4));

                text.setSelection(text.getText().length());
                viewFlipper.setDisplayedChild(0);
                text.setVisibility(View.VISIBLE);
                break;
            case 3:
            case 5:
            case 9:
                switch (swipeDirection8) {
                    case 1:
                    case 2:
                    case 8:
                        charposition = 0;
                        break;
                    case 3:
                        charposition = 1;
                        break;
                    case 4:
                    case 5:
                    case 6:
                        charposition = 2;
                        break;
                    default:
                        //TODO Add back button here
                        break;
                }
                int start = Math.max(text.getSelectionStart(),0);
                int end = Math.max(text.getSelectionEnd(),0);
                String insertChar = ""+characters[viewFlipper.getDisplayedChild()-2].charAt(charposition);
                text.getText().replace(Math.min(start,end),Math.max(start,end),insertChar,0,1);
//                text.setText(text.getText()+""+characters[viewFlipper.getDisplayedChild()-2].charAt(charposition));
                viewFlipper.setDisplayedChild(0);
                text.setSelection(text.getText().length());
                text.setVisibility(View.VISIBLE);
                break;
        }
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
                        swipeDirection8 = 5;
                    } else {
                        //right to left swipe
                        swipeDirection8 = 1;
                    }
                }

                //vertical swipe
                if (absDeltaX < MIN_DISTANCE && absDeltaY > MIN_DISTANCE){
                    if (y1 < y2){
                        //top to bottom swipe
                        swipeDirection8 = 7;
                    } else {
                        //bottom to top swipe
                        swipeDirection8 = 3;
                    }
                }

                //corner swipe
                if (absDeltaX > MIN_DISTANCE && absDeltaY > MIN_DISTANCE){
                    if (x1 > x2 && y1 < y2) {
                        //top right to bottom left swipe
                        swipeDirection8 = 8;
                    }
                    if (x1 > x2 && y1 > y2) {
                        //bottom right to top left swipe
                        swipeDirection8 = 2;
                    }
                    if (x1 < x2 && y1 < y2) {
                        //top left to bottom right swipe
                        swipeDirection8 = 6;
                    }
                    if (x1 < x2 && y1 > y2) {
                        //bottom left to top right swipe
                        swipeDirection8 = 4;
                    }
                }

                swipeDirection4=1; //Is it Bottom-Left, Top-Left, Top-Right or Bottom Right? (1,2,3,4)
                swipeDirection3=1; //Is it Bottom-Left, Top or Bottom Right? (1,2,3)

                // no swipe
                if (absDeltaX < MIN_DISTANCE && absDeltaY < MIN_DISTANCE){
                    swipeDirection8 = 0;
                    swipeDirection4 = 0;
                    swipeDirection3 = 0;
                }else{
                    showCharacters();
                }

                Log.d("Debug", "swipe direction: " + swipeDirection8);
                break;
        }
        return true;
    }
}
