package rw.theclick.carsrw;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AndroidHTMLActivity extends Activity {

    WebView myBrowser;
    EditText Msg;
    Button btnSendMsg;

    /** Called when the activity is first created. */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        myBrowser = (WebView)findViewById(R.id.mybrowser);

        final MyJavaScriptInterface myJavaScriptInterface
                = new MyJavaScriptInterface(this);

        myBrowser.addJavascriptInterface(myJavaScriptInterface, "AndroidFunction");

        myBrowser.getSettings().setJavaScriptEnabled(true);
        myBrowser.loadUrl("file:///android_asset/mypage.html");

        Msg = (EditText)findViewById(R.id.msg);
        btnSendMsg = (Button)findViewById(R.id.sendmsg);
        btnSendMsg.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                String msgToSend = Msg.getText().toString();
                myBrowser.loadUrl("javascript:callFromActivity(\""+msgToSend+"\")");

            }});

    }

    public class MyJavaScriptInterface {
        Context mContext;

        MyJavaScriptInterface(Context c) {
            mContext = c;
        }
        @JavascriptInterface
        public void showToast(String toast){
            Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
        }
        @JavascriptInterface
        public void openAndroidDialog(){
            AlertDialog.Builder myDialog
                    = new AlertDialog.Builder(AndroidHTMLActivity.this);
            myDialog.setTitle("DANGER!");
            myDialog.setMessage("You can do what you want!");
            myDialog.setPositiveButton("ON",null);
            myDialog.show();
        }

    }
}