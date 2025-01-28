/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.trackmysleepquality.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [SleepNight::class], version = 1, exportSchema = false)
//change the version if the schema of the database changes.
//exportSchema is true by default and saves the schema of the database to a folder
//this provides us the version history of the database
//For this app we don't need it, so it is set to false
abstract class SleepDatabase: RoomDatabase(){

    abstract val sleepDatabaseDao: SleepDatabaseDao

    //the companion object allows clients to access the methods for creating or getting the database without instantiating the class.
    //Since the only purpose of this class is to provide us with a database, there is no need to instantiate the class.
    companion object{
        @Volatile
        private var INSTANCE: SleepDatabase? = null
        //INSTANCE will keep a reference to the database once we have one.
        //This will help us avoid repeatedly opening connections to the database, which is expensive.
        //Volatile: This helps us make sure the value of the INSTANCE is always up to date and the same to all execution threats.
        //The value of a volatile variable will never be cached, and all writes and reads will be done to and from the main memory

        //getInstance method will return a reference to the SleepDatabase
        fun getInstance(context: Context) : SleepDatabase {
            //multiple threads can ask for a database instance at the same time.
            //Wrapping our code into synchronize means, only one thread of execution at a time can enter this block of code,
            //which makes sure that the database get initialized only once.
            synchronized (this) {
                var instance = INSTANCE

                if (instance == null){
                    instance = Room.databaseBuilder( //invoke room's database builder
                        context.applicationContext, //supply the context that was passed in
                        SleepDatabase::class.java, //need to tell which database to build, so we pass in a reference to the SleepDatabase
                        "sleep_history_database" //name for the database
                    )
                        .fallbackToDestructiveMigration() //migration -> if the schema of the database changes, then a new db must be created based on the new schema.
                        //migration object takes all the rows from old schema and convert them to new schema
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}