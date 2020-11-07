
import java.util.Arrays;

public class SentimentAnalyzer {
	  // Tip: labeled continue can be used when iterating featureSet + convert review to lower-case
		public static int[] detectProsAndCons(String review, String[][] featureSet, String[] posOpinionWords,
				String[] negOpinionWords) {
			int[] featureOpinions = new int[featureSet.length]; // output
						
			String sb = new String(review);
			review = sb.toLowerCase();
			
			// this iteration is only for 1 feature word "*To be changed*"
			for(int i = 0; i < featureSet.length; i++) {
				for(int j = 0; j < featureSet[i].length; j++) {
					if(review.contains(featureSet[i][j])) {
						String feature = featureSet[i][j];
						System.out.println("feature - " + feature);
						featureOpinions[i]=getOpinionOnFeature(review,feature,posOpinionWords,negOpinionWords);
					}
				}
			}
			
			
			
			/*for(String[]a:featureSet) {
				sb.append(Arrays.toString(a));
			}
			String feature = sb.toString();*/
			
	        // your code ~ you will be invoking getOpinionOnFeature		
			
			
			return featureOpinions;
		}

		// First invoke checkForWasPhrasePattern and 
		// if it cannot find an opinion only then invoke checkForOpinionFirstPattern
 		private static int getOpinionOnFeature(String review, String feature, String[] posOpinionWords, String[] negOpinionWords) {
			
			// your code
 			int opinion = 0;
 			String pattern = feature + " was ";
 			
 			if(review.contains(pattern)) {
 				System.out.println("checkForWasPhrasePattern INVOKED");
 				opinion += checkForWasPhrasePattern(review,feature,posOpinionWords,negOpinionWords);
 				
 			}
 			 			
 			else {
 				System.out.println("checkForOpinionFirstPattern INVOKED"); 
 				opinion += checkForOpinionFirstPattern(review,feature,posOpinionWords,negOpinionWords);
 			
 			}
 			
 			System.out.println(opinion);
 			
 			
 			return opinion;
		}	

		// Tip: Look at String API doc. Methods like indexOf, length, substring(beginIndex), startsWith can come into play
		// Return 1 if positive opinion found, -1 for negative opinion, 0 for no opinion
		// You can first look for positive opinion. If not found, only then you can look for negative opinion
		private static int checkForWasPhrasePattern(String review, String feature, String[] posOpinionWords, String[] negOpinionWords) {
			int opinion = 0;
			String pattern = feature + " was ";
				
			// your code
			for(String pat:posOpinionWords) {
				if(review.contains(pattern + pat)) {
					opinion++;
					System.out.println("Was phrase -- Positive word found");
				}
			}
			
			for(String pat:negOpinionWords) {
				if(review.contains(pattern + pat)) {
					opinion--;
					System.out.println("Was phrase -- Negative word found");
				}
			}
			return opinion; 	
		}
		
		// You can first look for positive opinion. If not found, only then you can look for negative opinion
		
		private static int checkForOpinionFirstPattern(String review, String feature, String[] posOpinionWords,
				String[] negOpinionWords) {
			// Extract sentences as feature might appear multiple times. 
			// split() takes a regular expression and "." is a special character 
			// for regular expression. So, escape it to make it work!!
			//String[] sentences = review.split(" ");
			int opinion = 0;
			
				
			// your code for processing each sentence. You can return if opinion is found in a sentence (no need to process subsequent ones)
			for(String pos: posOpinionWords) {
				
				if(review.contains(pos+" "+feature)) {
				opinion++;
								
				
								
				}
			}
				
			for(String neg: negOpinionWords) {
					if(review.contains(neg+" "+feature)) {					
					opinion--;
					
					}
					}
				
			
			return opinion;
		}

		public static void main(String[] args) {
			//String review = "Haven't been here in years! Fantastic service and the food was delicious! Definetly will be a frequent flyer! Francisco was very attentive";
			
			String review = "Sorry OG, but you just lost some loyal customers. Horrible service, no smile or greeting just attitude. The breadsticks were stale and burnt, appetizer was cold and the food came out before the salad.";
			
			String[][] featureSet = { 
			        { "ambiance", "ambience", "atmosphere", "decor" },
					{ "dessert", "ice cream", "desert" }, 
					{ "food" }, 
					{ "soup" },
					{ "service", "management", "waiter", "waitress", "bartender", "staff", "server" } };
			String[] posOpinionWords = { "good", "fantastic", "friendly", "great", "excellent", "amazing", "awesome",
					"delicious" };
			String[] negOpinionWords = { "slow", "bad", "horrible", "awful", "unprofessional", "poor" };

			int[] featureOpinions = detectProsAndCons(review, featureSet, posOpinionWords, negOpinionWords);
			System.out.println("Opinions on Features: " + Arrays.toString(featureOpinions));
		}
}
