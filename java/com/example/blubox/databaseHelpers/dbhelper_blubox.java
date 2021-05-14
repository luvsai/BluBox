package com.example.blubox.databaseHelpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.blubox.services_list.To_do.Quest;
import com.example.blubox.services_list.To_do.Task;

import java.util.ArrayList;



/*

 *Documentation:------------------------------

    *Name: dbhelper_todo.java (DATABASE helper class) (SQLite)9
        Consists : static class myDbHelper which creates the BluBox database
            and tables for services of app


    *Description :-----------
        A DATABASE helper class for the tables used for services
        A)TO_DO list
 *             1. Table "quests"
 *             2. Table "tasks"
        It also consits of method to insert delete and select data from the database Tables

 */

public class dbhelper_blubox {

    myDbHelper myhelper;

    ArrayList<Quest> quests ;
    Context context;
    ArrayList<Task> tasks ;

    public dbhelper_blubox(Context context)
    {
        this.context = context;
        this.myhelper = new myDbHelper(context);
        this.quests = new ArrayList<Quest>();
        this.tasks = new ArrayList<Task>();
    }




/*
 *   Class "myDbHelper" will help us to create Database and the tables
 *   We are defining the data base "BluBox"
 *   And creating Tables For App services
 *         A)TO_DO list
 *             1. Table "quests"
 *             2. Table "tasks"
 *         B)NOtes
 *              In Progress---------
 *         C) Blog
 *              ---
 *         D) gallery
            ----
 */


    static class myDbHelper extends SQLiteOpenHelper {

        private static final int DATABASE_Version = 1;    // Database Version
        private static final String DATABASE_NAME = "BluBox";    // Database Name
        private Context context;


        /*
         *Table "quests" data
         */

        private static final String QUEST_TABLE_NAME = "quests";   // Table Name


        //Column Names
        private static final String QID = "qId";     // Column I (Primary Key)
        private static final String QTITLE = "qTitle";    //Column II
        private static final String QIMG = "qImg ";    // Column III
        private static final String QTSTAMP = "qTStamp";// Column IV
        private static final String QMSG = "qMsg";// Column V
        private static final String QTASKCOUNT = "qTaskCount";// Column VI
        private static final String QREACHED = "qReached";// Column VII
        private static final String QSTATUS = "qStatus";// Column VIII

        //SQL for creating "quest" table

        private static final String CREATE_QUEST_TABLE
                = "CREATE TABLE " + QUEST_TABLE_NAME
                + " (" + QID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + QTITLE + " VARCHAR(255) ,"
                + QIMG + " VARCHAR(255) ,"
                + QTSTAMP + " VARCHAR(255) ,"
                + QMSG + " VARCHAR(225) ,"
                + QTASKCOUNT + " INTEGER ,"
                + QREACHED + " INTEGER,"
                + QSTATUS + " INTEGER);";

        //SQL for DRop table "quests"
        private static final String DROP_QUEST_TABLE = "DROP TABLE IF EXISTS " + QUEST_TABLE_NAME;


        /*
        Table 2 : "tasks"
         */

        private static final String TASK_TABLE_NAME = "tasks";   // Table Name


        //Column Names
        private static final String TID = "tId";     // Column I (Primary Key)
        private static final String TTITLE = "tTitle";    //Column II
        private static final String TTSTAMP = "tTStamp";    // Column III
        private static final String TEMP = "ttemp";// Column IV
        private static final String TSTATUS = "tStatus";// Column VIII

        //SQL for creating "tasks" table

        private static final String CREATE_TASK_TABLE
                = "CREATE TABLE " + TASK_TABLE_NAME
                + " (" + TID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TTITLE + " VARCHAR(255) ,"
                + TTSTAMP + " VARCHAR(255) ,"
                + TEMP + " VARCHAR(225) ,"
                + TSTATUS + " INTEGER,"
                + QID + " INTEGER ," +
                "CONSTRAINT fk_quests" +
                "    FOREIGN KEY(" + QID + ")" +   //References foreign key "qid
                "    REFERENCES " + QUEST_TABLE_NAME + "(" + QID + ")" +
                "    ON DELETE CASCADE);";

        //SQL for DRop table "quests"
        private static final String DROP_TASK_TABLE = "DROP TABLE IF EXISTS " + TASK_TABLE_NAME;


