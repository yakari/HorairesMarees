package info.yakablog.hmarees;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

public class HorairesMarees extends Activity {
	
	SharedPreferences prefs;
	Spinner s;
	WebView mWebView;
	int[] mPortNumber = {75, 77, 74, 20, 70, 201, 136, 106, 27, 87, 105, 51,
			415, 404, 426, 21, 2, 32, 13, 101, 92, 9, 401, 58, 137, 140, 7, 82,
			73, 430, 5, 83, 48, 135, 38, 418, 11, 71, 33, 428, 93, 130, 26, 56,
			37, 14, 24, 85, 301, 3, 405, 55, 99, 17, 307, 424, 416, 36, 10,
			121, 122, 16, 432, 403, 133, 35, 29, 45, 4, 50, 96, 200, 22, 427,
			128, 78, 123, 60, 98, 112, 113, 79, 126, 86, 47, 30, 94, 53, 49,
			431, 406, 127, 103, 134, 423, 80, 114, 89, 19, 18, 57, 109, 115,
			42, 8, 12, 414, 40, 61, 46, 125, 90, 63, 420, 69, 91, 95, 300, 411,
			138, 84, 1, 120, 34, 205, 25, 62, 400, 110, 417, 88, 66, 67, 306,
			132, 43, 129, 119, 116, 64, 97, 104, 28, 39, 76, 102, 100, 44, 72,
			429, 131, 108, 54, 41, 118, 124, 141, 52, 117, 59, 31, 15, 402, 419,
			421, 302, 422, 202, 68, 65, 111, 81, 23, 107, 413, 139, 412, 425,
			410, 203, 6, 204};
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        setContentView(R.layout.main);
        
        s = (Spinner) findViewById(R.id.spinnerPort);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.ports, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(adapter);

        s.setSelection(prefs.getInt("port", 0));
        
        mWebView = (WebView) findViewById(R.id.webview);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl("http://www.google.com");
        
        s.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
            	SharedPreferences.Editor editor = prefs.edit();
            	editor.putInt("port",s.getPositionForView(selectedItemView));
            	// Don't forget to commit your edits!!!
            	editor.commit();
                mWebView.loadUrl("http://horloge.maree.frbateaux.net/ws"+mPortNumber[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });
    }
}