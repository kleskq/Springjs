package pl.lodz.uni.math.controllers.pub;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.lodz.uni.math.dto.NoteRatingDto;
import pl.lodz.uni.math.service.NoteService;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
public class NoteListController {

	private static final Logger logger = LoggerFactory.getLogger(NoteListController.class);

	@Autowired
	private NoteService noteService;

	@RequestMapping(value = "/noteList", method = RequestMethod.GET)
	public String printWelcome(@ModelAttribute("person") NoteRatingDto person, BindingResult result, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {
		return "public/noteList";

	}

	@RequestMapping(value = "/springPaginationDataTables", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String springPaginationDataTables(HttpServletRequest request) throws IOException {
		// Fetch the page number from client
		int pageNumber = 0;
		if (null != request.getParameter("iDisplayStart"))
			pageNumber = (Integer.valueOf(request.getParameter("iDisplayStart")) / 10) + 1;

		String columntToSort = request.getParameter("iSortCol_0");
		String sortDirection = request.getParameter("sSortDir_0");
		logger.info("column number : " + columntToSort);
		logger.info("sort direction : " + sortDirection);
		// Fetch search parameter
		String searchParameter = request.getParameter("sSearch");

		// Fetch Page display length
		int pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json2 = gson.toJson(noteService.getNotes(pageNumber, searchParameter, pageDisplayLength, columntToSort,
				sortDirection));
		return json2;
	}
}