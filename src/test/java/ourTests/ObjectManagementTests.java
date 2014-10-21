package ourTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.objectManagement.ObjectMapper;

public class ObjectManagementTests {

	
	@Test
	public void mapUsernamesTest() {
		List<Object> rawUsernames = new ArrayList<Object>();
		Object rawString1 = (Object) "Test1";
		Object rawString2 = (Object) "Test2";
		Object rawString3 = (Object) "";
		rawUsernames.add(rawString1);
		rawUsernames.add(rawString2);
		rawUsernames.add(rawString3);
		
		assertEquals("{\"Usernames\":[{\"Username\":\"Test1\"},{\"Username\":\"Test2\"},{\"Username\":\"\"}]}", ObjectMapper.mapUsernames(rawUsernames));
	}
	
	@Test
	public void mapEmptyUsernamesTest() {
		List<Object> rawUsernames = new ArrayList<Object>();
		
		assertEquals("{\"Usernames\":[]}", ObjectMapper.mapUsernames(rawUsernames));
	}
	

}
