package mmci.mmcifocusedswipeboard;

import android.app.Activity;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

    private TextView mTextView;
    private static EditText text;

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
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO Wenn auf das Textfeld geklickt wird, soll die View/der Fokus geändert werden
                Log.d("Debug", "onClick performed");
            }
        });
    }
}
