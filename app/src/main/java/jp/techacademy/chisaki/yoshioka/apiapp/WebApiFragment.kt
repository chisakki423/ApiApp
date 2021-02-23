package jp.techacademy.chisaki.yoshioka.apiapp


import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import java.io.IOException

class WebApiFragment: Fragment() {

    private val webapiAdapter by lazy { WebApiAdapter(requireContext()) }
    private val handler = Handler(Looper.getMainLooper())

    private var fragmentCallback : WebFragmentCallback? = null // Fragment -> Activity にFavoriteの変更を通知する

    private var page = 0

    // Apiでデータを読み込み中ですフラグ。追加ページの読み込みの時にこれがないと、連続して読み込んでしまうので、それの制御のため
    private var isLoading = false

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is WebFragmentCallback) {
            fragmentCallback = context
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_api, container, false) // fragment_api.xmlが反映されたViewを作成して、returnします
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // ここから初期化処理を行う
        // ApiAdapterのお気に入り追加、削除用のメソッドの追加を行う
        webapiAdapter.apply {
            onClickAddFavorite = { // Adapterの処理をそのままActivityに通知する
                fragmentCallback?.onAddFavorite(it)
            }
            onClickDeleteFavorite = { // Adapterの処理をそのままActivityに通知する
                fragmentCallback?.onDeleteFavorite(it.id)
            }

        }
    }


    companion object {
        private const val COUNT = 20 // 1回のAPIで取得する件数
    }
}