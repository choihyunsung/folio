package kr.block.common.utils;

public class DataBaseUtils {
	private static DataBaseUtils instance = null;
	//DataBaseUtils는 싱글톤 객체이기 때문에 생성자를 Private로 선언한다.
	private DataBaseUtils() {} 
	
	public static DataBaseUtils getInstance() {
		if(instance == null) {
			instance = new DataBaseUtils();
		}
		return instance;
	}
}
