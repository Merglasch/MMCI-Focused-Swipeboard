package mmci.mmcifocusedswipeboard;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;

/**
 * Created by DNS on 29.01.2017.
 */

public class FSBFocusedBoxesView extends View {

    private Paint _focusedBoxesBackground = new Paint();

    public FSBFocusedBoxesView(Context context) {
        super(context);
        init(null, 0);
    }

    public FSBFocusedBoxesView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public FSBFocusedBoxesView(Context context, AttributeSet attrs, int defStyle) {
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

        // Zeichnertool festlegen
        Paint focusedBoxesPaint = new Paint();
        focusedBoxesPaint.setColor(0xffff7700);
        focusedBoxesPaint.setStrokeWidth(3);

        // Linien der Buchstabenblöcke zeichnen-----------------------------------------------------------------------------------------------------------------
        canvas.drawLine(0, getHeight()/4, getWidth(), getHeight()/4, focusedBoxesPaint); // Waagrecht oben
        canvas.drawLine(getWidth()/3, 0, getWidth()/3, getHeight()/4, focusedBoxesPaint); // Senkrecht oben links
        canvas.drawLine(getWidth()/3*2, 0, getWidth()/3*2, getHeight()/4, focusedBoxesPaint); // senkrecht oben rechts

        canvas.drawLine(0, getHeight()/4*2, getWidth()/3, getHeight()/4*2, focusedBoxesPaint); // Waagrecht mittig links
        canvas.drawLine(getWidth()/3*2, getHeight()/4*2, getWidth(), getHeight()/4*2, focusedBoxesPaint); // Waagrecht mittig rechts
        canvas.drawLine(getWidth()/3, getHeight()/4*2-1, getWidth()/3, getHeight(), focusedBoxesPaint); // Senkrecht unten links
        canvas.drawLine(getWidth()/3*2, getHeight()/4*2-1, getWidth()/3*2, getHeight(), focusedBoxesPaint); // senkrecht unten rechts

        canvas.drawLine(0, getHeight()/4*3, getWidth(), getHeight()/4*3, focusedBoxesPaint); // Waagrecht unten

        // Buchstaben in Buchstabenblöcke zeichnen-----------------------------------------------------------------------------------------------------------------
        Paint charactersPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        charactersPaint.setColor(0xff0088ff);
        charactersPaint.setTextSize(30);

        String characters = "Q W E";
        float occupiedRoomOfCharacters = charactersPaint.measureText(characters);
        canvas.drawText(characters, getWidth()/3-occupiedRoomOfCharacters-(getWidth()/3-occupiedRoomOfCharacters)/2, getHeight()/8+4, charactersPaint);

        characters = "R T Z U";
        occupiedRoomOfCharacters = charactersPaint.measureText(characters);
        canvas.drawText(characters, getWidth()/3*2-occupiedRoomOfCharacters-(getWidth()/3-occupiedRoomOfCharacters)/2, getHeight()/8+4, charactersPaint);

        characters = "I O P";
        occupiedRoomOfCharacters = charactersPaint.measureText(characters);
        canvas.drawText(characters, getWidth()-occupiedRoomOfCharacters-(getWidth()/3-occupiedRoomOfCharacters)/2, getHeight()/8+4, charactersPaint);

        characters = "A S D F";
        occupiedRoomOfCharacters = charactersPaint.measureText(characters);
        canvas.drawText(characters, getWidth()/3-occupiedRoomOfCharacters-(getWidth()/3-occupiedRoomOfCharacters)/2, getHeight()/8*5+12, charactersPaint);

        characters = "H J K L";
        occupiedRoomOfCharacters = charactersPaint.measureText(characters);
        canvas.drawText(characters, getWidth()-occupiedRoomOfCharacters-(getWidth()/3-occupiedRoomOfCharacters)/2, getHeight()/8*5+12, charactersPaint);

        characters = "Y X C";
        occupiedRoomOfCharacters = charactersPaint.measureText(characters);
        canvas.drawText(characters, getWidth()/3-occupiedRoomOfCharacters-(getWidth()/3-occupiedRoomOfCharacters)/2, getHeight()/8*7+16, charactersPaint);

        characters = "V G B N";
        occupiedRoomOfCharacters = charactersPaint.measureText(characters);
        canvas.drawText(characters, getWidth()/3*2-occupiedRoomOfCharacters-(getWidth()/3-occupiedRoomOfCharacters)/2, getHeight()/8*7+16, charactersPaint);

        characters = "M , .";
        occupiedRoomOfCharacters = charactersPaint.measureText(characters);
        canvas.drawText(characters, getWidth()-occupiedRoomOfCharacters-(getWidth()/3-occupiedRoomOfCharacters)/2, getHeight()/8*7+16, charactersPaint);
    }
}
