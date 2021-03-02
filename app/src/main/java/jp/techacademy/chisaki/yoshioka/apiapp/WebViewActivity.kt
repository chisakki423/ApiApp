package jp.techacademy.chisaki.yoshioka.apiapp

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_web_view.*
import android.content.Intent
import android.util.Log

class WebViewActivity: AppCompatActivity() {

    companion object {

        const val SHOP_STATE = "shop_state"

        fun start(activity: Activity, shop: Shop) {

            println("shop:${shop}")
            activity.startActivity(Intent(activity, WebViewActivity::class.java).putExtra(WebViewActivity.SHOP_STATE, shop))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
        //webView.loadUrl(intent.getStringExtra(SHOP.url).toString())

        val state = intent.getSerializableExtra(SHOP_STATE)

        if(state is Shop){
            Log.d("android_log",state.address)
            webView.loadUrl(state.couponUrls.sp)
        }

        //val state2 = intent.getSerializableExtra(SHOP_STATE2)

    }
}