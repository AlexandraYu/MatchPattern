import java.util.*;

public class MatchPattern{
    public static List<String> findAndReplacePattern(String[] words, String pattern) {
        Map<Character,Character> myMap = new HashMap<Character, Character>(); //used to store each character of a word as key, and each character in pattern as value
        List<String> result = new ArrayList<String>(); //list of the "matched pattern" words to be returned
        boolean pass = false; 
        for (int i=0; i<words.length; i++) { //we're going through each word here
            if (words[i].length()!=pattern.length()) continue; //if word length is different from pattern length, we know they don't match, so continue checking next word
            else {
                myMap.clear(); // 
                for (int j=0; j < words[i].length(); j++) {
                    char key = words[i].charAt(j);
                    char val = pattern.charAt(j); 
                    if (myMap.containsKey(key)) { //if we find there's a key already
                        if(myMap.get(key)==pattern.charAt(j)) pass = true; //we need to check if the matched value is same as the character from pattern (we can't match 1 key to different values)
                        else pass = false;
                    }
                    else { //if no key found, that means the character in word should be a new entry
                        int count=0; 
                        myMap.put(key, val);
                        for (char tmpKey : myMap.keySet()) { //it's possible that other keys have matched the same value, so we need to check
                            if (myMap.get(tmpKey)==val) count++;  //we check the frequency of a specific value
                        }
                        if (count==1) pass = true; //if frequency is 1, we now know it's 1:1 relationship for key and value
                        else pass = false; 
                    }
                    if (!pass) break; //this is for breaking the loop for checking character by character, b/c once we read a character is not a match to the pattern, we do the check on next word 
                }
                if (pass) result.add(words[i]); //once we get a word that "qualifies" to match the pattern, we add it to the list 
            }
        }
        return result; 
    }
    public static void main(String []args){
        String [] words= {"aaa", "aba", "bdb", "xxe", "aab", "aabb"}; 
        String pattern = "yry";
        List<String> myList = new ArrayList<String>(); 
        myList = findAndReplacePattern(words, pattern); 
        System.out.println(Arrays.toString(myList.toArray()));
    }
}
