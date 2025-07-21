package interface2.domain.userinfo.dao.oracle;

import interface2.domain.userinfo.UserInfo;
import interface2.domain.userinfo.dao.UserInfoDao;

public class UserInfoOracleDao implements UserInfoDao {

	@Override
	public void insertUserInfo(UserInfo userInfo) {
		System.out.println("insert into ORACLE DB userid = "+userInfo.getUserId());
		
	}

	@Override
	public void updateUserInfo(UserInfo userInfo) {
		System.out.println("update ORACLE DB userid = "+userInfo.getUserId());
		
	}

	@Override
	public void deleteUserInfo(UserInfo userInfo) {
		System.out.println("delete ORACLE DB userid = "+userInfo.getUserId());
		
	}

}
