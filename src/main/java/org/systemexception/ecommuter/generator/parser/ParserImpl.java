package org.systemexception.ecommuter.generator.parser;

import ru.lanwen.verbalregex.VerbalExpression;

/**
 * @author leo
 * @date 23/09/2016 21:38
 */
public class ParserImpl implements Parser {

	private final static String EMPTY = "";
	public  final static String STREET_INITIAL_DELIMITER = "(", STREET_FINAL_DELIMITER = ")", SPACE = " ";

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getStreetTypeFromLine(final String stringToParse) {
		VerbalExpression verbalExpression = VerbalExpression.regex().find(STREET_INITIAL_DELIMITER).anything()
				.find(STREET_FINAL_DELIMITER).build();
		return verbalExpression.getText(stringToParse).replace(STREET_INITIAL_DELIMITER, EMPTY)
				.replace(STREET_FINAL_DELIMITER,EMPTY);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getStreetNameFromLine(final String stringToParse) {
		VerbalExpression verbalExpression = VerbalExpression.regex().nonDigit().nonSpace().anything()
				.find(STREET_INITIAL_DELIMITER).build();
		return verbalExpression.getText(stringToParse).replace(STREET_INITIAL_DELIMITER, EMPTY).trim();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getPostalCodeFromLine(final String stringToParse) {
		VerbalExpression verbalExpression = VerbalExpression.regex().digit().build();
		return verbalExpression.getText(stringToParse).trim();
	}
}
