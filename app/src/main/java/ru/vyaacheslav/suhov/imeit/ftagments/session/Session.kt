package ru.vyaacheslav.suhov.imeit.ftagments.session

import android.support.v4.app.Fragment

class Session : Fragment() {

    /*   override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                                 savedInstanceState: Bundle?): View? {

           val v = inflater.inflate(R.layout.fragment_exzam, container, false)

           val prefs = PreferenceManager.getDefaultSharedPreferences(activity)

           val position = prefs.getString(getString(R.string.pref_groupe), "")
           val mDBHelper = DatabaseHelper(activity)

           try {
               mDBHelper.updateDataBase()
           } catch (mIOException: IOException) {
               throw Error("UnableToUpdateDatabase")
           }

           val mDb = mDBHelper.writableDatabase
           val clients = ArrayList<HashMap<String, Any>>()
           var client: HashMap<String, Any>

           val cursor = mDb.rawQuery("SELECT * FROM $position", null)
           cursor.moveToFirst()

           while (!cursor.isAfterLast) {
               client = HashMap()
               client["name"] = cursor.getString(1)
               client["type"] = cursor.getString(2)
               client["time"] = cursor.getString(3)
               client["build"] = cursor.getString(4)
               client["date"] = cursor.getString(5)
               clients.add(client)
               cursor.moveToNext()
           }
           cursor.close()

           val from = arrayOf("name", "type", "time", "build", "date")
           val to = intArrayOf(R.id.tex1, R.id.tex2, R.id.tex3, R.id.tex4, R.id.tex5)

           val adapter = SimpleAdapter(activity, clients, R.layout.custom_list, from, to)
           val listView = v.findViewById<ListView>(R.id.listView)
           listView.adapter = adapter
           return v
       }*/
}