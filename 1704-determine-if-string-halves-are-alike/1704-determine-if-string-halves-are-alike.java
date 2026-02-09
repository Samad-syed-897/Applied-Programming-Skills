class Solution {

    public boolean halvesAreAlike(String s) {

        int halfLen = s.length() / 2;

        return vowelCount(s.substring(0, halfLen)) == vowelCount(s.substring(halfLen));
    }

    public int vowelCount(String s) {
        int count = 0;

        for (char c : s.toCharArray()) {
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' || c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U') {
                count++;
            }
        }
        
        return count;
     }
}
       
