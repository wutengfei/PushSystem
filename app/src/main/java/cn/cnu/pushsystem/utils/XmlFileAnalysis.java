package cn.cnu.pushsystem.utils;


import cn.cnu.pushsystem.entity.Tb_word;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/5.
 */
public class XmlFileAnalysis {
    private String Dir;
    private String fileName;
    private List<Tb_word> list = new ArrayList<Tb_word>();

    public XmlFileAnalysis(String Dir, String fileName) {
        this.Dir = Dir;
        this.fileName = fileName;
    }

    public List<Tb_word> readXML(){
        String xmlpath = Dir + File.separator +fileName;//xml文件的路径
        System.out.println("xmlpath:"+xmlpath);
        Element root = null;
        File file = new File(xmlpath);//路径
        DocumentBuilder documentBuilder = null; //documentBuilder为抽象不能直接实例化(将XML文件转换为DOM文件)
        DocumentBuilderFactory documentBuilderFactory = null;
        try{
            documentBuilderFactory = DocumentBuilderFactory.newInstance();  //返回documentBuilderFactory对象
            documentBuilder = documentBuilderFactory.newDocumentBuilder();  //返回documentBuilder用XXFactory对象获得返回documentBudiler对象
            InputStream is  = new FileInputStream(file);//得到一个DOM并返回给document对象
            Document document = documentBuilder.parse(is);
            root =  document.getDocumentElement();    //得到一个element元素
            System.out.println("根元素:" + root.getNodeName());////获得根节点
            NodeList personNodes = root.getElementsByTagName("word");      //获得根元素下的子节点
            for (int i = 0;i<personNodes.getLength();i++){ //遍历这些words根节点中所有的word对象
                Tb_word word = new Tb_word();
                word = this.readNode((Element)personNodes.item(i));
                list.add(word);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public Tb_word readNode(Element personNode) {
        Tb_word word = new Tb_word();
        word.setName(personNode.getAttribute("name"));   //设置word的name值
        NodeList personChildNodes = personNode.getElementsByTagName("property");//获取具体word节点下所有的字节点

        for (int index = 0; index < personChildNodes.getLength(); index++) {//遍历所有的字节点
           Element node = (Element)personChildNodes.item(index); //获取字节点

                if ("propertyID".equals(node.getAttribute("name"))) {
                    word.setProID(node.getAttribute("value"));
                    continue;
                }
                if ("propertyTopic".equals(node.getAttribute("name"))) {
                    word.setProTopic(node.getAttribute("value"));
                    continue;
                }
                if ("propertyClass".equals(node.getAttribute("name"))) {
                    word.setProClass(node.getAttribute("value"));
                    continue;
                }

                if ("propertyPartsOfSpeech".equals(node.getAttribute("name"))) {
                    word.setProPartsOfSpeech(node.getAttribute("value"));
                    continue;
                }
                if ("propertyWordProperty".equals(node.getAttribute("name"))) {
                    word.setProWordProperty(node.getAttribute("value"));
                    continue;
                }
                if ("propertyChinese".equals(node.getAttribute("name"))) {
                    word.setProChinese(node.getAttribute("value"));
                    continue;
                }
                if ("propertyVersion".equals(node.getAttribute("name"))) {
                    word.setProVersion(node.getAttribute("value"));
                    continue;
                }

                if ("propertyBook".equals(node.getAttribute("name"))) {
                    word.setProBook(node.getAttribute("value"));
                    continue;
                }
                if ("propertyDifficulty".equals(node.getAttribute("name"))) {
                    word.setProDifficulty(node.getAttribute("value"));
                    continue;
                }
                if ("propertyNcyclopedia".equals(node.getAttribute("name"))) {
                    word.setProNcyclopedia(node.getAttribute("value"));
                    continue;
                }
                if ("propertyUse".equals(node.getAttribute("name"))) {
                    word.setProUse(node.getAttribute("value"));
                    continue;
                }

                if ("propertyAssociate".equals(node.getAttribute("name"))) {
                    word.setProAssociate(node.getAttribute("value"));
                    continue;
                }
                if ("propertyAntonym".equals(node.getAttribute("name"))) {
                    word.setProAntonym(node.getAttribute("value"));
                    continue;
                }
                if ("propertySynonyms".equals(node.getAttribute("name"))) {
                    word.setProSynonyms(node.getAttribute("value"));
                    continue;
                }
                if ("propertyExtend".equals(node.getAttribute("name"))) {
                    word.setProExtend(node.getAttribute("value"));
                    continue;
                }
                 if ("propertyCommonUse".equals(node.getAttribute("name"))) {
                    word.setProCommonUse(node.getAttribute("value"));
                    continue;
                 }
                if ("propertyText".equals(node.getAttribute("name"))) {
                    NodeList childchildNode = node.getElementsByTagName("pro");
                    for (int i = 0; i < childchildNode.getLength(); i++) {
                        Element chidchidElement = (Element) childchildNode.item(i);
                        switch (chidchidElement.getAttribute("grade")) {
                            case "1":
                                word.setProText1(chidchidElement.getAttribute("value"));
                                word.setPathText1(chidchidElement.getAttribute("path"));
                                break;
                            case "2":
                                word.setProText2(chidchidElement.getAttribute("value"));
                                word.setPathText2(chidchidElement.getAttribute("path"));
                                break;
                            case "3":
                                word.setProText3(chidchidElement.getAttribute("value"));
                                word.setPathText3(chidchidElement.getAttribute("path"));
                                break;
                            case "4":
                                word.setProText4(chidchidElement.getAttribute("value"));
                                word.setPathText4(chidchidElement.getAttribute("path"));
                                break;
                            case "5":
                                word.setProText5(chidchidElement.getAttribute("value"));
                                word.setPathText5(chidchidElement.getAttribute("path"));
                                break;
                            case "6":
                                word.setProText6(chidchidElement.getAttribute("value"));
                                word.setPathText6(chidchidElement.getAttribute("path"));
                                break;
                        }
                    }
                    continue;
                }

                if ("propertyScene".equals(node.getAttribute("name"))) {
                    NodeList childchildNode = node.getElementsByTagName("pro");
                    for (int i = 0; i < childchildNode.getLength(); i++) {
                        Element chidchidElement = (Element) childchildNode.item(i);
                        switch (chidchidElement.getAttribute("grade")) {
                            case "1":
                                word.setProScene1(chidchidElement.getAttribute("value"));
                                word.setPathScene1(chidchidElement.getAttribute("path"));
                                break;
                            case "2":
                                word.setProScene2(chidchidElement.getAttribute("value"));
                                word.setPathScene2(chidchidElement.getAttribute("path"));
                                break;
                            case "3":
                                word.setProScene3(chidchidElement.getAttribute("value"));
                                word.setPathScene3(chidchidElement.getAttribute("path"));
                                break;
                            case "4":
                                word.setProScene4(chidchidElement.getAttribute("value"));
                                word.setPathScene4(chidchidElement.getAttribute("path"));
                                break;
                            case "5":
                                word.setProScene5(chidchidElement.getAttribute("value"));
                                word.setPathScene5(chidchidElement.getAttribute("path"));
                                break;
                            case "6":
                                word.setProScene6(chidchidElement.getAttribute("value"));
                                word.setPathScene6(chidchidElement.getAttribute("path"));
                                break;
                        }
                    }
                    continue;
                }

                if ("pronunciation".equals(node.getAttribute("name"))) {
                    word.setPronunctionPath(node.getAttribute("value"));
                    continue;
                }
                if ("picture".equals(node.getAttribute("name"))) {
                    word.setPicturePath(node.getAttribute("value"));
                    continue;
                }
               if ("video".equals(node.getAttribute("name"))){
                   switch (node.getAttribute("difficulty")){
                       case "1":
                           word.setVedioPath1(node.getAttribute("path"));
                           break;
                       case "2":
                           word.setVedioPath2(node.getAttribute("path"));
                           break;
                       case "3":
                           word.setVedioPath3(node.getAttribute("path"));
                           break;

                   }
               }

        }
        return word;
    }
}
