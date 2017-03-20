package cn.cnu.pushsystem.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2016/8/22.
 */

    public class DBOpenHelper extends SQLiteOpenHelper{

        private static final String DB_NAME = "test.db";
        private static final int DB_VERSION = 1;

        //数据库表_user
        private static final String DB_TABLE_USER = "user";
        public static final String KEY_id = "id";
        public static final String KEY_userId = "userId";
        public static final String KEY_system = "system";
        public static final String KEY_userName = "userName";
        public static final String KEY_passWord = "passWord";
        public static final String KEY_realName = "realName";
        public static final String KEY_sex = "sex";
        public static final String KEY_school = "school";
        public static final String KEY_grade = "grade";
        public static final String KEY_classNum = "classNum";
        public static final String KEY_birthYear = "birthYear";
        public static final String KEY_beforeLevel = "beforeLevel";
        public static final String KEY_EnglishScore = "EnglishScore";
        public static final String KEY_learningStyle1 = "learningStyle1";
        public static final String KEY_learningStyle2 = "learningStyle2";
        public static final String KEY_learningStyle3 = "learningStyle3";
        public static final String KEY_learningStyle4 = "learningStyle4";
        public static final String KEY_token = "token";
        public static final String KEY_isFirstLogin="isFirstLogin";

        //数据库表_user_behavior
        private static final String DB_TABLE_USER_BEHAVIOR = "user_behavior";
        // public static final String KEY_id = "id";
        //  public static final String KEY_userId = "userId";
        public static final String KEY_doWhere = "doWhere";
        public static final String KEY_doWhat = "doWhat";
        public static final String KEY_doWhen = "doWhen";


        //数据库表_user_login
        private static final String DB_TABLE_USER_LOGIN = "user_login";
        //public static final String KEY_id = "id";
        //  public static final String KEY_userId = "userId";
        // public static final String KEY_userName  = "userName";
        public static final String KEY_ip = "ip";
        public static final String KEY_loginTime= "loginTime";
        public static final String KEY_loginoutTime = "loginoutTime";
        public static final String KEY_loginState = "loginState";

        //数据库表_user_resource
        private static final String DB_TABLE_USER_RESOURCE = "user_resource";
        //public static final String KEY_id = "id";
        // public static final String KEY_userId= "userId";
        public static final String KEY_resourceId  = "resourceId";
        public static final String KEY_mediaType = "mediaType";
        public static final String KEY_learnStartTime= "learnStartTime";
        public static final String KEY_learnEndTime = "learnEndTime";
        public static final String KEY_learnState = "learnState";

        //数据库表_user_test
        private static final String DB_TABLE_USER_TEST = "user_test";
        //public static final String KEY_id = "id";
        //public static final String KEY_userId = "userId";
        public static final String KEY_word = "word";
        public static final String KEY_testType = "testType";
        public static final String KEY_testAspect= "testAspect";
        public static final String KEY_testDifficulty= "testDifficulty";
        public static final String KEY_wrongTimes = "wrongTimes";
        public static final String KEY_rightTimes = "rightTimes";
        public static final String KEY_totalTime = "totalTime";
        public static final String KEY_startTime = "startTime";
        public static final String KEY_endTime = "endTime";

        //数据库表_user_word
        private static final String DB_TABLE_USER_WORD = "user_word";
        public static final String KEY_wordId = "wordId";
        // public static final String KEY_userId = "userId";  //该字段前面存在
        //   public static final String KEY_word = "word";
        public static final String KEY_topicLevel = "topicLevel";
        public static final String KEY_test= "test";
        public static final String KEY_wordClickCount = "wordClickCount";
        public static final String KEY_time = "time";

       //前面都是与用户有关的表，下面是资源表
       //每个day的资源表
       private static final String DB_TABLE_WORD = "word";
       public static final String KEY_name = "name";
       public static final String KEY_proID = "proID";
       public static final String KEY_proTopic= "proTopic";
       public static final String KEY_proClass = "proClass";
       public static final String KEY_proPartsOfSpeech= "proPartsOfSpeech";
       public static final String KEY_proWordProperty = "proWordProperty";
       public static final String KEY_proChinese = "proChinese";
       public static final String KEY_proVersion= "proVersion";
       public static final String KEY_proBook = "proBook";
       public static final String KEY_proDifficulty= "proDifficulty";
       public static final String KEY_proNcyclopedia= "proNcyclopedia";
       public static final String KEY_proUse = "proUse";
       public static final String KEY_proAssociate= "proAssociate";
       public static final String KEY_proAntonym= "proAntonym";
       public static final String KEY_proSynonyms = "proSynonyms";
       public static final String KEY_proExtend= "proExtend";
       public static final String KEY_proCommonUse = "proCommonUse";
       public static final String KEY_proText1 = "proText1";
       public static final String KEY_proText2= "proText2";
       public static final String KEY_proText3= "proText3";
       public static final String KEY_proText4 = "proText4";
       public static final String KEY_proText5= "proText5";
       public static final String KEY_proText6 = "proText6";
       public static final String KEY_pathText1 = "pathText1";
       public static final String KEY_pathText2= "pathText2";
       public static final String KEY_pathText3= "pathText3";
       public static final String KEY_pathText4 = "pathText4";
       public static final String KEY_pathText5= "pathText5";
       public static final String KEY_pathText6 = "pathText6";
       public static final String KEY_proScene1 = "proScene1";
       public static final String KEY_proScene2= "proScene2";
       public static final String KEY_proScene3= "proScene3";
       public static final String KEY_proScene4 = "proScene4";
       public static final String KEY_proScene5= "proScene5";
       public static final String KEY_proScene6 = "proScene6";
       public static final String KEY_pathScene1 = "pathScene1";
       public static final String KEY_pathScene2= "pathScene2";
       public static final String KEY_pathScene3= "pathScene3";
       public static final String KEY_pathScene4 = "pathScene4";
       public static final String KEY_pathScene5= "pathScene5";
       public static final String KEY_pathScene6 = "pathScene6";
       public static final String KEY_pronunciationPath= "pronunciationPath";
       public static final String KEY_picturePath= "picturePath";
       public static final String KEY_vedioPath1 = "vedioPath1";
       public static final String KEY_vedioPath2= "vedioPath2";
       public static final String KEY_vedioPath3 = "vedioPath3";
        //用户信息表
        private  static final String TABLE_CREATE_USER = "create table " +
                DB_TABLE_USER + " (" + KEY_id  + " integer primary key autoincrement," +
                KEY_userId + " varchar(10)," + KEY_system + " int(2)," + KEY_userName + " varchar(16)," +
                KEY_passWord + " varchar(20)," + KEY_realName + " varchar(10)," + KEY_sex + " int(2)," +
                KEY_school + " varchar(10)," + KEY_grade + " int(2)," + KEY_classNum + " int(2)," +
                KEY_birthYear + " int(4)," + KEY_beforeLevel + " int(2)," + KEY_EnglishScore + " double(2),"+
                KEY_learningStyle1 + " int(2)," + KEY_learningStyle2 + " int(2)," + KEY_learningStyle3 + " int(2)," +
                KEY_learningStyle4 + " int(2),"+KEY_token +" int(10)," + KEY_isFirstLogin + " int(2))";

        //用户行为表
        private static final String TABLE_CREATE_USER_BEHAVIOR ="create table " +
                DB_TABLE_USER_BEHAVIOR + " (" + KEY_id + " integer primary key autoincrement," +
                KEY_userId + " varchar(10)," + KEY_doWhere + " varchar(10)," + KEY_doWhat + " varchar(10)," +
                KEY_doWhen + " datetime(16))";

        //用户登录表
        private static final String TABLE_CREATE_USER_LOGIN = "create table " +
                DB_TABLE_USER_LOGIN + " (" + KEY_id + " integer primary key autoincrement," +
                KEY_userId + " varchar(10)," + KEY_userName + " varchar(16)," + KEY_ip + " varchar(20)," +
                KEY_loginTime + " datetime(16)," + KEY_loginoutTime + " datetime(16)," + KEY_loginState + " int(2))";

        //用户资源表
        private static final String TABLE_CREATE_USER_RESOURCE = "create table " +
                DB_TABLE_USER_RESOURCE + " (" + KEY_id  + " integer primary key autoincrement," +
                KEY_userId + " varchar(10)," + KEY_resourceId + " varchar(10)," + KEY_mediaType + " int(2)," +
                KEY_learnStartTime + " datetime(16)," + KEY_learnEndTime + " datetime(16)," + KEY_learnState + " int(2))";

        //用户测试表
        private static final String TABLE_CREATE_USER_TEST = "create table " +
                DB_TABLE_USER_TEST + " (" + KEY_id  + " integer primary key autoincrement," +
                KEY_userId + " varchar(10)," + KEY_word + " varchar(5)," + KEY_testType + " int(2)," +
                KEY_testAspect + " int(2)," + KEY_testDifficulty + " int(2)," + KEY_wrongTimes + " int(2)," +
                KEY_rightTimes + " int(2)," + KEY_totalTime + " int(5)," + KEY_startTime + " datetime(16)," +
                KEY_endTime + " datetime(16))";

        //用户学习单词表
        private static final String TABLE_CREATE_USER_WORD = "create table " +
                DB_TABLE_USER_WORD + " (" + KEY_wordId  + " integer primary key," +
                KEY_userId + " varchar(10) not null," + KEY_word + " varchar(5)," + KEY_topicLevel + " int(2)," +
                KEY_testAspect + " int(2)," + KEY_testDifficulty + " int(2)," + KEY_wrongTimes + " int(2)," +
                KEY_test + " int(2)," + KEY_wordClickCount + " int(5)," + KEY_time + " datetime(16))" ;

        //资源单词表，主题下的day
        private static final String TABLE_CREATE_WORD = "create table "+ DB_TABLE_WORD +
                " (" + KEY_name         +    " primary key,"        + KEY_proID +             " varchar(20),"  + KEY_proTopic +   " varchar(30),"
                     + KEY_proClass      +   " varchar(20),"        + KEY_proPartsOfSpeech + " varchar(5),"   + KEY_proWordProperty + " varchar(10),"
                     + KEY_proChinese +       " varchar(20),"       + KEY_proVersion +       " varchar(5),"    + KEY_proBook +      " varchar(5),"
                     + KEY_proDifficulty +   " varchar(2),"        + KEY_proNcyclopedia +   " varchar(100),"  + KEY_proUse +      " varchar(30),"
                     + KEY_proAssociate + " varchar(30),"           + KEY_proAntonym +      " varchar(20),"    + KEY_proSynonyms + " varchar(20),"
                     + KEY_proExtend +     " varchar(200),"         + KEY_proCommonUse +     " varchar(100),"   + KEY_proText1 +   " varchar(100),"
                     + KEY_proText2 +     " varchar(100),"          + KEY_proText3 +          " varchar(100),"   + KEY_proText4 +   " varchar(100),"
                     + KEY_proText5 +    " varchar(100),"          + KEY_proText6 +         " varchar(100),"      + KEY_pathText1 +     " varchar(30),"
                     + KEY_pathText2 +   " varchar(30),"            + KEY_pathText3 +       " varchar(30),"      + KEY_pathText4 +     " varchar(30),"
                     + KEY_pathText5 +    " varchar(30),"          + KEY_pathText6 +         " varchar(30),"    + KEY_proScene1 +   " varchar(100),"
                     +KEY_proScene2 +      " varchar(100),"        + KEY_proScene3 +    " varchar(100),"        + KEY_proScene4 +    " varchar(100),"
                     + KEY_proScene5 +   " varchar(100),"         + KEY_proScene6 +     " varchar(100),"       + KEY_pathScene1 +   " varchar(30),"
                     + KEY_pathScene2 +    " varchar(30),"        + KEY_pathScene3 +      " varchar(30),"        + KEY_pathScene4 +     " varchar(30),"
                     + KEY_pathScene5 +   " varchar(30),"         + KEY_pathScene6 +        " varchar(30),"     + KEY_pronunciationPath +  " varchar(40),"
                     + KEY_picturePath +  " varchar(30),"         + KEY_vedioPath1 +    " varchar(30),"        + KEY_vedioPath2 +        " varchar(30),"
                      + KEY_vedioPath3 +  " varchar(30))";


        public DBOpenHelper(Context context){
            super(context,DB_NAME,null,DB_VERSION);
        }


        @Override
        public void onCreate(SQLiteDatabase db) {
            //创建表
            db.execSQL(TABLE_CREATE_USER);
            db.execSQL(TABLE_CREATE_USER_BEHAVIOR);
            db.execSQL(TABLE_CREATE_USER_LOGIN);
            db.execSQL(TABLE_CREATE_USER_RESOURCE);
            db.execSQL(TABLE_CREATE_USER_TEST);
            db.execSQL(TABLE_CREATE_USER_WORD);
            db.execSQL(TABLE_CREATE_WORD);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }


