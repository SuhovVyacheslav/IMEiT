package ru.vyaacheslav.suhov.imeit.repository

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import io.reactivex.Observable
import ru.vyaacheslav.suhov.imeit.repository.entity.MapData
import ru.vyaacheslav.suhov.imeit.repository.entity.Schedule
import ru.vyaacheslav.suhov.imeit.util.Constants.MAX_PAIR

class MainInteractor(val repository: FirebaseRealtimeRepository) {

    fun getListBuildings(): Observable<ArrayList<MapData>> {

        return Observable.create {
            repository.getRefListEducationBuildings()

                    // Запрос выполниться один раз8
                    .addListenerForSingleValueEvent(object : ValueEventListener {
                        override fun onDataChange(snapshot: DataSnapshot) {

                            val list = arrayListOf<MapData>()

                            // Все существующие элементы узла
                            for (x:DataSnapshot in snapshot.children) {
                                list.add( x.getValue(MapData::class.java) ?: MapData())
                            }
                            it.onNext(list)
                        }

                        override fun onCancelled(snapshot: DatabaseError) {}
                    })
        }
    }

    fun getListGroups(institute: String, faculty: String): Observable<ArrayList<String>> {
        return Observable.create {
            repository.getRefListGroups(institute, faculty)
                    .addValueEventListener(object : ValueEventListener {
                        override fun onDataChange(snapshot: DataSnapshot) {

                            val list = arrayListOf("Группа не выбрана")
                            for (s: DataSnapshot in snapshot.children) {
                                list.add(s.key!!)
                            }
                            it.onNext(list)
                        }
                        override fun onCancelled(snapshot: DatabaseError) {}
                    })
        }
    }

    fun getScheduleDay(institute: String, faculty: String, day: String, group: String): Observable<ArrayList<Schedule>> {

        return Observable.create {
            repository.getRefListSchedule(institute, faculty, group, day)
                    .addValueEventListener(object : ValueEventListener {
                        override fun onDataChange(snapshot: DataSnapshot) {

                            val list = ArrayList<Schedule>()

                            // Берем данные c
                            for (x in 1..MAX_PAIR) {
                                list.add(snapshot.child("pair$x").getValue(Schedule::class.java) ?: Schedule()) // <- косяк ("pair$x")
                            }

                            it.onNext(list)
                        }

                        override fun onCancelled(snapshot: DatabaseError) {}
                    })
        }
    }
}