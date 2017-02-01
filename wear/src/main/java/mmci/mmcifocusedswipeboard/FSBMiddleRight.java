package mmci.mmcifocusedswipeboard;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by DNS on 31.01.2017.
 */

public class FSBMiddleRight extends View {

    private Paint _focusedBoxesBackground = new Paint();

    public FSBMiddleRight(Context context) {
        super(context);
        init(null, 0);
    }

    public FSBMiddleRight(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public FSBMiddleRight(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int i) {
        _focusedBoxesBackground.setColor(Color.WHITE);
    }

    @Override
    public void onDraw(Canvas canvas){
        super.onDraw(canvas);

        // Weißen Hintergrund Zeichnen
        _focusedBoxesBackground.setColor(Color.WHITE);
        _focusedBoxesBackground.setStyle(Paint.Style.FILL);
        canvas.drawPaint(_focusedBoxesBackground);

        // Buchstaben zeichnen-----------------------------------------------------------------------------------------------------------------
        Paint charactersPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        charactersPaint.setColor(0xff0088ff);
        charactersPaint.setTextSize(50);

        String characters = "H";
        float occupiedRoomOfCharacters = charactersPaint.measureText(characters);
        canvas.drawText(characters, getWidth()/12*2-(occupiedRoomOfCharacters/2), getHeight()/10*7, charactersPaint);

        characters = "J";
        occupiedRoomOfCharacters = charactersPaint.measureText(characters);
        canvas.drawText(characters, getWidth()/12*4-(occupiedRoomOfCharacters/2), getHeight()/10*3, charactersPaint);

        characters = "K";
        occupiedRoomOfCharacters = charactersPaint.measureText(characters);
        canvas.drawText(characters, getWidth()/12*8-(occupiedRoomOfCharacters/2), getHeight()/10*3, charactersPaint);

        characters = "L";
        occupiedRoomOfCharacters = charactersPaint.measureText(characters);
        canvas.drawText(characters, getWidth()/12*10-(occupiedRoomOfCharacters/2), getHeight()/10*7, charactersPaint);
    }
}