        public myDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_Version);
            this.context = context;
        }

        public void onCreate(SQLiteDatabase db) {

            try {
                db.execSQL(CREATE_QUEST_TABLE);
            } catch (Exception e) {

                //eror
            }

            try {
                db.execSQL(CREATE_TASK_TABLE);
            } catch (Exception e) {

                //eror
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {

                db.execSQL(DROP_QUEST_TABLE);
                db.execSQL(DROP_TASK_TABLE);
                onCreate(db);
            } catch (Exception e) {
                // Message.message(context,""+e);
            }
        }
    }






    /*
        *Functions for Handling Table "quests"


     */


    //Function to Insert data into Quest Table

    public long insertQuestData(String qTitle, String qMsg,String qImg ,String qTStamp,int qTaskCount , int qReached,int qStatus )
    {
        SQLiteDatabase dbb = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.QTITLE, qTitle);
        contentValues.put(myDbHelper.QMSG, qMsg);
        contentValues.put(myDbHelper.QIMG, qImg);
        contentValues.put(myDbHelper.QTSTAMP, qTStamp);
        contentValues.put(myDbHelper.QTASKCOUNT, qTaskCount);
        contentValues.put(myDbHelper.QREACHED, qReached);
        contentValues.put(myDbHelper.QSTATUS, qImg);

        long id = dbb.insert(myDbHelper.QUEST_TABLE_NAME, null , contentValues);
        return id;
    }


    //Function to get Quest table data

    public ArrayList<Quest> getQuestsData()
    {
        quests.clear();
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {myDbHelper.QID, myDbHelper.QTITLE,myDbHelper.QMSG,
                myDbHelper.QIMG,myDbHelper.QTSTAMP, myDbHelper.QTASKCOUNT,
                myDbHelper.QREACHED,myDbHelper.QSTATUS };
        Cursor cursor =db.query(myDbHelper.QUEST_TABLE_NAME,columns,null,null,null,null,null);
        StringBuffer buffer= new StringBuffer();
        while (cursor.moveToNext())
        {

             int qId  =cursor.getInt(cursor.getColumnIndex(myDbHelper.QID));
            String qTitle =cursor.getString(cursor.getColumnIndex(myDbHelper.QTITLE));
            String  qMsg  =cursor.getString(cursor.getColumnIndex(myDbHelper.QMSG));
            String  qImg  =cursor.getString(cursor.getColumnIndex(myDbHelper.QMSG));
            String  qTStamp =cursor.getString(cursor.getColumnIndex(myDbHelper.QTSTAMP));
            int  qTaskCount =cursor.getInt(cursor.getColumnIndex(myDbHelper.QTASKCOUNT));
            int  qReached =cursor.getInt(cursor.getColumnIndex(myDbHelper.QREACHED));
            int  qStatus =cursor.getInt(cursor.getColumnIndex(myDbHelper.QSTATUS));
            quests.add(0,new Quest(qId ,qTitle, qMsg,qImg , qTStamp, qTaskCount , qReached, qStatus));
        }
        cursor.close();
        return quests;
    }


    /*
        * Function to Update Quest Title updateTitle(QuestID, NEWQuestTitle)


     */



    public int updateTitle(int qId , String qTitle)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.QTITLE,qTitle);
        String[] whereArgs= {String.valueOf(qId)};
        int count =db.update(myDbHelper.QUEST_TABLE_NAME,contentValues, myDbHelper.QID+" = ?",whereArgs );
        return count;
    }

    /*
        *Function to update TaskCount : updateTaskCount(QuestId, NewTaskCount)
     */



    public int updateTaskCount(int qId , int qTaskCount)
    {

        SQLiteDatabase db = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.QTASKCOUNT,qTaskCount);
        String[] whereArgs= {String.valueOf(qId)};
        int count =db.update(myDbHelper.QUEST_TABLE_NAME,contentValues, myDbHelper.QID+" = ?",whereArgs );
        return count;
    }

    /*
        *Function to  update taskreached

     */
    public int updateQReached(int qId , int qReached )
    {

        SQLiteDatabase db = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.QREACHED,qReached); //new data
        String[] whereArgs= {String.valueOf(qId)};
        int count =db.update(myDbHelper.QUEST_TABLE_NAME,contentValues, myDbHelper.QID+" = ?",whereArgs );
        return count;
    }

        /*

            * Function to delete the Quest
         */
        public  int delete(int qId)
        {
            SQLiteDatabase db = myhelper.getWritableDatabase();
            String[] whereArgs ={String.valueOf(qId)};

            int count =db.delete(myDbHelper.QUEST_TABLE_NAME , myDbHelper.QID+" = ?",whereArgs);
            return  count;
        }

        //GEt QuestTitle(QuestID)
    public String getQuestsTitle(int qId)
    {
        String qTitle ="" ;
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = { myDbHelper.QTITLE};
        String[] selArgs = {String.valueOf(qId)} ;

        Cursor cursor =db.query(myDbHelper.QUEST_TABLE_NAME,columns,myDbHelper.QID+" = ?",selArgs,null,null,null);
        StringBuffer buffer= new StringBuffer();
        while (cursor.moveToNext())
        {


            qTitle =cursor.getString(cursor.getColumnIndex(myDbHelper.QTITLE));
        }
        cursor.close();
        return qTitle;
    }











    /*
     *Functions for Handling Table "tasks"


     */


    //Insertion data

    public long insertTaskData(int tQId,String tTitle, String tTStamp,int tStatus,String temp )
    {

        SQLiteDatabase dbb = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.QID, tQId);
        contentValues.put(myDbHelper.TTITLE, tTitle);
        contentValues.put(myDbHelper.TTSTAMP, tTStamp);
        contentValues.put(myDbHelper.TSTATUS, tStatus);
        contentValues.put(myDbHelper.TEMP, temp);
        long id = dbb.insert(myDbHelper.TASK_TABLE_NAME, null , contentValues);
        return id;
    }





    //Get Tasks with Quest Id

    public ArrayList<Task> getTasksData(int tQid)
    {
        tasks.clear();
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {myDbHelper.TID ,myDbHelper.QID ,myDbHelper.TTITLE,myDbHelper.TTSTAMP, myDbHelper.TSTATUS,myDbHelper.TEMP};
        Cursor cursor =db.query(myDbHelper.TASK_TABLE_NAME,columns,"qId = ?", new String[] { String.valueOf(tQid) },null,null,null);
        StringBuffer buffer= new StringBuffer();
        while (cursor.moveToNext())
        {


            int Qid;

            int tId =cursor.getInt(cursor.getColumnIndex(myDbHelper.TID));
            Qid =cursor.getInt(cursor.getColumnIndex(myDbHelper.QID));
            String tTitle =cursor.getString(cursor.getColumnIndex(myDbHelper.TTITLE));
            String  tTStamp =cursor.getString(cursor.getColumnIndex(myDbHelper.TTSTAMP));
            int tStatus =cursor.getInt(cursor.getColumnIndex(myDbHelper.TSTATUS));
            String  temp =cursor.getString(cursor.getColumnIndex(myDbHelper.TEMP));

            tasks.add(0, new Task( tId , Qid, tTitle, tTStamp,tStatus, temp ));

        }
        return tasks;
    }










    //Get TasksCount with Quest Id

    public int getTasksCount(int qId)
    {
        int tCount = 0 ;

        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {myDbHelper.TID ,myDbHelper.TSTATUS};
        Cursor cursor =db.query(myDbHelper.TASK_TABLE_NAME,columns,"qId = ?", new String[] { String.valueOf(qId) },null,null,null);
        StringBuffer buffer= new StringBuffer();

        while (cursor.moveToNext())
        {
                tCount++;
        }
        return tCount;
    }


    //Get TasksCount with Quest Id

    public int getTasksReachedCount(int qId)
    {
        int tRCount = 0 ;

        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {myDbHelper.TID ,myDbHelper.TSTATUS};
        Cursor cursor =db.query(myDbHelper.TASK_TABLE_NAME,columns,"qId = ?", new String[] { String.valueOf(qId) },null,null,null);
        StringBuffer buffer= new StringBuffer();

        while (cursor.moveToNext())
        {


            int tStatus =cursor.getInt(cursor.getColumnIndex(myDbHelper.TSTATUS));

            if ( tStatus  == 1) {
                tRCount++;
            }



        }
        return tRCount;
    }





    /*
     *Function to  Update Task Status

     */
    public int updateTStatus(int tId , int tStatus )
    {

        SQLiteDatabase db = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.TSTATUS,tStatus); //new data
        String[] whereArgs= {String.valueOf(tId)};
        int count =db.update(myDbHelper.TASK_TABLE_NAME,contentValues, myDbHelper.TID+" = ?",whereArgs );
        return count;
    }

    /*

     * Function to delete the Task
     */
    public  int deleteTask(int tId)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] whereArgs ={String.valueOf(tId)};

        int count =db.delete(myDbHelper.TASK_TABLE_NAME , myDbHelper.TID+" = ?",whereArgs);
        return  count;
    }













}



/*
    *Syntax for queries using sqlite query method
    *


For the query :

select name from mytable where id = "john"
use

(SQliteDatabase) db.query(
    "mytable" ,// table
        new String[] { "name" },  // columns
        "id = ?" , // where or selection
        new String[] { "john" } ,  // selectionArgs i.e. value to replace ?
        null ,// groupBy
        null , // having
        null  ,// orderBy
        );


 */