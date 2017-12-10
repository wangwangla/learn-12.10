package KW.utils.toBean;

import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
public class toBean {
	/*
	 * 将map转换指定的类型
	 * 
	 * 1.首先传入map，传入class
	 * 得到T类型的bean
	 */
	public static <T>T toBean(Map map,Class<T> clazz)
	{
		try{
			/*
			 * 1,创建指定的类型的javabean对象
			 */
		T bean=clazz.newInstance();
		/*
		 * 将数据封装到javabean中
		 */
		//ConvertUtils.register(new DateLocaleConverter(), java.util.Date.class);
		/*
		 * 将数据封装到javabean中
		 */
		BeanUtils.populate(bean, map);
		/*
		 * 返回bean对象。
		 */
		return bean;
		}
		catch(Exception e)
		{
			//这个是运行的，是不用进行处理，假如异常，也可以跑出去。
			throw new RuntimeException(e);
		}
		 
	}
}