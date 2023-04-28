package com.campos.thiago.booking.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.campos.thiago.booking.entity.Booking;
import com.campos.thiago.booking.entity.Property;
import com.campos.thiago.booking.exception.BookingNotFoundException;
import com.campos.thiago.booking.exception.DatesInvalidException;
import com.campos.thiago.booking.exception.GuestNotFoundException;
import com.campos.thiago.booking.exception.PeriodNotAvailableException;
import com.campos.thiago.booking.exception.PropertyNotFoundException;
import com.campos.thiago.booking.repository.BookingRepository;
import com.campos.thiago.booking.to.BookingDatesTO;
import com.campos.thiago.booking.to.BookingTO;

@Service
public class BookingService {
	
	@Autowired
	private BookingRepository bookingRepository;
	
	@Autowired
	private GuestService guestService;
	
	@Autowired
	private PropertyService propertyService;
	
	public Booking findById(int bookingId) throws BookingNotFoundException{
		return bookingRepository
				.findById(bookingId)
				.orElseThrow(() -> new BookingNotFoundException());
		
	}
	
	public List<Booking> findAll(){
		List<Booking> bookings = new ArrayList<Booking>();
		bookingRepository.findAll().forEach(bookings::add);
		return bookings;
	}
	
	public Booking insertBooking(BookingTO bto) throws PeriodNotAvailableException, PropertyNotFoundException, GuestNotFoundException, DatesInvalidException{
		if(!isDatesValids(bto.getStartDate(), bto.getEndDate())) {
			throw new DatesInvalidException();
		}
		
		if((getBookingsByPeriod(bto.getPropertyId(), bto.getStartDate(), bto.getEndDate())).isEmpty()) {
			return bookingRepository.save(parseToEntity(bto));
		}else {
			throw new PeriodNotAvailableException();
		}
	}
	
	public Booking updateBooking(int bookingId, BookingDatesTO newDates) throws PeriodNotAvailableException, PropertyNotFoundException, GuestNotFoundException, DatesInvalidException, BookingNotFoundException{
		if(!isDatesValids(newDates.getNewStartDate(), newDates.getNewEndDate())) {
			throw new DatesInvalidException();
		}
		
		Booking booking = findById(bookingId);
		List<Booking> bookingsOnPeriod = getBookingsByPeriod(booking.getProperty().getId(), newDates.getNewStartDate(), newDates.getNewEndDate());
		
		if((bookingsOnPeriod.isEmpty()) || ((bookingsOnPeriod.size()==1) && (bookingsOnPeriod.get(0).getId() == bookingId))){
			booking.setStartDate(newDates.getNewStartDate());
			booking.setEndDate(newDates.getNewEndDate());
			return bookingRepository.save(booking);
		}else {
			throw new PeriodNotAvailableException();
		}
	}
	
	public void deleteById(int bookingId) {
		bookingRepository.deleteById(bookingId);
	}

	private List<Booking> getBookingsByPeriod(int propertyId, LocalDate startDate, LocalDate endDate) {
		return bookingRepository.findBookingsOfPropertyInPeriod(propertyId, startDate, endDate);
		
	}
	
	private Booking parseToEntity(BookingTO bto) throws PropertyNotFoundException, GuestNotFoundException {
		Property property = propertyService.findById(bto.getPropertyId());
		
		Booking booking = new Booking();
		booking.setEndDate(bto.getEndDate());
		booking.setStartDate(bto.getStartDate());
		booking.setRentValue(bto.getRentValue());
		booking.setBlock(property.getHost().getRegister().equalsIgnoreCase(bto.getGuestId()));
		booking.setGuest(guestService.findByRegister(bto.getGuestId()));
		booking.setProperty(property);
		
		return booking;
	}
	
	private boolean isDatesValids(LocalDate startDate, LocalDate endDate) {
		boolean datesValids = true;
		
		datesValids = startDate.isBefore(endDate);
		datesValids = !startDate.isEqual(endDate);
		
		return datesValids;
	}
}
