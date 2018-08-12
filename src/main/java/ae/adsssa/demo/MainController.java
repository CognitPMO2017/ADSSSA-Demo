package ae.adsssa.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

@Controller
public class MainController {

	@Autowired
	DiscoveryServiceImpl discoveryService;

	@RequestMapping("/test")
	@ResponseBody
	public String test() {

		String myList[] = { "اختبارات", "اختبارات", "اختبارات" };
		Gson gson = new Gson();
		String json = gson.toJson(myList);
		return json;
	}

	@RequestMapping("/indexes")
	public ModelAndView indexPage() {
		ModelAndView mv = new ModelAndView("demo");
		return mv;
	}

	@RequestMapping("/queryDB")
	@ResponseBody
	public String queryDB(@RequestParam("searchQuery") String searchQuery) {

		System.out.println(searchQuery);
		List<String> queryList = discoveryService.queryDiscoveryDB(searchQuery);
		Gson gson = new Gson();
		String json = gson.toJson(queryList);
		return json;
	}
	
	
	@RequestMapping("/queryTweets")
	@ResponseBody
	public String queryTweets(@RequestParam("searchQuery") String searchQuery) {

		System.out.println(searchQuery);
		List<String> queryList = discoveryService.queryDiscoveryTweets(searchQuery);
		Gson gson = new Gson();
		String json = gson.toJson(queryList);
		return json;
	}

}
