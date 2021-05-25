package app.yonezawa.yone.realm

import io.realm.RealmObject

open class Memo (
    open var title: String = "",
    open var contet: String = ""
//RealmObjectという型の継承⇒MemoというクラスをRealmで保存できる型にする
) : RealmObject()
