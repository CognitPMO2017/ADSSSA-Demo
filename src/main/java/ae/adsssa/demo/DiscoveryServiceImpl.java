package ae.adsssa.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ibm.watson.developer_cloud.discovery.v1.Discovery;
import com.ibm.watson.developer_cloud.discovery.v1.model.QueryOptions;
import com.ibm.watson.developer_cloud.discovery.v1.model.QueryResponse;
import com.ibm.watson.developer_cloud.discovery.v1.model.QueryResult;

@Service
public class DiscoveryServiceImpl {

	boolean isInitializedDB = false;
	boolean isInitializedTweets = false;
	Discovery discoveryDB = null;
	QueryOptions.Builder discoveryBuilderDB = null;
	
	Discovery discoveryTweets = null;
	QueryOptions.Builder discoveryBuilderTweets = null;

	private void initDB() {
		discoveryDB = new Discovery("2018-03-05");
		//discovery.setUsernameAndPassword("85029e62-a511-4886-8f77-36f8e09e7161", "yRIqPKa8roMR");
		//String environmentId = "system";
		//String collectionId = "news-en";

		discoveryDB.setUsernameAndPassword("0a906b8e-a759-422a-9398-43c7e4594adc", "BQD0tIZU2isP");
		String environmentId = "61491aad-48d8-4676-aef1-3791e629b038";
		String collectionId = "f8a8c724-c562-4a6d-b5f2-6a55fc21cfd5";
		
		
		
		
		discoveryBuilderDB = new QueryOptions.Builder(environmentId, collectionId);
		isInitializedDB = true;

	}
	
	
	private void initTweets() {
		discoveryTweets = new Discovery("2018-03-05");
		//discovery.setUsernameAndPassword("85029e62-a511-4886-8f77-36f8e09e7161", "yRIqPKa8roMR");
		//String environmentId = "system";
		//String collectionId = "news-en";

		discoveryTweets.setUsernameAndPassword("0a906b8e-a759-422a-9398-43c7e4594adc", "BQD0tIZU2isP");
		String environmentId = "61491aad-48d8-4676-aef1-3791e629b038";
		String collectionId = "33d87907-ff22-463d-9736-7d5154692bf5";	
		discoveryBuilderTweets = new QueryOptions.Builder(environmentId, collectionId);
		isInitializedTweets = true;
	

	}

	public List<String> queryDiscoveryDB(String query) {

		List<String> queryDataList = new ArrayList<String>();

		if (!isInitializedDB) {
			initDB();
		}

		QueryOptions queryOption = discoveryBuilderDB.query(query).build();
		QueryResponse queryResponse = discoveryDB.query(queryOption).execute();
		List<QueryResult> queryResult = queryResponse.getResults();

		for (QueryResult response : queryResult) {
			String authorName = (String) response.get("text");
			if (authorName != null && !authorName.isEmpty()) {
				queryDataList.add(authorName);
			}

		}
		return queryDataList;

	}
	
	
	public List<String> queryDiscoveryTweets(String query) {

		List<String> queryDataList = new ArrayList<String>();

		if (!isInitializedTweets) {
			initTweets();
		}

		QueryOptions queryOption = discoveryBuilderTweets.query(query).build();
		QueryResponse queryResponse = discoveryTweets.query(queryOption).execute();
		List<QueryResult> queryResult = queryResponse.getResults();

		for (QueryResult response : queryResult) {
			String authorName = (String) response.get("text");
			if (authorName != null && !authorName.isEmpty()) {
				queryDataList.add(authorName);
			}

		}
		return queryDataList;

	}

	public static void main(String[] args) {
		DiscoveryServiceImpl service = new DiscoveryServiceImpl();
		service.queryDiscoveryTweets("أمن");
	}

}
