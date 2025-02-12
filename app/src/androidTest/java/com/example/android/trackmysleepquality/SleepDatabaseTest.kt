///*
// * Copyright 2018, The Android Open Source Project
// *
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// *     http://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
//
//package com.example.android.trackmysleepquality
//
//import androidx.room.Room
//import androidx.test.ext.junit.runners.AndroidJUnit4
//import androidx.test.platform.app.InstrumentationRegistry
//import com.example.android.trackmysleepquality.database.SleepDatabase
//import com.example.android.trackmysleepquality.database.SleepDatabaseDao
//import com.example.android.trackmysleepquality.database.SleepNight
//import org.junit.Assert.assertEquals
//import org.junit.After
//import org.junit.Before
//import org.junit.Test
//import org.junit.runner.RunWith
//import java.io.IOException
//
///**
// * This is not meant to be a full set of tests. For simplicity, most of your samples do not
// * include tests. However, when building the Room, it is helpful to make sure it works before
// * adding the UI.
// */
//
//@RunWith(AndroidJUnit4::class)
//class SleepDatabaseTest {
//
//    private lateinit var sleepDao: SleepDatabaseDao
//    private lateinit var db: SleepDatabase
//
//    //this block runs first
//    @Before //an in memory database is created. It will be destroyed after the tests are done.
//    fun createDb() {
//        val context = InstrumentationRegistry.getInstrumentation().targetContext
//        // Using an in-memory database because the information stored here disappears when the
//        // process is killed.
//        db = Room.inMemoryDatabaseBuilder(context, SleepDatabase::class.java)
//                // Allowing main thread queries, just for testing.
//                .allowMainThreadQueries() //By default, we will get errors if we run the queries on the main thread.
//            // This allows running tests on the main thread which we should only do during testing.
//                .build()
//        sleepDao = db.sleepDatabaseDao
//    }
//
//    @After //after the testing is done, the function with annotation @After is run to close the db
//    @Throws(IOException::class)
//    fun closeDb() {
//        db.close()
//    }
//
//    @Test
//    @Throws(Exception::class)
//    fun insertAndGetNight() {
//        val night = SleepNight() //create
//        sleepDao.insert(night) //insert
//        val tonight = sleepDao.getTonight() // retrieve a sleep night
//        assertEquals(tonight?.sleepQuality, -1) //in this function we insert and get the sleep night from th database.
//    // Here we are asserting that both are same. If anything goes wrong we throw an exception
//    }
//}
//
