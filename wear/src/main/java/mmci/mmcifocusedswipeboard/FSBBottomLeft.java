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

public class FSBBottomLeft extends View {

    private Paint _focusedBoxesBackground = new Paint();

    public FSBBottomLeft(Context context) {
        super(context);
        init(null, 0);
    }

    public FSBBottomLeft(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public FSBBottomLeft(Context context, AttributeSet attrs, int defStyle) {
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

        String characters = "Y";
        float occupiedRoomOfCharacters = charactersPaint.measureText(characters);
        canvas.drawText(characters, getWidth()/12*2-(occupiedRoomOfCharacters/2), getHeight()/10*7, charactersPaint);

        characters = "X";
        occupiedRoomOfCharacters = charactersPaint.measureText(characters);
        canvas.drawText(characters, getWidth()/12*6-(occupiedRoomOfCharacters/2), getHeight()/10*3, charactersPaint);

        characters = "C";
        occupiedRoomOfCharacters = charactersPaint.measureText(characters);
        canvas.drawText(characters, getWidth()/12*10-(occupiedRoomOfCharacters/2), getHeight()/10*7, charactersPaint);
    }
}