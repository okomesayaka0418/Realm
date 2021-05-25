package app.yonezawa.yone.realm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    //Realmの変数を宣言
    val realm: Realm = Realm.getDefaultInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val memo: Memo? = read()
        //データベースから取得したMemoをEditTextのテキストの表示する処理.データーベース日本されているものがなかったときにmemoがnullになりエラーになるためifなっている
        if (memo != null) {
            titleEditText.setText(memo.title)
            contentEditText.setText(memo.contet)
        }
        //保存が押されたときにtitleEditTextとontentEditTextに入力されたテキストを取得しsave（）メッソドに値を渡す（引数）
        saveButton.setOnClickListener {
            val title: String = titleEditText.text.toString()
            val content: String = contentEditText.text.toString()
            save(title, content)
        }
    }

    //画面終了時にRealmを閉じる.onDestoroyあの図のやつ！幼稚園→小学校→中学校のやつ
    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }
    //今回は画面を閉じてもデータが保存されているアプリ。画面が起動したと時にすでに保存されているMemoのデータを取得
    //返り値の型を指定。nullかもしれないから「？」をつけている
    fun read(): Memo? {
        //realm.where(データ型::class.java).findFirst()。今回はMemoのデータ１つだけだからfindfirst()。findAllもある
        return realm.where(Memo::class.java).findFirst()
    }
    //保存する処理
    fun save(tile: String, content: String) {
      //すでに保存されているメモ取得
        val memo: Memo? = read()

        //この中でデータベースの操作（書き込み）
        realm.executeTransaction {
            //メモの更新、新規作成を条件分岐
            if (memo != null) {
                //メモの更新
                memo.title = tile
                memo.contet = content
            } else {
                //メモの新規作成。it.createObject(データ型::class.jave)
                val newMemo: Memo = it.createObject(Memo::class.java)
                newMemo.title = tile
                newMemo.contet = content
            }
            Snackbar.make(container,"保存しました！！", Snackbar.LENGTH_SHORT).show()

            }
        }

    }
