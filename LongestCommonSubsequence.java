import java.util.StringTokenizer;

//Code snippets from Exam_Studienarbeit by Prof. Lano 
public class LongestCommonSubsequence {

	private final String SEPARATION_CHARS = " \n+-*/=<>.;[](){}";
	
	public int lcs(String s1, String s2) {
		char[] X = s1.toCharArray();
		char[] Y = s2.toCharArray();
		int m = X.length;
		int n = Y.length;
		int L[][] = new int[m + 1][n + 1];
		int result;
		double minLength = m > n ? n: m;
			/*
		     * Following steps build L[m+1][n+1] in bottom up fashion. Note that
		     * L[i][j] contains length of LCS of X[0..i-1] and Y[0..j-1]
		     */
		for (int i = 0; i <= m; i++) {
			for (int j = 0; j <= n; j++) {
				if (i == 0 || j == 0){
					L[i][j] = 0;
			
				}
				else if (X[i - 1] == Y[j - 1]){
			 		L[i][j] = L[i - 1][j - 1] + 1;
				}

				else{
					L[i][j] = Math.max(L[i - 1][j], L[i][j - 1]);
				}
			}
		}
		result = (int)Math.floor((L[m][n]/minLength) * 100);
		return result;
		}
	

	public String prepareCode(String s1) {
		
		StringTokenizer st = new StringTokenizer(s1, SEPARATION_CHARS, true);
		StringBuffer sb = new StringBuffer();
		while (st.hasMoreTokens()) {
			String toki = st.nextToken();
			if (SEPARATION_CHARS.contains(toki)) {
				sb.append(toki);
			} else {
				sb.append("_");
			}
		}
			
			return sb.toString();
		}
}
