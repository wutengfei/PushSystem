package cn.cnu.pushsystem.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Created by Administrator on 2016/9/22.
 */
public class ZIP {
    public ZIP(){}

    /**
     * DeCompress the ZIP to the path
     * @param zipFileString name of ZIP
     * @param outPathString path to the unZIP
     */
 public static void UnZipFolder(String zipFileString,String outPathString) throws Exception{
     ZipInputStream inZip = new ZipInputStream(new FileInputStream(zipFileString));
     ZipEntry zipEntry;
     String szName = "";
     while((zipEntry=inZip.getNextEntry())!=null){
         szName = zipEntry.getName();
         szName= szName.replace("\\","/");
         if (zipEntry.isDirectory()){
             szName = szName.substring(0,szName.length()-1);
             File folder = new File(outPathString+File.separator+szName);
             folder.mkdirs();
         }else{
             File file = new File(outPathString+File.separator +szName);
             file.createNewFile();
             FileOutputStream out = new FileOutputStream(file);
             int len;
             byte[] buffer = new byte[1024];
             while((len = inZip.read(buffer))!= -1){
                 out.write(buffer,0,len);
                 out.flush();
             }
             out.close();
         }
     }
     inZip.close();
 }

}
