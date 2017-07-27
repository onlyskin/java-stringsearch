import java.util.*;

public class StringSearcher {
    public static HashMap<String, Integer> makeBadCharTable(String pattern, String text) {
        String[] characters = pattern.split("");
        HashMap<String, Integer> badCharTable = new HashMap<>();
        int wordLength = characters.length;
        for (int i=0; i<wordLength; i++) {
            String currentChar = characters[i];
            if (!badCharTable.containsKey(currentChar)) {
                badCharTable.put(currentChar, wordLength);
            }
            if (i != wordLength - 1) {
                badCharTable.put(currentChar, wordLength - i - 1);
            }
        }
        String[] textCharacters = text.split("");
        for (int j=0; j<textCharacters.length; j++) {
            String currentTextChar = textCharacters[j];
            if (!badCharTable.containsKey(currentTextChar)) {
                badCharTable.put(currentTextChar, wordLength);
            }
        }
        return badCharTable;
    }

    private static boolean fullMatch(String text, String pattern, int position) {
        for (int i=pattern.length()-1; i>=0; i--) {
            if (pattern.charAt(i) != text.charAt(i+position)) {
                return false;
            }
        }
        return true;
    }

    protected static int getBadCharShift(String text,
                                       String pattern,
                                       int position,
                                       HashMap<String, Integer> badCharTable) {
        String key = String.valueOf(text.charAt(position + pattern.length() - 1));
        return badCharTable.get(key);
    }

    private static int[] search(String text, String pattern) {
        HashMap<String, Integer> badCharTable = makeBadCharTable(pattern, text);

        ArrayList<Integer> matches = new ArrayList();

        for (int i=0; i<text.length()-pattern.length(); i++) {
            if (fullMatch(text, pattern, i)) {
                matches.add(i++);
            }
        }
        int[] result = new int[matches.size()];
        for (int i=0; i<matches.size(); i++) {
            result[i] = matches.get(i);
        }
        return result;
    }

    public static void main(String[] args) {
        String text = args[0];
        String pattern = args[1];
        int[] matches = search(text, pattern);
        String output = Arrays.toString(matches);
        System.out.println(output);
    }
}
