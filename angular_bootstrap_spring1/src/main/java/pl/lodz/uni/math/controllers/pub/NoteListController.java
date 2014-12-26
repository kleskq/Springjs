package pl.lodz.uni.math.controllers.pub;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.lodz.uni.math.dto.NoteRatingDto;
import pl.lodz.uni.math.dto.NoteRatingJsonDto;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
public class NoteListController {

	@RequestMapping(value = "/noteList", method = RequestMethod.GET)
	public String printWelcome(@ModelAttribute("person") NoteRatingDto person,
			BindingResult result, ModelMap model, HttpServletRequest request,
			HttpServletResponse response) {
		return "public/noteList";

	}

	@RequestMapping(value = "/springPaginationDataTables", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String springPaginationDataTables(
			HttpServletRequest request) throws IOException {
		// Fetch the page number from client
		Integer pageNumber = 0;
		if (null != request.getParameter("iDisplayStart"))
			pageNumber = (Integer
					.valueOf(request.getParameter("iDisplayStart")) / 10) + 1;

		// Fetch search parameter
		String searchParameter = request.getParameter("sSearch");

		// Fetch Page display length
		Integer pageDisplayLength = Integer.valueOf(request
				.getParameter("iDisplayLength"));

		// Create page list data
		List<NoteRatingDto> personsList = createPaginationData(pageDisplayLength);
		// Here is server side pagination logic. Based on the page number you
		// could make call
		// to the data base create new list and send back to the client. For
		// demo I am shuffling
		// the same list to show data randomly
		if (pageNumber == 1) {
			Collections.shuffle(personsList);
		} else if (pageNumber == 2) {
			Collections.shuffle(personsList);
		} else {
			Collections.shuffle(personsList);
		}
		// Search functionality: Returns filtered list based on search parameter
		personsList = getListBasedOnSearchParameter(searchParameter,
				personsList);
		NoteRatingJsonDto personJsonObject = new NoteRatingJsonDto();
		// Set Total display record
		personJsonObject.setiTotalDisplayRecords(500);
		// Set Total record
		personJsonObject.setiTotalRecords(500);
		personJsonObject.setAaData(personsList);
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json2 = gson.toJson(personJsonObject);
		return json2;
	}

	private List<NoteRatingDto> getListBasedOnSearchParameter(
			String searchParameter, List<NoteRatingDto> personsList) {
		if (null != searchParameter && !searchParameter.equals("")) {
			List<NoteRatingDto> personsListForSearch = new ArrayList<NoteRatingDto>();
			searchParameter = searchParameter.toUpperCase();
			for (NoteRatingDto person : personsList) {
				if (person.getNoteTitle().toUpperCase()
						.indexOf(searchParameter) != -1
						|| person.getCategory().toUpperCase()
								.indexOf(searchParameter) != -1
						|| person.getAuthor().toUpperCase()
								.indexOf(searchParameter) != -1
						|| person.getRating().toUpperCase()
								.indexOf(searchParameter) != -1
						|| person.getCreateDate().toUpperCase()
								.indexOf(searchParameter) != -1
						|| person.getCode().toUpperCase()
								.indexOf(searchParameter) != -1) {
					personsListForSearch.add(person);
				}
			}
			personsList = personsListForSearch;
			personsListForSearch = null;
		}
		return personsList;
	}

	private List<NoteRatingDto> createPaginationData(Integer pageDisplayLength) {
		List<NoteRatingDto> noteRatingList = new ArrayList<NoteRatingDto>();
		for (int i = 0; i < 15; i++) {
			NoteRatingDto noteRatingDto = new NoteRatingDto();
			noteRatingDto.setNoteTitle("asdasd");
			noteRatingDto.setAuthor("user");
			noteRatingDto.setCategory("test");
			noteRatingDto.setCreateDate("12-23-2005");
			noteRatingDto.setCode("123123");
			noteRatingDto.setRating(((i % 5) + 1) + "");
				noteRatingList.add(noteRatingDto);
		}

		return noteRatingList;
	}
}