package mm;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.collection.IsArrayContainingInAnyOrder.arrayContainingInAnyOrder;
import static org.hamcrest.collection.IsArrayWithSize.emptyArray;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

public class MasterMindTest {

	@Test
	public void thisTestShouldAlwaysPass() {
		assertTrue(true);
	}
	
	@Test
	public void correctColorIncorrectPositionGetsOneWhitePeg() {
		
		MasterMind game = new MasterMind('B', 'R', 'O', 'G');
		Character[] feedback = game.play('Y', 'O', 'Y', 'Y');
		
		assertArrayEquals(new Character[] { 'W' }, feedback);
	}
	
	@Test
	public void noCorrectColorsGetsNoFeedback() {
		
		MasterMind game = new MasterMind('B', 'R', 'O', 'G');
		Character[] feedback = game.play('Y', 'Y', 'Y', 'Y');
		
		assertThat(feedback, is(emptyArray()));
	}
	
	@Test
	public void correctColorCorrectPositionGetsOneRedPeg() {
		
		MasterMind game = new MasterMind('B', 'R', 'O', 'G');
		Character[] feedback = game.play('Y', 'Y', 'O', 'Y');
		
		assertArrayEquals(new Character[] { 'R' }, feedback);
	}
	
	@Test
	public void shouldGetOneRedOneWhite() {
		
		MasterMind game = new MasterMind('B', 'R', 'O', 'G');
		Character[] feedback = game.play('B', 'O', 'Y', 'Y');
		
		assertThat(feedback, is(arrayContainingInAnyOrder('R', 'W')));
	}

	@Test
	public void correctColorIncorrectPositonMoreThanOnceGetsOneWhitePeg() {
		
		MasterMind game = new MasterMind('B', 'R', 'O', 'G');
		Character[] feedback = game.play('O', 'O', 'Y', 'Y');
		
		assertThat(feedback, is(arrayContainingInAnyOrder('W')));
	}
	
	@Ignore
	@Test
	public void matchCodeGetsAllRed() {
		
		MasterMind game = new MasterMind('B', 'R', 'O', 'G');
		Character[] feedback = null;
		
		// when
		try {
			feedback = game.play('B', 'R', 'O', 'G');
			fail("should have thrown an exception");
		} catch(RuntimeException e) {
			assertThat(feedback, is(arrayContainingInAnyOrder('R', 'R','R', 'R')));
		}
	}
	
	@Test
	public void tenFailedAttemptsLosesGame() {
		
		// given
		MasterMind game = new MasterMind('B', 'R', 'O', 'G');
		
		// when
		for(int i = 0; i < 9; i++) {
			game.play('Y', 'Y', 'Y', 'Y');
		}
		
		// then
		try {
			game.play('Y', 'Y', 'Y', 'Y');
			fail("should have thrown an exception!");
		} catch(RuntimeException e) {
			assertEquals("game over - you lost", e.getMessage());
		}
	}
	
	@Test
	public void attemptMatchingCodeWinsGame() {
		
		// given
		MasterMind game = new MasterMind('B', 'R', 'O', 'G');
		
		// when
		try {
			game.play('B', 'R', 'O', 'G');
			fail("should have thrown an exception");
		} catch(RuntimeException e) {
			// then
			assertEquals("game over - you won", e.getMessage());
		}
	}
	
	public void nineFailedAttemptsTenthAttemptMatchesCodeWinsGame() {
		
		// given
		MasterMind game = new MasterMind('B', 'R', 'O', 'G');
		
		// when
		for(int i = 0; i < 9; i++) {
			game.play('Y', 'Y', 'Y', 'Y');
		}
		
		// then
		try {
			game.play('B', 'R', 'O', 'G');
			fail("should have thrown an exception!");
		} catch(RuntimeException e) {
			assertEquals("game over - you won", e.getMessage());
		}
	}
	
	@Ignore
	@Test
	public void attemptAfterWinning() {
		
		// given
		MasterMind game = new MasterMind('B', 'R', 'O', 'G');
		
		try {
			game.play('B', 'R', 'O', 'G');
		} catch(RuntimeException ignored) { }
		
		// when
		try {
			game.play('Y', 'Y', 'Y', 'Y');
			fail("should have thrown an exception");
		} catch(RuntimeException e) {
			// then
			assertEquals("game over - you won", e.getMessage());
		}
		
	}
	
}
