package mm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MasterMind {
	
	private int numberOfTimePlayWasCalled = 0;
	private Character[] code;

	public MasterMind(Character...code) {
		this.code = code;
	}

	public Character[] play(Character...attempt) {
		
		this.numberOfTimePlayWasCalled++;
		
		if(Arrays.deepEquals(code, attempt)) {
			throw new RuntimeException("game over - you won");
		}
		
		if(this.numberOfTimePlayWasCalled >= 10) {
			throw new RuntimeException("game over - you lost");
		}
		
		List<Character> evaluation = new ArrayList<>();

		// first get positions the red pegs
		List<Integer> indicesOfMatchingColors = new ArrayList<>();
		for(int i = 0; i < code.length; i++) {
			if(code[i] == attempt[i]) {
				evaluation.add('R');
				indicesOfMatchingColors.add(i);
			}
		}
		
		// next make a collection of the remaining code
		List<Character> codeMinusMatchingColors = new ArrayList<>();
		for(int i = 0; i < code.length; i++) {
			if(!indicesOfMatchingColors.contains(i)) {
				codeMinusMatchingColors.add(code[i]);
			}
		}

		// now get the white pegs
		for(int i = 0; i < attempt.length; i++) {
			if(codeMinusMatchingColors.remove(attempt[i])) {
				evaluation.add('W');
			}
		}
		
		
		
		
//		for(int i = 0; i < code.length; i++) {
//			for(int j  = 0; j < code.length; j++) {
//				if(i == j) {
//					if(code[i] == attempt[j]) {
//						evaluation.add('R');
//					}
// 				} else {
// 					if(code[i] == attempt[j]) {
//						evaluation.add('W');
//					}
// 				}
//			}
//		}
		
		return evaluation.toArray(new Character[evaluation.size()]);
	}

}
