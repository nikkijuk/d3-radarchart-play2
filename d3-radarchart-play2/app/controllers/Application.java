package controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import play.Logger;
import play.libs.Yaml;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.dynamic;
import views.html.index;
import app.models.Metric;
import app.models.MetricSet;

public class Application extends Controller {
  
	private static MetricSet expected = null;
	
	static {
		try {
			if (expected == null) {
				// load yaml data
				Map<String, List<Object>> all = (Map<String, List<Object>>) Yaml
						.load("default-data.yml");
				// Insert employees
				expected = (MetricSet) all.get("expectations").get(0);
				Logger.info("Defaults added");

			}
		} catch (Exception e) {
			Logger.error("Defaults couldn't be added "+e.getMessage(), e);
		}
	}
	
    public static Result index() {
        return ok(index.render("Your new application is ready."));
    }
 

    public static Result dynamic() {
    	 
    	// note: to make sure this works without flaws one should use comparator
    	// note: lists which are always ordered by name (or other ordering type) would be printed in right order
    	
    	List<Map<String, Object>> ds = new ArrayList <Map<String, Object>>();
    	for (Metric  metric: expected.metrics) {
    		Map<String, Object> item = new HashMap <String, Object> (); 
    		item.put("axis", metric.name);
    		item.put("value", metric.value);
    		ds.add(item);
    	}
      	
    	List<List<Map<String, Object>>> radarMetrics = new ArrayList<List<Map<String, Object>>> ();
    	radarMetrics.add(ds);
    	
        return ok(dynamic.render("Your new application is ready.", radarMetrics));
    }

    
}
