package com.campos.thiago.booking.repository;


import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.campos.thiago.booking.entity.Booking;
import com.campos.thiago.booking.entity.Guest;
import com.campos.thiago.booking.entity.Host;
import com.campos.thiago.booking.entity.Property;
import com.campos.thiago.booking.entity.TypeProperty;

@DataJpaTest
public class BookingRepositoryTest {
	@Autowired
	BookingRepository repository;

	@Test
	public void testRepositoryIsEmpty() {
	    assertThat(repository.findAll()).isEmpty();
	}

	@Test
	public void testSaveABooking() {
		LocalDate startDate = LocalDate.now();
		LocalDate endDate = LocalDate.now().plusDays(3);
		
		Booking booking = repository.save(createBooking(startDate, endDate));

	    assertThat(booking).hasFieldOrPropertyWithValue("startDate", startDate);
	    assertThat(booking).hasFieldOrPropertyWithValue("endDate", endDate);
	    assertThat(booking).hasFieldOrPropertyWithValue("rentValue", BigDecimal.TEN);
	}

	@Test
	public void testFindAllBookings() {
		LocalDate startDate1 = LocalDate.now();
		LocalDate endDate1 = LocalDate.now().plusDays(1);
		
		LocalDate startDate2 = LocalDate.now().plusDays(2);
		LocalDate endDate2 = LocalDate.now().plusDays(3);
		
		LocalDate startDate3 = LocalDate.now().plusDays(4);
		LocalDate endDate3 = LocalDate.now().plusDays(5);
		
		Booking b1 = createBooking(startDate1, endDate1);
		Booking b2 = createBooking(startDate2, endDate2);
		Booking b3 = createBooking(startDate3, endDate3);
		
		repository.save(b1);
		repository.save(b2);
		repository.save(b3);

	    
		Iterable<Booking> bookings = repository.findAll();

	    assertThat(bookings).hasSize(3).contains(b1, b2, b3);
	}

	@Test
	public void testFindById() {
		LocalDate startDate1 = LocalDate.now();
		LocalDate endDate1 = LocalDate.now().plusDays(1);
		
		LocalDate startDate2 = LocalDate.now().plusDays(2);
		LocalDate endDate2 = LocalDate.now().plusDays(3);
		
		LocalDate startDate3 = LocalDate.now().plusDays(4);
		LocalDate endDate3 = LocalDate.now().plusDays(5);
		
		Booking b1 = createBooking(startDate1, endDate1);
		Booking b2 = createBooking(startDate2, endDate2);
		Booking b3 = createBooking(startDate3, endDate3);
		
		b1 = repository.save(b1);
		b2 = repository.save(b2);
		b3 = repository.save(b3);
		
	    Booking foundBooking = repository.findById(b2.getId()).get();

	    assertThat(foundBooking).isEqualTo(b2);
	    assertThat(foundBooking.getStartDate()).isEqualTo(startDate2);
	    assertThat(foundBooking.getEndDate()).isEqualTo(endDate2);
	}
  
	@Test
	public void testUpdateBooking() {
		//Not used register
		LocalDate startDate1 = LocalDate.now();
		LocalDate endDate1 = LocalDate.now().plusDays(1);
		Booking b1 = createBooking(startDate1, endDate1);
		b1 = repository.save(b1);
		
		//Old register
		LocalDate originalStartDate = LocalDate.now().plusDays(2);
		LocalDate originalEndDate = LocalDate.now().plusDays(3);
		Booking b2 = createBooking(originalStartDate, originalEndDate);
		b2 = repository.save(b2);

		//Find the register
		Booking foundBooking = repository.findById(b2.getId()).get();
		
		//Update the register
		LocalDate newStartDate = LocalDate.now().plusDays(4);
		LocalDate newEndDate = LocalDate.now().plusDays(5);
		foundBooking.setStartDate(newStartDate);
		foundBooking.setEndDate(newEndDate);
		repository.save(foundBooking);
		
		//Find the new register
		Booking updatedBooking = repository.findById(b2.getId()).get();
		
		//Validate new register
	    assertThat(updatedBooking.getStartDate()).isEqualTo(newStartDate);
	    assertThat(updatedBooking.getEndDate()).isEqualTo(newEndDate);  
	}

	@Test
	public void should_delete_tutorial_by_id() {
		LocalDate startDate1 = LocalDate.now();
		LocalDate endDate1 = LocalDate.now().plusDays(1);
		
		LocalDate startDate2 = LocalDate.now().plusDays(2);
		LocalDate endDate2 = LocalDate.now().plusDays(3);
		
		LocalDate startDate3 = LocalDate.now().plusDays(4);
		LocalDate endDate3 = LocalDate.now().plusDays(5);
		
		Booking b1 = createBooking(startDate1, endDate1);
		Booking b2 = createBooking(startDate2, endDate2);
		Booking b3 = createBooking(startDate3, endDate3);
		
		repository.save(b1);
		repository.save(b2);
		repository.save(b3);

	    repository.deleteById(b2.getId());

	    Iterable<Booking> bookings = repository.findAll();

	    assertThat(bookings).hasSize(2).contains(b1, b3);
	}
	

	private Booking createBooking(LocalDate startDate, LocalDate endDate) {
		Guest g1 = new Guest();
		g1.setName("Thiago Campos");
		g1.setRegister("123");
		
		Host h1 = new Host();
		h1.setName("Maria Alice");
		h1.setRegister("456");
			
		Property p1 = new Property();
		p1.setAddress("Avenida 1");
		p1.setCity("Campinas");
		p1.setType(TypeProperty.URBAN_HOUSE);
		p1.setHost(h1);
		
		Booking b = new Booking();
		b.setStartDate(startDate);
		b.setEndDate(endDate);
		b.setRentValue(BigDecimal.TEN);
		b.setProperty(p1);
		b.setGuest(g1);
		
		return b;
	}
}
