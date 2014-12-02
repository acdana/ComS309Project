package ourTests;

import java.sql.Date;

import org.junit.Test;

import junit.framework.TestCase;

import com.entities.*;

public class EntitiesTests extends TestCase {

	Item item = new Item();
	Message message = new Message();
	Profile profile = new Profile();
	Sale sale = new Sale();
	User user = new User();
	
	
	@Test
	public void testItem() {
		item.setItemID("15");
		item.setItemName("Test Item");
		item.setSaleID("21");
		item.setUsername("TestUser");
		
		assertEquals("15Test Item21TestUser", item.getItemID() + item.getItemName() + item.getSaleID() + item.getUsername());
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testMessage() {
		message.setDateOpened(new Date(114, 11, 3));
		message.setDateSent(new Date(114, 11, 1));
		message.setMessage("TestContent");
		message.setMessageID("MessageID");
		message.setSender("TestSender");
		message.setUsername("TestUsername");
		
		assertEquals("2014-12-032014-12-01TestContentMessageIDTestSenderTestUsername", 
				message.getDateOpened().toString() + message.getDateSent().toString() + message.getMessage() + 
				message.getMessageID() + message.getSender() + message.getUsername());
	}
	
	@Test public void testProfile() {
		profile.setBio("TestBio");
		profile.setProfilePicture("TestLocation");
		profile.setReputation(5);
		profile.setUsername("TestUser");
		assertEquals("TestBioTestLocation5TestUser", profile.getBio() + profile.getProfilePicture() + Integer.toString(profile.getReputation()) + profile.getUsername());
	}
	
	@SuppressWarnings("deprecation")
	@Test public void testSale() {
		sale.setDateCreated(new Date(114, 11, 3));
		sale.setLatitude(55.01);
		sale.setLongitude(43.22);
		sale.setPrimarySeller("PrimarySeller");
		sale.setSaleID("65");
		sale.setSecondarySeller("SecondarySeller");
		sale.setSecondarySellerLocation("OtherPlace");
		
		assertEquals("Pearson Hall2014-12-0355.0143.22PrimarySeller65SecondarySellerOtherPlace", sale.getDateCreated().toString() + 
				Double.toString(sale.getLatitude()) + Double.toString(sale.getLongitude()) + sale.getPrimarySeller() + sale.getSaleID() + 
				sale.getSecondarySeller() + sale.getSecondarySellerLocation());
	}
	
	@Test public void testUser() {
		user.setAccountStatus("TestAccountStatus");
		user.setEmail("TestEmail");
		user.setPassword("TestPassword");
		user.setPenalties(6);
		user.setUsername("TestUsername");
		user.setUserType("TestType");
		
		assertEquals("TestAccountStatusTestEmailTestPassword6TestUsernameTestType", user.getAccountStatus() + user.getEmail() + user.getPassword() + 
				Integer.toString(user.getPenalties()) + user.getUsername() + user.getUserType());
	}
	
	
}
