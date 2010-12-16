/**
 * 
 */
package info.yakablog.hmarees;

import android.app.Activity;
//import android.app.ListActivity;
import android.os.Bundle;
//import android.preference.PreferenceManager;
//import android.view.View;
import android.webkit.WebView;
//import android.widget.ViewFlipper;
//import android.widget.ViewSwitcher;

/**
 * @author yakari
 *
 */
public class ViewTides extends Activity {

	/** Le ViewSwicher est le conteneur qui contient toutes les vues */
	//private ViewFlipper flipper;
	
	private WebView mWebView;
	
	private int port;
	private String portName;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //prefs = PreferenceManager.getDefaultSharedPreferences(this);
        setContentView(R.layout.viewtide);
        
        Bundle bundle = this.getIntent().getExtras();
        port = bundle.getInt("info.yakablog.hmarees.port");
        portName = bundle.getString("info.yakablog.hmarees.portName");
        
        this.setTitle(portName);
        
        //flipper = new ViewFlipper(this);
        
        //View viewWeb = View.inflate(this, R.layout.viewtide, null);

        //flipper.addView(viewWeb, 0);
        //getListView().addFooterView(flipper);

        mWebView = (WebView) findViewById(R.id.webview);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl("http://horloge.maree.frbateaux.net/ws"+port);

		//flipper.showNext();
    }
    
}
