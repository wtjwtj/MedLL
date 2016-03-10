package softdev1.medll;

import android.app.Application;
import android.support.v7.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by Thot on 09.03.2016.
 */
public class ExpandableListApplication extends AppCompatActivity {

        private RequestQueue mRequestQueue;
        private static ExpandableListApplication mInstance;

        public void onCreate() {
            mInstance = this;
        }

        public static synchronized ExpandableListApplication getInstance() {
            return mInstance;
        }

        public RequestQueue getReqQueue() {
            if (mRequestQueue == null) {
                mRequestQueue = Volley.newRequestQueue(getApplicationContext());
            }

            return mRequestQueue;
        }

        public <T> void addToReqQueue(Request<T> req, String tag) {

            getReqQueue().add(req);
        }

        public <T> void addToReqQueue(Request<T> req) {

            getReqQueue().add(req);
        }



        public void cancelPendingReq(Object tag) {
            if (mRequestQueue != null) {
                mRequestQueue.cancelAll(tag);
            }
        }
    }
