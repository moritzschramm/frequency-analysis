package languages;

public class German extends LanguageProfile {

	public German() {
		
		super();
		
		setFrequency("e", 0.1740);
		setFrequency("n", 0.0978);
		setFrequency("i", 0.0755);
		setFrequency("s", 0.0727);
		setFrequency("r", 0.0700);
		setFrequency("a", 0.0651);
		setFrequency("t", 0.0615);
		setFrequency("d", 0.0508);
		setFrequency("h", 0.0476);
		setFrequency("u", 0.0435);
		setFrequency("l", 0.0344);
		setFrequency("c", 0.0306);
		setFrequency("g", 0.0301);
		setFrequency("m", 0.0253);
		setFrequency("o", 0.0251);
		setFrequency("b", 0.0189);
		setFrequency("w", 0.0189);
		setFrequency("f", 0.0166);
		setFrequency("k", 0.0121);
		setFrequency("z", 0.0113);
		setFrequency("p", 0.0079);
		setFrequency("v", 0.0067);
		setFrequency("ß", 0.0031);
		setFrequency("j", 0.0027);
		setFrequency("y", 0.0004);
		setFrequency("x", 0.0003);
		setFrequency("q", 0.0002);
		
		setFirstLetterFrequency("s", 0.118);
		setFirstLetterFrequency("k", 0.073);
		setFirstLetterFrequency("a", 0.071);
		setFirstLetterFrequency("p", 0.070);
		setFirstLetterFrequency("b", 0.057);
		setFirstLetterFrequency("m", 0.057);
		
		setLastLetterFrequency("n", 0.210);
		setLastLetterFrequency("e", 0.151);
		setLastLetterFrequency("r", 0.130);
		setLastLetterFrequency("t", 0.103);
		setLastLetterFrequency("s", 0.096);
	}
}
