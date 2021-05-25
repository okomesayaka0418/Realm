package app.yonezawa.yone.realm

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

class RealmMemoApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        Realm.init(this)
        val realmConfig :RealmConfiguration = RealmConfiguration.Builder()
            .deleteRealmIfMigrationNeeded()
            .build()
        Realm.setDefaultConfiguration(realmConfig)

    }
}