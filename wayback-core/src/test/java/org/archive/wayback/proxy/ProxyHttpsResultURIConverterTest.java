package org.archive.wayback.proxy;

import junit.framework.TestCase;

import java.util.regex.Pattern;

/**
 * test for {@link ProxyHttpsResultURIConverter}.
 * @author kenji
 *
 */
public class ProxyHttpsResultURIConverterTest extends TestCase {
	
	ProxyHttpsResultURIConverter cut;
	
	protected void setUp() throws Exception {
		cut = new ProxyHttpsResultURIConverter();
	}
	
	public void testMakeReplayURI() {
		final String input = "http://home.archive.org/index.html";
		assertEquals(input, cut.makeReplayURI("20140404102345", input));
	}
	
	public void testMakeReplayURI_https() {
		final String input = "https://home.archive.org/index.html";
		assertEquals(input.replaceFirst("https:", "http:"),
				cut.makeReplayURI("20140404102345", input));
	}
	
	public void testMakeReplayURI_justHostAndPath() {
		final String input = "home.archive.org/index.html";
		assertEquals("http://" + input, cut.makeReplayURI("20140404102345", input));
	}

	// followings test methods represent behavior of current implementation,
	// NOT an expected correct behavior.
	
	// unexpected input
	public void testMakeReplayURI_justPath() {
		final String input = "/index.html";
		assertEquals("http://" + input, cut.makeReplayURI("20140404102345", input));
	}
	// unexpected input
	public void testMakeReplayURI_relativePath() {
		final String input = "index.html";
		assertEquals("http://" + input, cut.makeReplayURI("20140404102345", input));
	}
	// unexpected input
	public void testMakeReplayURI_noScheme() {
		final String input = "//home.archive.org/";
		assertEquals("http://" + input, cut.makeReplayURI("20140404102345", input));
	}

	/**
	public void testRegex() {
       Pattern experimentalHttpPattern = Pattern
		.compile("(https:\\?/\\?/[A-Za-z0-9:_@.-]+)");

		Pattern defaultHttpPattern = Pattern
					.compile("(https://[A-Za-z0-9:_@.-]+)");


		String text1 = "hello <a href=\"https://alink.com\">text</a>";
		String text2 = "hello <a href=\"https:\\/\\/alink.com\">text</a>";

		assert(defaultHttpPattern.matcher(text1).matches());
		assert(experimentalHttpPattern.matcher(text1).matches());
		assert(!defaultHttpPattern.matcher(text2).matches());
		assert(experimentalHttpPattern.matcher(text2).matches());

	}
	**/
	
}
