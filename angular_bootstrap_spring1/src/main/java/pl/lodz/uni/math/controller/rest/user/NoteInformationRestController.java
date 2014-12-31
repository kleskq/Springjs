package pl.lodz.uni.math.controller.rest.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pl.lodz.uni.math.dto.NoteInfoDto;
import pl.lodz.uni.math.dto.UserDto;
import pl.lodz.uni.math.service.NoteService;
import pl.lodz.uni.math.service.UserService;

@RestController
@RequestMapping(value = "/rest")
public class NoteInformationRestController {
	@Autowired
	private NoteService noteService;

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/noteInfo", method = RequestMethod.GET, produces = "application/json")
	public NoteInfoDto getNoteInfo() {
		return noteService.getNotesInfo();
	}
	
	
}