package com.addressbook;

import java.time.LocalDate;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;


public class AddressBookTest {

	@Test
	public void givenContactsInDB_MatchContactCount() {
		AddressBookService addressBookService = new AddressBookService();
		List<PersonInformation> contactData = addressBookService.readContactData(AddressBookService.IOService.DB_IO);
		Assert.assertEquals(3, contactData.size());
		System.out.println("UC-16 completed");
	}

	//update the data in DB and also check sync
	@Test
	public void givenContactUpdated_SyncWithDB() {
		AddressBookService addressBookService = new AddressBookService();
		addressBookService.readContactData(AddressBookService.IOService.DB_IO);
		addressBookService.updatePersonInfo("Brijesh", "Manjeshwar");
		boolean result = addressBookService.checkContactInSyncWithDB("Brijesh");
		Assert.assertTrue(result);
		System.out.println("UC-17 completed");
	}

	//retrieve the data from DB using date range
	@Test
	public void givenContactWithinDateRange_MatchContactCount() {
		AddressBookService addressBookService = new AddressBookService();
		addressBookService.readContactData(AddressBookService.IOService.DB_IO);
		LocalDate startDate = LocalDate.of(2018, 05, 20);
		LocalDate endDate = LocalDate.now();
		List<PersonInformation> contactData = addressBookService.readContactForDateRange(AddressBookService.IOService.DB_IO, startDate, endDate);
		Assert.assertEquals(2, contactData.size());
		System.out.println("UC-18 completed");
	}
	
	//retrieve data from DB from particular city
    @Test
    public void givenCityNameInDB_MatchContactCount() {
    	AddressBookService addressBookService = new AddressBookService();
		addressBookService.readContactData(AddressBookService.IOService.DB_IO);
        List<PersonInformation> personInfoData = addressBookService.readContactForParticularCity(AddressBookService.IOService.DB_IO, "Manjeshwar");
        Assert.assertEquals(2, personInfoData.size());
    }
    
	//retrieve data from DB from particular state
	@Test
    public void givenStateNameInDB_MatchContactCount() {
		AddressBookService addressBookService = new AddressBookService();
		addressBookService.readContactData(AddressBookService.IOService.DB_IO);
        List<PersonInformation> personInfoData = addressBookService.readContactForParticularState(AddressBookService.IOService.DB_IO, "kerala");
        Assert.assertEquals(2, personInfoData.size());
        System.out.println("UC-19 completed");
	}
}

