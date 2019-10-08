package com.adi3000.charivariperm.api;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.adi3000.charivariperm.model.enumeration.PermanenceStatus;
import org.springframework.stereotype.Component;

import com.adi3000.charivariperm.model.dataobject.Account;
import com.adi3000.charivariperm.model.dataobject.Family;
import com.adi3000.charivariperm.model.dataobject.Holidays;
import com.adi3000.charivariperm.model.dataobject.Permanence;
import com.adi3000.charivariperm.model.dto.Authentification;
import com.adi3000.charivariperm.model.service.AccountService;
import com.adi3000.charivariperm.model.service.FamilyService;
import com.adi3000.charivariperm.model.service.impl.HolidaysService;
import com.adi3000.charivariperm.model.service.impl.PermanenceService;

@Component
@Path("/")
public class RestApiService {

	@Inject
	private AccountService accountService;
	@Inject
	private FamilyService familyService;
	@Inject
	private PermanenceService permanenceService;
	@Inject
	private HolidaysService holidaysService;
	
	
	@GET
	@Path("/family/{familyId}")
	@Produces(value={MediaType.APPLICATION_JSON})
	public Family getFamily(@PathParam("familyId") Long familyId){
		return familyService.findById(familyId);
	}
	
	@GET
	@Path("/families")
	@Produces(value={MediaType.APPLICATION_JSON})
	public List<Family> getFamilies(){
		return familyService.findAllFamiliesWithCurrentContract();
	}
	
	@GET
	@Path("/permanence/now")
	@Produces(value={MediaType.APPLICATION_JSON})
	public List<Permanence> getCurrentPermanence(){
		return permanenceService.getCurrentPermanences();
	}
	
	@GET
	@Path("/permanence/{permanenceId}")
	@Produces(value={MediaType.APPLICATION_JSON})
	public Permanence getPermanenceById(@PathParam("permanenceId") Long permanenceId){
		return permanenceService.findById(permanenceId);
	}
	
	@GET
	@Path("/permanence/from/{fromDate}/to/{toDate}")
	@Produces(value={MediaType.APPLICATION_JSON})
	public List<Permanence> getPermanenceByDates(@PathParam("fromDate") String fromDate, @PathParam("toDate") String toDate){
		LocalDateTime startDate = LocalDate.parse(fromDate).atTime(00, 00, 00);
		LocalDateTime endDate = LocalDate.parse(toDate).atTime(23, 59, 59);
		return permanenceService.getWeekPermanences(startDate, endDate);
	}
	
	@GET
	@Path("/permanence/date/{date}/slot/{slot}")
	@Produces(value={MediaType.APPLICATION_JSON})
	public List<Permanence> getPermanenceBySlot(@PathParam("date") String date, @PathParam("slot") String slot){
		LocalDate dateToReplace = LocalDate.parse(date);
		return permanenceService.getPermanenceToReplace(dateToReplace, slot);
	}
	
	@PUT
	@Path("/permanence/update")
	@Produces(value={MediaType.APPLICATION_JSON})
	public void updatePermanence(Permanence permanence){
		permanenceService.updatePermanence(permanence);
	}
	

	@POST
	@Path("/permanence/save")
	@Produces(value={MediaType.APPLICATION_JSON})
	public void savePermanence(Permanence permanence){
		permanenceService.savePermanence(permanence);
	}

	@GET
	@Path("/permanence/replacements/{nobodyId}")
	@Produces(value={MediaType.APPLICATION_JSON})
	public List<Permanence> getPermanenceByDates(@PathParam("nobodyId") Long nobodyId){
		return permanenceService.getReplacement(nobodyId);
	}

	@GET
	@Path("/permanence/notvalidate")
	@Produces(value={MediaType.APPLICATION_JSON})
	public List<Permanence> getPermanenceNotValidate(){
		return permanenceService.getNoValidatePermanence();
	}

	@PUT
	@Path("/permanence/close")
	@Produces(value= {MediaType.APPLICATION_JSON})
	public void closePermanences(String date) {
		LocalDate startDate = LocalDate.parse(date);
		permanenceService.validateMonthPermanences(startDate);
	}
	@GET
	@Path("/permanence/{family}/count")
	@Produces(value= {MediaType.APPLICATION_JSON})
	public void countPermanences(@PathParam("family") Long familyId,
								 @QueryParam("since") String since,
								 @QueryParam("to") String to,
								 @QueryParam("status")PermanenceStatus status) {
		LocalDateTime sinceDate = since != null ? LocalDateTime.parse(since) : null;
		LocalDateTime toDate = to != null ? LocalDateTime.parse(to) : null;
		permanenceService.countPermanences(familyId, sinceDate, toDate, status);
	}

	@GET
	@Path("permanence/tovalidate")
	@Produces(value= {MediaType.APPLICATION_JSON})
	public List<LocalDate> getMonthToValidate() {
		return permanenceService.getNotClosedPermanences();
	}



	@GET
	@Path("/holidays")
	@Produces(value={MediaType.APPLICATION_JSON})
	public List<Holidays> getHolidays(){
		return holidaysService.findAllHolidays();
	}

	@POST
	@Path("/holidays/from/{fromDate}/to/{toDate}")
	@Produces(value={MediaType.APPLICATION_JSON})
	public void saveHolidays(@PathParam("fromDate") String fromDate, @PathParam("toDate") String toDate){
		LocalDateTime startDate = LocalDate.parse(fromDate).atTime(00, 00, 00);
		LocalDateTime endDate = LocalDate.parse(toDate).atTime(23, 59, 59);
		holidaysService.generateHolidaysFromDates(startDate, endDate);
	}

	@POST
	@Path("/permanence/generate/{schedulingId}")
	@Produces(value={MediaType.APPLICATION_JSON})
	public void generatePermanenceByScheduling(@PathParam("schedulingId") Long schedulingId){
		permanenceService.generatePermanencesFamily(schedulingId);
	}

	@GET
	@Path("count/{familyId}")
	@Produces(value={MediaType.APPLICATION_JSON})
	public List<Permanence> getPermCountByFamily(@PathParam("familyId") Long familyId){
		return permanenceService.getPermCountByFamily(familyId);
	}
	
	@POST
	@Path("account")
	@Produces(value={MediaType.APPLICATION_JSON})
	public void saveAccount(Account account){
		accountService.saveAccount(account);
	}
	
	@POST
	@Path("connexion")
	@Produces(value={MediaType.APPLICATION_JSON})
	public Account connexion(Authentification auth){
		return accountService.connectByLogin(auth.getLogin(), auth.getPassword());
	}
	
}