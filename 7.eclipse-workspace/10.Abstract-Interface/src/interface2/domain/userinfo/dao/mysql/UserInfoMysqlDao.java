package interface2.domain.userinfo.dao.mysql;

import interface2.domain.userinfo.UserInfo;
import interface2.domain.userinfo.dao.UserInfoDao;

public class UserInfoMysqlDao implements UserInfoDao {

	@Override
	public void insertUserInfo(UserInfo userInfo) {
		System.out.println("insert into MYSQL DB userid = "+userInfo.getUserId());
		
	}

	@Override
	public void updateUserInfo(UserInfo userInfo) {
		System.out.println("update MYSQL DB userid = "+userInfo.getUserId());
		
	}

	@Override
	public void deleteUserInfo(UserInfo userInfo) {
		System.out.println("delete MYSQL DB userid = "+userInfo.getUserId());
		
	}

}
