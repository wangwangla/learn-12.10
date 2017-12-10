package KW.Test.User.service;

import KW.Test.User.dao.UserDao;
import KW.Test.User.domain.User;

public class UserService {
	UserDao userdao=new UserDao();
	public void registe(User user)throws UserException
	{
		/*
		 *返回的是null，那么就添加
		 *返回的不是null，那么就不添加。
		 */
		User _user=userdao.findByUserName(user.getName());
		System.out.print(_user);
		if(_user!=null)
			throw new UserException("用户名已经注册过了.他的原理his"+user.getName());
		else
		userdao.add(user);
	}
	public User login(User form) throws UserException {
		/*
		 * 使用form中的username，进行查询，得到的是User
		 */
		User user =userdao.findByUserName(form.getName());
		/*
		 * 如果返回null，说明用户名不存在，抛出异常
		 */
		if(user==null)
			throw new UserException("用户名不存在！");
		if(!form.getPass().equals(user.getPass()))
		{
			throw new UserException("密码错误!");
		}
		/*
		 * 方法数据库中查询的User，而不是form,我们想要的是所有的东西，不是用户名和密码。
		 */
		return user;
	}
}
