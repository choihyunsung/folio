package kr.block.common.mapper;

import java.io.FileReader;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;

import javax.servlet.ServletContext;

import kr.block.common.action.impl.ActionImpl;

/***
 * 
 * @author user1
 * 사용될 Action의 매핑 작업을 처리 
 */
public class ActionMapper {
	private HashMap<String, ActionImpl> actionMap = new HashMap<String, ActionImpl>();
	private Properties prop = new Properties();
	
	public ActionMapper(ServletContext sc, String mappingParamName) {
		try {
			prop.load(new FileReader(sc.getRealPath(sc.getInitParameter(mappingParamName))));
			Iterator<Object> keyIterator = prop.keySet().iterator();
			
			while(keyIterator.hasNext()) {
				String uri = (String)keyIterator.next(); //Uri 정보 
				String className = prop.getProperty(uri); //Action ClassString
				Class<?> actionClass = Class.forName(className);
				Constructor<?> constructor = actionClass.getConstructor(null);
				ActionImpl action = (ActionImpl)constructor.newInstance();
				actionMap.put(uri, action);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**등록되어 있는 ActionMap을 반환*/
	public HashMap<String, ActionImpl> getActionMap() { return actionMap; }
	/**프로퍼티를 반환*/
	public Properties getProp() { return prop; }
	/**해당 Uri의 Action을 반환 */
	public ActionImpl getAction(String uri) { return actionMap.get(uri); }	
}
