package KW.Test.User.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import KW.Test.User.domain.User;

public class UserDao {
	/*
	 * 访问数据库的，提供路径
	 */
	private String path="F:/users.xml";
	public User findByUserName(String name)
		/*
		 * 1.创建解析器
		 */
		 {
		SAXReader reader=new SAXReader();
		try {
			Document docu=reader.read(path);
			Element ele=(Element) docu.selectSingleNode("//user[@name='"+name+"']");
		
			//效验是否为null
			if(ele==null)
			{
				return null;		
			}
			//封装表单数据。
			User _user=new User();
			String Aname=ele.attributeValue("name");
			String Apass=ele.attributeValue("pass");
			_user.setName(Aname);
			_user.setPass(Apass);
			return _user;
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		}
	}
	public void add(User user)
	{
		SAXReader reader=new SAXReader();
		try {
			Document doc=reader.read(path);
			//得到根元素
			Element root=doc.getRootElement();
			//通过根元素创建新标签
			Element userEle=root.addElement("user");
			//通过跟标签添加元素
			userEle.addAttribute("name", user.getName());
			userEle.addAttribute("pass", user.getPass());
			/*
			 * 保存文档
			 */
			//首先设置格式
			OutputFormat format=new OutputFormat("/t",true);//是否使用缩进，真
		    format.setTrimText(true);
		    //创建xml
		    try {
				XMLWriter writer=new XMLWriter
						(new OutputStreamWriter(
								new FileOutputStream(path),"utf-8"),format);
				
					writer.write(doc);
				writer.close();
		    } catch (Exception e) {
				// TODO Auto-generated catch block
				throw new RuntimeException(e);
			
			}
			
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		}
	}
}
