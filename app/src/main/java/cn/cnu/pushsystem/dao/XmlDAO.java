package cn.cnu.pushsystem.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import cn.cnu.pushsystem.entity.Tb_word;

import java.util.List;


/**
 * Created by Administrator on 2016/9/7.
 */
public class XmlDAO {

    private DBOpenHelper helper;
    private SQLiteDatabase db;
    //private static Tb_user user;

    public XmlDAO(Context context) {
        helper = new DBOpenHelper(context);
    }
    public boolean insert(List<Tb_word> words) {
        db = helper.getWritableDatabase();
        try {
            for(Tb_word word:words){
                db.execSQL("insert into word (" +
                        "name,proID,proTopic,proClass,proPartsOfSpeech,proWordProperty," +
                          "proChinese,proVersion,proBook,proDifficulty,proNcyclopedia,proUse,proAssociate," +
                        "proAntonym,proSynonyms,proExtend,proCommonUse,proText1,proText2,proText3,proText4," +
                        "proText5,proText6,pathText1,pathText2,pathText3,pathText4,pathText5,pathText6,proScene1," +
                        "proScene2,proScene3,proScene4,proScene5,proScene6,pathScene1,pathScene2,pathScene3,pathScene4," +
                        "pathScene5,pathScene6,pronunciationPath,picturePath,vedioPath1,vedioPath2,vedioPath3) values (?,?,?,?,?,?,?,?,?,?," +
                        "?,?,?,?,?,?,?,?,?,?," + "?,?,?,?,?,?,?,?,?,?," + "?,?,?,?,?,?,?,?,?,?," + "?,?,?,?,?,?)",
                        new Object[]{
                                    word.getName(), word.getProID(), word.getProTopic(), word.getProClass(),word.getProPartsOfSpeech(),
                                    word.getProWordProperty(),word.getProChinese(),word.getProVersion(),word.getProBook(),word.getProDifficulty(),
                                    word.getProNcyclopedia(),word.getProUse(),word.getProAssociate(),word.getProAntonym(),word.getProSynonyms(),
                                    word.getProExtend(), word.getProCommonUse(),word.getProText1(),word.getProText2(), word.getProText3(),word.getProText4(),
                                    word.getProText5(),word.getProText6(), word.getPathText1(),word.getPathText2(), word.getPathText3(),word.getPathText4(),
                                    word.getPathText5(),word.getPathText6(), word.getProScene1(), word.getProScene2(), word. getProScene3(),word.getProScene4(),
                                    word.getProScene5(),word.getProScene6(), word.getPathScene1(),word.getPathScene2(), word.getPathScene3(),word.getPathScene4(),
                                    word.getPathScene5(),word.getPathScene6(),word.getPronunctionPath(),word.getPicturePath(), word.getVedioPath1(),
                                    word.getVedioPath2(),word.getVedioPath3(),
                                    });

            }
            db.close();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
