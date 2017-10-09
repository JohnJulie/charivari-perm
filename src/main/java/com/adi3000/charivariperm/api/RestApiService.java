package com.adi3000.charivariperm.api;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

import com.adi3000.charivariperm.model.dataobject.Family;
import com.adi3000.charivariperm.model.service.FamilyService;

@Component
@Path("/")
public class RestApiService {
	
	@Inject
	private FamilyService familyService;
	
	
	@GET
	@Path("/family/{familyId}")
	@Produces(value={MediaType.APPLICATION_JSON})
	public Family getFamily(@PathParam("familyId") Long familyId){
		return familyService.findById(familyId);
	}
	
	
}