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
		rawUsernames.add((Object) "Test1");
		rawUsernames.add((Object) "Test2");
		rawUsernames.add((Object) "");
		
		assertEquals("{\"Usernames\":[{\"Username\":\"Test1\"},{\"Username\":\"Test2\"},{\"Username\":\"\"}]}", ObjectMapper.mapUsernames(rawUsernames));
	}
	
	@Test
	public void mapEmptyUsernamesTest() {
		List<Object> rawUsernames = new ArrayList<Object>();
		
		assertEquals("{\"Usernames\":[]}", ObjectMapper.mapUsernames(rawUsernames));
	}
	

	@Test
	public void mapMessagesTest() {
		List<Object[]> rawMessages = new ArrayList<Object[]>();
		Object[] rawMessage1 = {"This is a test message", "TestSender"};
		Object[] rawMessage2 = {"Another test", "OtherTestUser"};
		Object[] rawMessage3 = {"", ""};
		rawMessages.add(rawMessage1);
		rawMessages.add(rawMessage2);
		rawMessages.add(rawMessage3);
		
		assertEquals("{\"Messages\":[{\"Sender\":\"TestSender\",\"Message\":\"This is a test message\"},{\"Sender\":\"OtherTestUser\","
				+ "\"Message\":\"Another test\"},{\"Sender\":\"\",\"Message\":\"\"}]}", ObjectMapper.mapMessages(rawMessages));
	}
	
	@Test
	public void mapEmptyMessagesTest() {
		List<Object[]> rawMessages = new ArrayList<Object[]>();
		
		assertEquals("{\"Messages\":[]}", ObjectMapper.mapMessages(rawMessages));
	}
	
	@Test
	public void mapCoordinatesTest() {
		List<Object[]> rawCoordinates = new ArrayList<Object[]>();
		Object[] rawCoordinates1 = {55.3, 54.2};
		Object[] rawCoordinates2 = {43.22, 123.9};
		rawCoordinates.add(rawCoordinates1);
		rawCoordinates.add(rawCoordinates2);
		
		assertEquals("[Point2D.Double[55.3, 54.2], Point2D.Double[43.22, 123.9]]", ObjectMapper.mapCoordinates(rawCoordinates).toString());
	}
	
	@Test
	public void mapEmptyCoordinatesTest() {
		List<Object[]> rawCoordinates = new ArrayList<Object[]>();
		
		assertEquals("[]", ObjectMapper.mapCoordinates(rawCoordinates).toString());
	}
	
	
	
	
}


