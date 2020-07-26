package com.janaagrah.repositories;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.janaagrah.infra.DatabaseConstants;

/**
 * Repository layer that interacts with underlying DB
 * to get the result set of states.
 * 
 * @author jimil
 *
 */
@Repository
public class StateRepository {

private Firestore firestore;
	
	/**
	 * Utility method to connect to the Firestore
	 * DB and return a pointer to the instance.
	 * 
	 * @return
	 * @throws Exception
	 */
	private Firestore getFirestore() throws Exception {
		GoogleCredentials creds = GoogleCredentials.getApplicationDefault();
		FirestoreOptions firestoreOptions = FirestoreOptions.getDefaultInstance().toBuilder()
				.setProjectId("cfg-team-19")
				.setCredentials(creds)
				.build();
		firestore = firestoreOptions.getService();
		return firestore;
	}
	
	/**
	 * Get a reference to the complaints collection
	 * 
	 * @return
	 * @throws Exception
	 */
	private CollectionReference getComplainCollection() throws Exception {
		return getFirestore().collection(DatabaseConstants.COMPLAINTS_COLLECTION_NAME);
	}
	
	/**
	 * Get top voted states across the country for the
	 * past month.
	 * 
	 * @return
	 * @throws Exception
	 */
	public ArrayList<Object> getTopStates() throws Exception {
		// Calculate Date - 1 Month and store as string
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		Date result = cal.getTime();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");  
		String strDate = dateFormat.format(result);  
		
		// Form the query to fetch data of last 30 days for concerned state
		ApiFuture<QuerySnapshot> query =
		        getComplainCollection()
		        .whereLessThanOrEqualTo(DatabaseConstants.COMPLAINTS_DATE_FIELD_NAME, strDate)
		        .get();
		QuerySnapshot querySnapshot = query.get();
		List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
		if(documents.size() == 0) {
			throw new Exception("Oops! Encountered an error. Please try again later.");
		}
		
		// Iterate through all documents to build count for citizens
		HashMap<String, Integer> map = new HashMap<>();
		for(QueryDocumentSnapshot doc: documents) {
			if(!map.containsKey(doc.get(DatabaseConstants.COMPLAINTS_STATE_FIELD_NAME))) {
				map.put(doc.getString(DatabaseConstants.COMPLAINTS_STATE_FIELD_NAME), 1);
			} else {
				int current = map.get(doc.get(DatabaseConstants.COMPLAINTS_STATE_FIELD_NAME));
				map.put(doc.getString(DatabaseConstants.COMPLAINTS_STATE_FIELD_NAME), current+1);
			}
		}
		
		// Order citizens by descending order of count of issues
		LinkedHashMap<String, Integer> reverseSortedMap = new LinkedHashMap<>();
		map.entrySet()
		    .stream()
		    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())) 
		    .forEachOrdered(x -> reverseSortedMap.put(x.getKey(), x.getValue()));
		
		// Add each HashMap entry to an ArrayList
		ArrayList<Object> response = new ArrayList<>();
		for(Map.Entry<String, Integer> entry: reverseSortedMap.entrySet()) {
			response.add(entry);
		}
		return response;
	}
	
	/**
	 * Get stats for each state over the past month and
	 * determine an overall rank.
	 * 
	 * @return
	 * @throws Exception
	 */
	public ArrayList<Object> getStatsForState(String state) throws Exception {
		// Calculate Date - 1 Month and store as string
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		Date result = cal.getTime();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");  
		String strDate = dateFormat.format(result);  
		
		// Form the query to fetch data of last 30 days for concerned state
		ApiFuture<QuerySnapshot> query =
		        getComplainCollection()
		        .whereEqualTo(DatabaseConstants.COMPLAINTS_STATE_FIELD_NAME, state)
		        .whereLessThanOrEqualTo(DatabaseConstants.COMPLAINTS_DATE_FIELD_NAME, strDate)
		        .get();
		QuerySnapshot querySnapshot = query.get();
		List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
		if(documents.size() == 0) {
			throw new Exception("Oops! Encountered an error. Please try again later.");
		}
		
		// Iterate through all documents to build count for citizens
		HashMap<String, Integer> response = new HashMap<>();
		response.put("Reported", 0);
		response.put("Resolved", 0);
		for(QueryDocumentSnapshot doc: documents) {
			if(doc.getString(DatabaseConstants.COMPLAINTS_STATUS_FIELD_NAME).equals("reported")) {
				response.put("Reported", response.get("Reported")+1);
			} else if(doc.getString(DatabaseConstants.COMPLAINTS_STATUS_FIELD_NAME).equals("resolved")) {
				response.put("Resolved", response.get("Resolved")+1);
			}
		}
		response.put("Quality of citizenship", response.get("Resolved")/response.get("Reported"));
		
		// Add each HashMap entry to an ArrayList
		ArrayList<Object> toReturn = new ArrayList<>();
		for(Map.Entry<String, Integer> entry: response.entrySet()) {
			toReturn.add(entry);
		}
		return toReturn;
	}
	
}
