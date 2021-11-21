package com.addressbook;

import java.time.LocalDate;
import java.util.List;

public class AddressBookService {
	private AddressBookDBServices AddressBookDBServices;
	private List<PersonInformation> contactList;

	public enum IOService {DB_IO}

	public AddressBookService() {
		AddressBookDBServices = com.addressbook.AddressBookDBServices.getInstance();
	}

	public List<PersonInformation> readContactData(IOService ioService) {
		if(ioService.equals(IOService.DB_IO))
			this.contactList = AddressBookDBServices.readData();
			return this.contactList;
	}

	public void updatePersonInfo(String name, String state) {
		int result = AddressBookDBServices.updateContact(name, state);
		if(result == 0)
			return;
		PersonInformation contactData = this.getContactData(name);
		if( contactData != null )
			contactData.state = state;
	}

	private PersonInformation getContactData(String name) {
		return this.contactList.stream()
				.filter(personInfo -> personInfo.first_name.equals(name))
				.findFirst()
				.orElse(null);
	}

	public boolean checkContactInSyncWithDB(String name) {
		List<PersonInformation> personInfoDataList = AddressBookDBServices.getContactData(name);
		return personInfoDataList.get(0).equals(getContactData(name));
	}

	public List<PersonInformation> readContactForDateRange(IOService ioService, LocalDate startDate, LocalDate endDate) {
		if( ioService.equals(IOService.DB_IO) )
			return AddressBookDBServices.getContactData(startDate, endDate);
		return null;
	}

	public List<PersonInformation> readContactForParticularCity(IOService ioService, String city) {
		if( ioService.equals(IOService.DB_IO) )
            return AddressBookDBServices.getContactForParticularCity(city);
        return null;
	}

	public List<PersonInformation> readContactForParticularState(IOService ioService, String state) {
		if( ioService.equals(IOService.DB_IO) )
            return AddressBookDBServices.getContactForParticularState(state);
        return null;
	}
}

